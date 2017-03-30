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
<<<<<<< Upstream, based on 144400927d5463a95869e39cd08857f817d08298
	<h3>Address Book App (Using MySQL)</h3>
	<a href="home?action=new">* New Contact</a>
	<a href="home?action=login">* Log In</a>
=======
	<h3>Language Translation Database</h3>
	<a href="home?action=new">* New Translation</a>
>>>>>>> 8a5f055 testtt
	<hr>
	<table>
		<thead>
			<th>Language</th>
			<th>Old Text</th>
			<th>New Text</th>
			<th>Thumbs Up</th>
			<th>Thumbs Down</th>
			<th></th>
		</thead>
		<tbody>
			<c:forEach items="${translations}" var="contact">
				<tr>
					<td><c:out value="${contact.language}" /></td>
					<td><c:out value="${contact.old}" /></td>
					<td><c:out value="${contact.newt}" /></td>
					<td><c:out value="${contact.thumbsup}" /></td>
					<td><c:out value="${contact.thumbsdown}" /></td>
					<td>
						<a href="home?action=edit&id=<c:out value="${contact._id}" />">Edit</a>
						<a href="home?action=delete&id=<c:out value="${contact._id}" />">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>