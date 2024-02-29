package com.bonami.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/consumerApi")
public class ConsumerController {

	private static final String URL="http://localhost:4041/producerApi/msg/";
	
	@GetMapping("/msg/{msg}")
	@CircuitBreaker(name ="mycircuitBreaker",fallbackMethod = "dummyMethod")
	public ResponseEntity<?> getMessage(@PathVariable String msg){
//		HttpEntity entity=new HttpEntity(null)

		RestTemplate template =new RestTemplate();
		ResponseEntity<String> exchange = template.exchange(URL+msg, HttpMethod.GET,null , String.class);
		return exchange;
	}
	public ResponseEntity<?> dummyMethod(String msg,Exception e) {
		return new ResponseEntity<String>("please try after some time",HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
