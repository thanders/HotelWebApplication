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

public class DBUtils {
	
	   public static UserAccount finUser(Connection conn, //
	            String userName, String password) throws SQLException {
	 
	        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a " //
	                + " where a.User_Name = ? and a.password= ?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, userName);
	        pstm.setString(2, password);
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	            String gender = rs.getString("Gender");
	            UserAccount user = new UserAccount();
	            user.setUserName(userName);
	            user.setPassword(password);
	            user.setGender(gender);
	            return user;
	        }
	        return null;
	    }
	 
	   public static Starwood findStarwoodMember(Connection conn, //
	            String userName, String password) throws SQLException {
	 
	        String sql = "Select * from Starwood a " //
	                + " where a.User_Name = ? and a.User_Password= ?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, userName);
	        pstm.setString(2, password);
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	        	
	            String name = rs.getString("Member_Name");
	            String surname = rs.getString("Member_Surname");
	            String address = rs.getString("Address");
	            String email = rs.getString("Email_Address");
	            String cardNumber = Integer.toString(rs.getInt("Card_Number"));
	            String phoneNumber = Integer.toString(rs.getInt("Phone_Number"));	            
	            Starwood member = new Starwood();
	            member.setUserName(userName);
	            member.setPassword(password);
	            member.setName(name);
	            member.setAddress(address);
	            member.setCardNumber(cardNumber);
	            member.setSurename(surname);
	            member.setEmail(email);
	            member.setPhoneNumber(phoneNumber);          
	            
	            
	            
	            return member;
	        }
	        return null;
	    }
	   
	    public static UserAccount findUser(Connection conn, String userName) throws SQLException {
	 
	        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "//
	                + " where a.User_Name = ? ";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, userName);
	 
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	            String password = rs.getString("Password");
	            String gender = rs.getString("Gender");
	            UserAccount user = new UserAccount();
	            user.setUserName(userName);
	            user.setPassword(password);
	            user.setGender(gender);
	            return user;
	        }
	        return null;
	    }
	    
	    public static Starwood findStarwoodMember(Connection conn, String userName) throws SQLException {
	   	 
	        String sql = "Select *  from Starwood a "//
	                + " where a.User_Name = ? ";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, userName);
	 
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	            String password = rs.getString("User_Password");
	            String name = rs.getString("Member_Name");
	            String surname = rs.getString("Member_Surname");
	            String address = rs.getString("Address");
	            String email = rs.getString("Email_Address");
	            String cardNumber = Integer.toString(rs.getInt("Card_Number"));
	            String phoneNumber = Integer.toString(rs.getInt("Phone_Number"));
	            
	            Starwood member = new Starwood();
	            member.setUserName(userName);
	            member.setPassword(password);
	            
	            member.setName(name);
	            member.setAddress(address);
	            member.setCardNumber(cardNumber);
	            member.setSurename(surname);
	            member.setEmail(email);
	            member.setPhoneNumber(phoneNumber);    
	            
	           
	            return member;
	        }
	        return null;
	    }
	    	 
	    public static List<Product> queryProduct(Connection conn) throws SQLException {
	        String sql = "Select a.Code, a.Name, a.Price from Product a ";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        ResultSet rs = pstm.executeQuery();
	        List<Product> list = new ArrayList<Product>();
	        while (rs.next()) {
	            String code = rs.getString("Code");
	            String name = rs.getString("Name");
	            float price = rs.getFloat("Price");
	            Product product = new Product();
	            product.setCode(code);
	            product.setName(name);
	            product.setPrice(price);
	            list.add(product);
	        }
	        return list;
	    }
	    
	    
	    
	    // queryRoom
	    public static List<Room> queryRoom(Connection conn) throws SQLException {
	        String sql = "Select a.Room_Number, a.FK_Room_Type_ID from Room a order by Room_Number";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        ResultSet rs = pstm.executeQuery();
	        // Create an arraylist
	        List<Room> list = new ArrayList<Room>();
	        while (rs.next()) {
	            String Room_Number = rs.getString("Room_Number");
	            int FK_Room_Type_ID = rs.getInt("FK_Room_Type_ID");
	            // Create instance of Room class
	            System.out.println("Test "+Room_Number);
	            Room room = new Room(Room_Number);
	            // Add Room class to list
	            list.add(room);
	        }
	        return list;
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
	        
	        // Assign auto generated Guest key to variable and create reservation
	        if(rs != null && rs.next()){
	        	GuestID = rs.getInt(1);
	        }

	        return GuestID;
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
	 
	    
	    
	    // insertMember
	    public static void insertMember(Connection conn, Starwood member) throws SQLException {
	        String sql = "Insert into Starwood(Member_Name, Member_Surname, Address, Email_Address, Card_Number, Phone_Number, User_Name, User_Password) values (?,?,?,?,?,?,?,?)";
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, member.getName());
	        pstm.setString(2, member.getSurename());
	        pstm.setString(3, member.getAddress());
	        pstm.setString(4, member.getEmail());
	        pstm.setString(5, member.getCardNumber());
	        pstm.setString(6, member.getPhoneNumber());
	        pstm.setString(7, member.getUserName());
	        pstm.setString(8, member.getPassword());
	        pstm.executeUpdate();
	        System.out.println("insertMember SQL executed");
            System.out.println(": "+  member.getName()+ "  " + member.getSurename() + "  " + member.getAddress() + "  " + member.getPhoneNumber()+ "  " +  member.getCardNumber() + "  " +   member.getUserName()+ "  " +  member.getPassword());
	    }
	 
	    public static Product findProduct(Connection conn, String code) throws SQLException {
	        String sql = "Select a.Code, a.Name, a.Price from Product a where a.Code=?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, code);
	 
	        ResultSet rs = pstm.executeQuery();
	 
	        while (rs.next()) {
	            String name = rs.getString("Name");
	            float price = rs.getFloat("Price");
	            
	            Product product = new Product(code, name, price);
	            return product;
	        }
	        return null;
	    }
	 
	    public static void updateProduct(Connection conn, Product product) throws SQLException {
	        String sql = "Update Product set Name =?, Price=? where Code=? ";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        pstm.setString(1, product.getName());
	        pstm.setFloat(2, product.getPrice());
	        pstm.setString(3, product.getCode());
	        pstm.executeUpdate();
	    }
	 
	    public static void insertProduct(Connection conn, Product product) throws SQLException {
	        String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        pstm.setString(1, product.getCode());
	        pstm.setString(2, product.getName());
	        pstm.setFloat(3, product.getPrice());
	 
	        pstm.executeUpdate();
	    }
	 
	    public static void deleteProduct(Connection conn, String code) throws SQLException {
	        String sql = "Delete From Product where Code= ?";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        pstm.setString(1, code);
	 
	        pstm.executeUpdate();
	    }

}
