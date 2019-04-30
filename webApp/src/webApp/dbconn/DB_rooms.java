package webApp.dbconn;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
		  
		   
		    // insert a booked room
		    public static void insertBookedRoom(Connection conn, String roomNumber, BigInteger reservationID) throws SQLException {
		        String sql = "Insert into Reserved_Rooms(roomNumber, reservationID ) values (?, ?)";
		        
		        PreparedStatement pstm = conn.prepareStatement(sql);
		   	 
		        pstm.setString(1, roomNumber);
		        pstm.setString(2, reservationID.toString());
		 
		        pstm.executeUpdate();
 
		    }
		    
	    	// Select booked rooms by reservationID
		   public static List<Room> selectBookedRooms(Connection conn, BigInteger reservationID) throws SQLException {
		 
		        String sql = "SELECT roomNumber, reservationID FROM Reserved_Rooms WHERE reservationID = ?";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setString(1, reservationID.toString());
		        
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
		   
		   
	    	// Select available Rooms
		   public static List<Room>selectAvailableRooms(Connection conn, LocalDate startDate, LocalDate endDate) throws SQLException {
		        String sql = "SELECT r.Room_Number, r.capacity, r.price FROM sse.Room AS r WHERE r.Room_Number NOT IN(SELECT b.roomNumber FROM sse.Reserved_Rooms AS b JOIN sse.Reservations AS a ON a.Reservation_Id=b.reservationID WHERE a.start >= ? OR a.end <= ? AND a.status='Active')";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setObject(1, startDate);
		        pstm.setObject(2, endDate);
		        
		        ResultSet rs = pstm.executeQuery();
		        
		        List<Room> list = new ArrayList<Room>();
				
		        // iterate through each record and add to arraylist
		        while (rs.next()) {
		        	
					String roomNumber = rs.getString(1);
					int roomCapacity = rs.getInt(2);
					int roomPrice = rs.getInt(3);
					// Create instance of Room Class
					Room room = new Room(roomNumber);
					room.setCapacity(roomCapacity);
					// Format currency as string
					room.setPrice(roomPrice);
					
					
					list.add(room);
				}
				
		        return list;
		   }
		   
	    	// Select price by room number
		   public static Double selectRoomPrice(Connection conn, String roomNumber) throws SQLException {
		 
		        String sql = "SELECT Room_Number, price FROM Room WHERE Room_Number = ?";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setString(1, roomNumber);
		        
		        ResultSet rs = pstm.executeQuery();
		        
		        if (rs.next()) {
					Double price = rs.getDouble(2);
					Room room = new Room(roomNumber);
					room.setPrice(price);
	
					return room.getPrice();
				}
				
		        return null;
		   }
		   
}
