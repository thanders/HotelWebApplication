package webApp.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/ReservationChooseDates" })
public class ReservationChooseDates extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
 
    public ReservationChooseDates() {
        super();
    }
 
    
    // Show product creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Load the ReservationChooseDates.jsp webpage (1/3)
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/ReservationChooseDates.jsp");
        dispatcher.forward(request, response);
    	
    }
 

    // doPost is a Servlet post method handler
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	


    }
        
        

   
 
}
