## IDEA Tutorial

### 快捷键
快捷键使用Eclipse模板（可以导入Eclipse copy.xml），然后修改一下快捷键：

- run: alt+r继承结构：F4
- 查看类的结构：ctrl+o
- 提示方法参数类型（Parameter Info）：ctrl+alt+/
- 重构：修改变量名和方法名：alt+shift+r
- 大小写转换：ctrl+shift+y
- 打开代码在文件中的位置：ctrl+shift+n
- 抽取局部变量为成员变量：ctrl+alt+f
- 全局查找：ctrl+h
- 显示UML结构图：ctrl+shift+u
- 查看方法的多层重写结构（method hierarchy）：ctrl+alt+h
- 抽取方法：alt+shift+m
- 打开最近修改文件：ctrl+e
- 查看方法在哪里被调用过（call hierarchy）：ctrl+shift+h


### 模板

#### 常用模板快捷键
- 输出：
    - sout/soutp/soutm/soutv/xxx.sout
- 迭代：
    - fori/iter/itar/list.for/list.forr
- 条件判断：
    - ifn/inn/xxx.nn/xxx.null
- 常用简写：
    - prsf/psf/psfi/psfs/psvm/geti
- 其它：
    - xxx.cast/xxx.castvar 强制转换
    - function.lambda 转换成lambda函数
    - xxx.try 捕获异常
    
#### 自定义模板
```xml
- jtest：
    @Test
    public void $VAR1$Test(){
        $END$
    }

- tcf：
    try{
        $VAR1$
    }catch(Exception e) {
        e.printStackTrace();
    }finally{
        $VAR2$
    }

- thsp：
    try {
        Thread.sleep($VAR1$);
        $VAR2$
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

- trylock：
    try{
        $var$.lock();
    }finally{
        $var$.unlock();
    }

- dps
<!-- 配置DispatcherServlet -->
<servlet>
    <servlet-name>dispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:$VAR1$</param-value>
    </init-param>
    <load-on-startup>$VAR2$</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>dispatcherServlet</servlet-name>
    <url-pattern>$VAR3$</url-pattern>
</servlet-mapping>

- ivr
<!-- InternalViewResolver相关模板 -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <!-- 默认: prefix="/WEB-INF/views" suffix=".jsp" -->
    <property name="prefix" value="$VAR1$"/>
    <property name="suffix" value="$VAR2$"/>
</bean>
```

### 调试

- 端点处右击可以选择让程序运行到指定的地方：
```java
for(int i = 0; i < 60; ++i) {
    if (i == 60){ // break point
        /**
         * 右击端点，在Condition中输入i==60
         * 然后Resume Program，程序就会运行到i=60处停止
         * 可以大大加快调试速度
         */
        System.out.println("come here!");
    }
}
```
- Ctrl+U：计算表达式的值

### Maven设置

在Maven安装目录下：/conf/settings.xml加入：
```xml
<!-- 阿里云镜像仓库 -->
<mirror>
  <id>alimaven</id>
  <name>aliyun maven</name>
  <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
  <mirrorOf>central</mirrorOf>        
</mirror>

<!-- java编译版本 -->
<profile>    
    <id>jdk-1.8</id>    
    <activation>    
        <activeByDefault>true</activeByDefault>    
        <jdk>1.8</jdk>    
    </activation>    
    <properties>    
        <maven.compiler.source>1.8</maven.compiler.source>    
        <maven.compiler.target>1.8</maven.compiler.target>    
        <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>    
    </properties>     
</profile>   
```

### .gitignore文件

.gitignore文件 [下载](https://github.com/github/gitignore)

Java项目.gitignore文件:
```shell
# Compiled class file
*.class

# Log file
*.log

# BlueJ files
*.ctxt

# Mobile Tools for Java (J2ME)
.mtj.tmp/

# Package Files #
*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar

# virtual machine crash logs, see http://www.java.com/en/download/help/error_hotspot.xml
hs_err_pid*

# IntelliJ project files
.idea
*.iml
out
gen
/.apt_generated/
/.classpath
/.factorypath
/.project
/.settings/
/.springBeans
/target/
/.gitignore
/user-profile-analysis.iml
/.idea
/README.md
.DS_Store
```