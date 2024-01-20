package com.neosoft.controller;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class TestController {

	@GetMapping("/msg")
	@HystrixCommand(fallbackMethod = "getDummyMessage",
					commandProperties = {@HystrixProperty(name ="circuitBreaker.enabled" ,value = "true")})
	public ResponseEntity<String> getMessage(){
		if(new Random().nextInt(10)<5) {
			throw new RuntimeException("Exception occured");
		}
		return new ResponseEntity<>("Hi",HttpStatus.OK);
	}
	
	public ResponseEntity<String> getDummyMessage(){
		return new ResponseEntity<String>("Some thing went wrong" ,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
