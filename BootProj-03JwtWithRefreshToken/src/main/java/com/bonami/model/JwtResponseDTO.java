package com.bonami.model;

public class JwtResponseDTO {
	
	private String accessToken;
	
	private String tokenId;
	
	public JwtResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public JwtResponseDTO(String accessToken, String tokenId) {
		super();
		this.accessToken = accessToken;
		this.tokenId = tokenId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	@Override
	public String toString() {
		return "JwtResponseDTO [accessToken=" + accessToken + ", tokenId=" + tokenId + "]";
	}
	

}
