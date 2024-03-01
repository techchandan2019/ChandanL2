package com.bonami.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Refresh_token02")
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String token;
	private Instant expiryTime;
	
	@OneToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private UserInfo userInfo;
	
	public RefreshToken() {
		// TODO Auto-generated constructor stub
	}

	public RefreshToken(Integer id, String token, Instant expiryTime, UserInfo userInfo) {
		super();
		this.id = id;
		this.token = token;
		this.expiryTime = expiryTime;
		this.userInfo = userInfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Instant getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(Instant expiryTime) {
		this.expiryTime = expiryTime;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "RefreshToken [id=" + id + ", token=" + token + ", expiryTime=" + expiryTime + ", userInfo=" + userInfo
				+ "]";
	}
	
}
