package webApp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webApp.beans.Guest;
import webApp.beans.Reservation;
import webApp.dbconn.DBUtils;
import webApp.cookies.SessionUtils;

@WebServlet(urlPatterns = { "/reservationConfirm" })
public class ReservationConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ReservationConfirmServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Calls the doGet function
        doGet(request, response);
        
        String guestName = (String) request.getParameter("guestName");
        String guestSurename = (String) request.getParameter("guestSurename");
        String guestAddress = (String) request.getParameter("guestAddress");
        String guestEmail = (String) request.getParameter("guestEmail");
        String guestCardNumber = (String) request.getParameter("guestCardNumber");
        String guestPhoneNumber = (String) request.getParameter("guestPhoneNumber");
        
        Guest guest = new Guest(guestName, guestSurename, guestAddress, guestEmail, guestCardNumber, guestPhoneNumber);

        Connection conn = SessionUtils.getStoredConnection(request);
        String errorString = null;
        
        // If error string is null, try to insert the guest object into the Guest database table
        try {
            	
        	// Insert the new Guest instance into the database
        	int GuestID = DBUtils.insertGuest(conn, guest);

        	// Insert the new Reservation into the database
        	DBUtils.insertReservation(conn, GuestID);
        	// Create an object for the new Reservation
        	Reservation resObj = DBUtils.queryReservation(conn, GuestID);

            // Store information to request attribute, before forward to views. 
        	request.setAttribute("reservationObj", resObj.getReservationId());
        	request.setAttribute("guestName", guest.getGuestName());
        	request.setAttribute("guestSurname", guest.getGuestSurename());
        	request.setAttribute("guestAddress", guest.getGuestAddress());
        	request.setAttribute("guestEmail", guest.getGuestEmail());
        	request.setAttribute("guestCardNumber", guest.getGuestCardNumber());
        	request.setAttribute("guestPhoneNumber", guest.getGuestPhoneNumber());
            request.setAttribute("errorString", errorString);
            
            
	        RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/reservationConfirmView.jsp");
	        dispatcher.forward(request, response);
               
       } catch (SQLException e) {
    	   e.printStackTrace();
    	   errorString = e.getMessage();
       }
    }
        

 
}