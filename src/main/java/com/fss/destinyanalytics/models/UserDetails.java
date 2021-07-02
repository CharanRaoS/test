package com.fss.destinyanalytics.models;

import java.util.List;

public class UserDetails {

	private String userName;
	private String password;
	private String role;
	private List usermenu;
	private String isPrimeUser;

	public UserDetails() {
		super();
	}

	public UserDetails(String userName, String password, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public String getIsPrimeUser() {
		return isPrimeUser;
	}

	public void setIsPrimeUser(String isPrimeUser) {
		this.isPrimeUser = isPrimeUser;
	}

	public List getUsermenu() {
		return usermenu;
	}

	public void setUsermenu(List usermenu) {
		this.usermenu = usermenu;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
