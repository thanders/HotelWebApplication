package webApp.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet(urlPatterns = { "/reservationCancel" })
public class ReservationCancel extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ReservationCancel() {
        super();
    }
 
    @Override // doGet is automatically loaded upon going to the @WebServlet URL Pattern
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
		// Request the current session and use it to retrieve objects from Reservation step 1
		HttpSession session = request.getSession();
		
        // Use get parameter to obtain posted data from form
    	// String cancel = (String) session.getAttribute("cancel");

    	String resNumberStr = (String) request.getParameter("resNumber");
    	

    	BigInteger resNumber = null;
    	
        // Convert resNumber string to int
        if (resNumberStr != null) {
        	
        	resNumber = new BigInteger(resNumberStr);
        	request.setAttribute("resNumber", resNumber);
        }
    	
        // Connect to database
        Connection conn = SessionUtils.getStoredConnection(request);
        
        // Check if user is logged in (Starwood member) - if yes, cancel reservation
		if(SessionUtils.getLoginedUser(session)!=null) {
					
			try {
			Starwood member = SessionUtils.getLoginedUser(request.getSession());
			List<Reservation> reservations = new ArrayList<>();
			
			BigInteger guestID = DB_members.getStarwoodMemberId(conn, member.getUserName());
			reservations = DB_reservation.queryReservations(conn, guestID , "Member");
			
			// Change the reservation's status to cancelled
	        DB_reservation.updateReservationStatus(conn, resNumber);
	        
	        // Delete room bookings for that reservation in Room table
	        DB_reservation.deleteBookings(conn, resNumber);
	        
	        // Execute query again to have an updated version of the reservations
			reservations = DB_reservation.queryReservations(conn, guestID , "Member");
			
			
			request.setAttribute("resNumber", resNumber);
			request.setAttribute("reservations", reservations);
			}
			
            catch (SQLException e){
          	   e.printStackTrace();
             }
            
		    // load the reservation confirmation jsp page:
	    	RequestDispatcher dispatcher = request.getServletContext()
	    		.getRequestDispatcher("/WEB-INF/views/starwoodDisplayView.jsp");
	    	    dispatcher.forward(request, response);
			
		}
		else {
        	
	        try {
	
	        // Create an object for the new Reservation (cancelled) and related Guest
		    Reservation resObj = DB_reservation.queryReservationRID(conn, resNumber);
		    
		    // current date
		    LocalDateTime now = LocalDateTime.now();
		    // Difference between current date and reservation date in days
		    long difference = ChronoUnit.HOURS.between(resObj.getBookingDate(), now);
		    
		    // If the difference is less than or equal to 24 hours
		    if (difference <= 24) {
		        // Change the reservation's status to cancelled
		        DB_reservation.updateReservationStatus(conn, resNumber);
		        
		        // Delete room bookings for that reservation in Room table
		        DB_reservation.deleteBookings(conn, resNumber);
		        
		        request.setAttribute("CancelMSG", "Your booking has been cancelled (Hours since booking: "+ difference+" hours)");
		        // Reload reservation object to get updated status from database:
			    resObj = DB_reservation.queryReservationRID(conn, resNumber);
		    
		    }
		    
		    else {
		    	request.setAttribute("CancelMSG", "Cannot cancel, cancellation must be done within 24 hours of booking "+ "(Hours since booking: "+ difference+" hours)");
		    }
		    
		    Guest guestObj = DB_guests.QueryGuest(conn, resObj.getGuestID());
		    
		    // Set reservation variables
		    
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
			request.setAttribute("reservationPrice", resObj.getPriceFormatted());
	
			
			// Set attributes for Guest data
			request.setAttribute("guestName", guestObj.getGuestName());
			request.setAttribute("guestSurname", guestObj.getGuestSurename());
		    
		        	
		    // load the reservation confirmation jsp page:
	    	RequestDispatcher dispatcher = request.getServletContext()
	    		.getRequestDispatcher("/WEB-INF/views/reservationConfirmView.jsp");
	    	    dispatcher.forward(request, response);
	    	        
	            }
	            
	            catch (SQLException e){
	         	   e.printStackTrace();
	            }
	            
	        }
    	}
    }
    
//}
   