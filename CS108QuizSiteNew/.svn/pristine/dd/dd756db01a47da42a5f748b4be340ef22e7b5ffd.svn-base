<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="quizzes.*, questions.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<%
	
%>
<meta charset="utf-8">
<title>Created Quiz Review</title>
<link rel="stylesheet"
      href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
<link rel="stylesheet"
     href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap-theme.min.css">
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>
	<div class="container">
		<div style="text-align: center">
			<h1>Review the Quiz That You Created</h1>
			<h3><%= quiz.getName() %></h3>
		</div>
		<div style="text-align: center">
    		<div class="well">
				<h3>Questions:</h3>
				<%
					for(int i = 0; i < quiz.getQuestions().size(); i++){
				%>
						<p>-----------------------------------------------------------------------------------------------------------------</p>
						<p>Question:</p>
						<p><%= quiz.getQuestions().get(i).getQuestion() %></p>
						<%
							if((quiz.getQuestions().get(i)).type() == 3){
						%>
								<img src=<%=((PictureResponseQuestion)(quiz.getQuestions().get(i))).getURL()%> height="50" width="50">
						<%}
						if((quiz.getQuestions().get(i)).type() == 8){
							String choices = "";
							for(int j = 0; j < ((MultiChoiceMultiAnswer)(quiz.getQuestions().get(i))).getAllChoices().size(); j++){
								choices += ((MultiChoiceMultiAnswer)(quiz.getQuestions().get(i))).getAllChoices().get(j);
								if(j + 1 != ((MultiChoiceMultiAnswer)(quiz.getQuestions().get(i))).getAllChoices().size())
									choices += "; ";
							}
						%>
							<p><%= choices%></p>
									
						<%}
						%>
						<%
						if((quiz.getQuestions().get(i)).type() == 0 || (quiz.getQuestions().get(i)).type() == 1 || (quiz.getQuestions().get(i)).type() == 3){
						%>
							<p>Correct Answers</p>
						<%
						}
						else if((quiz.getQuestions().get(i)).type() == 2 || (quiz.getQuestions().get(i)).type() == 5 || (quiz.getQuestions().get(i)).type() == 6){
						%>
							<p>Correct Answer</p>
						<%
						}
						else{
						%>
							<p>Correct Answers</p>
						<%
						}
						%>
						<%
						if((quiz.getQuestions().get(i)).type() == 0){
							for(int j = 0; j < ((ResponseQuestion)(quiz.getQuestions().get(i))).getAnswers().size(); j++){
						%>
       							<p><%= ((ResponseQuestion)(quiz.getQuestions().get(i))).getAnswers().get(j)%></p>
   						<%
							}
   						}
						else if((quiz.getQuestions().get(i)).type() == 1){
							for(int j = 0; j < ((FillBlankQuestion)(quiz.getQuestions().get(i))).getAnswers().size(); j++){
						%>
       							<p><%= ((FillBlankQuestion)(quiz.getQuestions().get(i))).getAnswers().get(j)%></p>
   						<%
							}
   						}
						else if((quiz.getQuestions().get(i)).type() == 2){
						%>
       						<p><%= ((MultipleChoice)(quiz.getQuestions().get(i))).getAnswer()%></p>
   						<%
						}
						else if((quiz.getQuestions().get(i)).type() == 3){
							for(int j = 0; j < ((PictureResponseQuestion)(quiz.getQuestions().get(i))).getAnswers().size(); j++){
						%>
       							<p><%= ((PictureResponseQuestion)(quiz.getQuestions().get(i))).getAnswers().get(j)%></p>
   						<%
							}
   						}
						else if((quiz.getQuestions().get(i)).type() == 4){
							HashMap<String, String> pairs = ((MatchingQuestion)(quiz.getQuestions().get(i))).getAnswers();
		    				Iterator<String> keysIter = pairs.keySet().iterator();
		    				while (keysIter.hasNext()) {
		    					String key = keysIter.next();
		    					String correct = key + ";  " + pairs.get(key);
						%>
       							<p><%= correct%></p>
   						<%
							}
   						}
						else if((quiz.getQuestions().get(i)).type() == 5){
						%>
	       					<p><%= ((EstimateQuestion)(quiz.getQuestions().get(i))).getAnswer()%></p>
	   					<%
						}
						else if((quiz.getQuestions().get(i)).type() == 6){
						%>
	       					<p><%= ((RandomMathQuestion)(quiz.getQuestions().get(i))).getAnswer()%></p>
	   					<%
						}
						else if((quiz.getQuestions().get(i)).type() == 8){
							int maxAnswers = ((MultiChoiceMultiAnswer)(quiz.getQuestions().get(i))).getAnswers().size();
							for(int j = 0; j < maxAnswers; j++){
						%>
						<%
	        				String currAnswer = "";
	        				if(((MultiChoiceMultiAnswer)(quiz.getQuestions().get(i))).getAnswers().size() > j)
								currAnswer = ((MultiChoiceMultiAnswer)(quiz.getQuestions().get(i))).getAnswers().get(j);
	        			%>
	       					<p><%= currAnswer%></p>
	   					<%
							}
						}
						else{
							for(int j = 0; j < ((MultiAnswerQuestion)(quiz.getQuestions().get(i))).getAnswers().size(); j++){
		        				String possAnswers = "";
		        				for(int k = 0; k <((MultiAnswerQuestion)(quiz.getQuestions().get(i))).getAnswers().get(j).size(); k++){
		       						possAnswers += ((MultiAnswerQuestion)(quiz.getQuestions().get(i))).getAnswers().get(j).get(k);
		       						if(k + 1 != ((MultiAnswerQuestion)(quiz.getQuestions().get(i))).getAnswers().get(j).size())
		       							possAnswers += ";";
		        				}
		        		%>
		       					<p><%= possAnswers%></p>
		   				<%
							}
						}
						%>
				<%
					}
				%>
				</div>
  		    </div>
 		</div>
 		<div style="text-align: center">
 			<form action="QuizCreator" method="post">
 				<input name="done" type="hidden" value ="done">
 				<button type="submit" class="btn btn-success">Submit Quiz</button>
 			</form>
 		</div>
	</div>
</body>
</html>