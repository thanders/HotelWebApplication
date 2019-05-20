<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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

		<h2>Starwood Member Registration</h2>
		<span style="color: red;">${errorString}</span>
		<div class="inputWrapper">

			<form method="POST"
				action="${pageContext.request.contextPath}/createStarwood">


				<div id="sectionInputGuest">

					<h3>Enter your personal details</h3>

					<p>
						<label for="name"> <span>Name</span>
						</label> <input type="text" name="name" id="name"
							value="${fn:escapeXml(member.name)}" placeholder="Joe"
							maxlength="40" required />
					</p>

					<p>
						<label for="surename"> <span>Surname</span>
						</label> <input type="text" name="surename" id="surname"
							value="${fn:escapeXml(member.surename)}" placeholder="Security"
							maxlength="40" required />
					</p>

					<p>
						<label for="address"> <span>Address</span>
						</label> <input type="text" name="address" id="address"
							value="${fn:escapeXml(member.address)}"
							placeholder="University College Dublin, Belfield, Dublin 4"
							maxlength="60" required />
					</p>

					<p>
						<label for="email"> <span>Email</span>
						</label> <input type="email" name="email" id="email"
							placeholder="joe.security@ucd.ie" maxlength="40" required />
					</p>

					<p>
						<label for="phoneNumber"> <span>Phone number</span>
						</label> <input type="text" name="phoneNumber" id="phoneNumber"
							value="${fn:escapeXml(member.phoneNumber)}" pattern="^[0-9]+$"
							placeholder="0555252560" maxlength="10"
							title="Input your phone number, digits only (max 10)" required />
					</p>

				</div>

				<div id="sectionInputCard">

					<h3>Payment information</h3>
					<p>
						<label for="CardNumber"> <span>Credit card number</span>
						</label> <input type="text" name="CardNumber" id="CardNumber"
							value="${fn:escapeXml(member.cardNumber)}" pattern="^\d{16,16}$"
							title="16 digits required" placeholder="1234567812345678"
							maxlength="16" required />
					</p>

					<p>
						<label for="CVV"> <span>CVV number</span>
						</label> <input type="text" name="CVV" id="CVV"
							value="${fn:escapeXml(member.CVV)}" pattern="^\d{3,3}$"
							title="Three digits required" placeholder="890" maxlength="3"
							required />
					</p>

					<p>
						<label for="ExpiryDate"> <span>Expiration date</span>
						</label> <input type="date" name="ExpiryDate" id="ExpiryDate"
							value="${fn:escapeXml(member.expiryDate)}" required />
					</p>

				</div>

				<div id="sectionInputCard">

					<h3>Log in details</h3>

					<p>
						<label for="userName"> <span>User Name</span>
						</label> <input type="text" name="userName" pattern=".{6,}"
							title="Must be minimum of 6 characters." required />
					</p>
					<p>
						<label for="username"> <span>Password</span>
						</label> <input type="password" name="password"
							pattern="(?=.*\d)(?=.*[\W_]).{8,}"
							title="Minimum of 8 characters. Should have at least one special character and one number."
							required />
					</p>
					<p>Password must be a minimum of 8 characters containing at
						least 1 digit and 1 a special character (!,?,$)</p>

				</div>

				<div class="inputSubmitReset">
					<input type="submit" value="Register" /> <input type="reset" />
				</div>

			</form>

		</div>

	</div>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>