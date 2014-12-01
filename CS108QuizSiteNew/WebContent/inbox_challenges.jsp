<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="account.*"%>
<%@page import="java.util.*"%>
<%@page import="quizzes.*"%>
<%@page import="dataBase.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Inbox</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>

	<%
		String redirect = (String) request.getParameter("redirect");
		if (redirect != null) { //should redirect after proper calculations have taken place
			String decision = request.getParameter("decision");
			String from = request.getParameter("from");
			String quizName = request.getParameter("quizName");
			Account cur_account = (Account) session.getAttribute("Account");

			cur_account.setPassword("Challenge Accepted LOL");
			System.out.println("Decision: " + decision + " From: " + from
					+ " Name: " + quizName);
			cur_account.acceptChallenge(from, quizName);
			if (decision.equals("Accept")) {
				System.out.println("accepting!");
				//redirect!
				response.sendRedirect("inbox.jsp"); //redo this to system likings
				return;
			} else {
				System.out.println("ignoring...pussy");
			}

		}
	%>

	<%
		//account.Account account = (account.Account) session
		//		.getAttribute("Account");
		System.out.println("Hello! Now in Inbox challenges!");

		Account account = (Account) session.getAttribute("Account");
		if (account == null) {
			System.out.println("ERROR SHIIIIIIIIIIIT");
		}
		System.out.println("TEST: " + account.getPassword());
	%>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="welcome-user.jsp">Home</a>
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

			<div class="col-xs-10" style="top: 70px;">
				<h1>Challenges</h1>
				<br>
				<%
					LinkedList<Challenge> chalList = account.getChallenges();
					System.out.println(chalList);
					System.out.println(chalList.toString());
				
					if (chalList.size() == 0) {
						out.println("You have no challenges at this time.");
					}

					else {
						for (Challenge cur : chalList) {
							out.println("<div class=\"well\">");

							DataBase db = Account.getDB();
							Account current = Account.getOnlineAccount(cur.from);
							double rating = 0;
							String imag = "nope";
							if (current == null) {
								db.getRating(cur.from);
								imag = "NOPE"; //TODO do after Parker makes getProfilePic
							} else {
								rating = current.getRating();
								imag = current.getProfilePic();
							}
							out.println("<img src=\""
									+ imag
									+ "\" style=\"width:50px;height:50px\">&nbsp;&nbsp;&nbsp;");
							out.println(cur.from + " &nbsp;| &nbsp;Score: " + cur.score
									+ " &nbsp;| &nbsp;Quiz: " + cur.quizName);

							//TODO!!!!!!!!!!!!!!!!!
							String quizURL = ""; //DO THIS!!!!!! should all be different or not...
							out.println("<form style=\"float:right\" action=\"inbox_challenges.jsp\" method=\"Post\">");

							String quizName = cur.quizName;
							String from = cur.from;

							out.println("<input type=\"hidden\" name=\"redirect\" value=\"yes\">");
							out.println("<input type=\"hidden\" name=\"quizName\" value=\""
									+ quizName + "\">");
							out.println("<input type=\"hidden\" name=\"from\" value=\""
									+ from + "\">");

							out.println("<input class=\"btn btn-success\" type=\"submit\" name=\"decision\" value=\"Accept\">");
							out.println("<input class=\"btn btn-warning\" type=\"submit\" name=\"decision\" value=\"Ignore\">");
							out.println("</form>");

							out.println("</div>");
						}
					}
				%>


			</div>

			<div class="col-xs-2 list-group" style="top: 70px;">

				<!-- <a class="disabled" role="presentation" style="text-align: center;"><font
					size="100px" color="black">Inbox</font></a>-->

				<a href="inbox.jsp" class="list-group-item">Friend Requests</a> <a
					href="inbox_challenges.jsp" class="list-group-item active">Challenges</a>
				<a href="#" class="list-group-item">Messages</a> <a href="#"
					class="list-group-item">Back to Inbox</a>
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"
		type="text/javascript"></script>
	<script src="”js/bootstrap.js”" type="text/javascript"></script>
</body>
</html>