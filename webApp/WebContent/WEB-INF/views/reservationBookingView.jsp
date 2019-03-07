<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" type="text/css" href="style.css">
      <title>Reservation Summary</title>
   </head>
   <body>
    
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
      
      <div class="container">
       
      <h2>Reservation (3/3)</h2>
       
      <p style="color: red;">${errorString}</p>
     
      
      From <b>${startDate}</b> until <b>${endDate}</b> (${duration} days)
      
     <br/> <br/>
      
     <br/> <br/>
     
     Below is an overview of this potential booking:
     
     <br/><br/>
      
      <table border="1" cellspacing="0">
      	<tr>	
	    	<th>Number of rooms</th>
	    	<th>Room numbers</th>
	    	
	    	<th>Price</th>
      	</tr>
      	<tr>
      	<td>${numRooms}</td>
      	<td> <c:forEach items="${choices}" var="choices" >${choices}  </c:forEach> </td>
      	<td>${reservationPrice}</td>
      	</tr>
     </table>
      
	<br/> <br/>
    
      
      <form method="POST" action="${pageContext.request.contextPath}/reservationConfirm">
      <c:if test="${empty sessionScope.loginedUser}">
    	If you would like to pay for this booking, enter your personal details:
    <br/> <br/>
         <table>
            <tr>
               <th>Name</th>
               <td><input type="text" name=guestName value="${Guest.guestName}" required/></td>
            </tr>
            <tr>
               <th>Surname</th>
               <td><input type="text" name="guestSurename" value="${Guest.guestSurename}" required/></td>
            </tr>
            <tr>
               <th>Address</th>
               <td><input type="text" name="guestAddress" value="${Guest.guestAddress}" required/></td>
            </tr>
            <tr>
               <th>Email</th>
               <td><input type="email" name="guestEmail" value="${Guest.guestEmail}" required/></td>
            </tr>
                        
            <tr>
               <th>Phone number</th>
               <td><input type="text" name="guestPhoneNumber" value="${Guest.guestPhoneNumber}" required/></td>
            </tr>

         	<tr>
               <th>Credit card number</th>
               <td><input type="text" name="guestCardNumber" value="${Guest.guestCardNumber}" required/></td>
            </tr>

         </table>
	</c:if>
	
	
	<p >Choose a Credit Card to pay with:</p>
         <select name="CreditCard">
    <c:forEach var="d" items="${cards}">
      <option value="${d.getCardNumber()}">${d.getCardNumber()}</option>
    </c:forEach>
		</select>
		<br/><br/>
         <input type="submit" value="Book and Pay" />
         
      </form>
       
      <jsp:include page="_footer.jsp"></jsp:include>
      
      </div>
       
   </body>
</html>