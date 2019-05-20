package org.servlets;

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

import org.beans.Guest;
import org.beans.Reservation;
import org.beans.Room;
import org.beans.Starwood;
import org.cookies.SessionUtils;
import org.dbconn.DB_guests;
import org.dbconn.DB_members;
import org.dbconn.DB_reservation;
import org.dbconn.DB_rooms;

@WebServlet(urlPatterns = { "/lockedOut" })
public class ReservationLockedOut extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
 
    public ReservationLockedOut() {
        super();
    }
 
    
    // Show product creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {		

    	// Load the lockedOutView jsp file
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/views/lockedOutView.jsp");
        dispatcher.forward(request, response);
    	
    }
    
    // Show product creation page.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {		

    	// Load the lockedOutView jsp file
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/views/lockedOutView.jsp");
        dispatcher.forward(request, response);
    	
    }
}
 

   
   