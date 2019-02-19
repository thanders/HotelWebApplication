package webApp.beans;

import java.io.Serializable;

public class Guest implements Serializable{

	// Declare class fields
	private static final long serialVersionUID = 1L;
	private String guestName;
	private String guestSurename;
	private String guestAddress;
	private String guestEmail;
	private String guestCardNumber;
	private String guestPhoneNumber;
	
	// Empty constructor
	public Guest() {}
	
	// Constructor
	public Guest(String guestName, String guestSurename, String guestAddress, String guestEmail, String guestCardNumber, String guestPhoneNumber) {
		super();
		this.guestName = guestName;
		this.guestSurename = guestSurename;
		this.guestAddress = guestAddress;
		this.guestEmail = guestEmail;
		this.guestCardNumber = guestCardNumber;
		this.guestPhoneNumber = guestPhoneNumber;
	}

	// Getters and Setters
	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestSurename() {
		return guestSurename;
	}

	public void setGuestSurename(String guestSurename) {
		this.guestSurename = guestSurename;
	}

	public String getGuestAddress() {
		return guestAddress;
	}

	public void setGuestAddress(String guestAddress) {
		this.guestAddress = guestAddress;
	}

	public String getGuestEmail() {
		return guestEmail;
	}

	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}

	public String getGuestCardNumber() {
		return guestCardNumber;
	}

	public void setGuestCardNumber(String guestCardNumber) {
		this.guestCardNumber = guestCardNumber;
	}

	public String getGuestPhoneNumber() {
		return guestPhoneNumber;
	}

	public void setGuestPhoneNumber(String guestPhoneNumber) {
		this.guestPhoneNumber = guestPhoneNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString() {
		return this.getClass().getName() +" "+ "[" + this.guestName +" "+ this.guestSurename + this.guestAddress + this.guestEmail + this.guestCardNumber + this.guestPhoneNumber + "]";
	}
	
	
}
