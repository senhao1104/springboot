# 一、Spring Boot  入门

## 简介 

> 简化Spring应用开发的一个框架；
整个Spring技术栈的一个大集合；
J2EE开发的一站式解决方案；

[百度百科](https://baike.baidu.com/item/Spring%20Boot/20249767?secondId=12985094)
[Spring Boot官网](https://spring.io/projects/spring-boot/)

### 优点

- 快速创建独立运行的Spring项目以及与主流框架集成
- 使用嵌入式的Servlet容器，应用无需打成WAR包
- starters自动依赖与版本控制
- 大量的自动配置，简化开发，也可修改默认值
- 无需配置XML，无代码生成，开箱即用
- 准生产环境的运行时应用监控
- 与云计算的天然集成

## 微服务

> 微服务：架构风格（服务微化）
一个应用应该是一组小型服务；可以通过HTTP的方式进行互通；
每一个功能元素最终都是一个可独立替换和独立升级的软件单元；

[微服务参考文档](https://martinfowler.com/articles/microservices.html#MicroservicesAndSoa)

## 环境约束

>jdk1.8：Spring Boot 推荐jdk1.7及以上；
maven3.x：maven 3.3以上版本；
SpringBoot 1.5.9.RELEASE : 1.5.9;

### Maven设置

>给maven 的settings.xml配置文件的profiles标签添加：（设置使用的jdk版本）
开发工具中的maven设置为自己配置的maven

```xml
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

## 创建HelloWorld项目

> 功能：浏览器发送hello请求，服务器接收请求并处理，响应Hello World字符串；

1. 创建一个Maven工程

2. 导入spring boot相关的依赖

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.1.RELEASE</version>
    <relativePath/>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

3. 编写一个主程序,启动Spring Boot应用

```java
package cn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author senhao
 * @SpringBootApplication 标注一个主程序类，表示这个是一个Springboot应用
 */

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		//启动应用
		SpringApplication.run(MainApplication.class, args);
	}

}
```

4. 编写一个Controller

```java
package cn.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}
}

```

5. 运行主程序main方法
6. 测试访问：localhost:8080/hello

## Hello World探究

### POM 文件

#### 1.父项目

```xml
<!--Hello World项目的父工程是org.springframework.boot-->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.1.RELEASE</version>
    <relativePath/>
</parent>

<parent>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-dependencies</artifactId>
<version>2.2.1.RELEASE</version>
<relativePath>../../spring-boot-dependencies</relativePath>
</parent>

```

> org.springframework.boot 的父项目是 spring-boot-dependencies
它来真正管理Spring Boot应用里面的所有依赖版本；
Spring Boot的版本仲裁中心；
以后我们导入依赖默认是不需要写版本（没有在dependencies里面管理的依赖自然需要声明版本号）；

#### 2.导入的依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

> spring-boot-starter：
spring-boot场景启动器；帮我们导入了web模块正常运行所依赖的组件；

> Spring Boot将所有的功能场景都抽取出来，做成一个个的starters（启动器），只需要在项目里面引入这些starter相关场景的所有依赖都会导入进来。要用什么功能就导入什么场景的启动器。

### 主程序类

```java
@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		//启动应用
		SpringApplication.run(MainApplication.class, args);
	}

}
```

> @SpringBootApplication:
Spring Boot应用标注在某个类上说明这个类是SpringBoot的主配置类，SpringBoot就应该运行这个类的main方法来启动SpringBoot应用；

@SpringBootApplication这个注解类的源码:

```java
@Target({ElementType.TYPE})    //可以给一个类型进行注解，比如类、接口、枚举
@Retention(RetentionPolicy.RUNTIME)    //可以保留到程序运行的时候，它会被加载进入到 JVM 中
@Documented    //将注解中的元素包含到 Javadoc 中去。
@Inherited    //继承，比如A类上有该注解，B类继承A类，B类就也拥有该注解
@SpringBootConfiguration
@EnableAutoConfiguration

/*
*创建一个配置类，在配置类上添加 @ComponentScan 注解。
*该注解默认会扫描该类所在的包下所有的配置类，相当于之前的 <context:component-scan>。
*/
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {
```

> @SpringBootConfiguration:
Spring Boot的配置类，标注在某个类上，表示这是一个Spring Boot的配置类;

> @EnableAutoConfiguration：
开启自动配置功能；以前我们需要配置的东西，Spring Boot帮我们自动配置；@EnableAutoConfiguration告诉SpringBoot开启自动配置功能；这样自动配置才能生效；

## Eclipse快速创建Spring Boot项目

IDE都支持使用Spring的项目创建向导快速创建一个Spring Boot项目；

选择我们需要的模块；向导会联网创建Spring Boot项目；

### 1.安装STS插件

eclipse —> help —> Eclipse Marketplace...

<img src = "https://github.com/senhao1104/springboot/blob/master/99-images/2020-02-04_19-30-23.jpg" width = "50%">

### 2.新建spring boot项目

File —> New —> Project...

<img src = "https://github.com/senhao1104/springboot/blob/master/99-images/2020-02-04_19-34-41.jpg" width = "50%">