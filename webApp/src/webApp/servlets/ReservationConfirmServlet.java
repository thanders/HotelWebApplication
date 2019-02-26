package webApp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webApp.beans.Guest;
import webApp.beans.Reservation;
import webApp.beans.Room;
import webApp.dbconn.DBUtils;
import webApp.dbconn.DB_reservation;
import webApp.dbconn.DB_rooms;
import webApp.cookies.SessionUtils;

@WebServlet(urlPatterns = { "/reservationConfirm" })
public class ReservationConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ReservationConfirmServlet() {
        super();
    }
 
    // The doGet method - is automatically called and can handle get requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	
    }
 
    
    // The doPost method - is called upon receiving a post request
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Calls the doGet function
        doGet(request, response);
        
        // Request the current session and use it to retrieve objects from Reservation step 1
        HttpSession session = request.getSession();
        LocalDate startObj = (LocalDate) session.getAttribute("startObj");
        LocalDate endObj = (LocalDate) session.getAttribute("endObj");
        int duration = (int) session.getAttribute("duration");
        int numRooms = (int) session.getAttribute("numRooms");
        String[] choices = (String[]) session.getAttribute("choices");
        
        Double resPrice = (Double) session.getAttribute("resPrice");
        
        
        
        

        
        // Use get parameter to obtain POSTED data from form
        String guestName = (String) request.getParameter("guestName");
        String guestSurename = (String) request.getParameter("guestSurename");
        String guestAddress = (String) request.getParameter("guestAddress");
        String guestEmail = (String) request.getParameter("guestEmail");
        String gCNumber = (String) request.getParameter("guestCardNumber");
        String gPNumber = (String) request.getParameter("guestPhoneNumber");
        
        

        
        
        
        System.out.println("Session Results: " + startObj + " " + endObj + " " + duration + " " + numRooms);
        
        int guestCardNumber = Integer.parseInt(gCNumber);
        int guestPhoneNumber = Integer.parseInt(gPNumber);
        
        // Create instance of Guest class
        Guest guest = new Guest(guestName, guestSurename, guestAddress, guestEmail, guestCardNumber, guestPhoneNumber);
      
        
        String errorString = null;
        
        // If error string is null, try to insert the guest object into the Guest database table
        try {
        	
            // Connect to database
            Connection conn = SessionUtils.getStoredConnection(request);
            	
        	// Insert the new Guest instance into the database
        	int GuestID = DBUtils.insertGuest(conn, guest);
        	String status = "Paid";
        	String reservationType = "Guest";
        	
        	
        	// Insert the new Reservation into the database
        	DB_reservation.insertReservation(conn, GuestID, startObj, endObj, numRooms, status, reservationType, resPrice);
        	
        	// Create an object for the new Reservation
        	Reservation resObj = DB_reservation.queryReservation(conn, GuestID);
        	
        	int reservationNumber = resObj.getReservationId();
    
        	String price = resObj.getPriceFormatted();
        	

        	
            // Create instances of room class for each booked room
            for (int i =0; i< choices.length; i++) {
         	   String roomNumber = choices[i];
         	   
         	   Room bookedRoom = new Room(roomNumber);
         	   		bookedRoom.setReservationNumber(reservationNumber);
         	   		
         	   	DB_rooms.insertBookedRoom(conn, bookedRoom.getRoomNumber(), bookedRoom.getReservationNumber());
         	   
            }
            
            // query records of booked rooms for reservationID
            try{
            	List<Room> bookedRooms = DB_rooms.selectBookedRooms(conn, reservationNumber);
            // room related set attributes
            	request.setAttribute("bookedRooms", bookedRooms);
            	

         // Update the Reservation Object variable
        	//resObj = DB_reservation.queryReservation(conn, GuestID);
            

            }
            catch(Exception e){
            System.out.println("SQL ERROR...........");
            System.out.println(e);
            e.printStackTrace();
            }
            
            
        	
        

            // Store information to request attribute, before forward to views. 
            
            // Reservation related set attributes
        	request.setAttribute("resNumber", reservationNumber);
        	request.setAttribute("start", resObj.getStart());
        	request.setAttribute("numberRooms", resObj.getNumberRooms());
        	request.setAttribute("end", resObj.getEnd());
        	request.setAttribute("status", resObj.getStatus());
        	request.setAttribute("bookingDate", resObj.getBookingDate());
        	request.setAttribute("reservationType", resObj.getReservationType());
        	request.setAttribute("reservationPrice", resObj.getPriceFormatted());
        	
        	// Guest related set attributes
        	request.setAttribute("guestName", guest.getGuestName());
        	request.setAttribute("guestSurname", guest.getGuestSurename());
        	request.setAttribute("guestAddress", guest.getGuestAddress());
        	request.setAttribute("guestEmail", guest.getGuestEmail());
        	request.setAttribute("guestCardNumber", guest.getGuestCardNumber());
        	request.setAttribute("guestPhoneNumber", guest.getGuestPhoneNumber());
            request.setAttribute("errorString", errorString);
            

            
	        RequestDispatcher dispatcher = request.getServletContext()
	        .getRequestDispatcher("/WEB-INF/views/reservationConfirmView.jsp");
	        dispatcher.forward(request, response);
               
       } catch (SQLException e) {
    	   e.printStackTrace();
    	   errorString = e.getMessage();
       }
    }
        

 
}