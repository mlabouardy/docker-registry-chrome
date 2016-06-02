package com.labouardy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.labouardy.service","com.labouardy.controllers"})
@SpringBootApplication
public class DockerRegistryChromeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DockerRegistryChromeApplication.class, args);
	}
}
