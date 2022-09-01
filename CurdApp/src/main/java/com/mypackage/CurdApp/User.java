package com.mypackage.CurdApp;

public class User {
	private int UserId;
	private String UserName;
	private String UserContact;
	private String UserAddress;
	private String UserEmail;
	private String UserPassword;
	
	
	public User(String userName, String userContact, String userAddress, String userEmail, String userPassword) {
		super();
		UserName = userName;
		UserContact = userContact;
		UserAddress = userAddress;
		UserEmail = userEmail;
		UserPassword = userPassword;
	}

	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserContact() {
		return UserContact;
	}
	public void setUserContact(String userContact) {
		UserContact = userContact;
	}
	public String getUserAddress() {
		return UserAddress;
	}
	public void setUserAddress(String userAddress) {
		UserAddress = userAddress;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

}
