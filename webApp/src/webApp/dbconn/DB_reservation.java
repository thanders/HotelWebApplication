package webApp.dbconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import webApp.beans.Reservation;

public class DB_reservation {

	    // Query ReservationRID
	    public static Reservation queryReservationRID(Connection conn, int Reservation_Id) throws SQLException {
	        String sql = "Select a.Reservation_Id, a.GuestID, a.start, a.end, a.duration, a.numberRooms, a.bookingDate, a.status, a.reservationType from Reservations a WHERE a.Reservation_Id = ? ";
	 
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
		        LocalDate bookingDate= rs.getDate("bookingDate").toLocalDate();
		        String status = rs.getString("status");
		        String reservationType = rs.getString("reservationType");
		        
		        System.out.println("Reservation: "+ Reservation_Id +" " + GuestID +" " + start +" " + end +" " + numberRooms +" " + bookingDate + " " + status);
		        // Create an instance of the Reservation class
		        Reservation reservation = new Reservation(Reservation_Id, GuestID, start, end, numberRooms, bookingDate, status, reservationType);
		        
		        return reservation;
	        }
	        
	        return null;
	            
	        
	    }
	    
	    	// queryReservation with Guest ID
		   public static Reservation queryReservation(Connection conn, int GuestID) throws SQLException {
		 
		        String sql = "Select a.Reservation_Id, a.GuestID, a.start, a.end, a.numberRooms, a.bookingDate, a.status, a.reservationType from Reservations a where a.GuestID = ?";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setInt(1, GuestID);
		        ResultSet rs = pstm.executeQuery();
		        // There should only be one GuestID with a reservation
		        if (rs.next()) {
		            int Reservation_Id = rs.getInt("Reservation_Id");
			        LocalDate start= rs.getDate("start").toLocalDate();
			        LocalDate end= rs.getDate("end").toLocalDate();
			        int numberRooms = rs.getInt("numberRooms");
			        LocalDate bookingDate= rs.getDate("bookingDate").toLocalDate();
			        String status = rs.getString("status");
			        String reservationType = rs.getString("reservationType");
			        
		            Reservation reservation = new Reservation(Reservation_Id, GuestID, start, end, numberRooms, bookingDate, status, reservationType);


		            return reservation;
		        }
		        
		        return null;
		        }
	    
	    
	    // insert Reservation
	    public static void insertReservation(Connection conn, int GuestID, LocalDate start, LocalDate end, int numberRooms, String status, String reservationType) throws SQLException {
	        String sql = "Insert into Reservations(GuestID, start, end, numberRooms, status, reservationType ) values (?, ?, ?, ?, ?, ?)";
	        
	        PreparedStatement pstm = conn.prepareStatement(sql);
	   	 
	        pstm.setInt(1, GuestID);
	        pstm.setObject(2, start);
	        pstm.setObject(3, end);
	        pstm.setInt(4, numberRooms);
	        pstm.setString(5, status);
	        pstm.setString(6, reservationType);
	 
	        pstm.executeUpdate();

	        System.out.println("insertReservation SQL executed");
	        
	    }
	    
	    
    	// queryReservations
	   public static List<Reservation> queryAllReservations(Connection conn) throws SQLException {
	 
	        String sql = "Select Reservation_Id, GuestID, start, end, numberRooms, bookingDate, status, reservationType from Reservations";
	 
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
		        LocalDate bookingDate= rs.getDate("bookingDate").toLocalDate();
		        String status = rs.getString("status");
		        String reservationType = rs.getString("reservationType");
		        
	            Reservation reservation = new Reservation(Reservation_Id, GuestID, start, end, numberRooms, bookingDate, status, reservationType);
	            
	            list.add(reservation);

	        }
	        
	        return list;
	        }

}
