## Spring Boot和Web开发

### 4.1 Web开发简介
使用Spring Boot开发Web应用步骤：
- 创建Spring Boot应用，选中我们需要的模块
- Spring Boot已经将默认这些场景配置好了，我们只需要在配置文件中指定少量的配置就可以运行起来
- 自己编写业务代码

自动配置原理：

这个场景Spring Boot帮助我们配置了什么，能不能修改，能修改哪些配置，能不能拓展？
- xxxAutoConfiguration：帮助我们给容器中自动配置组件
- @ConfigurationProperties：配置类来封装配置文件的内容

### 4.2 Spring Boot对静态资源的映射规则
Spring Boot对静态资源的映射通过WebMvcAutoConfiguration这个自动配置类完成：
```java
@Configuration
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter({ DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class,
		ValidationAutoConfiguration.class })
public class WebMvcAutoConfiguration {
    @Configuration
    @Import(EnableWebMvcConfiguration.class)
    // 这里引入了ResourceProperties配置类
    @EnableConfigurationProperties({ WebMvcProperties.class, ResourceProperties.class })
    @Order(0)
    public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer {
        // 配置MVC需要的bean
        // InternalResourceViewResolver
        // BeanNameViewResolver
        // ConditionalOnMissingBean
        // LocaleResolver
        // ...
    }
}

@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class ResourceProperties {
    
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
            "classpath:/resources/", "classpath:/static/", "classpath:/public/" };
    
    /**
     * Locations of static resources. Defaults to classpath:[/META-INF/resources/,
     * /resources/, /static/, /public/].
     */
    private String[] staticLocations = CLASSPATH_RESOURCE_LOCATIONS;
    
    /**
     * Whether to enable default resource handling.
     */
    private boolean addMappings = true;
    
    private final Chain chain = new Chain();
    
    private final Cache cache = new Cache();
    
    // ... 
}
```

