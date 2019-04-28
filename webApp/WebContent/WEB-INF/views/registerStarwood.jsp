<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
   
      <h3>Enter your personal details</h3>
         <table>
            <tr>
               <td>Name</td>
               <td><input type="text" name=name value="${Starwood.name}" required/></td>
            </tr>
            <tr>
               <td>Surname</td>
               <td><input type="text" name="surename" value="${Starwood.surename}" required/></td>
            </tr>
            <tr>
               <td>Address</td>
               <td><input type="text" name="address" value="${Starwood.address}" required/></td>
            </tr>
            <tr>
               <td>Email</td>
               <td><input type="email" name="email" value="${Starwood.mmail}" required/></td>
            </tr>
                        
            <tr>
               <td>Phone number</td>
               <td><input type="text" name="phoneNumber" value="${Starwood.phoneNumber}" required/></td>
            </tr>

         	<tr>
               <th>Credit card number</th>
               <td><input type="text" name="CardNumber" maxlength="80" value="${Starwood.cardNumber}" pattern=".{16,16}" title="16 digits required" required/></td>
            </tr>
            <tr>
               <th> CVV number</th>
               <td><input type="text" name="CVV" value="${Starwood.CVV}" pattern=".{3,3}" title="3 digits required" required/></td>
            </tr>
            
             <tr>
               <th>Expiry Date</th>
               <td><input type="date" name="ExpiryDate" value="${Starwood.ExpiryDate}" required/></td>
            </tr>
            <tr>
               <td>User Name</td>
               <td><input type="text" name="userName" value="${Starwood.userName}" required/></td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="password" name="password" value="${Starwood.password}" required/></td>
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