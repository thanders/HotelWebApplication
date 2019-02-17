package webApp.beans;

import java.io.Serializable;

// A class for hotel rooms
public class Room implements Serializable{
	
	// Fields
	private static final long serialVersionUID = 1L;
	private int Room_Number;
	private int FK_Room_Type_ID;
	
	// Constructor
	public Room(int Room_Number, int FK_Room_Type_ID) {
		super();
		this.Room_Number = Room_Number;
		this.FK_Room_Type_ID = FK_Room_Type_ID;
	}

	public int getRoom_Number() {
		return Room_Number;
	}

	public void setRoom_Number(int Room_Number) {
		this.Room_Number = Room_Number;
	}

	public int getFK_Room_Type_ID() {
		return FK_Room_Type_ID;
	}

	public void setFK_Room_Type_ID(int FK_Room_Type_ID) {
		this.FK_Room_Type_ID = FK_Room_Type_ID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// Getters and Setters
	


}
