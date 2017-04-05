<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sign Up</title>

	<!-- Bootstrap -->
	<link rel="stylesheet" type="text/css"  href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome/css/font-awesome.css">

	<!-- Stylesheet -->
	<link rel="stylesheet" type="text/css"  href="css/styles0.css">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,700,800,600,300" rel="stylesheet" type="text/css">
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
  		<div class="container">
    		<div class="navbar-header">
      			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
      				<i class="fa fa-bars"></i>
      			</button>
      			<a class="navbar-brand page-scroll" href="login?action=login"> 
      				<!--<a href = "home">--><i class="fa fa-paper-plane-o"></i> Translator<!--</a>-->
      			</a>
      		</div>
    
    		<div class="collapse navbar-collapse navbar-right navbar-main-collapse">
      			<ul class="nav navbar-nav">
        			<li class="hidden"> <a href="#page-top"></a> </li>
        			<li> <a href="login?action=login">Log In</a> </li>
        			<!--
        			<li> <a class="page-scroll" href="#services">Convert</a> </li>
      				-->
      			</ul>
    		</div>
  		</div>
	</nav>
	<div id="services" class="text-center">
		<div class="container">
    		<div class="section-title text-center center">
				<h2><c:if test="${empty document}">New</c:if> Contact Form</h2>
				<hr>
			</div>
			<form action="register" method="POST">
				<div class="col-md-6 col-md-offset-3">
					<c:if test="${!empty document}">
						<input name="id" type="hidden" value="${document._id}">
					</c:if>
					<p>Full Name: <input name="name" type="text"
						placeholder="Please enter your name." class="form-control" required
						<c:if test="${!empty document}">value="${document.name}"</c:if>>
					</p>
					<p>E-mail Address: <input name="email" type="text"
						placeholder="Please enter your email." class="form-control" required
						<c:if test="${!empty document}">value="${document.email}"</c:if>>
					</p>
					<p>Password: <input name="password" type="password"
						placeholder="Please enter your password." class="form-control" required
						<c:if test="${!empty document}">value="${document.password}"</c:if>>
					</p>
					<p>Cellphone Number: <input name="number" type="tel"
						placeholder="Please enter your number." required
						<c:if test="${!empty document}">value="${document.number}"</c:if>>
					</p>
					<p>Gender: 
						<select name="gender" class="form-control">
							<option value="male">Male</option>
							<option value="female">Female</option>
						</select>
						<c:if test="${!empty document}">value="${document.gender}"</c:if>
					</p>
					<button type="submit" class="btn btn-default">Save</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>