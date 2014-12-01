<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Incorrect Password</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-4"></div>
			<div class="col-xs-4">
				<div class="well">
				<h1>Your password was incorrect.</h1>
				<p>Please try again.</p>
					<form action="LoginServlet" method="post">
						<div class="form-group">
							<label for="username">Username:</label> <input type="text"
								class="form-control" name="username" placeholder="Username">
						</div>
						<div class="form-group">
							<label for="password">Password:</label> <input type="password"
								class="form-control" name="password" placeholder="Password">
						</div>
						<button type="submit" class="btn btn-success">Login</button>
					</form>
					<p></p>
					<p><a class="btn btn-warning" href ="GuestInitializeServlet"> Continue Without Logging In </a></p>
					<% 
					String url = "ask-security-question.jsp?id=" + (String) request.getParameter("username"); //Build URL
					out.println("<p><a class=\"btn btn-danger\" href=" + url + ">Forgot your password?</a></p>");
					%>
					<p><a class="btn btn-primary" href ="create-account-username-password.jsp">Create New Account</a></p>
				</div>
			</div>
			<div class="col-xs-4"></div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"
		type="text/javascript"></script>
	<script src="”js/bootstrap.js”" type="text/javascript"></script>
</body>
</html>