package webApp.servlets;

import java.io.IOException;
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

import webApp.beans.Reservation;
import webApp.beans.Room;
import webApp.cookies.SessionUtils;
import webApp.dbconn.DB_reservation;
import webApp.dbconn.DB_rooms;

@WebServlet(urlPatterns = { "/ReservationChooseRoom" })
public class ReservationChooseRoom extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
 
    public ReservationChooseRoom() {
        super();
    }
 
    
    // doGet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	// Connect to database
        Connection conn = SessionUtils.getStoredConnection(request);
        
        
    	HttpSession session = request.getSession();
    	
    	String resStart = request.getParameter("resStart");
    	String resEnd = request.getParameter("resEnd");
    	String rooms = request.getParameter("numRooms");
    	
    	

    	DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) ;
    	LocalDate start = LocalDate.parse( resStart , f ) ;
    	LocalDate end = LocalDate.parse( resEnd , f ) ;
    	Long durationLong = ChronoUnit.DAYS.between(start, end);
    	int duration = durationLong.intValue();
    	int numRooms = Integer.parseInt(rooms);
    		
    	// format LocalDate to display on webpage
    	DateTimeFormatter formatWeb = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy");
    		
    	System.out.println("Hello " + start + " " + end + " " + duration);
    		
    	// set object attributes to add to session
    	session.setAttribute("startObj", start);
    	session.setAttribute("endObj", end);
    		
    		
    	// set String/int attributes to send to web page
    	session.setAttribute("startDate", formatWeb.format(start));
    	session.setAttribute("endDate", formatWeb.format(end));
    	session.setAttribute("numRooms", numRooms);
    	session.setAttribute("duration", duration);
    	
    		
    		
    		
    	// Find number of total rooms
    	try {
    			
    		System.out.println("WHO?? ");
			int totalRooms = DB_rooms.countTotalRooms(conn);
			System.out.println("Rooms:   "+ totalRooms);		 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
    		
    	
    	
    	// Retrieve a list of all reservations
		List<Reservation> allReservations = null;
		List<Room> availableRooms = null;
		int numberAvailableRooms = 0;
		
		try {
			allReservations = DB_reservation.queryAllReservations(conn);
			 // Retrieve a list of rooms
			//availableRooms = DB_rooms.selectRooms(conn);
			availableRooms = DB_rooms.selectAvailableRooms(conn, start, end);
			numberAvailableRooms = availableRooms.size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (allReservations != null & availableRooms != null) {
		 System.out.println("YES!"+ allReservations.toString());
		 // set attribute for Room objects so they are accessible by the ReservationChooseRoom.jsp page
		 session.setAttribute("availableRooms", availableRooms);
		 session.setAttribute("numberAvailableRooms", numberAvailableRooms);
		 
		
		}
		
		// Load the choose room view
        RequestDispatcher dispatcher = request.getServletContext()
        		
                .getRequestDispatcher("/WEB-INF/views/ReservationChooseRoom.jsp");
        dispatcher.forward(request, response);
        
    }
 

    // doPost is a Servlet post method handler
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
	    // Request the current session and use it to retrieve objects from Reservation step 1
	    HttpSession session = request.getSession();
	    LocalDate resStart = (LocalDate) session.getAttribute("startObj");
	    LocalDate resEnd = (LocalDate) session.getAttribute("endObj");
	    int duration = (int) session.getAttribute("duration");
	    int numRooms = (int) session.getAttribute("numRooms");

	    // Get an array of chosen Hotel rooms
	    String[] choices = null;
	    choices = request.getParameterValues("choices");
	    
	    // Room choice form validation
	    if(choices.length!= numRooms) {
	    	System.out.println("dingus");
	    	session.setAttribute("validationCount", "Hey, that's not the number of rooms you requested! Try again.");

	    	
			// Load reservationBookingView
	        RequestDispatcher dispatcher = request.getServletContext()
	        		
	                .getRequestDispatcher("/WEB-INF/views/ReservationChooseRoom.jsp");
	        dispatcher.forward(request, response);
	        }
	    
	    else {
	    	
	    	session.setAttribute("validationCount", "");

			DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) ;
	
	    	// Connect to database
	        Connection conn = SessionUtils.getStoredConnection(request);
	        
			// Retrieve the booked rooms from the database
			try {
				 int totalRooms = DB_rooms.countTotalRooms(conn);
				 System.out.println("Rooms:   "+ totalRooms);
				 
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// format LocalDate to display on webpage
			DateTimeFormatter formatWeb = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy");
			
			System.out.println("Hello " + resStart + " " + resStart + " " + duration);
			
			// set object attributes to send to confirmation servlet
			session.setAttribute("startObj", resStart);
			session.setAttribute("endObj", resStart);
			
			// set String/int attributes to send to web page
			session.setAttribute("startDate", formatWeb.format(resStart));
			session.setAttribute("endDate", formatWeb.format(resStart));
			session.setAttribute("numRooms", numRooms);
			session.setAttribute("duration", duration);
			session.setAttribute("choices", choices);
		

		if(SessionUtils.getLoginedUser(request.getSession())==null)
		{
		    RequestDispatcher dispatcher = request.getServletContext()
	        		
	                .getRequestDispatcher("/WEB-INF/views/reservationTwoView.jsp");
	        dispatcher.forward(request, response);
	        
			
		}
		else
		{
		    RequestDispatcher dispatcher = request.getServletContext()
	        		
	                .getRequestDispatcher("/WEB-INF/views/starwoodChooseRoom.jsp");
	        dispatcher.forward(request, response);
	        
		}
		
    
	    }
    }
}
