<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="background: #f1f0eb; height: 55px; padding: 5px;">
  <div style="float: left">
     <h1>Welcome to Marriot Hotel UCD</h1>
  </div>
 
  <div style="float: right; padding: 10px; text-align: right;">
 
     <!-- User store in session with attribute: loginedUser -->
     	<core:if test="${loginedUser.name!=null}">
     		Hello <b>${loginedUser.name}</b>
      	</core:if>
   <br/>
 
  </div>
 
</div>