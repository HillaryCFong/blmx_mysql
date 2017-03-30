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
			<li>Username: <input name="email" type="email"
				placeholder="Please enter your email." required
				<c:if test="${!empty document}">value="${document.email}"</c:if>>
			</li>
			<li>Password: <input name="mobile" type="text"
				placeholder="Please enter your mobile no." required
				<c:if test="${!empty document}">value="${document.mobile}"</c:if>>
			</li>
		</ul>ul

</body>
</html>