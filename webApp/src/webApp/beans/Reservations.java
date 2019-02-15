package webApp.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservations implements Serializable {
	
	// Class fields
	private static final long serialVersionUID = 1L;
	private int Id;
	private int Room_Number;
	private String Room_Owner;
	private LocalDate Book_Date;
	private LocalDate End_Date;
	private Float Price;
	private Boolean Member_Status;
	
	// Constructor
	public Reservations(int id, int room_Number, String room_Owner, LocalDate book_Date, LocalDate end_Date,
			Float price, Boolean member_Status) {
		super();
		Id = id;
		this.Room_Number = room_Number;
		this.Room_Owner = room_Owner;
		this.Book_Date = book_Date;
		this.End_Date = end_Date;
		this.Price = price;
		this.Member_Status = member_Status;
	}

	// Getters and setters:
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getRoom_Number() {
		return Room_Number;
	}

	public void setRoom_Number(int room_Number) {
		Room_Number = room_Number;
	}

	public String getRoom_Owner() {
		return Room_Owner;
	}

	public void setRoom_Owner(String room_Owner) {
		Room_Owner = room_Owner;
	}

	public LocalDate getBook_Date() {
		return Book_Date;
	}

	public void setBook_Date(LocalDate book_Date) {
		Book_Date = book_Date;
	}

	public LocalDate getEnd_Date() {
		return End_Date;
	}

	public void setEnd_Date(LocalDate end_Date) {
		End_Date = end_Date;
	}

	public Float getPrice() {
		return Price;
	}

	public void setPrice(Float price) {
		Price = price;
	}

	public Boolean getMember_Status() {
		return Member_Status;
	}

	public void setMember_Status(Boolean member_Status) {
		Member_Status = member_Status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
