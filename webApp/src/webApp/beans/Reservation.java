package webApp.beans;

import java.io.Serializable;

public class Reservation implements Serializable {
	
	// Class fields
	private static final long serialVersionUID = 1L;
	private int Reservation_Id;
	private String Reserved_By;
	
	// empty constructor
	public Reservation() { }
	
	// Constructor
	public Reservation(int Reservation_Id, String Reserved_By) {
		this.Reservation_Id = Reservation_Id;
		this.Reserved_By = Reserved_By;
	}

	public int getReservation_Id() {
		return this.Reservation_Id;
	}

	public void setReservation_Id(int Reservation_Id) {
		this.Reservation_Id = Reservation_Id;
	}

	public String getReserved_By() {
		return this.Reserved_By;
	}

	public void setReserved_By(String Reserved_By) {
		this.Reserved_By = Reserved_By;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString(){
		return this.getClass().getName() +" "+ "[" + this.Reservation_Id +" "+ this.Reserved_By + "]";
	}
	


}
