package com.bonami.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonami.model.AuthRequest;
import com.bonami.model.JwtResponseDTO;
import com.bonami.model.RefreshToken;
import com.bonami.model.RefreshTokenRequest;
import com.bonami.model.UserInfo;
import com.bonami.repo.UserInforepository;
import com.bonami.service.JWTService;
import com.bonami.service.RefreshTokenService;


@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserInforepository repo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private RefreshTokenService refService;
	
	@Autowired
	private AuthenticationManager authmanger;

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
	
	@PostMapping("/authenticate")
	public JwtResponseDTO generateToken(@RequestBody AuthRequest authReq) throws Exception {
		Authentication authenticate = authmanger.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsn(),authReq.getPwd()));
		if(authenticate.isAuthenticated()) {
			RefreshToken refreshToken = refService.createRefreshToken(authReq.getUsn());
			String token = jwtService.generateToken(authReq.getUsn());
			//create response object
			JwtResponseDTO response=new JwtResponseDTO();
			response.setAccessToken(token);
			response.setTokenId(refreshToken.getToken());
			
			
			return response;
			
		}
		else
			throw new Exception("Invalid User");
	}
	@PostMapping("/refreshToken")
	public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequest req){
		Optional<RefreshToken> refTokenOptional = refService.findByToken(req.getToken());
		System.out.println("=========== Refresh Token ===============");
		if(refTokenOptional.isPresent()) {
			refService.verifyExpiration(refTokenOptional.get());
			RefreshToken refreshToken=refTokenOptional.get();
			UserInfo userInfo = refreshToken.getUserInfo();
			String accessToken = jwtService.generateToken(userInfo.getUsn());
			
			return new JwtResponseDTO(accessToken,req.getToken());
		}
		else {
			throw new RuntimeException("Refresh token is not present in database");
		}
		
	}
}
