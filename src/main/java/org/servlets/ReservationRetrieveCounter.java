package org.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/submitCount" })
public class ReservationRetrieveCounter extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("deprecation")
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {

		HttpSession session = request.getSession();       // this is how to get a session object  
		
		// session timeout after 2 minutes
		session.setMaxInactiveInterval(2*60);
		
		// Create a variable submitCount if it doesn't already exist
		Integer submitCount = (Integer) session.getAttribute("submitCount");  // retrieving value from session object
	
		if(submitCount == null) {
			submitCount = new Integer(1);
		}
		else {
			submitCount = new Integer(submitCount.intValue()+1) ;
		}                         
		
		// set submitCount so you can use it on the JSP
		session.setAttribute("submitCount", submitCount);              // storing the value with session object
		
		// If submitCount greater than 3, you are locked out - redirect to locked out page
		if (submitCount >3) {
			
			request.setAttribute("purposeLocked1", "viewing your reservation");
			request.setAttribute("purposeLocked2", "view your reservation");
			request.setAttribute("count", submitCount);

			RequestDispatcher rd = request.getRequestDispatcher("/lockedOut");
			rd.forward(request,response);
			
		}
		// Otherwise forward request to the ReservationDisplay page
		else {
			
			// Forward to reservationDisplayView
			RequestDispatcher dispatcher //
			= request.getRequestDispatcher("/views/reservationDisplayView.jsp");

			dispatcher.forward(request, response);
		}
	}
}
