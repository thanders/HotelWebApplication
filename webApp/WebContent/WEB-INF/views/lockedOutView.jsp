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
 		
      <h2>Having trouble ${purposeLocked1}?</h2>
      
      <p>Our web application supports access control and anti-brute-force techniques to prevent unauthorized access to your records.</p>
      
      Techniques include:
      <ul>
      	<li>Randomized reference number generation</li>
      	<li>Two second sleep in between incorrect reference number submission</li>
      	<li>Reference number submission lock out after 3 attempts</li>
      </ul>
      
      <p>Please wait <b>two minutes</b> for your session to expire before trying to ${purposeLocked2}.</p>
      
      <h3>Why am I being redirected to this page?</h3>
      
      <p style="color: red;">Incorrect attempts to ${purposeLocked2}: ${count}</p>
 		
 		
 		</div>
      <jsp:include page="_footer.jsp"></jsp:include>
      
   </body>
</html>