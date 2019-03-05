<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
<form method="POST" action="${pageContext.request.contextPath}/editDetails">
   
      <h3>Enter your personal details</h3>
         <table>
            <tr>
               <th>Name</th>
               <td><input type="text" name=name value="${Starwood.name}" /></td>
            </tr>
            <tr>
               <th>Surname</th>
               <td><input type="text" name="surename" value="${Starwood.surename}" /></td>
            </tr>
            <tr>
               <th>Address</th>
               <td><input type="text" name="address" value="${Starwood.address}" /></td>
            </tr>
            <tr>
               <th>Email</th>
               <td><input type="email" name="email" value="${Starwood.mmail}" /></td>
            </tr>
                        
            <tr>
               <th>Phone number</th>
               <td><input type="text" name="phoneNumber" value="${Starwood.phoneNumber}" /></td>
            </tr>

         	<tr>
               <th>Credit card number</th>
               <td><input type="text" name="cardNumber" value="${Starwood.cardNumber}" /></td>
            </tr>
            
            <tr>
               <th>User Name</th>
               <td><input type="text" name="userName" value="${Starwood.userName}" /></td>
            </tr>
            <tr>
               <th>Password</th>
               <td><input type="password" name="password" value="${Starwood.password}" /></td>
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