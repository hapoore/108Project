<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="account.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>User Home</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="guest-home.jsp">Home</a>
			</div>
			<a class="btn btn-default navbar-btn" href="quizHomePage.jsp">Browse
				Quizzes</a>
			<form class="navbar-form navbar-left" action="SearchUsersServlet"
				method="post">
				<div class="form-group">
					<input type="text" class="form-control" name="searchString"
						placeholder="Search Users">
				</div>
				<button type="submit" class="btn btn-default">Find User</button>
			</form>
			<form class="navbar-form navbar-left" action="SearchQuizzesServlet"
				method="post">
				<div class="form-group">
					<input type="text" class="form-control" name="searchString"
						placeholder="Search Quizzes">
				</div>
				<button type="submit" class="btn btn-default">Find Quiz</button>
			</form>
			<div class="nav navbar-nav navbar-right">
				<a class="btn btn-default navbar-btn" href="login-page.jsp">Login</a>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-xs-6">
				<div class="well">
					<h1>Popular Quizzes</h1>
					<p>This is where we</p>
					<p>will display the most popular quizzes</p>
					<p>sorted by number of plays</p>
					<a class="btn btn-success" href="quiz-home.jsp">Browse All
						Quizzes</a>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="well">
					<h1>Recent Quiz Activity</h1>
					<h2>Quizzes Taken:</h2>
					<p>a list of the user's'</p>
					<p>recently taken quizzes</p>
					<p>from this session</p>
				</div>
			</div>
		</div>
	</div>

	<script src="http://code.jquery.com/jquery-1.10.1.min.js"
		type="text/javascript"></script>
	<script src="”js/bootstrap.js”" type="text/javascript"></script>
</body>
</html>