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
      
      Thanks we have different rooms available for <b>${numPeople}</b> people between <b>${sessionScope.resStart}</b> and <b>${sessionScope.resEnd}</b>
      
      <br/> <br/>
      
      <h3>Booking summary</h3>
      
      <table border="1" cellspacing="0">
      	<tr>
      	<th>Hotel rooms required</th>
      	<th>Room capacity</th>
      	</tr>
      	<tr>
      	<td></>Test</td>
      	<td></>Test</td>
      	</tr>
     </table>
     
     <br/> <br/>
      
      <table border="1" cellspacing="0">
      	<tr>
      	<th>Nights in hotel</th>
      	<th>Cost per night</th>
      	<th>Total booking cost</th>
      	</tr>
      	<tr>
      	<td></>Test</td>
      	<td></>Test</td>
      	<td></>Test</td>
      	</tr>
     </table>
      

       
      <form method="POST" action="${pageContext.request.contextPath}/createReservation">
       

      <h3>Enter your personal details to start the booking process</h3>
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