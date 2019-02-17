package webApp.beans;

import java.io.Serializable;

public class Reservation implements Serializable {
	
	// Class fields
	private static final long serialVersionUID = 1L;
	private int reservationId;
	private String reservedBy;
	
	// empty constructor
	public Reservation() { }
	
	// Constructor
	public Reservation(int Reservation_Id, String Reserved_By) {
		this.reservationId = Reservation_Id;
		this.reservedBy = Reserved_By;
	}

	
	
	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public String getReservedBy() {
		return reservedBy;
	}

	public void setReservedBy(String reservedBy) {
		this.reservedBy = reservedBy;
	}

	public String toString(){
		return this.getClass().getName() +" "+ "[" + this.reservationId +" "+ this.reservedBy + "]";
	}
	


}
