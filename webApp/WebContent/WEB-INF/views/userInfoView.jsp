<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>User Info</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Hello: ${user.userName}</h3>
 
    Name: ${user.name}
    <br />
    Address: ${user.address} <br />
    <br />
    Email: ${user.email} <br />    
   <a href="${pageContext.request.contextPath}/editDetails"> Edit details</a>
   <br>
   <a href="${pageContext.request.contextPath}/addCard"> Add Card</a>
    <br />
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>