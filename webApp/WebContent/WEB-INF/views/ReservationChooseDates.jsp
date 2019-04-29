<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<h2>Make a Reservation (1/3)</h2>
	<p style="color: red;">${errorString}</p>
	This hotel currently has  a maximum of <b>${maxRooms}</b> rooms. The reservation system can tell you which rooms are free.
	<br/>
	<br/>

	Select the dates you would like to view and specify the number of rooms you want to book:
	<br/>
	<br/>

	<form action="${pageContext.request.contextPath}/ReservationChooseRoom">
		From: <input type="date" name="resStart" required>
		<br /> <br />
		Until: <input type="date" name="resEnd" required>
		<br /> <br />
		Number of rooms: <input type="number" name="numRooms" min="1" required> 
		<br /> <br />
		<p style="color: red;">${durationMSG}</p>
		<p style="color: red;">${durationVisit}</p>
		<p style="color: red;">${durationFromToday}</p>
		<input type="submit"> <input type="reset" value="Reset">
	</form>
	
	</div>
	
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>