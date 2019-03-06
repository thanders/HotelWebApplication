package webApp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webApp.cookies.SessionUtils;
import webApp.dbconn.DBUtils;

@WebServlet(urlPatterns = { "/deleteUser" })
public class DeleteUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	 
    public DeleteUserServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = SessionUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
 
      
        try {
            DBUtils.removeUser(conn, SessionUtils.getLoginedUser(session));
        } catch (SQLException e) {
            e.printStackTrace();
            
        } 
         
       
           response.sendRedirect(request.getContextPath() + "/logout");
        
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
