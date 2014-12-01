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
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
	Question question = ((Quiz)session.getAttribute("currQuiz")).getSession().currQuestion;
	List<String> answers = (List<String>)request.getAttribute("answers");
	String result = (String)request.getAttribute("result");
%>
<body>
	<div class="container">
		<div class="jumbotron">
			<div style="text-align: center">
				<%
					if(result.equals("success")){
				%>
						<h1>CORRECT!</h1>
						<h3>You got <%= request.getAttribute("questionPoints") %> out of <%= question.getWorth() %> points on this question.</h3>
						<img src="quizImages/kim_happy.jpg" class="img-circle">
				<%
					}
					else if(result.equals("not bad")){
				%>	
						<h1>NOT BAD!</h1>
						<h3>You got <%= request.getAttribute("questionPoints") %> out of <%= question.getWorth() %> points on this question.</h3>
						<img src="quizImages/obama_ok.JPG" class="img-circle">
				<%
					}
					else{
				%>
						<h1>YOU SUCK!</h1>
						<h3>You got <%= request.getAttribute("questionPoints") %> out of <%= question.getWorth() %> points on this question.</h3>
						<img src="quizImages/bush_pissy.jpg" class="img-circle">
				<%}
				if(question.type() == 0 || question.type() == 1 || question.type() == 2 || question.type() == 3 || question.type() == 5  || question.type() == 6){
				%>
					<p><br>Your Answer: </p>
					<p><%= answers.get(0) %></p>
				<%
				}
				if(question.type() == 7 || question.type() == 4 || question.type() == 8){
				%>
					<p><br>Your Answers: </p>
				<%
					for(int i = 0; i < answers.size(); i++){
				%>
						<p><%= answers.get(i) %></p>
				<%
					}
				}
				%>
				<br><br><br>
				<%
				if(question.type() == 0){
				%>
					<p>Acceptable Answers: </p>
				<%
					for(int i = 0; i < ((ResponseQuestion)question).getAnswers().size(); i++){
				%>
						<p><%=((ResponseQuestion)question).getAnswers().get(i) %></p>
				<%
					}
				}
				%>
				<%
				if(question.type() == 1){
				%>
					<p>Acceptable Answers: </p>
				<%
					for(int i = 0; i < ((FillBlankQuestion)question).getAnswers().size(); i++){
				%>
						<p><%=((FillBlankQuestion)question).getAnswers().get(i) %></p>
				<%
					}
				}
				%>
				<%
				if(question.type() == 2){
				%>
					<p>Correct Answer: </p>
					<p><%=((MultipleChoice)question).getAnswer() %></p>
				<%
				}
				%>
				<%
				if(question.type() == 3){
				%>
					<p>Acceptable Answers: </p>
				<%
					for(int i = 0; i < ((PictureResponseQuestion)question).getAnswers().size(); i++){
				%>
						<p><%=((PictureResponseQuestion)question).getAnswers().get(i) %></p>
				<%
					}
				}
				%>
				<%
				if(question.type() == 4){
					HashMap<String, String> pairs = ((MatchingQuestion)question).getAnswers();
					Iterator<String> keysIter = pairs.keySet().iterator();
				%>
					<p>Correct Answers: </p>
				<%
					while (keysIter.hasNext()) {
						String key = keysIter.next();
						String correct = key + ";  " + pairs.get(key);
				%>
						<p><%= correct%></p>
				<%
					}
				}
				%>
				<%
				if(question.type() == 5){
				%>
					<p>Correct Answer: </p>
					<p><%=((EstimateQuestion)question).getAnswer() %></p>
				<%
				}
				%>
				<%
				if(question.type() == 6){
				%>
					<p>Correct Answer: </p>
					<p><%=((RandomMathQuestion)question).getAnswer() %></p>
				<%
				}
				%>
				<%
				if(question.type() == 7){
				%>
					<p>Correct Answers: </p>
				<%
					for(int i = 0; i < ((MultiAnswerQuestion)question).getAnswers().size(); i++){
					%>
						<br>
					<%
						String possAnswers = "";
						for(int j = 0; j < ((MultiAnswerQuestion)question).getAnswers().get(i).size(); j++){
							possAnswers += ((MultiAnswerQuestion)question).getAnswers().get(i).get(j);
								if(j + 1 != ((MultiAnswerQuestion)question).getAnswers().get(i).size())
									possAnswers += ";";
						%>
								<p><%=possAnswers %></p>
						<%
						}
					}
				}
				%>
				<%
				if(question.type() == 8){
				%>
					<p>Correct Answers: </p>
				<%
					for(int i = 0; i < ((MultiChoiceMultiAnswer)question).getAnswers().size(); i++){
					%>
						<br>
						<p><%=((MultiChoiceMultiAnswer)question).getAnswers().get(i) %></p>
					<%
					}
				}
				%>
				<br>
				<br>
				<form action="QuizServlet" method="post">
					<input name="corrected" type="hidden" value= "corrected" >
					<button type="submit" class="btn btn-success">Continue</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
