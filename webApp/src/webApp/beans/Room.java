package webApp.beans;

import java.io.Serializable;

public class Room implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Room(float price, int roomNumber, String roomType) {
		super();
		this.price = price;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}
	
	private float price;
	private int  roomNumber;
	private String roomType;

	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	

}
