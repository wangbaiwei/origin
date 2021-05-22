package com.harmong.pojo;

public class User {
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
	public User(String userId, String userName, String userIdcard, String userPassword, String userPhone,
			String userResignDatetime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userIdcard = userIdcard;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userResignDatetime = userResignDatetime;
	}

	private String userId;
	private String userName;
	private String userIdcard;
	private String userPassword;
	private String userPhone;
	private String userResignDatetime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserIdcard() {
		return userIdcard;
	}
	public void setUserIdcard(String userIdcard) {
		this.userIdcard = userIdcard;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserResignDatetime() {
		return userResignDatetime;
	}
	public void setUserResignDatetime(String userResignDatetime) {
		this.userResignDatetime = userResignDatetime;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userIdcard=" + userIdcard + ", userPassword="
				+ userPassword + ", userPhone=" + userPhone + ", userResignDatetime=" + userResignDatetime + "]";
	}
}
