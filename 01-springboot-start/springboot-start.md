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
