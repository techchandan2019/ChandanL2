package com.nt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/getAll")
	public List<String> getList() throws Exception {
		Thread.sleep(2000);
		System.out.println(Thread.currentThread());
		return List.of("Abc","bbc","ccd");
	}
}
