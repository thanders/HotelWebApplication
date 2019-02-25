<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<div style="padding: 5px;">
 
   <a href="${pageContext.request.contextPath}/">Home</a>
   |
   <a href="${pageContext.request.contextPath}/userInfo">My Account Info</a>
   |
   <a href="${pageContext.request.contextPath}/userRooms">Available Rooms</a>
   |
   <a href="${pageContext.request.contextPath}/ReservationChooseDates">Make a Reservation</a>
   |
   <a href="${pageContext.request.contextPath}/createStarwood">Register Starwood Member</a>
   <c:if test="${empty sessionScope.loginedUser}">
    |
   <a id = "login" href="${pageContext.request.contextPath}/login">Login</a>
	</c:if>
	<c:if test="${not empty sessionScope.loginedUser}">
    |
   <a id = logout href="${pageContext.request.contextPath}/logout">Logout</a>
	</c:if>  
   
    
</div>  


