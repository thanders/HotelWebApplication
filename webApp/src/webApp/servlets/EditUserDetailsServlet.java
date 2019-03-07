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

import com.mysql.cj.util.StringUtils;

import webApp.beans.Starwood;
import webApp.dbconn.DBUtils;
import webApp.dbconn.DB_members;
import webApp.cookies.SessionUtils;

@WebServlet(urlPatterns = { "/editDetails" })
public class EditUserDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditUserDetailsServlet() {
		super();
	}

	// Show product edit page.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = SessionUtils.getStoredConnection(request);

		//         String code = (String) request.getParameter("code");

		Starwood member = null;

		String errorString = null;

		try {
			int id = DB_members.getStarwoodMemberId(conn, SessionUtils.getLoginedUser(request.getSession()).getUserName());
			member = DB_members.findStarwoodMember(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// If no error.
		// The product does not exist to edit.
		// Redirect to productList page.
		if (errorString != null && member == null) {
			response.sendRedirect(request.getServletPath() + "/userInfo");
			return;
		}

		// Store errorString in request attribute, before forward to views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("member", member);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
		dispatcher.forward(request, response);
	}

	// After the user modifies the product information, and click Submit.
	// This method will be executed.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = SessionUtils.getStoredConnection(request);
		Starwood currentUser = SessionUtils.getLoginedUser(request.getSession());
		try {
			currentUser.setId(DB_members.getStarwoodMemberId(conn, currentUser.getUserName()));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String errorString = null;
		String changesString = "";


		String name = (String) request.getParameter("name");
		String surename = (String) request.getParameter("surename");
		String address = (String) request.getParameter("address");
		String email = (String) request.getParameter("email");
		String cardNumber = (String) request.getParameter("cardNumber");
		String phoneNumber = (String) request.getParameter("phoneNumber");
		String userName = (String) request.getParameter("userName");
		String password = (String) request.getParameter("password");
		try {
			
			
			if (name != "" ) {
				currentUser.setName(name);
				DBUtils.updateMemberName(conn, currentUser);
				changesString +=" " + "Name has been updated";
			}
			
			if (surename != "" ) {
				currentUser.setSurename(surename);
				DBUtils.updateMemberSurname(conn, currentUser);
				changesString +=" " + "Surname has been updated";
			} 
			
			if (address != "" ) {
				System.out.println("here1");
				currentUser.setAddress(address);
				DBUtils.updateMemberAddress(conn, currentUser);
				System.out.println("here2");
				changesString +=" " + "Address has been updated";
			} 
			
			if (email != "" ) {
				currentUser.setEmail(email);
				DBUtils.updateMemberEmail(conn, currentUser);
				changesString +=" " + "Email has been updated";
			} 
			
			if (cardNumber != "" ) {
				
				if(StringUtils.isStrictlyNumeric(cardNumber)) {
					
					int cardNo = Integer.parseInt(cardNumber);
					int oldNo = currentUser.getCardNumber();
					currentUser.setCardNumber(cardNo);
					DBUtils.updateMemberCreditCard(conn, currentUser,oldNo);
					changesString +=" " + "Credit Card has been updated";
					
				}else {
					errorString = "Please enter a number for Credit Card";
				}
				
			} 
			
			if (phoneNumber != "" ) {
				
				if(StringUtils.isStrictlyNumeric(phoneNumber)) {
					
					int phoneNo = Integer.parseInt(phoneNumber);
					currentUser.setPhoneNumber(phoneNo);
					DBUtils.updateMemberPhoneNumber(conn, currentUser);
					changesString +=" " + "Phone Number has been updated";
					
				}else {
					errorString = "Please enter a number for phone number";
				}
				
			} 
			
			if (userName != "" ) {
				currentUser.setUserName(userName);
				DBUtils.updateMemberUsername(conn, currentUser);
				changesString +=" " + "Username has been updated";
			}
			
			if (password != "" ) {
				currentUser.setPassword(password);
				DBUtils.updateMemberPassword(conn, currentUser);
				changesString +=" " + "Password has been updated";
			}
			
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		
		// Store infomation to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("member", currentUser);
		request.setAttribute("changesString", changesString);
		System.out.println(changesString);

		// If error, forward to Edit page.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
			dispatcher.forward(request, response);
		}
		// If everything nice.
		// Redirect to the user information page.
		else {
			response.sendRedirect(request.getContextPath() + "/userInfo");
		}
	}

}

