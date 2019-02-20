package webApp.beans;

import java.io.Serializable;

public class Starwood implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public Starwood() {
		
	}
	
	public Starwood(String name, String surename, String address, String email, int cardNumber, int phoneNumber,
			String userName, String password, String membershipStatus) {
		super();
		this.name = name;
		this.surename = surename;
		this.address = address;
		this.email = email;
		this.cardNumber = cardNumber;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
		this.membershipStatus = membershipStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurename() {
		return surename;
	}
	public void setSurename(String surename) {
		this.surename = surename;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	public String getMembershipStatus() {
		return membershipStatus;
	}
	public void setMembershipStatus(String membershipStatus) {
		this.membershipStatus = membershipStatus;
	}
	
	private String surename;
	private String address;
	private String email;
	private int cardNumber;
	private int phoneNumber;
	private String userName;
	private String password;
	private String membershipStatus;

}
