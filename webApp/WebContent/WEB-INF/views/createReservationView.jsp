<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Make Reservation</title>
   </head>
   <body>
    
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
       
      <h2>Make a reservation</h2>
       
      <p style="color: red;">${errorString}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/createReservation">
       
      <h3>Guest information</h3>
         <table border="1">
            <tr>
               <td>Name</td>
               <td><input type="text" name=guestName value="${Guest.guestName}" /></td>
            </tr>
            <tr>
               <td>Surname</td>
               <td><input type="text" name="guestSurename" value="${Guest.guestSurename}" /></td>
            </tr>
            <tr>
               <td>Address</td>
               <td><input type="text" name="guestAddress" value="${Guest.guestAddress}" /></td>
            </tr>
            <tr>
               <td>Email</td>
               <td><input type="text" name="guestEmail" value="${Guest.guestEmail}" /></td>
            </tr>
            
            <tr>
               <td>Credit card number</td>
               <td><input type="text" name="guestCardNumber" value="${Guest.guestCardNumber}" /></td>
            </tr>
            
            <tr>
               <td>Phone number</td>
               <td><input type="text" name="guestPhoneNumber" value="${Guest.guestPhoneNumber}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="productList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
       
      <jsp:include page="_footer.jsp"></jsp:include>
       
   </body>
</html>