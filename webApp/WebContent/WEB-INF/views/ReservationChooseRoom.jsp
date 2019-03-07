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
	
	<div class="container">

	<h2>Reservation (2/3)</h2>
	
	<b>${numberAvailableRooms}</b> rooms are available between those dates. <b>${startDate}</b> until <b>${endDate}</b> (${duration} days)
	
	<br/><br/>
	
	Please choose any <b>${numRooms}</b>:
	
	<br/><br/>
	
	<form method="POST" action="${pageContext.request.contextPath}/ReservationChooseRoom">
	
	
	
	<table>
	
	<tr>
		<th> Room Number </th>
		<th> Capacity </th>
		<th> Price </th>
		<th> Choice </th>
	</tr>
	
	<c:forEach items="${availableRooms}" var="room" >
	<tr>
		<td> ${room.roomNumber} </td>
		<td> ${room.capacity} </td>
		<td>
${room.priceFormatted} 
		</td>
		<td> <input type="checkbox" name="choices" value="${room.roomNumber}"> </td>
	</tr>
	</c:forEach>
	
	</table>
	<tr><p style="color: red;"> ${validationCount} </p></tr>
	
	<input type="submit" name="submit" value="submit">
	</form>
	
	
	
	</div>

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>