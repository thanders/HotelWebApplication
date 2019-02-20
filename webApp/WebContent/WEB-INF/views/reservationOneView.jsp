<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservations</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>

	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h2>Reservation (1/2)</h2>

	Select the dates you would like to book:
	<br/>
	<br/>

	<form action="${pageContext.request.contextPath}/createReservation">
		From: <input type="date" name="resStart" required>
		<br /> <br />
		Until: <input type="date" name="resEnd" required>
		<br /> <br />
		Number of Guests: <input type="number" name="numRooms" min="1" required> 
		<br /> <br />

		<input type="submit"> <input type="reset" value="Reset">
	</form>

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>