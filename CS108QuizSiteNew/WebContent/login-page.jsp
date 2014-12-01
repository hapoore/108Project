<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Log In</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-4"></div>
			<div class="col-xs-4">
				<div class="well">
				<h1>Please Log In</h1>
					<form action="LoginServlet" method="post">
						<div class="form-group">
							<label for="username">Username</label> <input type="text"
								class="form-control" name="username" maxlength="7" placeholder="Username">
						</div>
						<div class="form-group">
							<label for="password">Password</label> <input type="password"
								class="form-control" name="password" placeholder="Password">
						</div>
						<button type="submit" class="btn btn-success">Login</button>
					</form>
					<p><a href="create-account-username-password.jsp"
					class="btn btn-large btn-warning">Sign up</a></p>
					<p><a class="btn btn-danger" href ="GuestInitializeServlet"> Continue Without Logging In </a></p>
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