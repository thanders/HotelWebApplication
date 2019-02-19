package webApp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webApp.beans.Guest;
import webApp.dbconn.DBUtils;
import webApp.cookies.SessionUtils;

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
    	String resStart = (String) request.getParameter("resStart");
    	String resEnd = (String) request.getParameter("resEnd");
    	String numPeople = request.getParameter("numPeople");
    	
    	System.out.println("From "+ resStart + "until "+ resEnd + "" + numPeople);
    	
    	// if start and end date are null, load reservations #1
    	if (resStart == null && resEnd == null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/reservationOneView.jsp");
            dispatcher.forward(request, response);
        	}
    	
    	// if start and end date are not null, load reservations #2
    	if (resStart != null && resEnd != null) {
    		
    		session.setAttribute("resStart", resStart);
    		session.setAttribute("resEnd", resEnd);
    		session.setAttribute("numPeople", numPeople);

	        RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/reservationTwoView.jsp");
	        dispatcher.forward(request, response);
    	}
    	
    }
 
    // When the user enters the product information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        Connection conn = SessionUtils.getStoredConnection(request);
        
        String guestName = (String) request.getParameter("guestName");
        String guestSurename = (String) request.getParameter("guestSurename");
        String guestAddress = (String) request.getParameter("guestAddress");
        String guestEmail = (String) request.getParameter("guestEmail");
        String guestCardNumber = (String) request.getParameter("guestCardNumber");
        String guestPhoneNumber = (String) request.getParameter("guestPhoneNumber");
        System.out.println("TEST Card" + " " + guestCardNumber);


        System.out.println("int Card" + " " + guestCardNumber);
        Guest guest = new Guest(guestName, guestSurename, guestAddress, guestEmail, guestCardNumber, guestPhoneNumber);
        
        System.out.println(guest.toString());
        String errorString = null;
 
        // Product ID is the string literal [a-zA-Z_0-9]
        // with at least 1 character
        
        /*
        String regex = "\\w+";
 
        if (code == null || !code.matches(regex)) {
            errorString = "Product Code invalid!";
        }
        
        */
 
        // If error string is null, try to insert the guest object into the Guest database table
        if (errorString == null) {
            try {
                DBUtils.insertGuest(conn, guest);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
         
        }
 
        // Store information to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("guestNew", guest);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/reservationOneView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        
        else {
            // Redirect to the product listing page.
            response.sendRedirect(request.getContextPath() + "/reservations");
        }
    }
 
}
