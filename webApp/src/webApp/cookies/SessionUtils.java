package webApp.cookies;

import java.sql.Connection;

import javax.crypto.SecretKey;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webApp.beans.*;

public class SessionUtils {
	
	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	 
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
 
    // Store Connection in request attribute.
    // (Information stored only exist during requests)
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }
 
    // Get the Connection object has been stored in attribute of the request.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }
 
    // Store user info in Session.
    public static void storeLoginedUser(HttpSession session, Starwood loginedUser) {
        // On the JSP can access via ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
      //setting session to expire in 30 minutes
		session.setMaxInactiveInterval(30*60);
    }
    
 // Removes user info in Session.
    public static void removeLoginedUser(HttpSession session,HttpServletResponse response) {
    	deleteUserCookie(response);
        session.removeAttribute("loginedUser");
        session.invalidate();
    }
 
    // Get the user information stored in the session.
    public static Starwood getLoginedUser(HttpSession session) {
        Starwood loginedUser = (Starwood) session.getAttribute("loginedUser");
        return loginedUser;
    }
    // Removes key info in Session.
    public static void removeSessionkey(HttpSession session,HttpServletResponse response) {
        session.removeAttribute("key");
    }
 
    public static void storeKey(HttpSession session, SecretKey key) {
        session.setAttribute("key", key);
    }
//    // Get the key information stored in the session.
//    public static SecretKey getSessionkey(HttpSession session) {
//    	SecretKey key = (SecretKey)session.getAttribute("key");
//        return key;
//    }
 
    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, Starwood user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
        // 1 day (Converted to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        cookieUserName.setHttpOnly(true);
        cookieUserName.setSecure(true);
        response.addCookie(cookieUserName);
    }
    
    public static String maskCardNumber(String cardNumber, String mask) {

        // format the number
        int index = 0;
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == '#') {
                maskedNumber.append(cardNumber.charAt(index));
                index++;
            } else if (c == '*') {
                maskedNumber.append(c);
                index++;
            } else {
                maskedNumber.append(c);
            }
        }

        // return the masked number
        return maskedNumber.toString();
    }
 
    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
 
    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        // 0 seconds (This cookie will expire immediately)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
    

}
