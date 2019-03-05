package webApp.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation implements Serializable {
	
	// Class fields
	private static final long serialVersionUID = 1L;
	private int reservationId;
	private int GuestID;
	private LocalDate start;
	private LocalDate end;
	private int numberRooms;
	private int duration;
	private LocalDateTime bookingDate;
	private String status;
	private String reservationType;
	private Double price;
    String pattern="\u20ac###,###.##";
    DecimalFormat euroFormatter = new DecimalFormat(pattern);
	private String priceFormatted;
	
	
	// empty constructor
	public Reservation() { }
	
	// Constructor
	public Reservation(int reservationId, int GuestID, LocalDate start, LocalDate end, int numberRooms, LocalDateTime bookingDate, String status, String reservationType, double price) {
		this.reservationId = reservationId;
		this.GuestID = GuestID;
		this.start = start;
		this.end = end;
		this.numberRooms = numberRooms;
		this.bookingDate = bookingDate;
		this.status = status;
		this.reservationType = reservationType;
		this.price = price;
		this.priceFormatted = euroFormatter.format(price);
		
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
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString(){
		return this.getClass().getName() + " " + "[" + this.reservationId + " " + this.GuestID + " " + this.start + this.end + this.duration + this.numberRooms + "]";
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getReservationType() {
		return reservationType;
	}

	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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
