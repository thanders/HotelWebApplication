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

import webApp.beans.Guest;
import webApp.dbconn.DBUtils;
import webApp.cookies.SessionUtils;

@WebServlet(urlPatterns = { "/createReservation" })
public class CreateReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CreateReservationServlet() {
        super();
    }
 
    // Show product creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createReservationView.jsp");
        dispatcher.forward(request, response);
    }
 
    // When the user enters the product information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	System.out.println("HELLLLOOOOOO!!!!");
        Connection conn = SessionUtils.getStoredConnection(request);
        int guestCardNumber = 0;
        int guestPhoneNumber =0;
        
        String guestName = (String) request.getParameter("guestName");
        String guestSurename = (String) request.getParameter("guestSurename");
        String guestAddress = (String) request.getParameter("guestAddress");
        String guestEmail = (String) request.getParameter("guestEmail");
        String gCardstr = (String) request.getParameter("guestCardNumber");
        String gPhonestr = (String) request.getParameter("guestPhoneNumber");

        try {
        	guestCardNumber = Integer.parseInt(gCardstr);
        	guestPhoneNumber = Integer.parseInt(gPhonestr);
        } catch (Exception e) { }
        
        Guest guest = new Guest(guestName, guestSurename, guestAddress, guestEmail, guestCardNumber, guestPhoneNumber);
        
        System.out.println(guest.toString());
        String errorString = null;
 
        // Product ID is the string literal [a-zA-Z_0-9]
        // with at least 1 character
        
        /*
        String regex = "\\w+";
 
        if (code == null || !code.matches(regex)) {
            errorString = "Product Code invalid!";
        }
        
        */
 
        // If error string is null, try to insert the guest object into the Guest database table
        if (errorString == null) {
            try {
                DBUtils.insertGuest(conn, guest);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
         
        }
 
        // Store information to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("guest", guest);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/reservations");
        }
    }
 
}
