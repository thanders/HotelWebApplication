<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Reservations</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>

	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<div class="container">

	<h3>Find my reservation</h3>

	<!-- If submitCount not equal to null, display -->
	<c:if test="${submitCount!=null}">
		<p style="color: blue;">Sorry that reservation doesn't exist (attempt: ${submitCount})</p>
	</c:if>

	Enter your reservation number (Guests only, not Starwood members):
	<br/>
	<br/>

	<div id="inputWrapper">
	
	<form method="POST" action="${pageContext.request.contextPath}/reservationDisplay">
		
		<label for="resNumber"> 
			<span>
				Reservation number
			</span> 
		</label>
		
		<input type="number" name="resNumber" id="resNumber" min="1" value="${resNumber}" required>

		<div class="inputSubmitReset">
			<input type="submit" value="Submit">
			<input type="reset" value="Reset">
		</div>
		
	</form>

	</div>
	</div>

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>