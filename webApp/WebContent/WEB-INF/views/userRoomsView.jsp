<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Available Rooms</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Available Rooms</h3>
 
    <p style="color: red;">${errorString}</p>
 
 	<!--Table to display available rooms-->
    <table border="1" cellspacing="0">
       <tr>
          <th>Room Number</th>
          <th>Room Type</th>
       </tr>
       <c:forEach items="${roomList}" var="room" >
          <tr>
             <td>${room.roomNumber}</td>
             <td>${room.fkRoomTypeId}</td>
          </tr>
       </c:forEach>
    </table>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>