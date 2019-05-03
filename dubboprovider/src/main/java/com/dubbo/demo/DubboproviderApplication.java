package com.dubbo.demo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableDubboConfiguration
//@ImportResource("dubbo/dubbo.xml")
public class DubboproviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboproviderApplication.class, args);
	}

}
