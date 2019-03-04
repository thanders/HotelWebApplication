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
 
    @Override // doGet is automatically loaded upon going to the @WebServlet URL Pattern
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	// Load the reservationView page
    	RequestDispatcher dispatcher = request.getServletContext()
        .getRequestDispatcher("/WEB-INF/views/reservationDisplayView.jsp");
        dispatcher.forward(request, response);

    	
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	

        // Use get parameter to obtain posted data from form
        String resNumberInt = (String) request.getParameter("resNumber");
        
        if (resNumberInt != null) {
        	int resNumber = Integer.parseInt(resNumberInt);
        	System.out.println(resNumber);
        	
        	// Connect to database
            Connection conn = SessionUtils.getStoredConnection(request);
            
        	// Create an object for the new Reservation
            try {
	        	Reservation resObj = DB_reservation.queryReservationRID(conn, resNumber);
	        	
	        	System.out.println("GID :  " + resObj.getGuestID());
	        	System.out.println("PRICE TEST :  " + resObj.getPrice());
	        	System.out.println("PRICE String :  " + resObj.getPriceFormatted());

	        	Guest guestObj = DB_guests.QueryGuest(conn, resObj.getGuestID());

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
            	 if(SessionUtils.getLoginedUser(request.getSession()).equals(null))
     		    {
				request.setAttribute("reservationPrice", resObj.getPriceFormatted());

     		    }
            	 else
            	 {
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
            
            catch (SQLException e){
         	   e.printStackTrace();
         	  String errorString = e.getMessage();
         	  System.out.println(errorString);
            }
    
        }

    }

}