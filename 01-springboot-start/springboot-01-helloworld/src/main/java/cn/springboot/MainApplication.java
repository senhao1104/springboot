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