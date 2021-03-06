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

import security.EncryptDecrypt;
import webApp.beans.AdHoc;
import webApp.beans.Starwood;
import webApp.cookies.SessionUtils;
import webApp.dbconn.DBUtils;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	// Show Login page.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		// Forward to /WEB-INF/views/loginView.jsp
		// (Users can not access directly into JSP pages placed in WEB-INF)

		// Check User has logged on
		Starwood loginedUser = SessionUtils.getLoginedUser(session);

		// Not logged in
		if (loginedUser != null) {
			// Redirect to login page.
			request.setAttribute("errorString", "Not logged in. Please login.");
			response.sendRedirect(request.getContextPath() + "/userInfo");
			return;
		}
		
		try {
		
			Integer loginCount = (Integer) session.getAttribute("loginCount");
		
			if (loginCount != null && loginCount >3) {
				
				// Forward to /WEB-INF/views/login.jsp
				RequestDispatcher dispatcher //
						= this.getServletContext().getRequestDispatcher("/loginCount");
	
				dispatcher.forward(request, response);
			}
			
		

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

		dispatcher.forward(request, response);
		
		}
		
		catch (Exception e) {
			System.out.println("ERRORRRRRRS:");
			e.printStackTrace();
		}

	}

	// When the user enters userName & password, and click Submit.
	// This method will be executed.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String rememberMeStr = request.getParameter("rememberMe");
		boolean remember = "Y".equals(rememberMeStr);

		Starwood user = null;
		boolean hasError = false;
		String errorString = null;
		
//		Starwood userlogInDetails = null;
		if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Required username and password!";
		} else {
			Connection conn = SessionUtils.getStoredConnection(request);
			try {
				///TODO
				EncryptDecrypt encoder = new EncryptDecrypt();
				String key = encoder.getKey();				
				// Find the user in the DB.
				user = DBUtils.findStarwoodMember(conn, userName,password, key);

				if (user == null) {					
					hasError = true;
					errorString = "User Name or password invalid, both are case sensitive";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}
		
		// Assuming user posted data, Try to login
		// If loginCount > 3, forward to /WEB-INF/views/login.jsp
		
		try {
		
			if (hasError) {
				// fix
				user = new Starwood();
				user.setUserName(userName);
				user.setPassword(password);
	
				// Store information in request attribute, before forward.
				request.setAttribute("errorString", errorString);
				request.setAttribute("user", user);
	
				// Forward to /WEB-INF/views/login.jsp
				RequestDispatcher dispatcher //
						= this.getServletContext().getRequestDispatcher("/loginCount");
	
				dispatcher.forward(request, response);
			}
			// If no error
			// Store user information in Session
			// And redirect to userInfo page.
			else {
				HttpSession session = request.getSession();

				SessionUtils.storeLoginedUser(session, user);
	
				// If user checked "Remember me".
				if (remember) {
					SessionUtils.storeUserCookie(response, user);
				}
				// Else delete cookie.
				else {
					SessionUtils.deleteUserCookie(response);
				}
	
				// Redirect to userInfo page.
				
				response.sendRedirect(request.getContextPath() + "/userInfo");
			}
			
		}
		
		catch (Exception e) {
			System.out.println("ERRORRRRRRS:");
			e.printStackTrace();
			hasError = true;
			errorString = e.getMessage();
		}
		
		
	}
		

}