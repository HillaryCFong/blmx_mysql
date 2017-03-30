<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log In</title>
</head>
<body>
	<h3>Log In</h3>
		<button type="submit">Save</button>
		<a href="home">Cancel</a>
		<ul class="fields">
			<li>Username: <input name="username" type="email"
				placeholder="Please enter your username." required
				<c:if test="${!empty document}"<%--> value="${document.username}"--%></c:if>>
			</li>
			<li>Password: <input name="password" type="password"
				placeholder="Please enter your password." required
				<c:if test="${!empty document}"<%--> value="${document.password}"--%></c:if>>
			</li>
		</ul>

</body>
</html>