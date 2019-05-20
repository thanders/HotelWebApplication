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
 
      <h2>Login</h2>
      
      <span style="color: red;">${errorString}</span>
      
      <div class="inputWrapper">
 
 		<!-- the login form -->
      <form method="POST" action="${pageContext.request.contextPath}/login">
 
 			<p>
 	        	<label for="userName">
					<span>User Name</span>
				</label>
               <input type="text" name="userName" id="userName" value= "${user.userName}" />
            </p>
            
            <p>
 	        	<label for="password">
					<span>Password</span>
				</label>
				
               <input type="password" name="password" id="password" value= "${user.password}" />
			
			</p>
			
			<p>
 	        	<label for="rememberMe">
					<span>Remember me</span>
				</label>

               <input type="checkbox" name="rememberMe" id="rememberMe" value= "Y" />
            </p>

			<div class="inputSubmitReset">
			
                  <input type="submit" value= "Submit" />
                  
                  <input type="reset" />
                  
			</div>
      </form>
      
      
    <!-- If loginCount not equal to null, display -->
	<c:if test="${loginCount!=null || loginCount.isempy()}">
		<p style="color: blue;">Invalid login attempts: ${loginCount}</p>
	</c:if>
	
	</div>
 
      <!-- <p style="color:blue;">User Name: Tom.yates, password: Password1</p> -->
 
      <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>