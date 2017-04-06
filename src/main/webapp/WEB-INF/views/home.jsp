<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="net.tutorial.utilities.AccountManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
RequestDispatcher dispatcher;
	
Cookie[] cookieList = request.getCookies();
	AccountManager accountManager = new AccountManager();
	boolean hasLoggedIn = false;
	request.getSession().setAttribute("Valid", false);
	
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	
	if(cookieList != null) {
		for(Cookie c: cookieList){
			if(c.getName().equals("Email") && request.getSession().getAttribute("Email") != null){
				hasLoggedIn = true;
				c.setMaxAge(60*15);
				response.addCookie(c);
				break;
			}
		}
	}
	
	if(!hasLoggedIn){
			dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
					dispatcher.forward(req, resp);
					
	}	else {
			dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/translate.jsp");
					dispatcher.forward(req, resp);
	}
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SQL - MySQL</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>

	<h3>Language Translation Database</h3>
	<a href="register?action=register">* Sign Up</a>
	<a href="login?action=login">* Log In</a>
	<a href="trans?action=translate">* Translate</a>
	<a href="home?action=main" method = "POST">* Main</a>
	
	<hr>
	<table>
		<thead>
			<th>Name</th>
			<th>Email</th>
			<th>Password</th>
			<th>Number</th>
			<th>Gender</th>
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
						<a href="home?action=edit&id=<c:out value="${contact._id}" />">Edit</a>
						<a href="home?action=delete&id=<c:out value="${contact._id}" />">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>