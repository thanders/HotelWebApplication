package webApp.dbconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import webApp.beans.Reservation;

public class DB_reservation {

	    // Query ReservationRID
	    public static Reservation queryReservationRID(Connection conn, int Reservation_Id) throws SQLException {
	        String sql = "Select a.Reservation_Id, a.GuestID, a.start, a.end, a.duration, a.numberRooms, a.bookingDate, a.status, a.reservationType, a.price from Reservations a WHERE a.Reservation_Id = ? "
	        		+ "and a.reservationType = 'Guest' ";
	 
	        // Connect to the database and execute the Select query
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, Reservation_Id);
	        ResultSet rs = pstm.executeQuery();
	        
	        // Get values from the query and assign to variables
	        if (rs.next()) {
		        int GuestID= rs.getInt("GuestID");
		        LocalDate start= rs.getDate("start").toLocalDate();
		        LocalDate end= rs.getDate("end").toLocalDate();
		        int numberRooms= rs.getInt("numberRooms");
		        LocalDateTime bookingDate= rs.getTimestamp("bookingDate").toLocalDateTime();
		        String status = rs.getString("status");
		        String reservationType = rs.getString("reservationType");
		        double price= rs.getDouble("price");
		        
		        System.out.println("Reservation: "+ Reservation_Id +" " + GuestID +" " + start +" " + end +" " + numberRooms +" " + bookingDate + " " + status + " " +price);
		        // Create an instance of the Reservation class
		        Reservation reservation = new Reservation(Reservation_Id, GuestID, start, end, numberRooms, bookingDate, status, reservationType, price);
		        
		        return reservation;
	        }
	        
