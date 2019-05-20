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
import javax.servlet.http.HttpSession;

import org.security.EncryptDecrypt;
import org.beans.Starwood;
import org.cookies.SessionUtils;
import org.dbconn.DBUtils;

@WebServlet(urlPatterns = { "/deleteAccount"})
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	   public DeleteAccountServlet() {
	       super();
	   }
	 
	   
	   @Override
	   protected void doGet(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {

		   HttpSession session = request.getSession();
			// Forward to /WEB-INF/views/loginView.jsp
			// (Users can not access directly into JSP pages placed in WEB-INF)

			// Check User has logged on
			Starwood loginedUser = SessionUtils.getLoginedUser(session);

			// Not logged in
			if (loginedUser == null) {
				// Redirect to login page.
				request.setAttribute("errorString", "Not logged in. Please login.");
				response.sendRedirect(request.getContextPath() + "/login");
				return;
			}
		   
	       
	       // (Users can not access directly into JSP pages placed in WEB-INF)
	       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("views/deleteAccountView.jsp");
	        
	       dispatcher.forward(request, response);
	        
	   }
	   
	   
	 
	   @Override
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
		   String password = request.getParameter("password");
		   String errorString = null;
		   boolean hasError = false;
		   HttpSession session = request.getSession();
		   
		   if (password == null || password.length() == 0) {
				hasError = true;
				errorString = "Required username and password!";
			}
		   else {
				Connection conn = SessionUtils.getStoredConnection(request);
				

				Starwood loggedInUser = SessionUtils.getLoginedUser(session);
				String key = new EncryptDecrypt().getKey();
				String actualpass = "";
				try {
					actualpass = EncryptDecrypt.decrypt(loggedInUser.getPassword(), key);
					
					if(actualpass.equals(password)) {
						DBUtils.removeUser(conn, loggedInUser);
					}else {
						errorString = "Incorrect Password";
						hasError = true;
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		   
		// If error, forward to /WEB-INF/views/login.jsp
			if (hasError) {
				
				// Store information in request attribute, before forward.
				request.setAttribute("errorString", errorString);
				

				// Forward to /WEB-INF/views/login.jsp
				RequestDispatcher dispatcher //
						= this.getServletContext().getRequestDispatcher("/views/deleteAccountView.jsp");

				dispatcher.forward(request, response);
			}
			
			else {
				response.sendRedirect(request.getContextPath() + "/logout");
			}
		   
	   }
}