- 所有的[webjars](https://www.webjars.org/)，都会去classpath:/META-INF/resources/webjars/目录下寻找资源，webjars是以jar包像是引入静态资源，先引入jquery的依赖：
```xml
<!-- 加入jquery webjar的maven依赖 -->
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.3.1</version>
</dependency>
```
可以直接通过浏览器访问**localhost:8080/webjars/jquery/3.3.1/jquery.js**：

![](./imgs/webjars.png)

- "/**"访问当前项目的任何资源（静态资源的文件夹）：
```java
@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class ResourceProperties {

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/" };
			// ...
}			
```

Spring Boot提供静态资源的文件夹包括：
- classpath:/META-INF/resources/
- classpath:/resources/
- classpath:/static/
- classpath:/public/

![](./imgs/默认四个静态资源路径.png)

通过浏览器访问时：以上述文件夹为根路径，例如要访问上述的bootstrap.min.js文件，浏览器输入：**http://localhost:8080/asserts/js/bootstrap.min.js**

- 欢迎页面：静态资源文件夹下的所有index.html页面，被 **/\*\*** 映射，例如localhost:8080/ ===> 找到index页面
- 所有的**/favicon.ico都是在静态资源文件夹下找到

同时也能自定义配置Spring Boot的静态资源文件夹的位置：
```properties
spring.resources.static-locations=classpath:/hello/,classpath:/world/
```
### 4.3 模板引擎
![](./imgs/thymeleaf介绍.png)

SpringBoot推荐的模板引擎，语法更加简单，功能更加强大：
- 引入[thymeleaf](https://www.thymeleaf.org/index.html)模板引擎
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
- 切换thymeleaf默认版本
```xml
<properties>
    <java.version>1.8</java.version>
    <!-- 这两个版本的对应关系最好对应上，可以在Maven的中央仓库中都选择最新版本 -->
    <thymeleaf.version>3.0.11.RELEASE</thymeleaf.version>
    <thymeleaf-layout-dialect.version>2.4.1</thymeleaf-layout-dialect.version>
</properties>
```
在Spring Boot官方文档thymeleaf章节中找到对应的thymeleaf需要的配置，当前Spring Boot版本2.1.8为例，[官方文档](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/reference/html/howto-hotswapping.html#howto-reload-thymeleaf-content)中提到将spring.thymeleaf.cache设置为false。

如果上述配置之后，仍然不能访问templates路径下的文件，推荐本地Maven的repository目录清空，重新启动程序加载Maven依赖。

### 4.4 thymeleaf语法
ThymeleafProperties类中封装了thymeleaf的默认规则：
```java
@ConfigurationProperties(prefix = "spring.thymeleaf")
public class ThymeleafProperties {

	private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

	// 只要把HTML页面放在classpath:/templates/目录下，thymeleaf就能够自动渲染
	public static final String DEFAULT_PREFIX = "classpath:/templates/";

	public static final String DEFAULT_SUFFIX = ".html";
	// ...
}	
```
- th: 任意html属性：来替换原生属性，官方文档提供如下：

| Feature  | describe   | Attributes   |
|---|---|---|
| Fragment inclusion  | jsp:include   | th:insert th:replace   |
| Fragment iteration  | c:foreach   | th:each   |
| Condition evaluation  | c:if   | th:if th:unless th:switch th:case  |
| Fragment inclusion  | jsp:include   | th:insert   |
| Local variable definition  | c:set | th:object th:with |
| General attribute modification | 支持prepend和append | th:attr th:attrprepend th:attrappend |
| Specific attribute modification  | 修改定制属性的值   | th:value th:href th:src ... |
| Text(tag body modification)  | 修改标签内容   | th:text th:utext   |
| Fragment specification  | 片段声明 | th:fragment |
| Fragment removal  | 片段删除 | th:remove |

* 表达式语法
```shell
# Simple expressions:
# 获取变量值；OGNL
    1）、获取对象的属性、调用方法
    2）、获取内置对象
        #ctx : the context object.
        #vars: the context variables.
        #locale : the context locale.
        #request : (only in Web Contexts) the HttpServletRequest object.
        #response : (only in Web Contexts) the HttpServletResponse object.
        #session : (only in Web Contexts) the HttpSession object.
        #servletContext : (only in Web Contexts) the ServletContext object.
    3）、内置的工具对象
        #execInfo : information about the template being processed.
        #messages : methods for obtaining externalized messages inside variables expressions, in the same way as they would be obtained using #{…} syntax.
        #uris : methods for escaping parts of URLs/URIs
        #conversions : methods for executing the configured conversion service (if any).
        #dates : methods for java.util.Date objects: formatting, component extraction, etc.
        #calendars : analogous to #dates , but for java.util.Calendar objects.
        #numbers : methods for formatting numeric objects.
        #strings : methods for String objects: contains, startsWith, prepending/appending, etc.
        #objects : methods for objects in general.
        #bools : methods for boolean evaluation.
        #arrays : methods for arrays.
        #lists : methods for lists.
        #sets : methods for sets.
        #maps : methods for maps.
        #aggregates : methods for creating aggregates on arrays or collections.
        #ids : methods for dealing with id attributes that might be repeated (for example, as a result of an iteration)
Variable Expressions: ${...}
# 选择表达式，类似于${...}，但是有一个补充功能：
    <div th:object="${session.user}">
    <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
    <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>
    <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
    </div>
Selection Variable Expressions: *{...}
# 获取国际化内容
Message Expressions: #{...}
# 定义URL链接：
    <!-- Will produce '/gtvg/order/details?orderId=3' (plus rewriting) -->
    <a href="details.html" th:href="@{/order/details(orderId=${o.id},execType='FAST')}">view</a>
Link URL Expressions: @{...}
# 片段引用表达式
Fragment Expressions: ~{...}
# Literals
Text literals: 'one text' , 'Another one!' ,…
Number literals: 0 , 34 , 3.0 , 12.3 ,…
Boolean literals: true , false
Null literal: null
Literal tokens: one , sometext , main ,…

# Text operations:
String concatenation: +
Literal substitutions: |The name is ${name}|
Arithmetic operations:
Binary operators: + , - , * , / , %
Minus sign (unary operator): -

# Boolean operations:
Binary operators: and , or
Boolean negation (unary operator): ! , not

# Comparisons and equality:
Comparators: > , < , >= , <= ( gt , lt , ge , le )
Equality operators: == , != ( eq , ne )
Conditional operators:
If-then: (if) ? (then)
If-then-else: (if) ? (then) : (else)
Default: (value) ?: (defaultvalue)

# Special tokens:
No-Operation: _
```
举例：

```java
@Controller
public class HelloController {
    @RequestMapping("/success")
    // 默认去classpath:/templates/中寻找success.html进行渲染
    public String successController(Map<String, Object> maps){
        maps.put("user", Arrays.asList("<h1>ZhangSan</h1>", "<h1>LiSi</h1>"));
        maps.put("hello", "<h1>你好</h1>");
        return "success";
    }
}
```
success.html
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>success</h1>
<div th:text="${#locale.country}">这是默认内容</div>
<div th:utext="${hello}">hello</div>
<div th:text="${hello}">hello</div>
<hr/>

<!-- th:each每次遍历时候都会生成当前这个标签：3个h4标签-->
<h4 th:text="${user}" th:each="user:${user}"></h4>
<hr/>
<h4>
    <!--[[]]: 不会进行转义，输出原始字符串，相当于th:utext-->
    <span th:each="user:${user}"> [[${user}]] </span>
</h4>
<h4>
    <!--[()]: 会进行转义，相当于th:text-->
    <span th:each="user:${user}"> [(${user})] </span>
</h4>
</body>
</html>
```

### 4.5 SpringMVC自动配置及拓展
SpringMVC自动配置原理[官网文档](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/reference/html/boot-features-developing-web-applications.html)，主要原理如下：
**29.1.1 Spring MVC Auto-configuration**

Spring Boot provides auto-configuration for Spring MVC that works well with most applications.

The auto-configuration adds the following features on top of Spring’s defaults:

- Inclusion of **ContentNegotiatingViewResolver** and BeanNameViewResolver beans.
- Support for serving **static resources**, including support for WebJars (covered later in this document)).
- Automatic registration of **Converter**, GenericConverter, and **Formatter** beans.
- Support for **HttpMessageConverters** (covered later in this document).
- Automatic registration of **MessageCodesResolver** (covered later in this document).
- Static **index.html** support.
- Custom **Favicon** support (covered later in this document).
- Automatic use of a **ConfigurableWebBindingInitializer** bean (covered later in this document).

If you want to keep Spring Boot MVC features and you want to add additional MVC configuration (interceptors, formatters, view controllers, and other features), **you can add your own @Configuration class of type WebMvcConfigurer but without @EnableWebMvc**. If you wish to provide custom instances of RequestMappingHandlerMapping, RequestMappingHandlerAdapter, or ExceptionHandlerExceptionResolver, you can declare a WebMvcRegistrationsAdapter instance to provide such components.

If you want to take complete control of Spring MVC, you can add your own @Configuration annotated with @EnableWebMvc.

- ContentNegotiatingViewResolver & BeanNameViewResolver 
	 - 自动配置了ViewResolver（视图解析器：根据方法的返回值得到视图对象（View），视图对象决定视图如何渲染（转发？重定向？））
	 - ContentNegotiatingViewResolver会组合所有的视图解析器
	 - 如何定值：我们可以自己手动地给容器中添加一个视图解析器，能够的被组合起来
		```java
		@SpringBootApplication
		public class RestfulCrudApplication {
		
		    public static void main(String[] args) {
		        SpringApplication.run(RestfulCrudApplication.class, args);
		    }
		
			// 自定义一个ViewResolver，观察是否会被ContentNegotiatingViewResolver自动组合		
		    @Bean
		    public ViewResolver myViewResolver(){
		        return new MyViewResolver();
		    }
		
		    public static class MyViewResolver implements  ViewResolver{
		
		        @Override
		        public View resolveViewName(String viewName, Locale locale) throws Exception {
		            return null;
		        }
		    }
		}
		```
	- 以Debug模式查看DispatcherServlet的doDispatch()方法：
![](./imgs/自定义视图解析器debug查看.png)
	 
 - Webjars静态资源路径问题
 - index.html静态首页访问
 - favicon support：favicon.ico图标
 - 自动注册了Converter, GenericConverter, and Formatter beans：类型转换使用Converter，页面格式化包括日期、国际化等
 - HttpMessageConverters：SpringMVC中用来转换http请求和响应的，例如User对象-》Json数据，也可以自己手动向容器中增加HttpMessageConverter对象，使用@Bean或者@Component注解等
```java
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.*;

@Configuration
public class MyConfiguration {

	@Bean
	public HttpMessageConverters customConverters() {
		HttpMessageConverter<?> additional = //...
		HttpMessageConverter<?> another = //...
		return new HttpMessageConverters(additional, another);
	}
}
```
- MessageCodesResolver :定义错误代码生成规则
- ConfigurableWebBindingInitializer：初始化Web数据绑定器（WebdataBinder），用于将请求数据封装成一个JavaBean对象，它会先从容器中获取，如果获取不到，会从父类中获取，我们也可以配置一个ConfigurableWebBindingInitializer 来替换默认的：
```java
@Override
protected ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
	try {
		return this.beanFactory.getBean(ConfigurableWebBindingInitializer.class);
	}
	catch (NoSuchBeanDefinitionException ex) {
		return super.getConfigurableWebBindingInitializer();
	}
}
```
**org.springframework.boot.autoconfigure.web中包含了web环境下的所有自动配置**

**修改Spring Boot的默认配置**：
- Spring Boot在自动配置很多组件时候，先看用户有没有自己配置（@Bean、@Component），如果有就会用用户配置的，如果没有才会自动配置。如果有些组件可以有多个（ViewResolver），将用户配置和默认配置组合起来
- Spring Boot中存在非常多的xxxConfigurer帮助我们进行拓展配置，详见下面介绍
- Spring Boot中存在非常多的xxxCustomizer帮助我们进行定制配置，详见下面介绍

**拓展SpringMVC**

编写一个配置类（@Configuration），是（继承）WebMvcConfigurer类型，不能标注@EnableWebMvc注解。这样既能保证保留了所有的自动配置，也能用我们拓展的。 
```java
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/sherman").setViewName("success");
    }
}
```
- WebMvcAutoConfiguration是SpringMVC的自动配置类
- 在做其它自动配置时候，会导入**EnableWebMvcConfiguration**.class
```java
@Configuration
public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration implements 	ResourceLoaderAware {
}
 
