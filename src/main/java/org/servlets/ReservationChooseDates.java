package org.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cookies.SessionUtils;
import org.dbconn.DB_rooms;

@WebServlet(urlPatterns = { "/ReservationChooseDates" })
public class ReservationChooseDates extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
 
    public ReservationChooseDates() {
        super();
    }
 
    
    // Show product creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
		// Connect to database
		Connection conn = SessionUtils.getStoredConnection(request);
			
		int maxRooms = 0;
		
    	try {
    		maxRooms = DB_rooms.countTotalRooms(conn);
    		request.setAttribute("maxRooms", maxRooms);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	// Load the ReservationChooseDates.jsp webpage (1/3)
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/views/ReservationChooseDates.jsp");
        dispatcher.forward(request, response);
    	
    }
 

    // doPost is a Servlet post method handler
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	

    }
        
        

   
 
}
