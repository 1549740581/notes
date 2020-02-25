## Mybatis和Hibernate

Heibernate是全自动的ORM框架，实现了POJO对象和数据库表之间的映射，以及SQL的自动生成和执行。Mybatis是半自动的ORM框架，着重点在POJO和SQL语句之间的映射关系，但是不会自动生成SQL语句，具体的SQL需要程序员自己编写，提供灵活性。Mybatis关注点在于sql操作，不在于缓存管理，如果为了提高缓存性能，可以结合第三方缓存工具一起使用。



## Mybatis内置类型和别名

|   别名    |   类型    |   别名   |  类型   |    别名    |    类型    |   别名   |   类型   |
| :-------: | :-------: | :------: | :-----: | :--------: | :--------: | :------: | :------: |
|   _int    |    int    | _integer | integer |   _short   |   short    |  _long   |   long   |
|  _double  |  double   |  _float  |  float  |   _byte    |    byte    | _boolean | boolean  |
|           |           |          |         |            |            |          |          |
|  string   |  String   |   byte   |  Byte   |    long    |    Long    |  short   |  Short   |
|    int    |  Integer  |  double  | Double  |   float    |   Float    | boolean  | Boolean  |
|   date    |   Date    |  object  | Object  | collection | Collection |   list   |   List   |
| arrayList | ArrayList |   map    |   Map   |  hashMap   |  HashMap   | iterator | Iterator |



## Mybatis主配置文件中标签

### \<environments/\>

\<environments/\>内部可以包含多个\<environment/\>标签，用于指定环境配置，类似profile功能：

```xml
<!-- 默认开发环境 -->
<environments default="dev">
	<environment id="dev">
        <!-- 配置开发环境 -->
    </environment>
    
    <environment id="prod">
        <!-- 配置生产环境 -->
    </environment>
    
    <environment id="test">
        <!-- 配置测试环境 -->
    </environment>
</environments>
```

### \<transactionManager/\>

<transactionManager/\>用于指定Mybatis使用的事务管理器，Mybatis默认支持两种事务管理器：

-   JDBC：使用JDBC的事务管理机制，通过Connection的commit()方法提交，rollback()方法回滚，默认情况下，Mybatis将自动提交（autocommit）功能关闭，需要手动提交
-   MANAGED：有容器（Spring）来管理事务的整个生命周期

```xml
<transactionManager type="JDBC" />
```

### \<dataSource/\>

\<dataSource/\>用于配置Mybatis使用的数据源和数据库连接的基本属性，常见的类型：

-   UNPOOLED：不使用连接池
-   POOLED：使用连接池
-   JNDI：数据源定义到应用外部，铜鼓JNDI容器来获取数据库连接

```xml
<dataSource type="POOLED">
	<property name="driver" value="com.mysql.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://localhost:3306/test" />
    <property name="username" value="smantle" />
    <property name="password" value="123456" />
</dataSource>

<!-- 从配置文件中读取值 -->
<dataSource type="POOLED">
	<property name="driver" value="${jdbc.driver}" />
    <property name="url" value="${jdbc.url}" />
    <property name="username" value="${jdbc.username}" />
    <property name="password" value="${jdbc.password}" />
</dataSource>
```

### \<mappers/\>

\<mappers/\>标签内部可以有多个子\<mapper/\>标签，指定dao层映射文件位置，可以有多种形式：

-   \<mapper resource="" /\>：最常用方式，直接指定对用dao层的.xml文件
-   \<mapper url="" /\>：可以在应用外部指定，例如磁盘位置，很少使用
-   \<mapper class="" />：类的全限定类名，要求dao文件名和接口名相同
-   \<mapper name="" /\>：到dao层映射文件较多时的简写形式

```xml
<mappers>
	<mapper resource="com.foo.dao.mapper1.xml" />
    <mapper url="file://E:\\foo\\bar\\IStudentDao.xml" />
    <mapper class="com.foo.dao.IStudentDao" />
    <mapper name="com.foo.dao" />
</mappers>
```

### \<typeAliases/\>

\<typeAliases/\>指定类的别名，用于简化操作。例如在\<select/\>标签中，有resultType属性，默认需要使用全限定类名，\<typeAlias/\>可以简化操作：

```xml
<!-- mybatis-config.xml -->
<typeAliases>
	<typeAlias type="com.foo.bar.beans.Student" alias="Student" />
</typeAliases>

<!-- IStudentDao.xml -->
<select id="selectStudentById" resultType="Student">
	select * from student where id=#{id}
</select>
```



## Mybatis运行流程

