<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Create Product</title>
   </head>
   <body>
    
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
       
      <h2>Reservation (2/3)</h2>
       
      <p style="color: red;">${errorString}</p>
      
      The following hotel rooms are available for <b>${numPeople}</b> people between <b>${sessionScope.resStart}</b> and <b>${sessionScope.resEnd}</b>
      
      <br/> <br/>
      
      <table border="1">
      	<tr>
	      	<th>Room Type</th>
	      	<th>Price</th>
	      	<th>Max occupancy</th>
	      	<th>Rooms available</th>
      	</tr>
      	
      	<tr>
	      	<td>Test</td>
	      	<td>Test</td>
	      	<td>Test</td>
	      	<td>Test</td>
      	</tr>
     </table>
       
      <form method="POST" action="${pageContext.request.contextPath}/createReservation">
       

      <h3>Enter your personal details to start the booking process</h3>
         <table border="1">
            <tr>
               <td>Name</td>
               <td><input type="text" name=guestName value="${Guest.guestName}" required/></td>
            </tr>
            <tr>
               <td>Surname</td>
               <td><input type="text" name="guestSurename" value="${Guest.guestSurename}" required/></td>
            </tr>
            <tr>
               <td>Address</td>
               <td><input type="text" name="guestAddress" value="${Guest.guestAddress}" required/></td>
            </tr>
            <tr>
               <td>Email</td>
               <td><input type="email" name="guestEmail" value="${Guest.guestEmail}" required/></td>
            </tr>
            
            <tr>
               <td>Credit card number</td>
               <td><input type="text" name="guestCardNumber" value="${Guest.guestCardNumber}" required/></td>
            </tr>
            
            <tr>
               <td>Phone number</td>
               <td><input type="text" name="guestPhoneNumber" value="${Guest.guestPhoneNumber}" required/></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <input type="reset" value="Reset">
               </td>
            </tr>
         </table>
      </form>
       
      <jsp:include page="_footer.jsp"></jsp:include>
       
   </body>
</html>