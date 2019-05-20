package org.dbconn;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.beans.AdHoc;
import org.beans.Starwood;

public class DB_members{ 
		    
		    
		    // insertMember
		    public static String insertMember(Connection conn, Starwood member) throws SQLException {
		        String output = null;
		    	BigInteger existingMember = getStarwoodMemberId(conn,member.getUserName());
		    	
		    	//If statement represents the case when the username is take, set member to null and return
		    	if(!existingMember.toString().equals(BigInteger.valueOf(-1).toString())) {
		    		System.out.println("hereyaah");
		    		output = "Username taken, please chose different username";
		    	}
		    	else {
		    		String sql = "Insert into Starwood(Id,Member_Name, Member_Surname, Address, Email_Address, Card_Number, Phone_Number, User_Name,CVV,ExpiryDate) values (?,?,?,?,?,?,?,?,?,?)";
			        AdHoc adHoc = new AdHoc();
			        PreparedStatement pstm = conn.prepareStatement(sql);
			        pstm.setString(1, adHoc.randomBigInteger().toString());
			        pstm.setString(2, member.getName());
			        pstm.setString(3, member.getSurename());
			        pstm.setString(4, member.getAddress());
			        pstm.setString(5, member.getEmail());
			        pstm.setString(6, member.getCardNumber());
			        pstm.setInt(7, member.getPhoneNumber());
			        pstm.setString(8, member.getUserName());
			        pstm.setInt(9, member.getCVV());
			        pstm.setObject(10, member.getExpiryDate());
			        pstm.executeUpdate();
		    	}
		    	
		    	
		        
		        return output;
		    }
		    
		    
		    public static Starwood findStarwoodMember(Connection conn, BigInteger id) throws SQLException {
			   	 
		        String sql = "Select *  from Starwood a "//
		                + " where a.id = ? ";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setString(1, id.toString());
		 
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
		    public static BigInteger getStarwoodMemberId(Connection conn, String userName) throws SQLException {
		    	
		    	BigInteger id = BigInteger.valueOf(-1);
		    	
		    	String sql = "Select Id  from Starwood a "//
		                + " where a.User_name = ? ";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setString(1, userName);
		 
		        ResultSet rs = pstm.executeQuery();
		        
		        if (rs.next()) {
		        	id  = BigInteger.valueOf(rs.getLong("Id"));
		        }
		    	
		    	return id;
		    }
		    // insertMember
		    public static void insertMemberLogIn(Connection conn, Starwood member) throws SQLException {
		        String sql = "Insert into Members(User_Name, User_Password) values (?,?)";
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setString(1, member.getUserName());
		        pstm.setString(2, member.getPassword());
		        pstm.executeUpdate();
		    }

}
