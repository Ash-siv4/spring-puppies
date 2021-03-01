package com.qa.puppies.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.puppies.service.test.ResponseService;

@RestController
public class TestController {

	private ResponseService service;

	public TestController(ResponseService service) {
		super();
		this.service = service;
	}

	@GetMapping("/Test")
	public String hello() {
//		return "Heyo!";
		return this.service.generateResponse();
	}
}