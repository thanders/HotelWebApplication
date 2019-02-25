package webApp.beans;

import java.io.Serializable;

// A class for hotel rooms
public class Room implements Serializable{
	
	// Fields
	private static final long serialVersionUID = 1L;
	private String roomNumber;
	private int capacity;
	private int reservationNumber;
	private int price;
	
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	
}