// 其父类DelegatingWebMvcConfiguration有如下一段代码：
@Configuration
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {

private final WebMvcConfigurerComposite configurers = new WebMvcConfigurerComposite();

// 从容器中获取所有的WebMvcConfigurer
@Autowired(required = false)
public void setConfigurers(List<WebMvcConfigurer> configurers) {
	if (!CollectionUtils.isEmpty(configurers)) {
		this.configurers.addWebMvcConfigurers(configurers);
	}
}
// 以一个参考实现为例：将所有的WebMvcConfigurer相关配置都来一起调用
@Override
public void addViewControllers(ViewControllerRegistry registry) {
	for (WebMvcConfigurer delegate : this.delegates) {
		delegate.addViewControllers(registry);
	}
}
```
- 容器中所有的WebMvcConfigurer都会一起起作用，包括我们自己自定义的配置类，最终的实现效果SpringMVC和我们扩展配置都会起作用
- 如果使用了@EnableWebMvc注解，我们就会**全面接管**SpringMVC，Spring Boot对SpringMVC的所有自动配置都会失效，导致所有失效的自动配置都需要我们手动配置

@EnableWebMvc使得SpringMVC自动配置**失效**原理：
- @EnableWebMvc注解中，导入了DelegatingWebMvcConfiguration类
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(DelegatingWebMvcConfiguration.class)
public @interface EnableWebMvc {
}
```
- DelegatingWebMvcConfiguration类继承于WebMvcConfigurationSupport 
```java
@Configuration
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
}
```
- WebMvcAutoConfiguration类的注解声明中有 **@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)**：
```java
@Configuration
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter({ DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class,
		ValidationAutoConfiguration.class })
public class WebMvcAutoConfiguration {
}
```
:star:：@EnableWebMvc注解中会引入**WebMvcConfigurationSupport.class**，而这个class在WebMvcAutoConfiguration的注解中是以@ConditionalOnMissingBean形式说明的，因此不满足条件，导致WebMvcAutoConfiguration自动配置失效。