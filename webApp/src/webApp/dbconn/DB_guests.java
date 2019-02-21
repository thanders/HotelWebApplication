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

public class DB_guests {
	    
	    
	    // QueryGuest
	    public static Guest QueryGuest(Connection conn, int GuestID) throws SQLException {
	        String sql = "Select a.Guest_Name, a.Guest_Surname, a.Address, a.Card_Number, a.Phone_Number, a.Email_Address from Guest a WHERE Id = ?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, GuestID);
	        ResultSet rs = pstm.executeQuery();
	        
	        // Each Guest ID is unique (Guest are not registered so can appear more than once)
	        if (rs.next()) {
	            String Guest_Name = rs.getString("Guest_Name");
	            String Guest_Surname = rs.getString("Guest_Surname");
	            String Address = rs.getString("Address");
	            int Card_Number = rs.getInt("Card_Number");
	            int Phone_Number = rs.getInt("Phone_Number");
	            String Email_Address = rs.getString("Email_Address");
	            
	            Guest guest = new Guest (Guest_Name, Guest_Surname, Address, Email_Address, Card_Number, Phone_Number);
	            guest.setGuestID(GuestID);
	            
	            System.out.println("Guests:  " + guest.toString());
	            return guest;
	        }
	        System.out.println("Something is wrong");
	        return null;
	    }
	    
	    // insertGuest, obtain auto generated GuestID key and create Reservation
	    public static int insertGuest(Connection conn, Guest guest) throws SQLException {
	        String sql = "Insert into Guest(Guest_Name, Guest_Surname, Address, Email_Address, Card_Number, Phone_Number) values (?,?,?,?,?,?)";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	 
	        pstm.setString(1, guest.getGuestName());
	        pstm.setString(2, guest.getGuestSurename());
	        pstm.setString(3, guest.getGuestAddress());
	        pstm.setString(4, guest.getGuestEmail());
	        pstm.setInt(5, guest.getGuestCardNumber());
	        pstm.setInt(6, guest.getGuestPhoneNumber());
	        
	        pstm.executeUpdate();
	        
	        ResultSet rs = pstm.getGeneratedKeys();
	        
	        int GuestID =0;
	        
	        // Assign auto generatReservationed Guest key to variable and create reservation
	        if(rs != null && rs.next()){
	        	GuestID = rs.getInt(1);
	        }

	        return GuestID;
	    }

}
