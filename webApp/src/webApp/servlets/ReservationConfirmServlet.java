package webApp.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import security.EncryptDecrypt;
import webApp.beans.AdHoc;
import webApp.beans.Guest;
import webApp.beans.Reservation;
import webApp.beans.Room;
import webApp.beans.Starwood;
import webApp.cookies.SessionUtils;
import webApp.dbconn.DBUtils;
import webApp.dbconn.DB_members;
import webApp.dbconn.DB_reservation;
import webApp.dbconn.DB_rooms;

@WebServlet(urlPatterns = { "/reservationConfirm" })
public class ReservationConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReservationConfirmServlet() {
		super();
	}

	// The doGet method - is automatically called and can handle get requests
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


	}


	// The doPost method - is called upon receiving a post request
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Calls the doGet function
		doGet(request, response);

		// Request the current session and use it to retrieve objects from Reservation step 1
		HttpSession session = request.getSession();
		LocalDate startObj = (LocalDate) session.getAttribute("startObj");
		LocalDate endObj = (LocalDate) session.getAttribute("endObj");
		int numRooms = (int) session.getAttribute("numRooms");
		String[] choices = (String[]) session.getAttribute("choices");

		Double resPrice = (Double) session.getAttribute("resPrice");
		DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) ;

		EncryptDecrypt encoder = new EncryptDecrypt();
		String key = encoder.getKey();


		// Create encrypter/decrypter class
	//	EncryptDecrypt encrypter = new EncryptDecrypt(key);

		// IF not logged in (GUEST)
		if(SessionUtils.getLoginedUser(request.getSession())==null)
		{
			String guestName = (String) request.getParameter("guestName");
			String guestSurename = (String) request.getParameter("guestSurename");
			String guestAddress = (String) request.getParameter("guestAddress");
			String guestEmail = (String) request.getParameter("guestEmail");
			String guestCardNumber = (String) request.getParameter("guestCardNumber");
			String gPNumber = (String) request.getParameter("guestPhoneNumber");
			int guestPhoneNumber = Integer.parseInt(gPNumber);
			String guestCardDate= (String) request.getParameter("ExpiryDate");
			System.out.println(guestCardDate+" yes");
			String cvvNumber = (String) request.getParameter("CVV");
			int guestcvvNumber = Integer.parseInt(cvvNumber);
			LocalDate expiry = LocalDate.parse( guestCardDate , f ) ;
			
			Long durationExpired = ChronoUnit.DAYS.between(expiry, LocalDate.now());

			String destination = "test";
			
			if (durationExpired >0) {
				request.setAttribute("cardExpired", "Your creditcard has expired. Try again with another one");
				destination = "/WEB-INF/views/reservationBookingView.jsp";

			}
			else {
				destination = "/WEB-INF/views/reservationConfirmView.jsp";
			}
			
			String encryptedCredit="";
			try {
				encryptedCredit = EncryptDecrypt.encrypt(guestCardNumber,key);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// Decrypt
			// Create instance of Guest class
			Guest guest = new Guest(guestName, guestSurename, guestAddress, guestEmail, encryptedCredit, guestPhoneNumber,guestcvvNumber,expiry);


			String errorString = null;

			// If error string is null, try to insert the guest object into the Guest database table
			try {

				//Connect to database
				Connection conn = SessionUtils.getStoredConnection(request);

				// Insert the new Guest instance into the database
				BigInteger GuestID = DBUtils.insertGuest(conn, guest);
				String status = "Active";
				String reservationType = "Guest";
				
				System.out.println("GUEST ID generation: "+ GuestID);
				
				// get random reservation ID
				AdHoc random = new AdHoc();
				BigInteger reservationID = random.randomNumber();

				// Insert the new Reservation into the database
				DB_reservation.insertReservation(conn, reservationID, GuestID, startObj, endObj, numRooms, status, reservationType, resPrice);

				// Create an object for the new Reservation
				Reservation resObj = DB_reservation.queryReservation(conn, GuestID);
				BigInteger reservationNumber = resObj.getReservationId();



				// Create instances of room class for each booked room
				for (int i =0; i< choices.length; i++) {
					String roomNumber = choices[i];

					Room bookedRoom = new Room(roomNumber);
					bookedRoom.setReservationNumber(reservationNumber);

					DB_rooms.insertBookedRoom(conn, bookedRoom.getRoomNumber(), bookedRoom.getReservationNumber());

				}

				// query records of booked rooms for reservationID
				try{
					List<Room> bookedRooms = DB_rooms.selectBookedRooms(conn, reservationNumber);
					// room related set attributes
					request.setAttribute("bookedRooms", bookedRooms);


					// Update the Reservation Object variable
					//resObj = DB_reservation.queryReservation(conn, GuestID);


				}
				catch(Exception e){
					e.printStackTrace();
				}





				// Store information to request attribute, before forward to views. 
				DateTimeFormatter bookingDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				
				// Reservation related set attributes
				request.setAttribute("resNumber", reservationNumber);
				request.setAttribute("start", resObj.getStart());
				request.setAttribute("numberRooms", resObj.getNumberRooms());
				request.setAttribute("end", resObj.getEnd());
				request.setAttribute("status", resObj.getStatus());
				request.setAttribute("bookingDate", bookingDateFormat.format(resObj.getBookingDate()));
				request.setAttribute("reservationType", resObj.getReservationType());
				request.setAttribute("reservationPrice", resObj.getPriceFormatted());

				// Guest related set attributes
				request.setAttribute("guestName", guest.getGuestName());
				request.setAttribute("guestSurname", guest.getGuestSurename());
				request.setAttribute("guestAddress", guest.getGuestAddress());
				request.setAttribute("guestEmail", guest.getGuestEmail());
				try {
					request.setAttribute("guestCardNumber", EncryptDecrypt.decrypt(guest.getGuestCardNumber(),key));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("guestPhoneNumber", guest.getGuestPhoneNumber());
				request.setAttribute("errorString", errorString);



				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher(destination);
				dispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}

		//This part is for Starwood Members
		else{

			// Use get parameter to obtain POSTED data from form
			String guestName = SessionUtils.getLoginedUser(request.getSession()).getName();
			String guestSurename = SessionUtils.getLoginedUser(request.getSession()).getSurename();
			String guestAddress = SessionUtils.getLoginedUser(request.getSession()).getAddress();
			String guestEmail = SessionUtils.getLoginedUser(request.getSession()).getEmail();
			LocalDate guestExpiryDate = SessionUtils.getLoginedUser(request.getSession()).getExpiryDate();
			String guestCardNumber="";
			
			try {
				guestCardNumber = EncryptDecrypt.decrypt(SessionUtils.getLoginedUser(request.getSession()).getCardNumber(),key);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// Check to make sure credit card is not expired
			Long durationExpired = ChronoUnit.DAYS.between(guestExpiryDate, LocalDate.now());

			String destination = "test";
			
			// 
			if (durationExpired >0) {
				request.setAttribute("cardExpired", "Your creditcard has expired. Try again with another one");
				destination = "/WEB-INF/views/reservationBookingView.jsp";

			}
			else {
				destination = "/WEB-INF/views/reservationConfirmView.jsp";
			}
			
			int guestPhoneNumber = SessionUtils.getLoginedUser(request.getSession()).getPhoneNumber();

			// Create instance of Guest class
			Starwood guest = new Starwood(guestName, guestSurename, guestAddress, guestEmail, guestCardNumber, guestPhoneNumber);


			String errorString = null;

			// If error string is null, try to insert the guest object into the Guest database table
			try {

				// Connect to database
				Connection conn = SessionUtils.getStoredConnection(request);

				BigInteger GuestID = DB_members.getStarwoodMemberId(conn, SessionUtils.getLoginedUser(request.getSession()).getUserName());
				String status = "Active";
				String reservationType = "Member";

				// get random reservation ID
				AdHoc random = new AdHoc();
				BigInteger reservationID = random.randomNumber();
				
				// Insert the new Reservation into the database
				DB_reservation.insertReservation(conn, reservationID, GuestID, startObj, endObj, numRooms, status, reservationType, resPrice);

				// Create an object for the new Reservation
				Reservation resObj = DB_reservation.queryReservation(conn, GuestID, startObj, endObj, numRooms, status, reservationType, resPrice);
				
				BigInteger reservationNumber = resObj.getReservationId();

				// Create instances of room class for each booked room
				for (int i =0; i< choices.length; i++) {
					String roomNumber = choices[i];

					Room bookedRoom = new Room(roomNumber);
					bookedRoom.setReservationNumber(reservationNumber);

					DB_rooms.insertBookedRoom(conn, bookedRoom.getRoomNumber(), bookedRoom.getReservationNumber());
				}

				// query records of booked rooms for reservationID
				try{List<Room> bookedRooms = DB_rooms.selectBookedRooms(conn, reservationNumber);
				// room related set attributes
				request.setAttribute("bookedRooms", bookedRooms);
				}
				catch(Exception e){
					e.printStackTrace();
				}





				// Store information to request attribute, before forward to views. 
				DateTimeFormatter bookingDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				
				// Reservation related set attributes
				request.setAttribute("resNumber", reservationNumber);
				request.setAttribute("start", resObj.getStart());
				request.setAttribute("numberRooms", resObj.getNumberRooms());
				request.setAttribute("end", resObj.getEnd());
				request.setAttribute("status", resObj.getStatus());
				request.setAttribute("bookingDate", bookingDateFormat.format(resObj.getBookingDate()));
				request.setAttribute("reservationType", resObj.getReservationType());
	
				// Guest related set attributes
				request.setAttribute("guestName", guest.getName());
				request.setAttribute("guestSurname", guest.getSurename());
				request.setAttribute("guestAddress", guest.getAddress());
				request.setAttribute("guestEmail", guest.getEmail());
				request.setAttribute("guestCardNumber", guest.getCardNumber());
				request.setAttribute("guestPhoneNumber", guest.getPhoneNumber());
				request.setAttribute("errorString", errorString);



				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher(destination);
				dispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}

		}

	}


}