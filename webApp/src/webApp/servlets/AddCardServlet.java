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

import webApp.dbconn.DBUtils;
import webApp.dbconn.DB_members;
import webApp.beans.CreditCard;
import webApp.cookies.SessionUtils;

@WebServlet(urlPatterns = { "/addCard" })

public class AddCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddCardServlet() {
		super();
	}
	  @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	 	        RequestDispatcher dispatcher = request.getServletContext()
		                .getRequestDispatcher("/WEB-INF/views/addCard.jsp");
		        dispatcher.forward(request, response); 	
	    }


	// When the user enters the product information, and click Submit.
	// This method will be called.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CreditCard card = null;
		Connection conn = SessionUtils.getStoredConnection(request);
		String cardNumber = (String) request.getParameter("card");
	System.out.println(cardNumber);
		int CardNumber = Integer.parseInt(cardNumber);

		String errorString = null;

		// If error string is null, try to insert the guest object into the Guest
		// database table
		if (errorString == null) {
			try {
	    		int id = DB_members.getStarwoodMemberId(conn, SessionUtils.getLoginedUser(request.getSession()).getUserName());
	    		 card = new CreditCard(id, CardNumber);

				DBUtils.insertCard(conn, card);
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}

		}

		// Store information to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);

		// Makes member available for page redirection
		request.setAttribute("card", card);

		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/addCard.jsp");
			dispatcher.forward(request, response);
		}

		else {
			// Redirect to the product listing page.
			//TODO Make logged in
			response.sendRedirect(request.getContextPath() + "/userInfo");
			
		}
	}

}
