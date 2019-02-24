package webApp.dbconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import webApp.beans.Reservation;
import webApp.beans.Room;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_rooms {
	    
	    	// Count total number of rooms in Hotel
		   public static int countTotalRooms(Connection conn) throws SQLException {
		 
		        String sql = "SELECT COUNT(*) FROM Room";
		 
		        Statement smt=conn.createStatement();
		  
		        ResultSet rs = smt.executeQuery(sql);
				
				if (rs.next()) {
					int count = rs.getInt(1);
					
					// conn.close();
					
					return count;
				}
				
				return 0;
		   }
		   
		   
	    	// Select all rooms
		   public static List<Room> selectRooms(Connection conn) throws SQLException {
		 
		        String sql = "SELECT Room_Number FROM Room";
		 
		        Statement smt=conn.createStatement();
		  
		        ResultSet rs = smt.executeQuery(sql);
		        
		        List<Room> list = new ArrayList<Room>();
				
		        // iterate through each record and add to arraylist
		        while (rs.next()) {
		        	
					String roomNumber = rs.getString(1);
					// Create instance of Room Class
					Room room = new Room(roomNumber);

					list.add(room);
				}
				
		        return list;
		   }
		   
		    // insert a booked room
		    public static void insertBookedRoom(Connection conn, String roomNumber, int reservationID) throws SQLException {
		        String sql = "Insert into Reserved_Rooms(roomNumber, reservationID ) values (?, ?)";
		        
		        PreparedStatement pstm = conn.prepareStatement(sql);
		   	 
		        pstm.setString(1, roomNumber);
		        pstm.setInt(2, reservationID);
		 
		        pstm.executeUpdate();

		        System.out.println("Room inserted into Booked_Rooms table");
		        
		    }
		    
	    	// Select booked rooms by reservationID
		   public static List<Room> selectBookedRooms(Connection conn, int reservationID) throws SQLException {
		 
		        String sql = "SELECT roomNumber, reservationID FROM Reserved_Rooms WHERE reservationID = ?";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setInt(1, reservationID);
		        
		        ResultSet rs = pstm.executeQuery();
		        
		        List<Room> list = new ArrayList<Room>();
				
		        // iterate through each record and add to arraylist
		        while (rs.next()) {
		        	
					String roomNumber = rs.getString(1);
					// Create instance of Room Class
					Room room = new Room(roomNumber);

					list.add(room);
				}
				
		        return list;
		   }

}
