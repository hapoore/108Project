<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="account.*"%>
<%@page import="java.util.*"%>
<%@page import="quizzes.*"%>
<%@page import="dataBase.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Achievements</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
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
		<div class="row">
			<div class="col-xs-12">
				<div class="well">
					<%
String usernameCurrPage = request.getParameter("id");
out.println("<h1>" + usernameCurrPage + "'s Achievements:</h1>");
DataBase db = new DataBase();
Account currPageAccount = db.getAccount(usernameCurrPage);
Set<Achievement> achievements = currPageAccount.getAchievementList();
if(achievements.size() == 0) {
	out.println("<p>" + usernameCurrPage + " has not earned any achievements.</p>");
} else {
	for(Achievement achievement : achievements) {
		out.println("<p>" + achievement.getName() + "</p>");
		out.println("<img src=\"" + achievement.getURL() + "class=\"img-rounded\" alt=\"Rounded Image\" height=\"100\" width=\"100\">");
	}
}
%>
				</div>
			</div>
		</div>
	</div>

	<script src="http://code.jquery.com/jquery-1.10.1.min.js"
		type="text/javascript"></script>
	<script src="”js/bootstrap.js”" type="text/javascript"></script>
</body>
</html>