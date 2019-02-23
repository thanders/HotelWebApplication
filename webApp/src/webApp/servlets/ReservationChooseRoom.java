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
    	

    	
    }
 

    // doPost is a Servlet post method handler
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	


   
        
	System.out.println("CHOOSE ROOM NOW");

    // Request the current session and use it to retrieve objects from Reservation step 1
    HttpSession session = request.getSession();
    LocalDate resStart = (LocalDate) session.getAttribute("startObj");
    LocalDate resEnd = (LocalDate) session.getAttribute("endObj");
    int duration = (int) session.getAttribute("duration");
    int numRooms = (int) session.getAttribute("numRooms");
	
	// if start and end date are not null, load reservations booking page
	if (resStart != null && resEnd != null) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) ;

    	// Connect to database
        Connection conn = SessionUtils.getStoredConnection(request);
        
		// Find number of total rooms
		try {
			 int totalRooms = DB_rooms.countTotalRooms(conn);
			 System.out.println("Rooms:   "+ totalRooms);
			 
			 List<Reservation> allReservations = DB_reservation.queryAllReservations(conn);
			 System.out.println("YES!"+ allReservations.toString());
			 
		
					
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
		

		
		
        RequestDispatcher dispatcher = request.getServletContext()
        		
                .getRequestDispatcher("/WEB-INF/views/reservationTwoView.jsp");
        dispatcher.forward(request, response);
        
	}
        
		
	}
        

   
 
}
