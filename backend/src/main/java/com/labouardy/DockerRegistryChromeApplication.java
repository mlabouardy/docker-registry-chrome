package com.labouardy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@SpringBootApplication
public class DockerRegistryChromeApplication {
	
	@RequestMapping(method=RequestMethod.GET)
	public String message(){
		
		return "hello world";
	}

	public static void main(String[] args) {
		SpringApplication.run(DockerRegistryChromeApplication.class, args);
	}
}
