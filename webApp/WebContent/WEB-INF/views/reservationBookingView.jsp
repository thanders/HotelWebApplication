<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" type="text/css" href="style.css">
      <title>Reservation Summary</title>
   </head>
   <body>
    
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
      
      <div class="container">
       
      <h2>Reservation (3/3)</h2>
       
      <p style="color: red;">${errorString}</p>
     
      
      From <b>${startDate}</b> until <b>${endDate}</b> (${duration} days)
      
     <br/> <br/>
      
     <br/> <br/>
     
     Below is an overview of this potential booking:
     
     <br/><br/>
      
      <table>
      	<tr>	
	    	<th>Number of rooms</th>
	    	<th>Room numbers</th>
	    	
	    	<th>Price</th>
      	</tr>
      	<tr>
      	<td>${numRooms}</td>
      	<td> <c:forEach items="${choices}" var="choices" >${choices}  </c:forEach> </td>
      	<td>${reservationPrice}</td>
      	</tr>
     </table>
      
	<br/> <br/>
    
		<div class= "inputWrapper">
	      
	    	<form method="POST" action="${pageContext.request.contextPath}/reservationConfirm">
	    	
	    	<!-- If user is not logged-in -->
	    	<c:if test="${empty sessionScope.loginedUser}">
	    		If you would like to pay for this booking, enter your personal details:
	    		<br/> <br/>
		
			<div id="sectionInputGuest">
		
				<h3> Guest information</h3>
			
				<p>
					<label for="guestName">
						<span>First Name</span>
					</label>
					<input type="text" name=guestName id=guestName value="${Guest.guestName}" placeholder="Joe" maxlength = "40" required/>
				</p>
		        
				<p>
		        	<label for="guestSurename">
						<span>Surname</span>
					</label>
		            <input type="text" name="guestSurename" id="guestSurename" value="${Guest.guestSurename}" placeholder="Security" maxlength = "40" required/>
		        </p>
		        
		        <p>
		        	<label for="guestAddress">
						<span>Address</span>
					</label>
		       		<input type="text" name="guestAddress" id="guestAddress" value="${Guest.guestAddress}" placeholder="University College Dublin, Belfield, Dublin 4" maxlength = "60" required/>
		        </p>
		        
				<p>
		        	<label for="guestEmail">
						<span>Email</span>
					</label>
		        	<input type="email" name="guestEmail" id="guestEmail" placeholder="joe.security@ucd.ie" maxlength = "40"required/>
		    	</p>
		                        
				<p>
			        <label for="guestPhoneNumber">
						<span>Phone number</span>
					</label>
		           	<input type="text" name="guestPhoneNumber" id="guestPhoneNumber" value="${Guest.guestPhoneNumber}" pattern="^[0-9]+$" placeholder="0555252560" maxlength = "10" title="Input your phone number, digits only (max 10)" required/>
		   		</p>
		   	
			</div>
		
			<div id="sectionInputCard">
		
				<h3> Payment information</h3>
			
				<p>
			        <label for="guestCardNumber">
						<span>Credit card number</span>
					</label>
		        	<input type="text" name="guestCardNumber" id="guestCardNumber" value="${Guest.guestCardNumber}" pattern="^\d{16,16}$" title="16 digits required" placeholder="1234567812345678" maxlength = "16" required/>
		    	</p>
		        
				<p>
			        <label for="CVV">
						<span>CVV number</span>
					</label>
		            <input type="text" name="CVV" id="CVV" value="${Guest.CVV}" pattern="^\d{3,3}$" title="Three digits required" placeholder="890" maxlength = "3" required/>
		    	</p>
		            
				<p>
		        	<label for="ExpiryDate">
						<span>Expiration date</span>
					</label>
		            <input type="date" name="ExpiryDate" id="ExpiryDate" value="${Guest.ExpiryDate}" required/>
		    	</p>
	    
	    	</div>

		</c:if>
		
		<!-- If user is logged-in -->
		<c:if test="${not empty sessionScope.loginedUser}">
		<p >Choose a Credit Card to pay with:</p>
	         <select name="CreditCard">
	    <c:forEach var="d" items="${cards}">
	      <option value="${d.getCardNumber()}">${d.getCardNumber()}</option>
	    </c:forEach>
			</select>
			<br/><br/>
			</c:if>
			
	         <div class="inputSubmitReset"> 
	         	<input type="submit" value="Book and Pay" />
	         	<input type="reset" value="Reset" />
	         </div>
	         
	      </form>
	      
	      </div>
      </div>
       
      <jsp:include page="_footer.jsp"></jsp:include>
      

       
   </body>
</html>