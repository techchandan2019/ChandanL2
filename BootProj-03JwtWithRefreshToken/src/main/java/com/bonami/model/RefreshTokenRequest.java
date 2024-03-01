package com.bonami.model;

public class RefreshTokenRequest {
	
	private String token;
	
	public RefreshTokenRequest() {
		// TODO Auto-generated constructor stub
	}

	public RefreshTokenRequest(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "RefreshTokenRequest [token=" + token + "]";
	}
	
}
