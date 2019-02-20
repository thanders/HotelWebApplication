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
 
    <h2>Reservation confirmation</h2>
 
    <p style="color: red;">${errorString}</p>
 	
 	Thank you for booking a room with us. The confirmation details are below:
    
    <h4>Personal information</h4>
    
    <table border="1" cellspacing="0" >
    <tr>
        <th>Reservation ID</th>
    	<th>Name</th>
    	<th>Surname</th>
    	<th>Address</th>
    	<th>Email</th>
    	<th>Card number</th>
    	<th>Phone number</th>

    </tr>
          <tr>
          	<td>${reservationObj}</td>
             <td>${guestName}</td>
             <td>${guestSurname}</td>
             <td>${guestAddress}</td>
             <td>${guestEmail}</td>
             <td>${guestCardNumber}</td>
             <td>${guestPhoneNumber}</td>
          </tr>
    </table>
    
    <h4>Room booking</h4>
    
        <table border="1" cellspacing="0" >
    <tr>
    	<th>Number of rooms booked</th>
    	<th>Cost per room</th>
    	<th>Total booking cost</th>
    </tr>
          <tr>
          	<td>Test</td>
             <td>Test</td>
             <td>Test</td>
          </tr>
    </table>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>