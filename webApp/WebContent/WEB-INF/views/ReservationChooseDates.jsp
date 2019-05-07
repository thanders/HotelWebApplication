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
	<P>This hotel currently has  a maximum of <b>${maxRooms}</b> rooms. The reservation system can tell you which rooms are free.</P>

	<P> Select the dates you would like to view and specify the number of rooms you want to book:</p>

<div id="reservationDates">

	<form action="${pageContext.request.contextPath}/ReservationChooseRoom">
		
		<div class="formInput">
		
			<p>
				<label for="resStart">
					<span>From</span>
				</label>
				<input type="date" name="resStart" id="resStart" required>
			</p>
			
			<p>
				<label for="resEnd">
					<span>Until</span>
				</label>
				<input type="date" name="resEnd" id="resEnd"required>
			</p>
			
			<p>
				<label for="numRooms">
					<span>Number of rooms</span>
				</label>
				<input type="number" name="numRooms" id="numRooms" title="a number from 1 to 20 is required" min="1" max="20" maxlength = "3" required> 
		</p>
		
		<p style="color: red;">${durationMSG}</p>
		
	</div>
	
		<div class="inputSubmitReset">
		
		<input type="submit"> <input type="reset" value="Reset">
		
		</div>
		
	</form>
	
</div>	
	
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>