package webApp.dbconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import webApp.beans.Members;
import webApp.beans.Starwood;

public class DB_members{ 
		    
		    
		    // insertMember
		    public static void insertMember(Connection conn, Starwood member) throws SQLException {
		        String sql = "Insert into Starwood(Member_Name, Member_Surname, Address, Email_Address, Card_Number, Phone_Number, User_Name,CVV,ExpiryDate) values (?,?,?,?,?,?,?,?,?)";
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setString(1, member.getName());
		        pstm.setString(2, member.getSurename());
		        pstm.setString(3, member.getAddress());
		        pstm.setString(4, member.getEmail());
		        pstm.setString(5, member.getCardNumber());
		        pstm.setInt(6, member.getPhoneNumber());
		        pstm.setString(7, member.getUserName());
		        pstm.setInt(8, member.getCVV());
		        pstm.setObject(9, member.getExpiryDate());
		        pstm.executeUpdate();
		    }
		    
		    
		    public static Starwood findStarwoodMember(Connection conn, int id) throws SQLException {
			   	 
		        String sql = "Select *  from Starwood a "//
		                + " where a.id = ? ";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setInt(1, id);
		 
		        ResultSet rs = pstm.executeQuery();
		 
		        if (rs.next()) {
		            String userName = rs.getString("User_Name");
		            String name = rs.getString("Member_Name");
		            String surname = rs.getString("Member_Surname");
		            String address = rs.getString("Address");
		            String email = rs.getString("Email_Address");
		            String cardNumber = rs.getString("Card_Number");
		            String phoneNumber = Integer.toString(rs.getInt("Phone_Number"));
		            int PhoneNumber = Integer.parseInt(phoneNumber);
		            Starwood member = new Starwood();
		            member.setUserName(userName);
		            
		            member.setName(name);
		            member.setAddress(address);
		            member.setCardNumber(cardNumber);
		            member.setSurename(surname);
		            member.setEmail(email);
		            member.setPhoneNumber(PhoneNumber);    
		            
		           
		            return member;
		        }
		        return null;
		    }
		    
		    //Method returns -1 if it doesn't find member with given username
		    public static int getStarwoodMemberId(Connection conn, String userName) throws SQLException {
		    	
		    	int id = -1;
		    	
		    	String sql = "Select Id  from Starwood a "//
		                + " where a.User_name = ? ";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setString(1, userName);
		 
		        ResultSet rs = pstm.executeQuery();
		        
		        if (rs.next()) {
		        	id  = rs.getInt("Id");
		        }
		    	
		    	return id;
		    }
		    // insertMember
		    public static void insertMemberLogIn(Connection conn, Members member) throws SQLException {
		        String sql = "Insert into Members(User_Name, User_Password) values (?,?)";
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setString(1, member.getUserName());
		        pstm.setString(2, member.getPassword());
		        pstm.executeUpdate();
		    }

}
