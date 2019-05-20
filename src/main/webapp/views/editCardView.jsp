<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Edit Card</title>
</head>
<body>

	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	<form method="POST"
		action="${pageContext.request.contextPath}/editCard">

		<p>Choose a Credit Card to remove:</p>
		<p style="color: red;">${errorString}</p>
		<select name="CreditCard">
			<c:forEach var="d" items="${cards}">
				<option value="${d.getCardNumber()}">${d.getCardNumber()}</option>
			</c:forEach>
		</select> <br /> <br />


		<div>
			<input type="submit" value="Remove" /> 
			
		</div>

	</form>

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>