Mybatis运行主要涉及以下几个类：

-   Resources：Mybatis封装的一个工具类，用于读取配置文件
-   SqlSessionFactoryBuilder：建造者设计模式，通过调用build()方法返回SqlSessionFactory工厂实例
-   SqlSessonFactory：Mybatis核心类，该接口对象是一个重量级对象，线程安全，一个应用只需要一个工厂即可
-   SqlSession：SqlSession接口对象用于执行持久化操作，一个SqlSession对应一次数据库会话

```java
public class MyBatisUtil {
	private static SqlSessionFactory factory;
    public static SqlSession getSqlSession() {
		try{
            if (factory == null) {
                InputStream is = Resources.getResourceAsStream("mybatis-conf.xml");
                factory = new SqlSessionFactoryBuilder().build(is);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            // 注意InputStream其实不用关闭，build()方法内部已经完成关闭操作了
        }
        return factory.openSession();
    }   
}
```



## CRUD

### insert

```xml
<!-- 接口方法：public int insertStudent(Student student); -->
<insert id="insertStudent">
	insert into student(name, age, score)
    values(#{name}, #{age}, #{score})
    <selectKey resultType="int" keyProperty="id" order="AFTER">
    	<!-- select last_insert_id() 效果相同 -->
        select @@identity
    </selectKey>
</insert>
```

注意

-   selectKey标签的order属性：用于指出id生成相对于insert语句执行是在前还是在后。MySql数据库表中的id，是先执行insert操作，然后才生成id，所以值为**AFTER**，ORACLE正好相反。
-   注意自增id：无论insert最终是commit还是rollback，DB均会为该记录分配id，之后的insert操作会跳过该id

### update

```xml
<!-- 接口方法：public int updateStudent(Student student); -->
<update id="updateStudent">
	update student set name=#{name}, age=#{age}, score=#{score}
    where id=#{id}
</update>
```

### delete

```xml
<!-- 接口方法：public int deleteStudent(int id); -->
<delete id="deleteStudent">
	delete from student where id=#{id}
</delete>
```

### select

-   select操作返回值是单个POJO对象、或者是List列表，使用的sql语句类似：

```xml
<!-- 接口方法：public Student selectStudentById(int id); -->
<!-- 接口方法：public List<Student> selectAllStudents(); -->
<select id="selectStudentById" resultType="Student">
	select * from student where id=#{id}
</select>

<select id="selectAllStudents" resultType="Student">
	select * from student
</select>
```

-   返回Map：将查询出来的所有对象封装成Map<Stirng, Object>，因此调用时需要指定需要将那个字段作为key，并且key可能出现重复，后面的key会覆盖前面的key。很少使用

```xml
<!-- 接口方法：public Map<String, Student> selectStudentsMap(); -->
public Map<String, Student> selectStudentsMap() {
	Map<String, Student> res = null;
    try{
    	session = MyBatisUtil.getSqlSession();
    	// 指定name字段作为返回Map类型的key
    	res = session.selectMap("selectStudentsMap", "name");
    }finally{
    	if (session != null) {
    		session.close();
    	}
    }
}

<!-- 查询sql完全相同 -->
<select id="selectStudentsMap" resultType="Student">
	select * from student    
</select>    
```

### select（模糊查询）

注意模糊查询中不要使用**${}**，${}只是进行简单的字符串拼接，以硬编码的方法拼接到sql语句中，存在SQ注入风险。**#{}**是占位符，在日志中可以看到被编译成**?**做站位作用，使用的是PrepareStatement，效率高，无SQL注入风险。

```xml
<select id="selectStudentByName" resultType="Student">
	select * from student where name like concat('%', #{name}, '%')
    <!-- 等价于 -->
    select * from student where name like '%' #{name} '%'
</select>
```

### select（根据Map查询）

```xml
<!-- 接口方法：public Student selectStudentByMap(Map<String, Object> map); -->
<select id="selectStudentByMap" resultType="Student">
	select * from student where name=#{student.name} and score=#{student.score}
</select>
```



## 数据库字段和POJO字段不相同解决方案

### sql别名

sql查询时，可以使用as给字段起别名：

```xml
<select id="selectStudentById" resultType="Student">
	select id, first_name as firstName, last_name as lastName full_name as fullName
    from student where id=#{id}
</select>
```

### 开启驼峰转换

如果DB中字段是以下划线形式，POJO是驼峰显示，可以开启下划线向驼峰形式转换，在mybatis-config.xml中：

```xml
<configuration>
	<settings>
    	<setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
</configuration>
```

### 使用ResultMap

