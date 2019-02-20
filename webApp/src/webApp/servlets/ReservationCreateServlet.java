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
import webApp.beans.Reservation;
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
 

    // doPost is a Servlet post method handler
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	


    }
        
        

   
 
}
