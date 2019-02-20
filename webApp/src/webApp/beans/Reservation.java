package webApp.beans;

import java.io.Serializable;

public class Reservation implements Serializable {
	
	// Class fields
	private static final long serialVersionUID = 1L;
	private int reservationId;
	private int GuestID;
	
	// empty constructor
	public Reservation() { }
	
	// Constructor
	public Reservation(int GuestID) {
		this.GuestID = GuestID;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getGuestID() {
		return this.GuestID;
	}

	public void setGuestID(int GuestID) {
		this.GuestID = GuestID;
	}

	public String toString(){
		return this.getClass().getName() +" "+ "[" + this.reservationId +" "+ this.GuestID + "]";
	}
	


}
