<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
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
 
 		<div>
 		
      <h3>Having trouble viewing your reservation?</h3>
      
      <p>Our web application supports access control and anti-brute-force techniques to prevent unauthorized access to your reservation records.</p>
      
      Techniques include:
      <ul>
      	<li>Randomized reference number generation</li>
      	<li>Two second sleep in between incorrect reference number submission</li>
      	<li>Reference number submission lock out after 3 attempts</li>
      </ul>
      
      <p>Please wait <b>two minutes</b> for your session to expire before trying to access your reservation again.</p>
      
      
      <p style="color: red;">${errorString}</p>
 		
 		
 		</div>
      <jsp:include page="_footer.jsp"></jsp:include>
      
   </body>
</html>