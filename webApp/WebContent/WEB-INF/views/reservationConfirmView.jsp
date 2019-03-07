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
 
    <h2>Reservation confirmation</h2>
 
    <p style="color: red;">${errorString}</p>
 	
 	Thank you for booking a room with us. Your reservation number is <b>${resNumber}</b>
 	<br/> <br/>
 	The booking is from <b>${start}</b> until <b>${end}</b>
 	<br/> <br/>
 	The confirmation details are below:
 	<br/> <br/>
    
    <table border="1" cellspacing="0" >
    	<tr>
    		<th>Name</th>
    		<th>Surname</th>
    		<th>Number of rooms</th>
    		<th>Room numbers</th>
    		<th>Price</th>
    	</tr>
    	<tr>
    		<td>${guestName}</td>
    		<td>${guestSurname}</td>
    		<td>${numberRooms}</td>
    		<td><c:forEach items="${bookedRooms}" var="room" >${room.getRoomNumber()}  </c:forEach></td>
    		<td>${reservationPrice}</td>
    	</tr>
    </table>
    
    <br/> <br/>
    
    <table border="1" cellspacing="0" >
    	<tr>
    		<th>Booking Date</th>
    		<th>Reservation Type</th>
    		<th>Status</th>
    	</tr>
    	<tr>
    		<td>${bookingDate}</td>
    		<td>${reservationType}</td>
    		<td>${status}</td>
    	</tr>
    </table>
    
    <br/> <br/>
    
    <p style="color: blue;">${CancelMSG}</p>
    
    <c:choose>
    <c:when test="${status=='Active'}">
    <form method="POST" action="${pageContext.request.contextPath}/reservationCancel">
    <input type="hidden" name="cancel" value="cancel"/>
    <input type="hidden" name="resNumber" value="${resNumber}"/>
    <input type="submit" name="submit" value="Cancel reservation"/>
    </form>
 	</c:when>
 	</c:choose>
 	
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>