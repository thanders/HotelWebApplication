<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" type="text/css" href="style.css">
      <title>Delete Account</title>
   </head>
   <body>
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Delete Account Page</h3>
      <p style="color: red;">${errorString}</p>
      <h2>Enter Password to confirm deletion</h2>
 
      <form method="POST" action="${pageContext.request.contextPath}/deleteAccount">
         <table border="1">           
            <tr>
               <td>Password</td>
               <td><input type="password" name="password" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                  <a href="${pageContext.request.contextPath}/">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
 
      
      <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>