sql标签有ResultMap属性，它是ResultType的增强版本，用于完成DB中字段到POJO中属性的映射，达到将查询结果封装成对象的目的:

```xml
<select id="selectStudentById" resultMap="studentMapper">
	select id, first_name, last_name, full_name from sutdent where id=#{id}
</select>

<resultMap type="Student" id="studentMapper">
	<id column="id" property="id" />
    <result colomn="first_name" property="firstName" />
    <result colomn="last_name" property="lastName" />
</resultMap>
```

注意：

-   \<select/\>标签中resultMap的值要和\<resultMap/\>标签中id值相同
-   \<resultMap/\>标签中主键用\<id/\>标签，其余用\</result/\>标签



## 动态SQL

动态sql用于解决查询调节不确定的情况：在程序运行期间，根据用户提交的条件进行查询，条件不同，对应的sql语句就不相同。常用的动态sql标签有\<if/\>、\<where/\>、\<chose/\>、\<foreach/\>。

### 实体符号

动态sql中如果出现>、<、>=等符号，最好转换成实体符号，其中<是一定不能出现在xml中。

|  原符号  |   <   |   <=   |   >   |   >=   |   &    |    '    |    "    |
| :------: | :---: | :----: | :---: | :----: | :----: | :-----: | :-----: |
| 实体符号 | \&lt; | \&lt;= | \&gt; | \&gt;= | \&amp; | \&apos; | \&quot; |

### if标签

```xml
<select id="selectStudentsIf" resultType="Student">
	select * from student
    where 1 = 1
    <if test="name != null and name != ''">
    	and name like '%' #{name} '%'
    </if>
    <if test="age > 0">
    	and age > #{age}
    </if>
</select>
```

### where标签

上面if标签中，where后面必须加上**1 = 1**，否则两个if测试为false时，where后面没有表达式，导致语法错误。但是当数据量很大时，额外添加一个1 = 1又会小号性能，可以引入where标签：

```xml
<select id="selectStudentsWhere" resultType="Student">
	select * from student
    <where>
        <if test="name != null and name != ''">
    		and name like '%' #{name} '%' <!-- where后面第一个判断的and可以不写 -->
    	</if>
    	<if test="age > 0">
    		and age > #{age}              <!-- where后面除第一个判断外，and必须写 -->
    	</if>
    </where>
</select>
```

### choose标签

choose标签里面只可以包含when和otherwise标签，类似于switch...case功能：

```xml
<select id="selectStudentChoose" resultType="Student">
	select * from student
    <where>
    	<choose>
        	<when test="name != null and name != ''">
            	and name like '%' #{name} '%'
            </when>
            <when test="age > 0">
            	and age &lt; #{age}
            </when>
            <otherwise>
            	1 != 1
            </otherwise>
        </choose>
    </where>
</select>
```

### foreach标签

foreach标签用于实现对数组和集合的遍历：

```xml
<!-- 传入类型是数组 -->
<select id="selectStudentForeachArray" resultTyep="Student">
	select * from student
    <if test="array != null and array.length > 0">
    	where id in
        <foreach collection="array" open="(" close=")" item="item" separator=",">
        	#{item}
        </foreach>
    </if>
</select>

<!-- 传入类型是List -->
<select id="selectStudentForeachList" resultTyep="Student">
	select * from student
    <if test="list != null and list.size > 0">
    	where id in
        <foreach collection="list" open="(" close=")" item="item" separator=",">
        	#{item}
        </foreach>
    </if>
</select>

<!-- 传入类型是List，且List里面元素是自定义类型，需要使用#{item.field}形式 -->
<select id="selectStudentForeachList" resultTyep="Student">
	select * from student
    <if test="list != null and list.length > 0">
    	where id in
        <foreach collection="list" open="(" close=")" item="item" separator=",">
        	#{item.id}
        </foreach>
    </if>
</select>
```

注意：

-   如果传入的是数组，test里面必须使用array，是Mybatis内置别名；如果是List，则必须是list

-   collection表示遍历的是集合类型
-   open、close、separator为对遍历内容的sql进行拼接

### sql标签

sql标签用于定义sql片段，防止多次重复sql语句编写，提高复用率。需要使用sql片段时，通过\<include/\>进行引入即可。sql标签可以出现在文件中任何位置：

```xml
<!-- 定义sql片段 -->
<sql id="selectBase">
	select id, first_name, last_name, full_name from student
</sql>

<select id="selectStudentForeachList" resultType="Student">
	<!-- 引用sql片段 -->
    <include refid="selectBase" />
    <if test="list != null and list.size() >0">
    	where id in
        <foreach collection="list" open="(", separator="," close=")" item="item">
        	#{student.id}
        </foreach>
    </if>
</select>
```



