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

	<h3>Language Translation Database</h3>
	<a href="home?action=new">* Sign Up/Add Account</a><br>
	<a href="home?action=login">* Log In</a><br>
	<a href="home?action=translate">* Translate</a><br>
	<a href="home?action=main">* Main</a>
	
	<hr>
	
	<table>
		<thead>
			<th>Full Name:</th>
			<th>Email:</th>
			<th>Password:</th>
			<th>Number:</th>
			<th>Gender:</th>
			<th></th>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="contact">
				<tr>
					<td><c:out value="${contact.name}" /></td>
					<td><c:out value="${contact.email}" /></td>
					<td><c:out value="${contact.password}" /></td>
					<td><c:out value="${contact.number}" /></td>
					<td><c:out value="${contact.gender}" /></td>
					<td>
						<a href="home?action=uedit&id=<c:out value="${contact._id}" />">Edit</a>
						<a href="home?action=udelete&id=<c:out value="${contact._id}" />">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>