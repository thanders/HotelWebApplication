package webApp.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
	
	// Class fields
	private static final long serialVersionUID = 1L;
	private int reservationId;
	private int GuestID;
	private LocalDate start;
	private LocalDate end;
	private int numberRooms;
	private int duration;
	
	// empty constructor
	public Reservation() { }
	
	// Constructor
	public Reservation(int reservationId, int GuestID, LocalDate start, LocalDate end, int duration, int numberRooms) {
		this.reservationId = reservationId;
		this.GuestID = GuestID;
		this.start = start;
		this.end = end;
		this.duration = duration;
		this.numberRooms = numberRooms;
		
	}


	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getGuestID() {
		return GuestID;
	}

	public void setGuestID(int guestID) {
		GuestID = guestID;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public int getNumberRooms() {
		return numberRooms;
	}

	public void setNumberRooms(int numberRooms) {
		this.numberRooms = numberRooms;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString(){
		return this.getClass().getName() + " " + "[" + this.reservationId + " " + this.GuestID + " " + this.start + this.end + this.duration + this.numberRooms + "]";
	}
	


}
