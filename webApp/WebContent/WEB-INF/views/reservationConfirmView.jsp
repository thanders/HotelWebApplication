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
 	
 	<h3>Your reservation details (Reservation Class)</h3>
 	
    <table border="1" cellspacing="0" >
       <tr>
          <th>Reservation ID</th>
          <th>Reserved By</th>

       </tr>
       <c:forEach items="${reservationList}" var="res" >
          <tr>
             <td>${res.reservationId}</td>
             <td>${res.reservedBy}</td>
          </tr>
       </c:forEach>
    </table>
    
    <h3>Your personal details (Guest Class)</h3>
    
    
    <table border="1" cellspacing="0" >
    <tr>
    	<th>Name</th>
    	<th>Surname</th>
    	<th>Address</th>
    	<th>Email</th>
    	<th>Card number</th>
    	<th>Phone number</th>

    </tr>
       <c:forEach items="${latestGuest}" var="guest" >
          <tr>
             <td>${guest.guestName}</td>
             <td>${guest.guestSurename}</td>
             <td>${guest.guestAddress}</td>
             <td>${guest.guestEmail}</td>
             <td>${guest.guestCardNumber}</td>
             <td>${guest.guestPhoneNumber}</td>
          </tr>
       </c:forEach>
    </table>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>