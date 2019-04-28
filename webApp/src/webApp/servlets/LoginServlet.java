package webApp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webApp.beans.Members;
import webApp.beans.Starwood;
import webApp.dbconn.DBUtils;
import webApp.cookies.SessionUtils;

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


    	Enumeration e = (Enumeration) (session.getAttributeNames());

        while ( e.hasMoreElements())
        {
            String tring;
            if((tring = (String) e.nextElement())!=null)
            {
                System.out.println( tring);
                
            }

        }
    	 
		
		// Check User has logged on
		Starwood loginedUser = SessionUtils.getLoginedUser(session);

		// Not logged in
		if (loginedUser != null) {
			// Redirect to login page.
			request.setAttribute("errorString", "Not logged in. Please login.");
			response.sendRedirect(request.getContextPath() + "/userInfo");
			return;
		}
		
		RequestDispatcher dispatcher //
		= this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

		dispatcher.forward(request, response);

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
		Members userlogInDetails = null;
		if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Required username and password!";
		} else {
			Connection conn = SessionUtils.getStoredConnection(request);
			try {
				// Find the user in the DB.
				//edit to check for username
				user = DBUtils.findStarwoodMember(conn, userName);
				userlogInDetails = DBUtils.findMember(conn,userName,password);
				if (user == null && userlogInDetails ==null) {
					hasError = true;
					errorString = "User Name or password invalid";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}
		// If error, forward to /WEB-INF/views/login.jsp
		if (hasError) {
			//fix
			user = new Starwood();
			user.setUserName(userName);
			userlogInDetails= new Members(userName,password);


			// Store information in request attribute, before forward.
			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);

			// Forward to /WEB-INF/views/login.jsp
			RequestDispatcher dispatcher //
			= this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

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

}