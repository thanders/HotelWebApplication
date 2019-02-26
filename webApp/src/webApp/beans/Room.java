package webApp.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

// A class for hotel rooms
public class Room implements Serializable{
	
	// Fields
	private static final long serialVersionUID = 1L;
	private String roomNumber;
	private int capacity;
	private int reservationNumber;
	private double price;
    String pattern="\u20ac###,###.##";
    DecimalFormat euroFormatter = new DecimalFormat(pattern);
	private String priceFormatted;
	
	// Constructor
	public Room(String Room_Number) {
		super();
		this.roomNumber = Room_Number;
		
	}
	// Getters and Setters
	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getReservationNumber() {
		return reservationNumber;
	}
	public void setReservationNumber(int reservationNumber) {
		this.reservationNumber = reservationNumber;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
		this.priceFormatted = euroFormatter.format(price);
	}
	public String getPriceFormatted() {
		return priceFormatted;
	}
	public void setPriceFormatted(String priceFormatted) {
		this.priceFormatted = priceFormatted;
	}

	
}
