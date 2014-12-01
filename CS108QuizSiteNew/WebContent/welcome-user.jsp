<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="account.*"%>
<%@page import="java.util.*"%>
<%@page import="quizzes.*"%>
<%@page import="dataBase.*"%>
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
<%
final int NUM_RECENT_ACTIVITIES = 5;
Account account = (Account) session.getAttribute("Account");
String username = account.getUserName();
String profilePicture = account.getProfilePic();
String status = account.getStatus();
Set<Achievement> achievements = account.getAchievementList();
boolean isAdministrator = account.isAdmin();
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
			<div class="col-xs-7">
			<%
			if(isAdministrator) {
				out.println("<div class=\"well\">");
				out.println("<h1>Administrator Home</h1>");
				out.println("<a class=\"btn btn-success\" href=\"admin-home.jsp\">Go to administrator home</a>");
				out.println("</div>");
			}
			%>
				<div class="well">
					<h1>Announcements</h1>
					<%
					DataBase db = new DataBase();
					ArrayList<String> announcements = db.getAnnouncements();
					if(announcements.size() == 0) {
						out.println("<h3>No announcements</h3>");
					} else {
						for(String announcement : announcements) {
							out.println("<p>" + announcement + "</p>");
						}
					}
					%>
				</div>
				<div class="well">
					<h1>Popular Quizzes</h1>
					<p>This is where we</p>
					<p>will display the most popular quizzes</p>
					<p>sorted by number of plays</p>
					<a class="btn btn-success" href="quizHomePage.jsp">Browse All
						Quizzes</a>
				</div>
				<div class="well">
					<h1>My Recent Quiz Activity</h1>
					<h2>Quizzes Created:</h2>
					<%
					LinkedList<Quiz> quizzesMade = account.getQuizzesCreated();
					if(quizzesMade == null || quizzesMade.size() == 0) {
						out.println("<p>You have not made any quizzes!</p>");
					} else {
						int counter = 0;
						for(int i = 0; i < quizzesMade.size(); i++) {
							out.println("<p> " + quizzesMade.get(i).toString() + "</p>");
							counter++;
							if(counter == 5) break;
						}
					}
					%>
					<h2>Quizzes Taken:</h2>
					<%
					LinkedList<QuizResult> pastResults = account.getPastPerformances();
					if(pastResults == null || pastResults.size() == 0) {
						out.println("<p>You have not taken any quizzes!</p>");
					} else {
						int counter = 0;
						for(int i = 0; i < pastResults.size(); i++) {
							out.println("<p> " + pastResults.get(i).toString() + "</p>");
							counter++;
							if(counter == 5) break;
						}
					}
					%>
					<a class="btn btn-success" href="complete-user-history.jsp">View complete history</a>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="well">
					<%
					out.println("<h1>" + username + "</h1>");
					out.println("<img src=\"" + profilePicture + "class=\"img-rounded\" alt=\"Rounded Image\" height=\"150\" width=\"200\">");
					%>
					<h2>Status:</h2>
					<% if(status == null || status.equals("null")) {
						out.println("<p>No current status</p>");
					} else {
						out.println("<p>" + status + "</p>"); 
					}%>
					<a class="btn btn-success" href="update-status.jsp">Update
						Status</a>
					<h1>My Statistics</h1>
					<h2>My rating is:</h2>
					<%out.println("<h1>" + account.getRating() + " /5</h1>"); %>
					<h2>My user score is:</h2>
					<% out.println("<h1>" + account.getUserScore() + "</h1>"); %>
					<h2>My Achievements:</h2>
					<%
					if(achievements.size() == 0) {
						out.println("<p>Uh oh, you haven't achieved anything!</p>");
					} else {
						int counter = 0;
						for(Achievement achievement : achievements) {
							out.println("<p>" + achievement.getName() + "</p>");
							out.println("<img src=\"" + achievement.getURL() + "class=\"img-rounded\" alt=\"Rounded Image\" height=\"100\" width=\"100\">");
							counter++;
							if(counter == 3) break;
						}
						out.println("<p><a class=\"btn btn-success\" href=\"user-achievements.jsp\">All My Achievements</a></p>");
					}
					%>
				</div>
				<div class="well">
					<h1>Mail</h1>
					<%
					if(account.newMessage()) {
						out.println("<h3>You have new messages!</h3>");
					} else {
						out.println("<h3>No new messages.</h3>");
					}
					%>
					<a class="btn btn-success" href="inbox.jsp">Continue to Inbox</a>
				</div>
				<div class="well">
					<h1>Recent Friend Activity</h1>
					<%
					LinkedList<RecentFriendActivity> activity = account.getRecentFriendActivities();
					if(activity == null || activity.size() == 0) {
						out.println("No recent friend activity.");
					} else {
						int counter1 = 0;
						for(RecentFriendActivity recent : activity) {
							String friendURL = "display-user-page.jsp?id=" + recent.userName;
							String quizURL = "quizStartPage.jsp?quizname=" + recent.quizName + "&username=" + recent.userName;
							if(recent.activity == RecentFriendActivity.QUIZ_CREATED) {
								out.println("<p>Your friend <li><a href=" + friendURL + ">" + recent.userName + "</a></li>created the following quiz: <li><a href=" + quizURL + ">" + recent.quizName + "</a></li></p>"); 
							} else if(recent.activity == RecentFriendActivity.QUIZ_TAKEN) {
								out.println("<p>Your friend <li><a href=" + friendURL + ">" + recent.userName + "</a></li>took the following quiz: <li><a href=" + quizURL + ">" + recent.quizName + "</a></li></p>"); 
							} else if(recent.activity == RecentFriendActivity.ACHIEVEMENT_EARNED) {
								out.println("<p>Your friend <li><a href=" + friendURL + ">" + recent.userName + "earned an achievement!</a></li></p>"); 
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