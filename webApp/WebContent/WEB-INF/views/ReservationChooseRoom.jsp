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

	You have requested:
	<br/><br/>
	
	<b>${numRooms}</b> rooms
	
	<br/><br/>
	
	From <b>${startDate}</b> until <b>${endDate}</b> (${duration} days)
	
	<br/>
	<br/>
	
	<form method="POST" action="${pageContext.request.contextPath}/ReservationChooseRoom">
	
	<table>
	
	<tr>
		<th> Room Number </th>
		<th> Choice </th>
	</tr>
	<tr>
		<td> Placeholder </td>
		<td> <input type="radio" name="choice" value="yes" required> </td>
	</tr>
	
	</table>

	<input type="submit">
	</form>
	
	
	
	

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>