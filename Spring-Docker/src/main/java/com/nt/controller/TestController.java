package com.nt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	Logger logger=LoggerFactory.getLogger(TestController.class);
	
	@GetMapping("/msg/{message}")
	public String getMessage(@PathVariable String message) {
		logger.info("=============message :"+message);
		return message;
	}
}
