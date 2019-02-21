package webApp.beans;

import java.io.Serializable;

public class Guest implements Serializable{

	// Declare class fields
	private static final long serialVersionUID = 1L;
	private int guestID;
	private String guestName;
	private String guestSurename;
	private String guestAddress;
	private String guestEmail;
	private int guestCardNumber;
	private int guestPhoneNumber;
	
	// Empty constructor
	public Guest() {}
	
	// Constructor
	public Guest(String guestName, String guestSurename, String guestAddress, String guestEmail, int guestCardNumber, int guestPhoneNumber) {
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

	public int getGuestCardNumber() {
		return guestCardNumber;
	}

	public void setGuestCardNumber(int guestCardNumber) {
		this.guestCardNumber = guestCardNumber;
	}

	public int getGuestPhoneNumber() {
		return guestPhoneNumber;
	}

	public void setGuestPhoneNumber(int guestPhoneNumber) {
		this.guestPhoneNumber = guestPhoneNumber;
	}
	
	public int getGuestID() {
		return guestID;
	}

	public void setGuestID(int guestID) {
		this.guestID = guestID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString() {
		return this.getClass().getName() +" "+ "[" + this.guestID + this.guestName +" "+ this.guestSurename + this.guestAddress + this.guestEmail + this.guestCardNumber + this.guestPhoneNumber + "]";
	}
	
	
}
