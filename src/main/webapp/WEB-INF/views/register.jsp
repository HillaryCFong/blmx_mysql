<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SQL - MySQL</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<h3><c:if test="${empty document}">New</c:if> Contact Form</h3>
	<form action="register" method="POST">
		<button type="submit">Save</button>
		<a href="login">Cancel</a>
		<hr>

		<c:if test="${!empty document}">
			<input name="id" type="hidden" value="${document._id}">
		</c:if>
		<ul class="fields">

			<li>Full Name: <input name="name" type="text"
				placeholder="Please enter your name." required
				<c:if test="${!empty document}">value="${document.name}"</c:if>>
			</li>
			<li>E-mail Address: <input name="email" type="text"
				placeholder="Please enter your email." required
				<c:if test="${!empty document}">value="${document.email}"</c:if>>
			</li>
			<li>Password: <input name="password" type="password"
				placeholder="Please enter your password." required
				<c:if test="${!empty document}">value="${document.password}"</c:if>>
			</li>
			<li>Cellphone Number: <input name="number" type="tel"
				placeholder="Please enter your number." required
				<c:if test="${!empty document}">value="${document.number}"</c:if>>
			</li>
			<li>Gender: <select name="gender">
				  <option value="male">Male</option>
				  <option value="female">Female</option>
				</select>
								
				<c:if test="${!empty document}">value="${document.gender}"</c:if>>
			</li>
		</ul>

	</form>
</body>
</html>