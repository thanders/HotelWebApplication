
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
import webApp.beans.Starwood;
import webApp.cookies.SessionUtils;

@WebServlet(urlPatterns = { "/createStarwood" })

public class CreateStarwoodUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateStarwoodUserServlet() {
		super();
	}
	  @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	 	        RequestDispatcher dispatcher = request.getServletContext()
		                .getRequestDispatcher("/WEB-INF/views/registerStarwood.jsp");
		        dispatcher.forward(request, response); 	
	    }


	// When the user enters the product information, and click Submit.
	// This method will be called.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = SessionUtils.getStoredConnection(request);

		String name = (String) request.getParameter("name");
		String surename = (String) request.getParameter("surename");
		String address = (String) request.getParameter("address");
		String email = (String) request.getParameter("email");
		String cardNumber = (String) request.getParameter("cardNumber");
		String phoneNumber = (String) request.getParameter("phoneNumber");
		String userName = (String) request.getParameter("userName");
		String password = (String) request.getParameter("password");
		String membershipStatus = (String) request.getParameter("membershipStatus");
		System.out.println("TEST Card" + " " + cardNumber);

		System.out.println("int Card" + " " + cardNumber);
		Starwood member = new Starwood(name, surename, address, email, cardNumber, phoneNumber, userName, password,
				membershipStatus);

		System.out.println(member.toString());
		String errorString = null;

		// If error string is null, try to insert the guest object into the Guest
		// database table
		if (errorString == null) {
			try {
				DBUtils.insertMember(conn, member);
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}

		}

		// Store information to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);

		// Makes member available for page redirection
		request.setAttribute("starwoodNew", member);

		// If error, forward to Edit page.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/registerStarwood.jsp");
			dispatcher.forward(request, response);
		}

		else {
			// Redirect to the product listing page.
			//TODO Make logged in
			response.sendRedirect(request.getContextPath() + "/userInfo");
			
		}
	}

}