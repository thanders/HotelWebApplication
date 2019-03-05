<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Enter Card</h3>
      <p style="color: red;">${errorString}</p>
         <form method="POST" action="${pageContext.request.contextPath}/addCard">
            <table border="1">
               <tr>
                  <td>Card Number</td>
                  <td><input type="text" name="card" value="${CreditCard.cardNumber}"  /></td>
               </tr>
               <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
               </td>
            </tr>
            </table>
         </form>
 
      <jsp:include page="_footer.jsp"></jsp:include>
 
   </body>
</html>