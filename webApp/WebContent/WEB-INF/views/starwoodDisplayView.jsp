<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Reservations</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
 	<div class="container">
 	
    <h2>Reservations</h2>
 
    <p style="color: red;">${errorString}</p>
 	
 	Thank you for booking a room with us. Your reservations are below:
 	<br/> <br/>
 	
    <table class="viewResMembers" >
    	<tr>
    		<th>Reservation ID</th>
    		<th>Start Date</th>
    		<th>End Date</th>
    		<th>Number of rooms</th>
    		<th>Status</th>
    		<th>Price</th>
    		<th>Cancel reservation</th>
    	</tr>
    	<c:forEach items="${reservations}" var="reservation" > 
    	<tr>
    		<td>${reservation.reservationId}</td>
    		<td>${reservation.start}</td>
    		<td>${reservation.end}</td>
    		<td>${reservation.numberRooms}</td>
    		<td>‎${reservation.status}</td>
    		<td>‎€${reservation.price}</td>
    		<td class="resMemberCancelSubmit">‎<form method="POST" action="${pageContext.request.contextPath}/reservationCancel">
    			<input type="hidden" name="resNumber" value="${reservation.reservationId}"/>
    			<input type="submit" value="Cancel" />
    			</form></td>
    	</tr>
    	</c:forEach>
    </table>
    
    </div>
    
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>