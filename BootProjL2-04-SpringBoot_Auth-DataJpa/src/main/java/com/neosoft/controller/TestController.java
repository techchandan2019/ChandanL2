package com.neosoft.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.model.UserInfo;
import com.neosoft.repo.UserInforepository;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserInforepository repo;
	
	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/adduser")
	public String adduser(@RequestBody UserInfo userInfo) {
		
		userInfo.setPwd(encoder.encode(userInfo.getPwd()));
		repo.save(userInfo);
		Optional<UserInfo> userIf=repo.getByUserName(userInfo.getUsn());
		return "successfully save user "+userIf.get();
	}
	@GetMapping("/msg")
	public String getMessage() {
		return "Hello";
	}
}
