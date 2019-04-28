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

	public static Starwood findMember(Connection conn, //
			String userName, String password) throws SQLException {

		String sql = "Select * from Members a " //
				+ " where a.User_Name = ? and a.User_Password = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			Starwood member = new Starwood(userName, password);

			return member;
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

	// queryRoom
	public static List<Room> queryRoom(Connection conn) throws SQLException {
		String sql = "Select a.Room_Number, a.FK_Room_Type_ID from Room a order by Room_Number";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		// Create an arraylist
		List<Room> list = new ArrayList<Room>();
		while (rs.next()) {
			String Room_Number = rs.getString("Room_Number");
			// Create instance of Room class
			Room room = new Room(Room_Number);
			// Add Room class to list
			list.add(room);
		}
		return list;
	}

	// insertGuest, obtain auto generated GuestID key and create Reservation
	public static int insertGuest(Connection conn, Guest guest) throws SQLException {
		String sql = "Insert into Guest(Guest_Name, Guest_Surname, Address, Email_Address, Card_Number, Phone_Number,ExpiryDate,CVV) values (?,?,?,?,?,?,?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		pstm.setString(1, guest.getGuestName());
		pstm.setString(2, guest.getGuestSurename());
		pstm.setString(3, guest.getGuestAddress());
		pstm.setString(4, guest.getGuestEmail());
		pstm.setString(5, guest.getGuestCardNumber());
		pstm.setInt(6, guest.getGuestPhoneNumber());
		pstm.setObject(7, guest.getExpiryDate());
		pstm.setInt(8, guest.getCVV());
		pstm.executeUpdate();

		ResultSet rs = pstm.getGeneratedKeys();

		int GuestID = 0;

		// Assign auto generated Guest key to variable and create reservation
		if (rs != null && rs.next()) {
			GuestID = rs.getInt(1);
		}

		return GuestID;
	}

	// insert Reservation
	public static void insertReservation(Connection conn, int GuestID, LocalDate start, LocalDate end, int numberRooms)
			throws SQLException {
		String sql = "Insert into Reservations(GuestID, start, end, numberRooms) values (?, ?, ?, ?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, GuestID);
		pstm.setObject(2, start);
		pstm.setObject(3, end);
		pstm.setInt(4, numberRooms);

		pstm.executeUpdate();

	}

	// insertMember
	public static int insertMember(Connection conn, Starwood member) throws SQLException {
		String sql = "Insert into Starwood(Member_Name, Member_Surname, Address, Email_Address, Card_Number, Phone_Number, User_Name) values (?,?,?,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, member.getName());
		pstm.setString(2, member.getSurename());
		pstm.setString(3, member.getAddress());
		pstm.setString(4, member.getEmail());
		pstm.setString(5, member.getCardNumber());
		pstm.setInt(6, member.getPhoneNumber());
		pstm.setString(7, member.getUserName());
		pstm.executeUpdate();

		int ID = 0;
		ResultSet rs = pstm.getGeneratedKeys();

		// Assign auto generated Guest key to variable and create reservation
		if (rs != null && rs.next()) {
			ID = rs.getInt(1);
		}

		return ID;
	}

	public static void removeUser(Connection conn, Starwood member) throws SQLException {

		String username = member.getUserName();

		String sql = "Delete From Starwood where User_Name= ?";
		String sql2 = "Delete From Members where User_Name= ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, username);

		removeUserReservations(conn, member);
		removeCreditCards(conn, DB_members.getStarwoodMemberId(conn,username));
		pstm.executeUpdate();

		PreparedStatement pstm1 = conn.prepareStatement(sql2);

		pstm1.setString(1, username);

		pstm1.executeUpdate();
	}

	private static void removeCreditCards(Connection conn, int id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "Delete From Credit_Card where MemberID = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		pstm.executeUpdate();

	}

	public static List<CreditCard> getCards(Connection conn, int id) throws SQLException {
		String sql = "Select *  from Credit_Card a where a.MemberID = ?   ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		List<CreditCard> list = new ArrayList<CreditCard>();
		while (rs.next()) {
			String card = rs.getString(1);
			CreditCard creditCard = new CreditCard(card, id);
			list.add(creditCard);
		}
		return list;

	}

	public static void removeUserReservations(Connection conn, Starwood member) throws SQLException {

		int guestID = DB_members.getStarwoodMemberId(conn, member.getUserName());
		List<Reservation> reservations = DB_reservation.queryReservations(conn, guestID, "Member");

		for (Reservation r : reservations) {

			String sql = "Delete From Reservations where Reservation_Id= ?";

			PreparedStatement pstm = conn.prepareStatement(sql);
			List<Room> rooms = getReservedRooms(conn, r.getReservationId());

			for (Room room : rooms) {
				removeReservedRoom(conn, room.getId());
			}

			pstm.setInt(1, r.getReservationId());

			pstm.executeUpdate();

		}

	}

	public static void removeReservedRoom(Connection conn, int id) throws SQLException {

		String sql = "Delete From Reserved_Rooms where id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, id);

		pstm.executeUpdate();

	}

	public static List<Room> getReservedRooms(Connection conn, int reservationId) throws SQLException {

		List<Room> rooms = new ArrayList<>();
		String sql = "Select *  from Reserved_Rooms a where a.reservationID = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, reservationId);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			Room r = new Room(rs.getString("roomNumber"));
			r.setReservationNumber(reservationId);
			r.setId(rs.getInt("id"));
			rooms.add(r);
		}

		return rooms;

	}
	

	public static void updateMemberName(Connection conn, Starwood member) throws SQLException {
		String sql = "Update Starwood set Member_Name =?  where Id=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, member.getName());
		pstm.setInt(2, member.getId());
		pstm.executeUpdate();
	}

	public static void updateMemberSurname(Connection conn, Starwood member) throws SQLException {
		String sql = "Update Starwood set Member_Surname =?  where Id=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, member.getSurename());
		pstm.setInt(2, member.getId());
		pstm.executeUpdate();
	}

	public static void updateMemberAddress(Connection conn, Starwood member) throws SQLException {
		String sql = "Update Starwood set Address =?  where Id=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, member.getAddress());
		pstm.setInt(2, member.getId());
		pstm.executeUpdate();
	}

	public static void updateMemberEmail(Connection conn, Starwood member) throws SQLException {
		String sql = "Update Starwood set Email_Address =?  where Id=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, member.getEmail());
		pstm.setInt(2, member.getId());
		pstm.executeUpdate();
	}

	public static void updateMemberPhoneNumber(Connection conn, Starwood member) throws SQLException {
		String sql = "Update Starwood set Phone_Number =?  where Id=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, member.getPhoneNumber());
		pstm.setInt(2, member.getId());
		pstm.executeUpdate();
	}

	public static void updateMemberCreditCard(Connection conn, Starwood member, String oldNo) throws SQLException {
		String sql = "Update Starwood set Card_Number =?  where Id=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, member.getCardNumber());
		pstm.setInt(2, member.getId());

		// Update the old card in the credit card table as well
		String sql2 = "Update Credit_Card set Card_Number =?  where Card_Number=? ";
		PreparedStatement pstm2 = conn.prepareStatement(sql2);
		pstm2.setString(1, member.getCardNumber());
		pstm2.setString(2, oldNo);
		pstm2.executeUpdate();

		pstm.executeUpdate();
	}

	public static void updateMemberUsername(Connection conn, Starwood member, String oldUserName) throws SQLException {
		String sql = "Update Starwood set User_Name =?  where Id=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, member.getUserName());
		pstm.setInt(2, member.getId());
		pstm.executeUpdate();
		
		String sql2 = "Update Members set User_Name =? where User_Name=? ";
		PreparedStatement pstm2 = conn.prepareStatement(sql2);
		pstm2.setString(1, member.getUserName());
		pstm2.setString(2, oldUserName);
		pstm2.executeUpdate();
	}


	public static void updateMemberPasword(Connection conn, Starwood member) throws SQLException {
		String sql = "Update Members set User_Password =? where User_Name=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, member.getPassword());
		pstm.setString(2, member.getUserName());
		pstm.executeUpdate();
	}

	public static void insertCard(Connection conn, CreditCard card) throws SQLException {
		String sql = "Insert into Credit_Card(MemberID, Card_Number,ExpiryDate, CVV) values (?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, card.getId());
		pstm.setString(2, card.getCardNumber());
		pstm.setObject(3, card.getExpiryDate());
		pstm.setInt(4, card.getCVV());
		pstm.executeUpdate();
	}

}
