package webApp.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class Starwood implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Starwood(String name, String surename, String address, String email, String cardNumber, int phoneNumber) {
		super();
		this.name = name;
		this.surename = surename;
		this.address = address;
		this.email = email;
		this.cardNumber = cardNumber;
		this.phoneNumber = phoneNumber;
	}

	public Starwood(String name, String surename, String address, String email, String cardNumber, int phoneNumber,
			String userName, int cVV, LocalDate expiryDate) {
		super();
		this.name = name;
		this.surename = surename;
		this.address = address;
		this.email = email;
		this.cardNumber = cardNumber;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.CVV = cVV;
		this.expiryDate = expiryDate;
	}

	public int getCVV() {
		return CVV;
	}

	public void setCVV(int cVV) {
		CVV = cVV;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	private String name;
	private int id;
	public Starwood() {
		
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
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String  cardNumber) {
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
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	private String surename;
	private String address;
	private String email;
	private String cardNumber;
	private int phoneNumber;
	private String userName;
	private int CVV;
	private LocalDate expiryDate;


	public String toString() {
		return this.getClass().getName() +" "+ "[" +this.id+" "+ this.name +" "+ this.surename + this.address + this.email + this.cardNumber + this.phoneNumber +  "]";
	}
}
