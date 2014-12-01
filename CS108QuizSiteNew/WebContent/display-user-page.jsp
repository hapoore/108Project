<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="account.*"%>
<%@page import="java.util.*"%>
<%@page import="quizzes.*"%>
<%@page import="dataBase.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Other User Page</title>
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
	<%
Account loggedIn = (Account) session.getAttribute("Account");
String usernameCurrPage = request.getParameter("id");
DataBase db = new DataBase();
Account currPageAccount = db.getAccount(usernameCurrPage);
db.close();
Set<Achievement> achievements = currPageAccount.getAchievementList();
if(loggedIn != null && loggedIn.isFriend(usernameCurrPage)) {
	out.println("<div class=\"container\">");
	out.println("<div class=\"row\">");
	out.println("<div class=\"col-xs-6\">");
	out.println("<div class=\"well\">");
	out.println("<h1>" + usernameCurrPage + "</h1>");
	out.println("<img src=\"" + currPageAccount.getProfilePic() + "\" class=\"img-rounded\" alt=\"Rounded Image\" height=\"150\" width=\"200\">");
	out.println("<h1>" + usernameCurrPage + "'s Achievements and Score</h1>");
	out.println("<h2>Rating:</h2>");
	out.println("<h1>" + currPageAccount.getRating() + " /5</h1>");
	out.println("<h2>User Score:</h2>");
	out.println("<h1>" + currPageAccount.getUserScore() + "</h1>");
	out.println("<h2>Achievements:</h2>");
	int counter = 0;
	if(achievements.size() == 0) {
		out.println("<p>" + usernameCurrPage + " has not earned any achievements.</p>");
	} else {
		for(Achievement achievement : achievements) {
			out.println("<p>" + achievement.getName() + "</p>");
			out.println("<img src=\"" + achievement.getURL() + "class=\"img-rounded\" alt=\"Rounded Image\" height=\"100\" width=\"100\">");
			counter++;
			if(counter == 3) break;
		}
		out.println("<p><a class=\"btn btn-success\" href=\"another-user-achievements.jsp?id=" + usernameCurrPage +"\">All Achievements</a></p>");
	}
	out.println("</div>");
	out.println("<div class=\"well\">");
	out.println("<h1>Status:</h1>");
	String status = currPageAccount.getStatus();
	if(status == null || status.equals("null")) {
		out.println("<p>No current status</p>");
	} else {
		out.println("<p>" + status + "</p>"); 
	}
	out.println("</div>");
	out.println("</div>");
	out.println("<div class=\"col-xs-6\">");
	out.println("<div class=\"well\">");
	out.println("<h1>" + usernameCurrPage + "'s Recent Quiz Activity</h1>");
	out.println("<h2>Quizzes Created:</h2>");
	LinkedList<Quiz> quizzesMade = currPageAccount.getQuizzesCreated();
	if(quizzesMade == null || quizzesMade.size() == 0) {
		out.println("<p>" + usernameCurrPage + " has not made any quizzes!</p>");
	} else {
		int counter1 = 0;
		for(int i = 0; i < quizzesMade.size(); i++) {
			out.println("<p> " + quizzesMade.get(i).toString() + "</p>");
			counter1++;
			if(counter1 == 5) break;
		}
	}
	out.println("<h2>Quizzes Taken:</h2>");
	LinkedList<QuizResult> pastResults = currPageAccount.getPastPerformances();
	if(pastResults == null || pastResults.size() == 0) {
		out.println("<p>You have not taken any quizzes!</p>");
	} else {
		int counter2 = 0;
		for(int i = 0; i < pastResults.size(); i++) {
			out.println("<p> " + pastResults.get(i).toString() + "</p>");
			counter2++;
			if(counter2 == 5) break;
		}
	}
	out.println("</div>");
	out.println("<div class=\"well\">");
	out.println("<h1>Actions:</h1>");
	out.println("<p><a class=\"btn btn-success\" href=\"inbox.jsp\">Send a message</a></p>");
	
	out.println("<form action=\"RemoveFriendServlet\" method=\"post\">");
	out.println("<input name=\"removing\" type=\"hidden\" value=\"" + usernameCurrPage + "\">");
	out.println("<p></p>");
	out.println("<button type=\"submit\" class=\"btn btn-danger\">Remove Friend</button>");
	out.println("</div>");
	out.println("</div>");
	out.println("</div>");
} else {
	out.println("<div class=\"container\">");
	out.println("<div class=\"row\">");
	out.println("<div class=\"col-xs-6\">");
	out.println("<div class=\"well\">");
	out.println("<h1>" + usernameCurrPage + "</h1>");
	out.println("<img src=\"" + currPageAccount.getProfilePic() + "\" class=\"img-rounded\" alt=\"Rounded Image\" height=\"150\" width=\"200\">");
	out.println("<h2>Rating:</h2>");
	out.println("<h1>" + currPageAccount.getRating() + " /5</h1>");
	out.println("<h2>User Score:</h2>");
	out.println("<h1>" + currPageAccount.getUserScore() + "</h1>");
	out.println("</div>");
	out.println("</div>");
	out.println("<div class=\"col-xs-6\">");
	out.println("<div class=\"well\">");
	out.println("<h1>" + usernameCurrPage + "'s Achievements</h1>");
	out.println("<h2>Achievements:</h2>");
	int counter3 = 0;
	if(achievements.size() == 0) {
		out.println("<p>" + usernameCurrPage + " has not earned any achievements.</p>");
	} else {
		for(Achievement achievement : achievements) {
			out.println("<p>" + achievement.getName() + "</p>");
			out.println("<img src=\"" + achievement.getURL() + "class=\"img-rounded\" alt=\"Rounded Image\" height=\"100\" width=\"100\">");
			counter3++;
			if(counter3 == 3) break;
		}
		out.println("<p><a class=\"btn btn-success\" href=\"another-user-achievements.jsp?id=" + usernameCurrPage +"\">All Achievements</a></p>");
	}
	out.println("</div>");
	out.println("<div class=\"well\">");
	if(!loggedIn.friendRequestSent(usernameCurrPage)) {
		out.println("<form action=\"AddFriendServlet\" method=\"post\">");
		out.println("<input name=\"addingUsername\" type=\"hidden\" value=\"" + usernameCurrPage + "\">");
		out.println("<button type=\"submit\" class=\"btn btn-default\">Add Friend</button>");
		out.println("</form>");
	} else {
		out.println("<h2>Friend request sent</h2>");
	}
	out.println("</div>");
	out.println("</div>");
	out.println("</div>");
}

%>

</body>
</html>