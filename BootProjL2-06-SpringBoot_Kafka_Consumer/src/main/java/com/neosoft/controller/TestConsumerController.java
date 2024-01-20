package com.neosoft.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.service.MessageService;

@RestController
public class TestConsumerController {

	@Autowired
	private MessageService service;
	@GetMapping("/msg")
	public Set<Object> getMessage() {
		return service.getAllMessage();
	}
}
