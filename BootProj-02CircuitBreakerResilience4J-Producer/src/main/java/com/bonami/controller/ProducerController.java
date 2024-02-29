package com.bonami.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producerApi")
public class ProducerController {

	@GetMapping("/msg/{msg}")
	public ResponseEntity<?> getMessage(@PathVariable String msg) {
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
}
