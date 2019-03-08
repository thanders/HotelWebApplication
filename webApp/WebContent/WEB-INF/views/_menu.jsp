<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<div class="menu">
 
   <a href="${pageContext.request.contextPath}/home">Home</a>
   |
   <a href="${pageContext.request.contextPath}/userInfo">My Account Info</a>
   |
   <a href="${pageContext.request.contextPath}/ReservationChooseDates">View Available Rooms and Book</a>
   
   <c:if test="${empty sessionScope.loginedUser}">
   |
   <a href="${pageContext.request.contextPath}/createStarwood">Register Starwood Member</a>
   </c:if>
   
   <c:if test="${empty sessionScope.loginedUser}">
    |
   <a id = "login" href="${pageContext.request.contextPath}/login">Login</a>
	</c:if>
	<c:if test="${not empty sessionScope.loginedUser}">
    |
   <a id = logout href="${pageContext.request.contextPath}/logout">Logout</a>
	</c:if>  
   
    
</div>  


