
package webApp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webApp.beans.Guest;
import webApp.beans.Reservation;
import webApp.beans.Room;
import webApp.cookies.SessionUtils;
import webApp.dbconn.DB_guests;
import webApp.dbconn.DB_reservation;
import webApp.dbconn.DB_rooms;

@WebServlet(urlPatterns = { "/reservationDisplay" })
public class ReservationDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReservationDisplayServlet() {
		super();
	}

	@Override // doGet is automatically loaded upon going to the @WebServlet URL Pattern
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		// Load the reservationView page
		if(SessionUtils.getLoginedUser(request.getSession())!=null)
		{

			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/starwoodDisplayView.jsp");
			dispatcher.forward(request, response);
		}
		else {

			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/reservationDisplayView.jsp");
			dispatcher.forward(request, response);
		}


	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		// Use get parameter to obtain posted data from form
		String resNumberInt = (String) request.getParameter("resNumber");

		if (resNumberInt != null) {
			int resNumber = Integer.parseInt(resNumberInt);
			System.out.println(resNumber);

			// Connect to database
			Connection conn = SessionUtils.getStoredConnection(request);

			String cancel = (String) request.getParameter("cancel");

			// If cancellation button clicked (POST message)
			if (cancel != null) {
				request.setAttribute("cantCancel", "24 hours has passed, can't cancel");
			}
			if(SessionUtils.getLoginedUser(request.getSession())==null)
			{
				// Create an object for the new Reservation
				try {
					Reservation resObj = DB_reservation.queryReservationRID(conn, resNumber);

					if(resObj!=null) {
						System.out.println("GID :  " + resObj.getGuestID());

						Guest guestObj = DB_guests.QueryGuest(conn, resObj.getGuestID());
						if(guestObj!=null) {
							DateTimeFormatter formatWeb = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy");

							List<Room> bookedRooms = DB_rooms.selectBookedRooms(conn, resNumber);
							// room related set attributes
							request.setAttribute("bookedRooms", bookedRooms);

							// Set attributes for Reservations data
							request.setAttribute("resNumber", resNumber);
							request.setAttribute("start", formatWeb.format(resObj.getStart()));
							request.setAttribute("end", formatWeb.format(resObj.getEnd()));
							request.setAttribute("numberRooms", resObj.getNumberRooms());
							request.setAttribute("status", resObj.getStatus());
							request.setAttribute("bookingDate", resObj.getBookingDate());
							request.setAttribute("reservationType", resObj.getReservationType());
							// request.setAttribute("bookingDate", formatWeb.format(resObj.getBookingDate()));


							// Set attributes for Guest data
							request.setAttribute("guestName", guestObj.getGuestName());
							request.setAttribute("guestSurname", guestObj.getGuestSurename());


							RequestDispatcher dispatcher = request.getServletContext()
									.getRequestDispatcher("/WEB-INF/views/reservationConfirmView.jsp");
							dispatcher.forward(request, response);
						}
						else {
							request.setAttribute("errorString", "Guest with that reservation ID doesn't exist");
							// Forward to /WEB-INF/views/login.jsp
							RequestDispatcher dispatcher //
							= request.getRequestDispatcher("/WEB-INF/views/reservationDisplayView.jsp");

							dispatcher.forward(request, response);
						}
					}
					else {
						request.setAttribute("errorString", "Reservation with entered ID doesn't exist");
						// Forward to /WEB-INF/views/login.jsp
						RequestDispatcher dispatcher //
						= request.getRequestDispatcher("/WEB-INF/views/reservationDisplayView.jsp");

						dispatcher.forward(request, response);
					}
				}

				catch (SQLException e){
					e.printStackTrace();
					String errorString = e.getMessage();
					System.out.println(errorString);
				}
			}
			//		else
			//		{
			//			
			//			//TODO: show list of reservations, let them click on reseration and link to resrvation confirmation
			//			//for that reservation
			//			try {
			//				List<Reservation> resObj = DB_reservation.queryAllMemberReservations(conn,(SessionUtils.getLoginedUser(request.getSession()).getId()));
			//				//				resObj.get(0).getBookingDate()
			//				Starwood memberObj = DB_members.findStarwoodMember(conn, resObj.get(0).getGuestID());
			//				request.setAttribute("guestName", memberObj.getName());
			//				request.setAttribute("guestSurname",  memberObj.getSurename());        		
			//				RequestDispatcher dispatcher = request.getServletContext()
			//						.getRequestDispatcher("/WEB-INF/views/starwoodDisplayView.jsp");
			//				dispatcher.forward(request, response);
			//
			//			} catch (SQLException e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
			//
			//
			//
			//		}

		}

	}

}