## 查询缓存

Mybatis的查询缓存机制根据作用域的不同，可以分为一级缓存的二级缓存。

### Mybatis究竟缓存什么

一个**`\<select id="xxx"\> \</select\>**`有id决定其唯一性，对于一个查询sql而言，可以直接缓存查询出来的结果，下一次相同查询时，直接将查询出来的结果返回即可。也可以缓存sql语句的id（即上面的xxx）。那么Mybatis是缓存查询出来的结果还是缓存sql语句的id呢？

缓存sql语句的id！Hibernate缓存的是查询出来的结果。

可以用以下示例证明：

```xml
<!-- 两个查询语句除了id之外完全相同 -->
<select id="selectId1" resultType="Student">
	select * from student where id=#{id}
</select>
<select id="selectId2" resultType="Student">
	select * from student where id=#{id}
</select>

<!-- 传入的id都相同，只是调用了不同的方法 -->
@Test
public void test() {
	int id = 5;
	Student student1 = dao.selectId1(id);
	System.out.println(student1);

	Student student2 = dao.selectId2(id);	
	System.out.println(student2)
}
```

分析：如果Mybatis缓存的是查询出来的结果，那么上面的示例中，日志里面只会有一条预编译sql日志记录，因为它们只是sql标签的id不相同，查询的是同一个对象。但是试验输出的是两条预编译sql日志记录：

```txt
[DEBUG] ==>  Preparing: select * from student where id=?
[DEBUG] ==> Parameters: 5(Integer)
[DEBUG] <==      Total: 1
Student [id=5, firstName="smantle", lastName="wang", fullName="smantle wang"]
[DEBUG] ==>  Preparing: select * from student where id=?
[DEBUG] ==> Parameters: 5(Integer)
[DEBUG] <==      Total: 1
Student [id=5, firstName="smantle", lastName="wang", fullName="smantle wang"]
```

无论是一级缓存还是二级缓存，底层都是使用HashMap实现：key为sql标签的id，value是从数据库查询出来的结果。如果sql标签的id在缓存中，直接通过id到HashMap中查找对应的value，而不需要到DB中查找。

从Mybatis缓存的是sql标签的id也可以看出：Mybatis的缓存并不是为了在查询之间共享数据（否则的话直接缓存查询结果效果更好），而Hibernate的缓存是为了数据共享。Mybatis的缓存是为了延长该查询的保存时间。

无论是一级缓存还是二级缓存，增、删、改sql操作无论最终结果是commit还是rollback，都会刷新缓存。

### 一级缓存

Mybatis的一级缓存是基于org.apache.ibatis.cache.impl.PerpetualCache类的HashMap本地缓存，作用域是SqlSession。即在同一个SqlSession范围内，执行两次相同的sql语句，第一次执行完毕后，会将查询结果缓存本地，下一次直接从本地缓存中获取。一级缓存在Mybatis中是默认开启的，且无法关闭。

### 二级缓存

二级缓存的作用域是mapper文件的namespace，不同的namespace互相不干扰。

二级缓存开启必须满足两个条件：

-   POJO对象实现Serializable接口，如果该对象存在父类或者属性是自定义对象，父类和自定义对象也需要实现Serializable接口
-   在对应dao层的mapper.xml中加入：\<cache/\>标签

\<cache/\>标签还支持自定义相关配置：

```xml
<mapper namespace="xxx">
	<cache eviction="FIFO" flushInterval="3600000" readOnly="true" size="512" />
</mapper>
```

-   eviction：逐出策略，当二级缓存中对象达到最大值时，需要通过对应的逐出策略移除对象，常用的策略与：LRU、FILO
-   flushInterval：刷新缓存的间隔，单位毫秒，一般不指定，而是通过增、删、该来自动刷新
-   readOnly：设置缓存数据是否可读，默认false
-   size：二级缓存中最多可以存放多少个对象，默认1024个

二级缓存的使用建议：

-   只在一个namespace下使用二级缓存：二级缓存本省作用域就是namespace，由namespace进行隔离。如果多个namespace同时对一个表进行操作，可能各个namespace中出现数据不一致情况
-   只在单表中使用二级缓存：如果一个表和其它表有关联，也可能存在多个namespace同时操作一个表，出现缓存不一致问题
-   查询远多于修改时使用二级缓存：任何修改操作（增、删、改）都会刷新二级缓存，频繁改操作导致缓存频繁刷新从而降低性能。