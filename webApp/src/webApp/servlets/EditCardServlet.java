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

import security.EncryptDecrypt;
import webApp.beans.CreditCard;
import webApp.beans.Starwood;
import webApp.cookies.SessionUtils;
import webApp.dbconn.DBUtils;
import webApp.dbconn.DB_members;

@WebServlet(urlPatterns = { "/editCard" })
public class EditCardServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public EditCardServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection conn = SessionUtils.getStoredConnection(request);
		EncryptDecrypt encoder = new EncryptDecrypt();
		String key = encoder.getKey();
		
		try {
			List<CreditCard> cards = DBUtils.getCards(conn, DB_members.getStarwoodMemberId(conn, SessionUtils.getLoginedUser(request.getSession()).getUserName()),key);
			for(CreditCard card : cards) {
				card.setCardNumber(SessionUtils.maskCardNumber(card.getCardNumber(), "************####"));
				System.out.println("bob");
			}
			request.setAttribute("cards", cards);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editCardView.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection conn = SessionUtils.getStoredConnection(request);
		EncryptDecrypt encoder = new EncryptDecrypt();
		String key = encoder.getKey();
		String cardToRemove = request.getParameter("CreditCard");
		String errorString = null;
		try {
			List<CreditCard> cardz = DBUtils.getCards(conn, DB_members.getStarwoodMemberId(conn, SessionUtils.getLoginedUser(request.getSession()).getUserName()),key);
			if(cardz.size()>1) {
				System.out.println(cardz.size());
				Starwood member = DBUtils.removeCard(conn, SessionUtils.getLoginedUser(request.getSession()), DB_members.getStarwoodMemberId(conn, SessionUtils.getLoginedUser(request.getSession()).getUserName()), cardToRemove, key);
				member.setCardNumber(SessionUtils.maskCardNumber(member.getCardNumber(), "************####"));
				SessionUtils.storeLoginedUser(request.getSession(), member);
				response.sendRedirect(request.getContextPath() + "/userInfo");
			}else {
				errorString = "Card not deleted you need to have at least one card as a Starwood member :)";
				request.setAttribute("errorString", errorString);
				request.setAttribute("user", SessionUtils.getLoginedUser(request.getSession()));
				RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
		dispatcher.forward(request, response);
				System.out.println("here33");
			}
			
//			// If error, forward to Edit page.
//			if (errorString != null) {
//				RequestDispatcher dispatcher = request.getServletContext()
//						.getRequestDispatcher("/WEB-INF/views/editCardView.jsp");
//				dispatcher.forward(request, response);
//				System.out.println("here33");
//			}
	
//			else{
//				response.sendRedirect(request.getContextPath() + "/userInfo");
//			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
