<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<nav class="menu">

<ul>
	<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
	
	<c:if test="${not empty sessionScope.loginedUser}">
   		<li><a href="${pageContext.request.contextPath}/userInfo">My Account Info</a></li>
	</c:if> 
  	
  	<li><a href="${pageContext.request.contextPath}/ReservationChooseDates">View Available Rooms and Book</a></li>
  	
  	<c:if test="${empty sessionScope.loginedUser}">
  		<li><a href="${pageContext.request.contextPath}/createStarwood">Register Starwood Member</a></li>
   	</c:if>
   
   	<c:if test="${empty sessionScope.loginedUser}">
   		<li><a id = "login" href="${pageContext.request.contextPath}/login">Login</a><li>
	</c:if>

	<c:if test="${not empty sessionScope.loginedUser}">
		<li><a id = logout href="${pageContext.request.contextPath}/logout">Logout</a></li>
	</c:if>  
  	
  	<li style="float:right"><a class="active" href="${pageContext.request.contextPath}/reservationDisplay">View Reservations</a></li>
</ul>
 
    
</nav>  


