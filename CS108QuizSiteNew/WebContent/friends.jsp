<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="account.*"%>
<%@page import="java.util.*"%>
<%@page import="quizzes.*"%>
<%@page import="dataBase.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>My Friends</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>
<%
Account account = (Account) session.getAttribute("Account");
Set<String> friendlist = account.getFriendList();
%>
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
					<h1>My Friends</h1>
					<%
					if(friendlist.size() == 0) {
						out.println("<p><h3>Uh oh, looks like you don't have any friends.</h3></p>");
					} else {
						DataBase db = new DataBase();
						for(String friend: friendlist) {
							Account friendAccount = db.getAccount(friend);
							String url = "display-user-page.jsp?id=" + friend; //Build URL
							if(Account.accountOnline(friendAccount)) {
								out.println("<p><li><a href=" + url + ">" + friend + " (Online)</a></li></p>");
							} else {
								out.println("<p><li><a href=" + url + ">" + friend + "</a></li></p>");
							}
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