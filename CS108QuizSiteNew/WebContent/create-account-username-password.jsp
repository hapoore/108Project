<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Create Account</title>
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
					<h1>Create new account</h1>
					<p>Please enter proposed username and password.</p>
					<form action="NewUsernameServlet" method="post">
						<div class="form-group">
							<label for="username">Username:</label> <input
								type="text" name="username" class="form-control" id="username"
								maxlength="64" placeholder="Username">
						</div>
						<div class="form-group">
							<label for="password">Password:</label> <input
								type="password" name="password" class="form-control" id="password"
								maxlength="64" placeholder="Password">
						</div>
						<button type="submit" class="btn btn-success">Submit</button>
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