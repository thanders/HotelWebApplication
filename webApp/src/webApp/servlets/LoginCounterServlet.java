package webApp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/loginCount" })
public class LoginCounterServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {

		HttpSession sessionLoginCount = request.getSession();       // this is how to get a session object  
		
		// session timeout after 2 minutes
		sessionLoginCount.setMaxInactiveInterval(2*60);
		
		// Create a variable submitCount if it doesn't already exist
		Integer loginCount = (Integer) sessionLoginCount.getAttribute("loginCount");  // retrieving value from session object
	
		if(loginCount == null) {
			loginCount = new Integer(1);
			
			// set submitCount so you can use it on the JSP
			sessionLoginCount.setAttribute("loginCount", loginCount);  
			
			// Forward to reservationDisplayView
			RequestDispatcher dispatcher //
			= request.getRequestDispatcher("/WEB-INF/views/loginView.jsp");

			dispatcher.forward(request, response);
			
		}
		
		// If loginCount not null or less than 3 forward request to the ReservationDisplay page
		else if (loginCount < 3) {
			
			loginCount +=1 ;

			// set submitCount so you can use it on the JSP
			sessionLoginCount.setAttribute("loginCount", loginCount);  
			
			// Forward to reservationDisplayView
			RequestDispatcher dispatcher //
			= request.getRequestDispatcher("/WEB-INF/views/loginView.jsp");

			dispatcher.forward(request, response);
		}
		
		// If submitCount greater than 3, you are locked out - redirect to locked out page
		else{
			
			loginCount =4 ;
			
			// set session login count to loginCount
			sessionLoginCount.setAttribute("loginCount", loginCount);
			request.setAttribute("locked", loginCount);
			request.setAttribute("purposeLocked1", "logging into your account");
			request.setAttribute("purposeLocked2", "log into your account");
			sessionLoginCount.setAttribute("count", loginCount);
			
			RequestDispatcher rd = request.getRequestDispatcher("/lockedOut");
			rd.forward(request,response);
			
		}
		
	}
}
