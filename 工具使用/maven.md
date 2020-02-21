## 阿里云镜像

```xml
<!-- 阿里云镜像仓库 -->
<mirror>
  <id>alimaven</id>
  <name>aliyun maven</name>
  <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
  <mirrorOf>central</mirrorOf>        
</mirror>
```



## 默认Java编译版本

```xml
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



## Maven打包本地jar

以打包本地支付宝支付的jar为例：

将下载后将对应的jar包放入到src/main/resources/lib下，在pom.xml中加入：

```xml
<dependency>
    <groupId>com.alipay</groupId>
    <artifactId>com-alipay</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/src/main/resources/lib/alipay-sdk-
        								java20161213173952.jar</systemPath>
</dependency>

<dependency>
    <groupId>com.alipay.demo</groupId>
    <artifactId>com-alipay-demo</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/src/main/resources/lib/alipay-trade-sdk-
        		20161215.jar</systemPath>
</dependency>
```

使用：`mvn clean package install -Dmaven.test.skip=true`测试是否通过