	        return null;
	            
	        
	    }
	    
	    	// queryReservation with Guest ID
		   public static Reservation queryReservation(Connection conn, int GuestID) throws SQLException {
		 
		        String sql = "Select a.Reservation_Id, a.GuestID, a.start, a.end, a.numberRooms, a.bookingDate, a.status, a.reservationType, a.price from Reservations a where a.GuestID = ?";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setInt(1, GuestID);
		        ResultSet rs = pstm.executeQuery();
		        // There should only be one GuestID with a reservation
		        if (rs.next()) {
		            int Reservation_Id = rs.getInt("Reservation_Id");
			        LocalDate start= rs.getDate("start").toLocalDate();
			        LocalDate end= rs.getDate("end").toLocalDate();
			        int numberRooms = rs.getInt("numberRooms");
			        LocalDateTime bookingDate= rs.getTimestamp("bookingDate").toLocalDateTime();
			        String status = rs.getString("status");
			        String reservationType = rs.getString("reservationType");
			        Double price = rs.getDouble("price");
		            Reservation reservation = new Reservation(Reservation_Id, GuestID, start, end, numberRooms, bookingDate, status, reservationType, price);
		            reservation.setPrice(price);

		            return reservation;
		        }
		        
		        return null;
		        }
		   
		// queryReservation with Guest ID
		   public static Reservation queryReservation(Connection conn, int GuestID, LocalDate start, LocalDate end, int numberRooms, String status, String reservationType, Double price) throws SQLException {
		 
		        String sql = "Select a.Reservation_Id, a.GuestID, a.start, a.end, a.numberRooms, a.bookingDate, a.status, a.reservationType, a.price from Reservations a "
		        		+ "where a.GuestID = ? and a.start = ? and a.end = ? and a.numberRooms = ? and a.status = ?"
		        		+ "and a.reservationType = ? and a.price = ?";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setInt(1, GuestID);
		        pstm.setObject(2, start);
		        pstm.setObject(3, end);
		        pstm.setInt(4, numberRooms);
		        pstm.setString(5, status);
		        pstm.setString(6, reservationType);
		        pstm.setDouble(7, price);
		        ResultSet rs = pstm.executeQuery();


		        if (rs.next()) {
		            int Reservation_Id = rs.getInt("Reservation_Id");
			        LocalDateTime bookingDate= rs.getTimestamp("bookingDate").toLocalDateTime();			       
			        Reservation reservation = new Reservation(Reservation_Id, GuestID, start, end, numberRooms, bookingDate, status, reservationType, price);
		            reservation.setPrice(price);

		            return reservation;
		        }
		        
		        return null;
		        }
	    
		   
		// Query Reservations for a given member with Guest ID
		   public static List<Reservation> queryReservations(Connection conn, int GuestID, String reservationType) throws SQLException {
		 
		        String sql = "Select a.Reservation_Id, a.GuestID, a.start, a.end, a.numberRooms, a.bookingDate, a.status, a.reservationType, a.price from Reservations a where a.GuestID = ? and a.reservationType = ? ";
		        List<Reservation> reservations = new ArrayList<>();
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setInt(1, GuestID);
		        pstm.setString(2, reservationType);
		        ResultSet rs = pstm.executeQuery();
		        // There should only be one GuestID with a reservation
		        while (rs.next()) {
		            int Reservation_Id = rs.getInt("Reservation_Id");
			        LocalDate start= rs.getDate("start").toLocalDate();
			        LocalDate end= rs.getDate("end").toLocalDate();
			        int numberRooms = rs.getInt("numberRooms");
			        LocalDateTime bookingDate= rs.getTimestamp("bookingDate").toLocalDateTime();
			        String status = rs.getString("status");
			        //String reservationType = rs.getString("reservationType");
			        Double price = rs.getDouble("price");
			        Reservation reservation = new Reservation(Reservation_Id, GuestID, start, end, numberRooms, bookingDate, status, reservationType, price);
		            reservation.setPrice(price);

		            reservations.add(reservation);
		        }
		        
		        return reservations;
		        }
	    
	    // insert Reservation
	    public static void insertReservation(Connection conn, int GuestID, LocalDate start, LocalDate end, int numberRooms, String status, String reservationType, Double price) throws SQLException {
	        String sql = "Insert into Reservations(GuestID, start, end, numberRooms, status, reservationType, price) values (?, ?, ?, ?, ?, ?, ?)";
	        
	        PreparedStatement pstm = conn.prepareStatement(sql);
	   	 
	        pstm.setInt(1, GuestID);
	        pstm.setObject(2, start);
	        pstm.setObject(3, end);
	        pstm.setInt(4, numberRooms);
	        pstm.setString(5, status);
	        pstm.setString(6, reservationType);
	        pstm.setDouble(7, price);
	 
	        pstm.executeUpdate();

	        System.out.println("insertReservation SQL�executed");
	        
	    }
	    
	    
    	// queryReservations
	   public static List<Reservation> queryAllReservations(Connection conn) throws SQLException {
	 
	        String sql = "Select Reservation_Id, GuestID, start, end, numberRooms, bookingDate, status, reservationType, price from Reservations";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);

	        ResultSet rs = pstm.executeQuery();
	        // There should only be one GuestID with a reservation
	        
	        List<Reservation> list = new ArrayList<Reservation>();
	        while (rs.next()) {
	            int Reservation_Id = rs.getInt("Reservation_Id");
	            int GuestID = rs.getInt("GuestID");
		        LocalDate start= rs.getDate("start").toLocalDate();
		        LocalDate end= rs.getDate("end").toLocalDate();
		        int numberRooms = rs.getInt("numberRooms");
		        LocalDateTime bookingDate= rs.getTimestamp("bookingDate").toLocalDateTime();
		        String status = rs.getString("status");
		        String reservationType = rs.getString("reservationType");
		        Double price = rs.getDouble("price");
		        
	            Reservation reservation = new Reservation(Reservation_Id, GuestID, start, end, numberRooms, bookingDate, status, reservationType, price);
	            
	            list.add(reservation);

	        }
	        
	        return list;
	        }
	   
	   
	    // insert Reservation
	    public static void updateReservationPrice(Connection conn, int reservationID, Double price) throws SQLException {
	        String sql = "UPDATE Reservations SET price = ? where Reservation_ID = ?";
	        
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setDouble(1, price);
	        pstm.setInt(2, reservationID);

	        pstm.executeUpdate();

	        System.out.println("insertReservation PRICE SQL executed");
	        
	    }
	    
	    // insert Reservation
	    public static void updateReservationStatus(Connection conn, int reservationID) throws SQLException {
	        String sql = "UPDATE Reservations SET status = 'cancelled' where Reservation_Id = ?";
	        
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, reservationID);

	        pstm.executeUpdate();

	        System.out.println("insertReservation Status SQL executed");
	        
	    }

}
