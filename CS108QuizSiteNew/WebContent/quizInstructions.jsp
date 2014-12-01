<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="quizzes.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Instructions</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<%
	Quiz quiz = (Quiz)session.getAttribute("currQuiz");	
	Information instructions = new Information();
	String variableWord = "to";
	String oppositeMode = "practice";
%>
<body>
	<div class="container">
		<div style="text-align: center">
			<h1><%= quiz.getName() %> Instructions</h1>
		</div>
		<% 
			String practiceMode = "practice";
			if(practiceMode.equals(request.getAttribute("practiceMode")) || practiceMode.equals(request.getParameter("practiceMode"))){
				variableWord = "from";
				oppositeMode = "!practice";
		%>
				<p><%= instructions.practiceModeInstructions() %></p>
		<%} %>
		<p><%= instructions.getInstructions()%></p>
		<div style="text-align: center">
			<form action="QuizServlet" method="post">
 				<input name="practiceMode" type="hidden" value= <%=request.getParameter("practiceMode") %> >
 				<button type="submit" class="btn btn-success">Start Quiz</button>
 			</form>
 			<%if(quiz.getPracticeMode()){ %>
 			<form action="quizInstructions.jsp">
 				<input name="practiceMode" type="hidden" value= <%=oppositeMode %> >
 				<button type="submit" class="btn btn-warning">Change <%= variableWord %> Practice Mode</button>
 			</form>
 			<%} %>
			<a href="quizListPage.jsp"
					class="btn btn-large btn-danger">Find Another Quiz</a>
		</div>
	</div>	
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"
		type="text/javascript"></script>
	<script src="”js/bootstrap.js”" type="text/javascript"></script>
</body>
</html>