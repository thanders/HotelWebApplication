<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" type="text/css" href="style.css">
      <title>Login</title>
   </head>
   <body>
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Login Page</h3>
      <p style="color: red;">${errorString}</p>
      
      	<!-- If submitCount not equal to null, display -->
	<c:if test="${loginCount!=null || loginCount.isempy()}">
		<p style="color: blue;">Sorry that reservation doesn't exist (attempt: ${loginCount})</p>
	</c:if>
 
 
      <form method="POST" action="${pageContext.request.contextPath}/login">
         <table border="1">
            <tr>
               <td>User Name</td>
               <td><input type="text" name="userName" value= "${user.userName}" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="password" name="password" value= "${user.password}" /> </td>
            </tr>
            <tr>
               <td>Remember me</td>
               <td><input type="checkbox" name="rememberMe" value= "Y" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                  <a href="${pageContext.request.contextPath}/">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
 
      <p style="color:blue;">User Name: Tom.yates, password: Password1</p>
 
      <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>