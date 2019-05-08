<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
<div>
  <div>
     <h1>Welcome to Marriott Hotel UCD</h1>
  </div>
 
  <div style="float: right; padding: 10px; text-align: right;">
 
     <!-- User store in session with attribute: loginedUser -->
     	<core:if test="${loginedUser.name!=null}">
     		Hello <b>${loginedUser.name}</b>
      	</core:if>
   <br/>
 
  </div>
 
</div>
</header>