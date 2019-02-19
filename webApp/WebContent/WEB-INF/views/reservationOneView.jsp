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
 
    <h2>Reservation (1/3)</h2>
    
    Select the dates you would like to book:<br/> <br/>
    
    <form action="${pageContext.request.contextPath}/createReservation">
  	From: <input type="date" name="resStart"> <br/> <br/>
   	Until: <input type="date" name="resEnd"> <br/> <br/>
   	<input type="submit">
	</form>
 


 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>