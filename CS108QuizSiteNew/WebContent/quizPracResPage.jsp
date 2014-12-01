<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="quizzes.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Quiz Home</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
	Quiz quiz = (Quiz)session.getAttribute("currQuiz");	
	PracticeResult result = (PracticeResult)request.getAttribute("result");
%>
<body>
	<div class="container">
		<div style="text-align: center;">
			<h1><%= quiz.getName() %> Practice Result</h1>
		</div>
 		<div class="row">
   			<div class="col-xs-6 col-xs-offset-3">
    			<div class="well">
					<h1>Your Performance</h1>
					<p> average repetitions = <%= result.averageRepetitions %> </p>
					<p> time needed = <%= result.timeNeeded / 1000 %> seconds </p>
				</div>
  		    </div>
 		</div>
 		<div style="text-align: center">
 			<form action="quizInstructions.jsp">
 				<input name="practiceMode" type="hidden" value= "!practice" >
 				<button type="submit" class="btn btn-success">Take Quiz For Real</button>
 			</form>
 			<form action="quizInstructions.jsp">
 				<input name="practiceMode" type="hidden" value= "practice" >
 				<button type="submit" class="btn btn-warning">Practice Quiz Again</button>
 			</form>
 				<a class="btn btn-danger" href="quizListPage.jsp">Find Another Quiz</a>
 		</div>
 		<div style="text-align: center">
 			<a class="btn btn-danger" href="quizStartPage.jsp">Look at stats they are so cool arent they just being all statlike</a>
 		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"
		type="text/javascript"></script>
	<script src="”js/bootstrap.js”" type="text/javascript"></script>
</body>
</html>