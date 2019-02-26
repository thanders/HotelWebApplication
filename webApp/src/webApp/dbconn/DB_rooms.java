package webApp.dbconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
					Double price = rs.getDouble(2);
					// Create instance of Room Class
					Room room = new Room(roomNumber);
					

					list.add(room);
				}
				
		        return list;
		   }
		   
		   // availableRooms
		   
		   // select * from booking WHERE begin <= startDate AND end > = endDate
		   // __ SELECT * FROM sse.Reservations WHERE start >= '2019-02-21' AND end <= '2019-02-26';
		   
		   // SELECT m.room_ID FROM Reservations AS r JOIN booking_map AS m ON b.id = m.id WHERE b.begin <= startDate AND b.end >= endDate
		   // SELECT b.roomNumber FROM sse.Reservations AS a INNER JOIN sse.Reserved_Rooms AS b ON a.Reservation_Id=b.reservationID WHERE a.start >= '2019-02-21' AND a.end <= '2019-02-26';
		   
		   // SELECT * FROM room as r WHERE r.ID NOT IN (SELECT m.room_id FROM booking as b JOIN booking_map AS m ON b.id = m.id WHERE b.begin <= startDate AND b.end >= endDate)
		   // SELECT r.Room_Number FROM sse.Room AS r WHERE r.Room_Number NOT IN(SELECT b.roomNumber FROM sse.Reservations AS a INNER JOIN sse.Reserved_Rooms AS b ON a.Reservation_Id=b.reservationID WHERE a.start >= '2019-02-21' AND a.end <= '2019-02-26');

		   
	    	// Select available Rooms
		   public static List<Room>selectAvailableRooms(Connection conn, LocalDate startDate, LocalDate endDate) throws SQLException {
		 
		        String sql = "SELECT r.Room_Number, r.capacity, r.price FROM sse.Room AS r WHERE r.Room_Number NOT IN(SELECT b.roomNumber FROM sse.Reservations AS a INNER JOIN sse.Reserved_Rooms AS b ON a.Reservation_Id=b.reservationID WHERE a.start >= ? AND a.end <= ?)";
		 
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
