
package webApp.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import security.EncryptDecrypt;
import webApp.dbconn.DBUtils;
import webApp.dbconn.DB_members;
import webApp.beans.CreditCard;
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
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		int cvvNumber = 0;
		String name = (String) request.getParameter("name");
		String surename = (String) request.getParameter("surename");
		String address = (String) request.getParameter("address");
		String email = (String) request.getParameter("email");
		String cardNumber = (String) request.getParameter("CardNumber");
		String phoneNumber = (String) request.getParameter("phoneNumber");
		String userName = (String) request.getParameter("userName");
		String password = (String) request.getParameter("password");
		int PhoneNumber = Integer.parseInt(phoneNumber);
		String CardDate = (String) request.getParameter("ExpiryDate");
		String cvv = (String) request.getParameter("CVV");
		cvvNumber = Integer.parseInt(cvv);
		EncryptDecrypt encoder = new EncryptDecrypt();
		String key = encoder.getKey();


		// Encrypt
		String encryptedPassword="",encryptedCredit="";
		try {
			encryptedPassword = EncryptDecrypt.encrypt(password,key);
			encryptedCredit = EncryptDecrypt.encrypt(cardNumber,key);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		LocalDate expiry = LocalDate.parse(CardDate, f);
		//		System.out.println("key1: " + key.toString());
		Starwood member = new Starwood(name, surename, address, email, encryptedCredit, PhoneNumber, userName, encryptedPassword,
				cvvNumber, expiry);
		String errorString = null;

		// If error string is null, try to insert the guest object into the Guest
		// database table
		if (errorString == null) {
			try {
				errorString = DB_members.insertMember(conn, member);
				if(errorString == null) {
					DB_members.insertMemberLogIn(conn, member);

					BigInteger id = DB_members.getStarwoodMemberId(conn, userName);
					CreditCard card = new CreditCard(encryptedCredit, id, cvvNumber, expiry);

					DBUtils.insertCard(conn, card);
				}

			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();

			}

		}

		// Store information to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);
		try {

			member.setCardNumber(EncryptDecrypt.decrypt(encryptedCredit,key));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Makes member available for page redirection
		request.setAttribute("member", member);

		// If error, forward to Edit page.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/registerStarwood.jsp");
			dispatcher.forward(request, response);
		}

		else {
			// Redirect to the product listing page.
			// TODO Make logged in
			HttpSession session = request.getSession();
			SessionUtils.storeLoginedUser(session, member);
			// Redirect to userInfo page.
			response.sendRedirect(request.getContextPath() + "/userInfo");

		}
	}

}
