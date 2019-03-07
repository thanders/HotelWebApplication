<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" type="text/css" href="style.css">
      <title>Create Product</title>
   </head>
   <body>
    
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
       
      <h2>Reservation (3/3)</h2>
       
      <p style="color: red;">${errorString}</p>
     
      
      From <b>${startDate}</b> until <b>${endDate}</b> (${duration} days)
      
     <br/> <br/>
      
     <br/> <br/>
     
     You are requesting to book:
      
      <table>
      	<tr>
	    	<th>Number of rooms</th>
	    	<th>Room numbers</th>
	    	
	    	<th>Price</th>
      	</tr>
      	<tr>
      	<td>${numRooms}</td>
      	<td> <c:forEach items="${choices}" var="choices" >${choices}  </c:forEach> </td>
      	<td>Placeholder</td>
      	</tr>
     </table>
      
	<br/> <br/>
    <br/> <br/>
      
      <form method="POST" action="${pageContext.request.contextPath}/reservationConfirm">
      
         <table>
   
            
          	<tr>
               <td colspan="2">                  
                   <input type="submit" value="Book and Pay" />
                   <input type="reset" value="Reset"/>
               </td>
            </tr>
         </table>
      </form>
       
      <jsp:include page="_footer.jsp"></jsp:include>
       
   </body>
</html>