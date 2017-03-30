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
	<form action="home" method="POST">
		<button type="submit">Save</button>
		<a href="home">Cancel</a>
		<hr>

		<c:if test="${!empty document}">
			<input name="id" type="hidden" value="${document._id}">
		</c:if>
		<ul class="fields">

			<li>Name: <input name="language" type="text"
				placeholder="Please enter your name." required
				<c:if test="${!empty document}">value="${document.language}"</c:if>>
			</li>
			<li>Email: <input name="old" type="text"
				placeholder="Please enter your email." required
				<c:if test="${!empty document}">value="${document.old}"</c:if>>
			</li>
			<li>Mobile No.: <input name="newt" type="text"
				placeholder="Please enter your mobile no." required
				<c:if test="${!empty document}">value="${document.newt}"</c:if>>
			</li>
			<li>Name: <input name="thumbsup" type="text"
				placeholder="Please enter your name." required
				<c:if test="${!empty document}">value="${document.thumbsup}"</c:if>>
			</li>
			<li>Name: <input name="thumbsdown" type="text"
				placeholder="Please enter your name." required
				<c:if test="${!empty document}">value="${document.thumbsdown}"</c:if>>
			</li>
		</ul>

	</form>
</body>
</html>