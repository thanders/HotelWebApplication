package webApp.dbconn;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import webApp.beans.Reservation;
import webApp.beans.Starwood;

public class DB_reservation {

	    // Query ReservationRID
	    public static Reservation queryReservationRID(Connection conn, BigInteger Reservation_Id) throws SQLException {
	        String sql = "Select a.Reservation_Id, a.GuestID, a.start, a.end, a.duration, a.numberRooms, a.bookingDate, a.status, a.reservationType, a.price from Reservations a WHERE a.Reservation_Id = ? "
	        		+ "and a.reservationType = 'Guest' ";
	 
	        // Connect to the database and execute the Select query
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, Reservation_Id.toString());
	        ResultSet rs = pstm.executeQuery();
	        
	        // Get values from the query and assign to variables
	        if (rs.next()) {
	        	BigInteger GuestID= BigInteger.valueOf(rs.getLong("GuestID"));
		        LocalDate start= rs.getDate("start").toLocalDate();
		        LocalDate end= rs.getDate("end").toLocalDate();
		        int numberRooms= rs.getInt("numberRooms");
		        LocalDateTime bookingDate= rs.getTimestamp("bookingDate").toLocalDateTime();
		        String status = rs.getString("status");
		        String reservationType = rs.getString("reservationType");
		        double price= rs.getDouble("price");
		        
		        // Create an instance of the Reservation class
		        Reservation reservation = new Reservation(Reservation_Id, GuestID, start, end, numberRooms, bookingDate, status, reservationType, price);
		        
		        return reservation;
	        }
	        
	        return null;
	            
	        
	    }
	    
	    	// queryReservation with Guest ID
		   public static Reservation queryReservation(Connection conn, BigInteger GuestID) throws SQLException {
		 
		        String sql = "Select a.Reservation_Id, a.GuestID, a.start, a.end, a.numberRooms, a.bookingDate, a.status, a.reservationType, a.price from Reservations a where a.GuestID = ?";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setString(1, GuestID.toString());
		        ResultSet rs = pstm.executeQuery();
		        // There should only be one GuestID with a reservation
		        if (rs.next()) {
		        	BigInteger Reservation_Id = BigInteger.valueOf(rs.getLong("Reservation_Id"));
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
		   public static Reservation queryReservation(Connection conn, BigInteger GuestID, LocalDate start, LocalDate end, int numberRooms, String status, String reservationType, Double price) throws SQLException {
		 
		        String sql = "Select a.Reservation_Id, a.GuestID, a.start, a.end, a.numberRooms, a.bookingDate, a.status, a.reservationType, a.price from Reservations a "
		        		+ "where a.GuestID = ? and a.start = ? and a.end = ? and a.numberRooms = ? and a.status = ?"
		        		+ "and a.reservationType = ? and a.price = ?";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setString(1, GuestID.toString());
		        pstm.setObject(2, start);
		        pstm.setObject(3, end);
		        pstm.setInt(4, numberRooms);
		        pstm.setString(5, status);
		        pstm.setString(6, reservationType);
		        pstm.setDouble(7, price);
		        ResultSet rs = pstm.executeQuery();


		        if (rs.next()) {
		        	BigInteger Reservation_Id = BigInteger.valueOf(rs.getLong("Reservation_Id"));
			        LocalDateTime bookingDate= rs.getTimestamp("bookingDate").toLocalDateTime();			       
			        Reservation reservation = new Reservation(Reservation_Id, GuestID, start, end, numberRooms, bookingDate, status, reservationType, price);
		            reservation.setPrice(price);

		            return reservation;
		        }
		        
		        return null;
		        }
	    
		   
		// Query Reservations for a given member with Guest ID
		   public static List<Reservation> queryReservations(Connection conn, BigInteger GuestID, String reservationType) throws SQLException {
		 
		        String sql = "Select a.Reservation_Id, a.GuestID, a.start, a.end, a.numberRooms, a.bookingDate, a.status, a.reservationType, a.price from Reservations a where a.GuestID = ? and a.reservationType = ? ";
		        List<Reservation> reservations = new ArrayList<>();
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setString(1, GuestID.toString());
		        pstm.setString(2, reservationType);
		        ResultSet rs = pstm.executeQuery();
		        // There should only be one GuestID with a reservation
		        while (rs.next()) {
		        	BigInteger Reservation_Id = BigInteger.valueOf(rs.getLong("Reservation_Id"));
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
	    public static void insertReservation(Connection conn, BigInteger reservationID, BigInteger GuestID, LocalDate start, LocalDate end, int numberRooms, String status, String reservationType, Double price) throws SQLException {
	        String sql = "Insert into Reservations(reservation_Id, GuestID, start, end, numberRooms, status, reservationType, price) values (?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, reservationID.toString());
	        pstm.setString(2, GuestID.toString());
	        pstm.setObject(3, start);
	        pstm.setObject(4, end);
	        pstm.setInt(5, numberRooms);
	        pstm.setString(6, status);
	        pstm.setString(7, reservationType);
	        pstm.setDouble(8, price);
	        pstm.executeUpdate();
	    }
	    
	    
    	// queryReservations
	   public static List<Reservation> queryAllReservations(Connection conn) throws SQLException {
	 
	        String sql = "Select Reservation_Id, GuestID, start, end, numberRooms, bookingDate, status, reservationType, price from Reservations";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);

	        ResultSet rs = pstm.executeQuery();
	        // There should only be one GuestID with a reservation
	        
	        List<Reservation> list = new ArrayList<Reservation>();
	        while (rs.next()) {
	        	BigInteger Reservation_Id = BigInteger.valueOf(rs.getLong("Reservation_Id"));
	        	BigInteger GuestID = BigInteger.valueOf(rs.getLong("GuestID"));
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
	
	    
	    public static void updateReservationStatus(Connection conn, BigInteger reservationID) throws SQLException {
	        String sql = "Update Reservations set status =? where Reservation_Id=? ";
	        
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, "Cancelled");
	        pstm.setString(2, reservationID.toString());
	        pstm.executeUpdate();

	    }
	   
		public static void deleteBookings(Connection conn, BigInteger reservationID) throws SQLException {

			reservationID.toString();

			String sql = "Delete From Reserved_Rooms where reservationID= ?";

			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setString(1, reservationID.toString());
			
			pstm.executeUpdate();

		}

}
