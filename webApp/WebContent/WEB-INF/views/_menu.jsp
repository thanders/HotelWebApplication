<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
    
<div style="padding: 5px;">
 
   <a href="${pageContext.request.contextPath}/">Home</a>
   |
   <a href="${pageContext.request.contextPath}/userInfo">My Account Info</a>
   |
   <a href="${pageContext.request.contextPath}/userRooms">Available Rooms</a>
   |
   <a href="${pageContext.request.contextPath}/createReservation">Make a Reservation</a>
   |
   <a href="${pageContext.request.contextPath}/createStarwood">Register Starwood Member</a>
   |
   <a id = "login" href="${pageContext.request.contextPath}/login">Login</a>
   |
   <a id = logout href="${pageContext.request.contextPath}/logout">Logout</a><!-- switch to logout path -->
    
</div>  