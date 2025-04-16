package com.sunbeam;

import java.sql.Date;

public class User {
	private int uId;
	private String fullName;
	private String email;
	private String password;
	private String phoneNo;
	private Date createdTime;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int uId, String fullName, String email, String password, String phoneNo) {
		this.uId = uId;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", fullName=" + fullName + ", email=" + email + ", password=" + password
				+ ", phoneNo=" + phoneNo + ", createdTime=" + createdTime + "]";
	}

}
