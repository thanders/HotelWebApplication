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

    	String name = (String) request.getParameter("name");
		String surename = (String) request.getParameter("surename");
		String address = (String) request.getParameter("address");
		String email = (String) request.getParameter("email");
		String cardNumber = (String) request.getParameter("cardNumber");
		String phoneNumber = (String) request.getParameter("phoneNumber");
		String userName = (String) request.getParameter("userName");
		String password = (String) request.getParameter("password");
		int CardNumber = Integer.parseInt(cardNumber);
        int PhoneNumber = Integer.parseInt(phoneNumber);
        
        Starwood member= new Starwood( name,  surename,  address,  email,  CardNumber,  PhoneNumber, userName,  password) ;
        String errorString = null;
        SessionUtils.storeLoginedUser(request.getSession(), member);
        try {
    		int id = DB_members.getStarwoodMemberId(conn, SessionUtils.getLoginedUser(request.getSession()).getUserName());
    		member.setId(id);
            DBUtils.updateMember(conn, member);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("member", member);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
 
}

