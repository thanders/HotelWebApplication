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
 	
 	Thank you for booking a room with us. Your reservation number is <b>${resNumber}</b>
 	<br/> <br/>
 	The booking is from <b>${start}</b> until <b>${end}</b>
 	<br/> <br/>
 	The confirmation details are below:
 	<br/> <br/>
 	<p style="color: red;">${cantCancel}</p>
    
    <table border="1" cellspacing="0" >
    	<tr>
    		<th>Guest Name</th>
    		<th>Guest Surname</th>
    	</tr>
    	<tr>
    		<td><c:forEach items="${List}" var="list" >
    		<td>${guestName}</td>
    		<td>${guestSurname}</td>    	
    		 </c:forEach></td>
    		



    	</tr>
    </table>
    
    
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>