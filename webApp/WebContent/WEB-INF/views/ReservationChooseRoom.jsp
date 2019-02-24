<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservations</title>

</head>

<body>

	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h2>Reservation (2/3)</h2>
	
	Success, rooms are available between those dates.
	
	<br/><br/>
	
	Below is a list of available rooms from <b>${startDate}</b> until <b>${endDate}</b> (${duration} days)
	
	<br/>
	<br/>
	
	Please choose any <b>${numRooms}</b>:
	
	<br/><br/>
	
	<form method="POST" action="${pageContext.request.contextPath}/ReservationChooseRoom">
	
	
	
	<table>
	
	<tr>
		<th> Room Number </th>
		<th> Choice </th>
	</tr>
	
	<c:forEach items="${availableRooms}" var="room" >
	<tr>
		<td> ${room.roomNumber} </td>
		<td> <input type="checkbox" name="choices" value="${room.roomNumber}"> </td>
	</tr>
	</c:forEach>
	
	</table>

	<input type="submit" name="submit" value="submit">
	</form>
	
	
	
	

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>