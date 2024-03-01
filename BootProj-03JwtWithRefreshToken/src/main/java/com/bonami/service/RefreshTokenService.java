package com.bonami.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonami.model.RefreshToken;
import com.bonami.repo.IRefreshTokenRepository;
import com.bonami.repo.UserInforepository;

@Service
public class RefreshTokenService {

	@Autowired
	private UserInforepository userRepo;
	@Autowired
	private IRefreshTokenRepository refreshRepo;
	
	public RefreshToken createRefreshToken(String usn) {
		
		RefreshToken reftoken=new RefreshToken();
		reftoken.setUserInfo(userRepo.getByUserName(usn).get());
		reftoken.setToken(UUID.randomUUID().toString());
		reftoken.setExpiryTime(Instant.now().plusMillis(1000*60*10));
		
		return refreshRepo.save(reftoken);
		
	}
	public Optional<RefreshToken> findByToken(String tokenId){
		return refreshRepo.findByToken(tokenId);
	}
	public RefreshToken verifyExpiration(RefreshToken token) {
		if(token.getExpiryTime().compareTo(Instant.now())<0) {
			refreshRepo.delete(token);
			throw new RuntimeException("refresh token was expired,please make a new sign in request");
		}
		return token;
	}
}
