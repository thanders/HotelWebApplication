package webApp.beans;

import java.io.Serializable;

// A class for hotel rooms
public class Room implements Serializable{
	
	// Fields
	private static final long serialVersionUID = 1L;
	private int roomNumber;
	private int fkRoomTypeId;
	
	// Constructor
	public Room(int Room_Number, int FK_Room_Type_ID) {
		super();
		this.roomNumber = Room_Number;
		this.fkRoomTypeId = FK_Room_Type_ID;
	}
	// Getters and Setters
	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getFkRoomTypeId() {
		return fkRoomTypeId;
	}

	public void setFkRoomTypeId(int fkRoomTypeId) {
		this.fkRoomTypeId = fkRoomTypeId;
	}

	
	


}
