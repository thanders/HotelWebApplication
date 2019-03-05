<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Reservations</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h2>Reservations</h2>
 
    <p style="color: red;">${errorString}</p>
 	
 	Thank you for booking a room with us. Your reservations are below:
 	<br/> <br/>
 	
    <table border="1" style="text-align:center" >
    	<tr>
    		<th>Reservation ID</th>
    		<th>Start Date</th>
    		<th>End Date</th>
    		<th>Number of rooms</th>
    		<th>Status</th>
    		<th>Price</th>
    		<th>Show Booking Confirmation</th>
    	</tr>
    	<c:forEach items="${reservations}" var="reservation" > 
    	<tr>
    		<td>${reservation.reservationId}</td>
    		<td>${reservation.start}</td>
    		<td>${reservation.end}</td>
    		<td>${reservation.numberRooms}</td>
    		<td>‎${reservation.status}</td>
    		<td>‎€${reservation.price}</td>
    		<td>‎<form method="POST" action="${pageContext.request.contextPath}/reservationDisplay">
    			<input type="hidden" name="resNumber" value="${reservation.reservationId}"/>
    			<input type="submit" value="Show confirmation" />
    			</form></td>
    	</tr>
    	</c:forEach>
    </table>
    
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>