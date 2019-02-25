package webApp.dbconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import webApp.beans.Starwood;

public class DB_members{ 
		    
		    
		    // insertMember
		    public static void insertMember(Connection conn, Starwood member) throws SQLException {
		        String sql = "Insert into Starwood(Member_Name, Member_Surname, Address, Email_Address, Card_Number, Phone_Number, User_Name, User_Password) values (?,?,?,?,?,?,?,?)";
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setString(1, member.getName());
		        pstm.setString(2, member.getSurename());
		        pstm.setString(3, member.getAddress());
		        pstm.setString(4, member.getEmail());
		        pstm.setInt(5, member.getCardNumber());
		        pstm.setInt(6, member.getPhoneNumber());
		        pstm.setString(7, member.getUserName());
		        pstm.setString(8, member.getPassword());
		        pstm.executeUpdate();
		        System.out.println("insertMember SQL executed");
	            System.out.println(": "+  member.getName()+ "  " + member.getSurename() + "  " + member.getAddress() + "  " + member.getPhoneNumber()+ "  " +  member.getCardNumber() + "  " +   member.getUserName()+ "  " +  member.getPassword());
		    }
		    
		    
		    public static Starwood findStarwoodMember(Connection conn, int id) throws SQLException {
			   	 
		        String sql = "Select *  from Starwood a "//
		                + " where a.id = ? ";
		 
		        PreparedStatement pstm = conn.prepareStatement(sql);
		        pstm.setInt(1, id);
		 
		        ResultSet rs = pstm.executeQuery();
		 
		        if (rs.next()) {
		            String userName = rs.getString("User_Name");
		            String password = rs.getString("User_Password");
		            String name = rs.getString("Member_Name");
		            String surname = rs.getString("Member_Surname");
		            String address = rs.getString("Address");
		            String email = rs.getString("Email_Address");
		            String cardNumber = Integer.toString(rs.getInt("Card_Number"));
		            String phoneNumber = Integer.toString(rs.getInt("Phone_Number"));
		            int CardNumber = Integer.parseInt(cardNumber);
		            int PhoneNumber = Integer.parseInt(phoneNumber);
		            Starwood member = new Starwood();
		            member.setUserName(userName);
		            member.setPassword(password);
		            
		            member.setName(name);
		            member.setAddress(address);
		            member.setCardNumber(CardNumber);
		            member.setSurename(surname);
		            member.setEmail(email);
		            member.setPhoneNumber(PhoneNumber);    
		            
		           
		            return member;
		        }
		        return null;
		    }

}
