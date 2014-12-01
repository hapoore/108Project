<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="quizzes.*, questions.*"%>
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
<link rel="stylesheet" href="http://tablesorter.com/themes/blue/style.css" type="text/css" media="print, projection, screen" />
<script src="http://code.jquery.com/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="http://tablesorter.com/__jquery.tablesorter.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() 
	    { 
	        $("#userTopScores").tablesorter(); 
	    } 
	); 
</script>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
	Quiz quiz = (Quiz)session.getAttribute("currQuiz");
	if(request.getParameter("sessionDone") != null)
		quiz.endSession();
	
%>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="testing-user-homepage.jsp">Home</a>
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
				<a class="btn btn-default navbar-btn" href="mailbox.jsp">Inbox</a> <a
					class="btn btn-default navbar-btn" href="friends.jsp">My
					Friends</a> <a class="btn btn-default navbar-btn"
					href="account-settings.jsp">Account Settings</a> <a
					class="btn btn-default navbar-btn" href="LogoutServlet">Logout</a>
			</div>
		</div>
	</nav>
	<div class="container">
		<div style="text-align: center">
			<h1><%= quiz.getName() %></h1>
		</div>
 		<div class="row">
   			<div class="col-xs-6">
    			<div class="well">
					<h1>Quiz Statistics</h1>
					<p><%= quiz.getStatistics() %></p>
					<p>High Scores:</p>
					<table id="highScores" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Rank</th>
								<th>User</th>
								<th>Score</th>
								<th>Time Needed</th>
							</tr>
						</thead>
						<tbody>
						<%
						List<QuizResult> highscores = quiz.getTopResults();
						if(highscores.size() != 0){
							for(int i = 0; i < highscores.size(); i++)
							{
							%>
								<tr id="tr_id_1" class="tr-class-1">
        							<td id="td_id_1" class="td-class-1"><%= i + 1 %></td>
       								<td><%= highscores.get(i).user %></td>
      								<td><%= highscores.get(i).score %></td>
      								<td><%= highscores.get(i).timeNeeded / 1000 %></td>
   								</tr>
   							<%
   							}
						}
   						%>
						</tbody>
					</table>
					<p>Recent Scores:</p>
					<table id="recScores" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Time</th>
								<th>User</th>
								<th>Score</th>
								<th>Time Needed</th>
							</tr>
						</thead>
						<tbody>
						<%
						List<QuizResult> recscores = quiz.getRecentResults();
						if(recscores.size() != 0){
							for(int i = 0; i < recscores.size(); i++)
							{
							%>
								<tr id="tr_id_1" class="tr-class-1">
        							<td id="td_id_1" class="td-class-1"><%= recscores.get(i).date %></td>
       								<td><%= recscores.get(i).user %></td>
      								<td><%= recscores.get(i).score %></td>
      								<td><%= recscores.get(i).timeNeeded / 1000 %></td>
   								</tr>
   							<%
   							}
						}
   						%>
						</tbody>
					</table>
				</div>
   			</div>
   			<div class="col-xs-6">
   				<div class="well">
					<h1>Quiz Information</h1>
					<%if(quiz.getDescription() != null){ %>
					<p>Description: <%= quiz.getDescription() %></p>
					<%} %>
					<p>Number of Questions: <%= quiz.getQuestions().size() %></p>
					<p>Creator: <%= quiz.getCreator() %></p>
					<p>Date Created: <%= quiz.getCreated() %></p>
					<%if(quiz.getTag() != null){ %>
					<p>Tag: <%= quiz.getTag() %></p>
					<%} %>
					<%if(quiz.getTag() != null){ %>
					<p>Type: <%= quiz.getType() %></p>
					<%} %>
					<p>Practice Mode Available: <%= quiz.getPracticeMode() %></p>
					<p>Number of Plays: <%= quiz.getNumPlays() %></p>
					<p>Rating: <%= quiz.getRating() %></p>
				</div>
   				<div class="row">
   					<div class="col-xs-12">
    					<div class="well">
							<h1>Your Statistics</h1>
							<p><%= quiz.getStatistics() %></p>
							<p>Top Scores:</p>
							<table id="userTopScores" class="tablesorter">
								<thead>
									<tr>
										<th>Rank</th>
										<th>Score</th>
										<th>Time Needed</th>
										<th>Date</th>
									</tr>
								</thead>
							<tbody>
								<%
									List<QuizResult> usertopscores = quiz.getTopResults();
									if(usertopscores.size() != 0){
										for(int i = 0; i < usertopscores.size(); i++)
										{
								%>
								<tr id="tr_id_1" class="tr-class-1">
        							<td id="td_id_1" class="td-class-1"><%= quiz.getStatistics().getRankOf(usertopscores.get(i)) %></td>
      								<td><%= usertopscores.get(i).score %></td>
      								<td><%= usertopscores.get(i).timeNeeded / 1000 %></td>
      								<td><%= usertopscores.get(i).date %></td>
   								</tr>
   								<%
   										}
									}
   								%>
							</tbody>
						</table>
					</div>
  		   		</div>
  			</div>   		
 		</div>
 		<div style="text-align: center">
 			<form action="quizInstructions.jsp">
 				<input name="practiceMode" type="hidden" value= "!practice" >
 				<button type="submit" class="btn btn-success">Take Quiz</button>
 			</form>
 			<%if(quiz.getPracticeMode()){ %>
 			<form action="quizInstructions.jsp">
 				<input name="practiceMode" type="hidden" value= "practice" >
 				<button type="submit" class="btn btn-warning">Practice Quiz</button>
 			</form>
 			<%} %>
 			<a class="btn btn-danger" href="quizListPage.jsp">Find Another Quiz</a>
 		</div>
	</div>
	<script>
		$( "p" ).click(function() {
  		$( this ).slideUp();
		});
</script>
</body>
</html>