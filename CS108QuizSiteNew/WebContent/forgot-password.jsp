<%@page import="web.*"%>
<%@page import="account.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Password Hint</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
				<div class="well">
					<%
String username = request.getParameter("username");
ServletContext context = request.getServletContext();
AccountManager mgr = (AccountManager) context.getAttribute("Account Manager");
Account currAccount = mgr.getAccount(username);
String hint = currAccount.getPasswordHint();
out.println("<h1>Your password hint is:</h1>");
out.println("<h2>" + hint + "</h2>");
%>

					<p>Please Log In</p>
					<form action="LoginServlet" method="post">
						<div class="form-group">
							<label for="username">Username</label> <input type="text"
								class="form-control" name="username" id="username"
								placeholder="Username">
						</div>
						<div class="form-group">
							<label for="password">Password</label> <input type="password"
								class="form-control" name="password" id="password"
								placeholder="Password">
						</div>
						<button type="submit" class="btn btn-warning">Login</button>
						<a class="btn btn-danger" href="GuestInitializeServlet"> Continue
							Without Logging In </a>
					</form>
				</div>
			</div>
			<div class="col-xs-2"></div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"
		type="text/javascript"></script>
	<script src="”js/bootstrap.js”" type="text/javascript"></script>
</body>
</html>