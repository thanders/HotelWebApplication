package webApp.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webApp.beans.Guest;
import webApp.beans.Reservation;
import webApp.beans.Room;
import webApp.beans.Starwood;
import webApp.cookies.SessionUtils;
import webApp.dbconn.DB_guests;
import webApp.dbconn.DB_members;
import webApp.dbconn.DB_reservation;
import webApp.dbconn.DB_rooms;

@WebServlet(urlPatterns = { "/reservationDisplay" })
public class ReservationDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReservationDisplayServlet() {
		super();
	}

	@Override // doGet is automatically loaded upon going to the @WebServlet URL Pattern
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Load the reservationView page
		if(SessionUtils.getLoginedUser(request.getSession())!=null)
		{

			Starwood member = SessionUtils.getLoginedUser(request.getSession());
			Connection conn = SessionUtils.getStoredConnection(request);
			List<Reservation> reservations = new ArrayList<>();

			try {
				BigInteger guestID = DB_members.getStarwoodMemberId(conn, member.getUserName());
				reservations = DB_reservation.
						queryReservations(conn, guestID , "Member");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("reservations", reservations);


			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/starwoodDisplayView.jsp");
			dispatcher.forward(request, response);
		}
		else {
			
			System.out.println("WHY AM I NOT SEEEING THE PAGE???");

			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/reservationDisplayView.jsp");
			dispatcher.forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("HELLOOO");
		// Use get parameter to obtain posted data from form
		String resNumberInt = (String) request.getParameter("resNumber");
		System.out.println("resNumber: " + resNumberInt);
		
		BigInteger resNumber = null;
		Reservation resObj = null;
		Guest guestObj = null;

		if (resNumberInt != null) {
			 resNumber=new BigInteger(resNumberInt);
		}

		// Connect to database
		Connection conn = SessionUtils.getStoredConnection(request);
	
		// Try to create an object for the posted reservationID number
		try {

			resObj = DB_reservation.queryReservationRID(conn, resNumber);
			
			// if reservation found:
			if(resObj!=null) {
				
				// if session exists kill it (this will remove stored login attempts)
				HttpSession session = request.getSession();
				session.invalidate();
				
				System.out.println(resObj.toString());
				
				guestObj = DB_guests.QueryGuest(conn, resObj.getGuestID());
				if(guestObj!=null) {

					// Date formats:
					DateTimeFormatter formatWeb = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy");
					DateTimeFormatter bookingDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

					List<Room> bookedRooms = DB_rooms.selectBookedRooms(conn, resNumber);
					// room related set attributes
					request.setAttribute("bookedRooms", bookedRooms);

					// Set attributes for Reservations data
					request.setAttribute("resNumber", resNumber);
					request.setAttribute("start", formatWeb.format(resObj.getStart()));
					request.setAttribute("end", formatWeb.format(resObj.getEnd()));
					request.setAttribute("numberRooms", resObj.getNumberRooms());
					request.setAttribute("status", resObj.getStatus());
					request.setAttribute("bookingDate", bookingDateFormat.format(resObj.getBookingDate()));
					request.setAttribute("reservationType", resObj.getReservationType());

					System.out.println("WTFFFF");



					if(SessionUtils.getLoginedUser(request.getSession())==null){
						request.setAttribute("reservationPrice", resObj.getPriceFormatted());
					}
					else{
						double resPrice = Double.parseDouble( resObj.getPriceFormatted());
						double reducedPrice = resPrice - (resPrice *0.1);

						String price = Double.toString(reducedPrice);

						request.setAttribute("reservationPrice", price);
					}
				

					// Set attributes for Guest data
					request.setAttribute("guestName", guestObj.getGuestName());
					request.setAttribute("guestSurname", guestObj.getGuestSurename());


					RequestDispatcher dispatcher = request.getServletContext()
							.getRequestDispatcher("/WEB-INF/views/reservationConfirmView.jsp");
					dispatcher.forward(request, response);

				}
				else {
					System.out.println("GUEST ID IS NULL!");
					System.out.println(resObj.toString());
				}

			}
			
			// else reservation not found
			else {
				
				// Sleep for two seconds to delay a brute force attack
				TimeUnit.SECONDS.sleep(2);
				
				// Forward to /WEB-INF/views/reservationDisplayView.jsp
				RequestDispatcher dispatcher //
				= request.getRequestDispatcher("/submitCount");

				dispatcher.forward(request, response);
			}


		}
		catch ( Exception e){
			System.out.println("THIS is an exception");
			e.printStackTrace();
		}

	}

}

