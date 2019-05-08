<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
      
      <div class="container">
      
<form method="POST" action="${pageContext.request.contextPath}/createStarwood">
<p style="color: red;">${errorString}</p>
   
      <h3>Enter your personal details</h3>
         <table>
            <tr>
               <td>Name</td>
               <td><input type="text" name=name value="${fn:escapeXml(member.name)}" required/></td>
            </tr>
            <tr>
               <td>Surname</td>
               <td><input type="text" name="surename" value="${fn:escapeXml(member.surename)}"required/></td>
            </tr>
            <tr>
               <td>Address</td>
               <td><input type="text" name="address"  value="${fn:escapeXml(member.address)}" required/></td>
            </tr>
            <tr>
               <td>Email</td>
               <td><input type="email" name="email" value="${fn:escapeXml(member.email)}" required/></td>
            </tr>
                        
            <tr>
               <td>Phone number</td>
               <td><input type="text" name="phoneNumber"  value="${fn:escapeXml(member.phoneNumber)}" required/></td>
            </tr>

         	<tr>
               <th>Credit card number</th>
               <td><input type="text" name="CardNumber" maxlength="80"  pattern="^\d{16,16}$" title="16 digits required" required/></td>
            </tr>
            <tr>
               <th> CVV number</th>
               <td><input type="text" name="CVV"  pattern="^\d{3,3}$" title="3 digits required"  required/></td>
            </tr>
            
             <tr>
               <th>Expiry Date</th>
               <td><input type="date" name="ExpiryDate" required/></td>
            </tr>
            <tr>
               <td>User Name</td>
               <td><input type="text" name="userName" pattern=".{6,}" required/></td>
            </tr>
            <tr> 
               <td>Password</td>
               <td><input type="password" name="password" pattern="(?=.*\d)(?=.*[\W_]).{8,}" title="Minimum of 8 characters. Should have at least one special character and one number." required/></td>
            </tr>
          	<tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <input type="reset" value="Reset">
               </td>
            </tr>
         </table>
      </form>
      
      </div>
       
      <jsp:include page="_footer.jsp"></jsp:include>
       
   </body>
</html>