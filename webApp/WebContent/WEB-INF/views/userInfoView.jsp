<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>User Info</title>
</head>
<body>

	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	
	<div class="container">
	<p style="color: green;">${changesString}</p>
	<p style="color: red;">${errorString}</p>
	
	<h3>Hello: ${user.name}</h3>
	Surname: ${user.surename}
	<br> Name: ${user.name}
	<br>Address: ${user.address} 
	<br> Email: ${user.email}
	<br> Phone Number: ${user.phoneNumber}
	<br>
	<a href="${pageContext.request.contextPath}/editDetails"> Edit
		details</a>
	<br>
	<a href="${pageContext.request.contextPath}/addCard"> Add Card</a>
	<br />
	
	<a href="${pageContext.request.contextPath}/editCard"> Delete a card</a>
	<br />

	<form method="POST"
		action="http://localhost:8080/MarriotWebApp/deleteAccount">
		<br> <input type="submit" value="Delete Account" /> <br />
	</form>
	
	</div>

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>