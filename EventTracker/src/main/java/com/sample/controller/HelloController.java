package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	@RequestMapping(value = "/greeting" , method = RequestMethod.GET)
	public String sayHello(Model model) {
		model.addAttribute("greeting", "Hello World");
		
		return "hello";
	}
	
	@RequestMapping(value = "/index")
	public String index(Model model) {
		return "forward:index.jsp";
	}
}

