<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">

<title>Insert title here</title>

</head>
<body>

      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
<form method="POST" action="${pageContext.request.contextPath}/editDetails">
   
      <h3>Please refill this form to update your details</h3>
      <p style="color: red;">${errorString}</p>
         <table>
            <tr>
               <th>Name</th>
               <td><input type="text" name=name value="${fn:escapeXml(member.name)}" /></td>
            </tr>
            <tr>
               <th>Surname</th>
               <td><input type="text" name="surename" value="${fn:escapeXml(member.surename)}" /></td>
            </tr>
            <tr>
               <th>Address</th>
               <td><input type="text" name="address" value="${fn:escapeXml(member.address)}" /></td>
            </tr>
            <tr>
               <th>Email</th>
               <td><input type="email" name="email" value="${fn:escapeXml(member.email)}" /></td>
            </tr>
                        
            <tr>
               <th>Phone number</th>
               <td><input type="text" name="phoneNumber" value="${fn:escapeXml(member.phoneNumber)}" /></td>
            </tr>            
            <tr>
               <th>User Name</th>
               <td><input type="text" name="userName" value="${fn:escapeXml(member.userName)}" /></td>
            </tr>
            <tr>
               <th>Password</th>
               <td><input type="password" name="password" value="${fn:escapeXml(member.password)}" pattern="(?=.*\d)(?=.*[\W_]).{8,}" title="Minimum of 8 characters. Should have at least one special character and one number." required/></td>
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