package com.labouardy.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@RequestMapping("/")
@Controller
public class HomeController {

	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "index.html";
	}
}
