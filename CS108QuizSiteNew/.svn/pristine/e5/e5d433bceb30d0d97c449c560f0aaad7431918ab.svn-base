<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="quizzes.*, questions.*, dataBase.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Quiz Home</title>
<link rel="stylesheet"
      href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
<link rel="stylesheet"
     href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap-theme.min.css">
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="welcome-user.jsp">Home</a>
			</div>
			<a class="btn btn-default navbar-btn" href="quizListPage.jsp">Browse
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
				<a class="btn btn-default navbar-btn" href="inbox.jsp">Inbox</a> <a
					class="btn btn-default navbar-btn" href="friends.jsp">My
					Friends</a> <a class="btn btn-default navbar-btn"
					href="account-settings.jsp">Account Settings</a> <a
					class="btn btn-default navbar-btn" href="LogoutServlet">Logout</a>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="well">
    		<div style="text-align: center">
    			<h1>Quiz Creator</h1>
    			<form action="QuizCreatorServlet" method="post">
    				Name:<input type="text" name="name" maxlength="15"><br><br>
    				Tag:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="tag" maxlength="10"><br><br>
    				<p>Randomized Questions?</p>
    				yes &nbsp;<input type="radio" name="randomized" value="randomized" checked>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					no &nbsp;<input type="radio" name="randomized" value="!randomized"><br><br>
					<p>Practice Mode Allowed?</p>
					yes &nbsp;<input type="radio" name="practice" value="practice" checked>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					no &nbsp;<input type="radio" name="practice" value="!practice"><br><br>
					<p>Multiple Pages?</p>
					yes &nbsp;<input type="radio" name="multiple" value="multiple" checked>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					no &nbsp;<input type="radio" name="multiple" value="!multiple"><br><br>
					<p>Immediate Correction?</p>
					yes &nbsp;<input type="radio" name="immediate" value="immediate" checked>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					no &nbsp;<input type="radio" name="immediate" value="!immediate"><br><br>
    				Description:<input type="text" name="description" maxlength="50"><br><br>
    				<button type="submit" class="btn btn-success">Submit</button>
    			</form>
    		</div>
    	</div>
    </div>

</body>
</html>