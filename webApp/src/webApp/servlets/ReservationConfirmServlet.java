package webApp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webApp.beans.Guest;
import webApp.beans.Reservation;
import webApp.dbconn.DBUtils;
import webApp.cookies.SessionUtils;

@WebServlet(urlPatterns = { "/reservationConfirm" })
public class ReservationConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ReservationConfirmServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = SessionUtils.getStoredConnection(request);
 
        String errorString = null;
        
        // Retrieve the reservation list
        List<Reservation> list = null;
        try {
            list = DBUtils.queryReservations(conn);
            System.out.println("list created and returned successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("reservationList", list);
        
        System.out.println("ReservationList retrieved");
        System.out.println(list.toString());
        
        // Retrieve the reservation list
        List<Guest> glist = null;
        try {
            glist = DBUtils.QueryLatestGuest(conn);
            System.out.println("glist created and returned successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("latestGuest", glist);
        
        System.out.println("Guest list retrieved");
        System.out.println(glist.toString());
        
        
    
        // Forward to /WEB-INF/views/userRoomsView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/reservationConfirmView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}