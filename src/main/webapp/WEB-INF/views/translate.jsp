<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Translator</title>

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
      			<a class="navbar-brand page-scroll" href="home?action=main"> 
      				<i class="fa fa-paper-plane-o"></i> Translator
      			</a>
      		</div>
    
    		<div class="collapse navbar-collapse navbar-right navbar-main-collapse">
      			<ul class="nav navbar-nav">
        			<li class="hidden"> <a href="#page-top"></a> </li>
        			<li> <a href="home.jsp" action="logout" method="POST">Log Out</a> </li>
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
				<h2>Translate Text</h2>
				<hr>
    		</div>
    		<div class="space"></div>
    		<div class="row">
				<div class="col-md-6">
					<div class="service">
						<form action="/trans" method="POST">
							<p class="tr-selector">
								<select name="tr-model-id" class="form-control">
									<option value="en-ar">English - Arabic</option>
									<option value="en-fr">English - French</option>
									<option value="en-pt">English - Portuguese</option>
									<option value="en-es">English - Spanish</option>
								</select>
							</p>
							<p class="panel">
								<textarea name="tr-from" rows="10" class="form-control" placeholder="Type text to translate here." required><c:if test="${!empty text}">${text}</c:if></textarea>
								<p class="help-block text-danger"></p>
							</p>
							<p class="action">
								<input type="submit" value="Translate" class="btn btn-default">
							</p>
						</form>
					</div>
    			</div>
    			<div class="col-md-6">
    				<div class="service">
    					<br><br>
    					<p class="panel">
							<textarea class="form-control" name="tr-to" rows="10" readonly><c:if test="${!empty translation}">${translation}</c:if></textarea>
						</p>
						<p class="action">
							<button type="button" class="btn btn-default">
								<i class="fa fa-thumbs-o-up"></i>
								<span class="badge">0</span>
							</button>
							<button type="button" class="btn btn-default">
								<i class="fa fa-thumbs-o-down"></i>
								<span class="badge">0</span>
							</button>
						</p>
    				</div>
    			</div>
			</div>
		</div>
	</div>
</body>
</html>