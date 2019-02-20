package webApp.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/createReservation" })
public class ReservationCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
 
    public ReservationCreateServlet() {
        super();
    }
 
    
    // Show product creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	HttpSession session = request.getSession();
    	
    	String resStart = request.getParameter("resStart");
    	String resEnd = request.getParameter("resEnd");
    	String rooms = request.getParameter("numRooms");
    	
    	
    	
    	// if start and end date are null, load reservations #1
    	if (resStart == null && resEnd == null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/reservationOneView.jsp");
            dispatcher.forward(request, response);
        	}
    	
    	// if start and end date are not null, load reservations #2
    	if (resStart != null && resEnd != null) {
    		DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) ;
    		LocalDate start = LocalDate.parse( resStart , f ) ;
    		LocalDate end = LocalDate.parse( resEnd , f ) ;
    		Long durationLong = ChronoUnit.DAYS.between(start, end);
    		int duration = durationLong.intValue();
    		int numRooms = Integer.parseInt(rooms);
    		
    		// format LocalDate to display on webpage
    		DateTimeFormatter formatWeb = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy");
    		
    		System.out.println("Hello " + start + " " + end + " " + duration);
    		
    		// set object attributes to send to confirmation servlet
    		session.setAttribute("startObj", start);
    		session.setAttribute("endObj", start);
    		
    		// set String/int attributes to send to web page
    		session.setAttribute("startDate", formatWeb.format(start));
    		session.setAttribute("endDate", formatWeb.format(end));
    		session.setAttribute("numRooms", numRooms);
    		session.setAttribute("duration", duration);
    		
    		
	        RequestDispatcher dispatcher = request.getServletContext()
	        		
	                .getRequestDispatcher("/WEB-INF/views/reservationTwoView.jsp");
	        dispatcher.forward(request, response);
    		
    	}
    	
    }
 

    // doPost is a Servlet post method handler
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	


    }
        
        

   
 
}
