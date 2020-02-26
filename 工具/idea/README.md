## 快捷键
以下快捷键都是按照基于Eclipse为基础，进行自定义修改：

- run：`alt+r`
- 继承结构：`f4`
- 查看类的结构：`ctrl+o`
- 提示方法参数类型（Parameter Info）：`ctrl+alt+/`
- 修改变量名和方法名：`alt+shift+r`
- 大小写转换：`ctrl+shift+y`
- 打开代码在文件中的位置：`ctrl+shift+n`
- 抽取局部变量为成员变量：`ctrl+alt+f`
- 全局查找：`ctrl+h`
- 显示UML结构图：`ctrl+shift+u`
- 查看方法的多层重写结构（method hierarchy）：`ctrl+alt+h`
- 抽取方法：`alt+shift+m`
- 打开最近修改文件：`ctrl+e`
- 查看方法在哪里被调用过（call hierarchy）：`ctrl+shift+h`


## 模板

### 常用模板快捷键
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
    
### 自定义模板

-   junit test（jtest）

```java
@Test
public void $VAR1$Test(){
    $END$
}
```

-   try-catch-finnally（tcf）

```java
try{
    $VAR1$
}catch(Exception e) {
    e.printStackTrace();
}finally{
    $VAR2$
}
```

-   thread-sleep（thsp）

```java
try {
    Thread.sleep($VAR1$);
    $VAR2$
} catch (InterruptedException e) {
    e.printStackTrace();
}
```

-   try-lock（trylock）

```java
try{
    $var$.lock();
}finally{
    $var$.unlock();
}
```

-   DispatchServlet（dst）

``` xml
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
```

-   InternalResourceViewResolver（ivr）

```xml
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <!-- 默认: prefix="/WEB-INF/views" suffix=".jsp" -->
    <property name="prefix" value="$VAR1$"/>
    <property name="suffix" value="$VAR2$"/>
</bean>
```



## 开启热部署

-   Settings -> Build, Execution, Deployment -> Compiler，勾选：`Build project automatically`
-   `ctrl+shit+alt+/`按键后，勾选：`compiler.automake.allow.when.app.running`

