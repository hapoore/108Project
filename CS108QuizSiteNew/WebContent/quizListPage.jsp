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
<%
	Information information = new Information();
	List<String> tags = information.getTags();
	String tagsString = "";
	for(int i = 0; i < tags.size(); i++){
		if(i != tags.size() - 1)
			tagsString += "#" + tags.get(i) + ",";
		else
			tagsString += "#" + tags.get(i);
	}
	Quiz quiz = (Quiz)session.getAttribute("quiz");
	List<Quiz> searchResults;
	DataBase db = new DataBase();
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
			<h1>Quiz Homepage</h1>
			<p>Click on a quiz name for more information about that quiz</p>
		</div>
		<div class="row">
		<% 
		for(int i = 0; i < tags.size(); i++){
			String accordiontag = tags.get(i);
			if(tags.size() == 1){
		%>
				<div class="col-xs-4 col-xs-offset-4">
		<%
			}
			else if(tags.size() == 2 && i == 0){
		%>
				<div class="col-xs-4 col-xs-offset-2">
		<%
			}
			else{
		%>
				<div class="col-xs-4">
		<%
			}
		%>
    			<div class="well">
    				<div style="text-align: center">
    					<h3><%= tags.get(i) %></h3>
    					<div class="accordion" id=<%=accordiontag%>>
    					  	<%
    							searchResults = db.getQuizByTag(tags.get(i));
    							System.out.println(searchResults);
    							
    							for(int j = 0; j < searchResults.size(); j++){
    								Quiz curr = searchResults.get(j);
    								String label = tags.get(i) + j;
    						%>
    								<div class="panel">
   										<button type="button" class="btn btn-info" data-toggle="collapse" data-target=<%="#"+ label%> data-parent=<%=tagsString%>><%= curr.getName() %> </button>
    										<div id=<%= label%> class="collapse">
        										<p>Description: <%= curr.getDescription() %></p>
												<p>Number of Questions: <%= curr.getQuestions().size() %></p>
												<p>Creator: <%= curr.getCreator() %></p>
												<p>Date Created: <%= curr.getCreated() %></p>
												<%
												if(curr.getType() != null){
												%>
													<p>Type: <%= curr.getType() %></p>
												<%} %>
												<p>Practice Mode Available: <%= curr.getPracticeMode() %></p>
												<p>Number of Plays: <%= curr.getNumPlays() %></p>
												<p>Rating: <%= curr.getRating() %></p>
												<form action="quizStartPage.jsp">
 													<% session.setAttribute("currQuiz", curr);	%>
 													<button type="submit" class="btn btn-success">Go To Quiz</button>
 												</form>
    										</div>
       					 			</div>
       					 	<%
    							}
    						%>
						</div>
    				</div>
    			</div>
    		</div>
    	<%
		}
    	%>
    	</div>
	</div>
</body>
</html>