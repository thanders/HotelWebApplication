package webApp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webApp.beans.Starwood;
import webApp.cookies.SessionUtils;

@WebServlet(urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	 
    public LogoutServlet() {
        super();
    }
    
 // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	HttpSession session = request.getSession();
    	 
        // Check User has logged on
        Starwood loginedUser = SessionUtils.getLoginedUser(session);
        
     // Logged in
        if (loginedUser != null) {
            // Redirect to home page.
        	SessionUtils.removeLoginedUser(session);
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
 
        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        request.setAttribute("errorString", "Not logged in");
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
        dispatcher.forward(request, response);
 
    }

}
