package webApp.dbconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import webApp.beans.*;

public class DB_reservation {

	    // Query ReservationRID
	    public static Reservation queryReservationRID(Connection conn, int Reservation_Id) throws SQLException {
	        String sql = "Select a.Reservation_Id, a.GuestID, a.start, a.end, a.duration, a.numberRooms from Reservations a WHERE a.Reservation_Id = ? ";
	 
	        // Connect to the database and execute the Select query
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, Reservation_Id);
	        ResultSet rs = pstm.executeQuery();
	        
	        // Get values from the query and assign to variables
	        if (rs.next()) {
		        int GuestID= rs.getInt("GuestID");
		        LocalDate start= rs.getDate("start").toLocalDate();
		        LocalDate end= rs.getDate("end").toLocalDate();
		        int duration= rs.getInt("duration");
		        int numberRooms= rs.getInt("numberRooms");
		        
		        System.out.println("Reservation: "+ Reservation_Id +" " + GuestID +" " + start +" " + end +" " + numberRooms);
		        // Create an instance of the Reservation class
		        Reservation reservation = new Reservation(Reservation_Id, GuestID, start, end, duration, numberRooms);
		        
		        return reservation;
	        }
	        
	        return null;
	            
	        
	    }
	    
	    	// queryReservation with Guest ID
		   public static Reservation queryReservation(Connection conn, int GuestID) throws SQLException {
		 
		        String sql = "Select a.Reservation_Id, a.GuestID, a.start, a.end, a.duration, a.numberRooms from Reservations a where a.GuestID = ?";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setInt(1, GuestID);
		        ResultSet rs = pstm.executeQuery();
		        // There should only be one GuestID with a reservation
		        if (rs.next()) {
		            int Reservation_Id = rs.getInt("Reservation_Id");
			        LocalDate start= rs.getDate("start").toLocalDate();
			        LocalDate end= rs.getDate("end").toLocalDate();
			        int duration = rs.getInt("duration");
			        int numberRooms = rs.getInt("numberRooms");
			        
		            Reservation reservation = new Reservation(Reservation_Id, GuestID, start, end, duration, numberRooms );


		            return reservation;
		        }
		        
		        return null;
		        }
	    
	    
	    // insert Reservation
	    public static void insertReservation(Connection conn, int GuestID, LocalDate start, LocalDate end, int numberRooms) throws SQLException {
	        String sql = "Insert into Reservations(GuestID, start, end, numberRooms) values (?, ?, ?, ?)";
	        
	        PreparedStatement pstm = conn.prepareStatement(sql);
	   	 
	        pstm.setInt(1, GuestID);
	        pstm.setObject(2, start);
	        pstm.setObject(3, end);
	        pstm.setInt(4, numberRooms);
	 
	        pstm.executeUpdate();

	        System.out.println("insertReservation SQL executed");
	        
	    }

}
