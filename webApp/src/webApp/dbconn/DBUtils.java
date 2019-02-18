package webApp.dbconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webApp.beans.*;

public class DBUtils {
	
	   public static UserAccount findUser(Connection conn, //
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
	    // Query Reservation
	    public static List<Reservation> queryReservations(Connection conn) throws SQLException {
	        String sql = "Select a.Reservation_Id, a.Reserved_By from Reservations a ";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        ResultSet rs = pstm.executeQuery();
	        List<Reservation> list = new ArrayList<Reservation>();
	        while (rs.next()) {
	            int Reservation_Id = rs.getInt("Reservation_Id");
	            String Reserved_By= rs.getString("Reserved_By");
	            
	            System.out.println("Reservation: "+ Reservation_Id + Reserved_By);
	            Reservation reservation = new Reservation();
	            reservation.setReservationId(Reservation_Id);
	            reservation.setReservedBy(Reserved_By);
	            list.add(reservation);
	            
	            System.out.println(list.toString());
	        }
	        return list;
	    }
	    
	    // QueryLatestGuest
	    public static List<Guest> QueryLatestGuest(Connection conn) throws SQLException {
	        String sql = "Select a.Guest_Name, a.Guest_Surname, a.Address, a.Card_Number, a.Phone_Number, a.Email_Address from Guest a ORDER BY Id DESC LIMIT 1";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        ResultSet rs = pstm.executeQuery();
	        List<Guest> list = new ArrayList<Guest>();
	        while (rs.next()) {
	            String guestName = rs.getString("Guest_Name");
	            String Guest_Surname= rs.getString("Guest_Surname");
	            String Address= rs.getString("Address");
	            int Card_Number= rs.getInt("Card_Number");
	            int Phone_Number= rs.getInt("Phone_Number");
	            String Email_Address= rs.getString("Email_Address");
	            
	            System.out.println("Guest: "+ guestName + Guest_Surname + Address + Card_Number + Phone_Number + Email_Address);
	            Guest guest = new Guest();
	            guest.setGuestName(guestName);
	            guest.setGuestSurename(Guest_Surname);
	            guest.setGuestAddress(Address);
	            guest.setGuestCardNumber(Card_Number);
	            guest.setGuestPhoneNumber(Phone_Number);
	            guest.setGuestEmail(Email_Address);
	            
	            list.add(guest);
	            
	            System.out.println(list.toString());
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
	            int Room_Number = rs.getInt("Room_Number");
	            int FK_Room_Type_ID = rs.getInt("FK_Room_Type_ID");
	            // Create instance of Room class
	            System.out.println("Test "+Room_Number);
	            Room room = new Room(Room_Number, FK_Room_Type_ID);
	            // Add Room class to list
	            list.add(room);
	        }
	        return list;
	    }
	    
	    // insertGuest
	    public static void insertGuest(Connection conn, Guest guest) throws SQLException {
	        String sql = "Insert into Guest(Guest_Name, Guest_Surname, Address, Email_Address, Card_Number, Phone_Number) values (?,?,?,?,?,?)";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        pstm.setString(1, guest.getGuestName());
	        pstm.setString(2, guest.getGuestSurename());
	        pstm.setString(3, guest.getGuestAddress());
	        pstm.setString(4, guest.getGuestEmail());
	        pstm.setInt(5, guest.getGuestCardNumber());
	        pstm.setInt(6, guest.getGuestPhoneNumber());
	 
	        pstm.executeUpdate();
	        System.out.println("insertGuest SQL executed");
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
