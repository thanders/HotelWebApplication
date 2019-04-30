package webApp.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.DecimalFormat;


// A class for hotel rooms
public class Room implements Serializable{
	
	// Fields
	private static final long serialVersionUID = 1L;
	private int id;
	private String roomNumber;
	private int capacity;
	private BigInteger reservationNumber;
	private double price;
    public double getReducedPrice() {
		return price-(price*0.1);
	}

	String pattern="\u20ac###,###.##";
    DecimalFormat euroFormatter = new DecimalFormat(pattern);
	private String priceFormatted;
	
	// Constructor
	public Room(String Room_Number) {
		super();
		this.roomNumber = Room_Number;
		
	}
	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public BigInteger getReservationNumber() {
		return reservationNumber;
	}
	public void setReservationNumber(BigInteger reservationNumber) {
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
