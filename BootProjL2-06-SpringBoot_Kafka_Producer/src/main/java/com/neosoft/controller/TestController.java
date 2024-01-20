package com.neosoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.model.Employee;
import com.neosoft.service.TestService;

@RestController
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@GetMapping("/publish/{msg}")
	public String publishMessage(@PathVariable String msg) {
		
		return testService.saveList(msg);
	
	}
	
	@PostMapping("/publishEmp")
	public String publishEmployee(@RequestBody Employee emp) {
		
		return testService.saveEmployeeList(emp);
	
	}
}
