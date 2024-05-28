package com.zosh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeCongtroller {
	
	@GetMapping
	public String homeControllerHandler() {
		return "this is home controller";
		
	}
	@GetMapping("/home")
	public String homeControllerHandler2() {
		return "this is home controller home";
		
	}
	//@DeleteMapping

}
