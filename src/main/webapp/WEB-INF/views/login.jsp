<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Log In</title>

	<!-- Bootstrap -->
	<link rel="stylesheet" type="text/css"  href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome/css/font-awesome.css">

	<!-- Stylesheet -->
	<link rel="stylesheet" type="text/css"  href="css/styles0.css">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,700,800,600,300" rel="stylesheet" type="text/css">
	<style type="text/css"> 
		#message {
				color: white;
				text-align: center;
				margin: auto;
			}
	</style>
	
	
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
        			<li> <a href="register?action=register">Sign Up</a> </li>
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
				<h2>Log In</h2>
				<hr>
			</div>
			<form method="POST" name="loginForm" action="login" id="loginForm">
				<div class="col-md-6 col-md-offset-3">
					<p>Email:</p>
					<input name="email" type="email"
						placeholder="Please enter your email." class="form-control" required>
					<p class="help-block text-danger"></p>
					<p>Password:</p>
					<input name="password" type="password"
						placeholder="Please enter your password." class="form-control" required>
					<p class="help-block text-danger"></p>
					<button type="submit" value="Submit" class="btn btn-default">Log In</button>
	            </div>
	        </form>
		</div>
		<div class="container" id="message">
			<%
			    if(null!=request.getAttribute("error"))
			    {
			        out.println(request.getAttribute("error"));
			    }
			%>
		</div>
	</div>
</body>
</html>