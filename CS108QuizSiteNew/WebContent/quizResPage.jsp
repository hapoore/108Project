<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="quizzes.*, questions.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<%
	Quiz quiz = (Quiz)session.getAttribute("currQuiz");	
	QuizResult result = (QuizResult)request.getAttribute("result");
	Map<Question, ArrayList<String>> answers = (HashMap<Question, ArrayList<String>>)request.getAttribute("answers");
%>
<meta charset="utf-8">
<title>Quiz Home</title>
<link rel="stylesheet"
      href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
<link rel="stylesheet"
     href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap-theme.min.css">
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
	<script type="text/javascript">
	function starClick(i){
	    for(var button = 1; button <= i; button++){
	    	document.getElementById(button).src = "quizImages/fullstar.jpg";
		}
	    i++;
	    for(var button2 = i; button2 < 6; button2++){
	    	document.getElementById(button2).src = "quizImages/emptystar.jpg";
	    }
	    i--;
	    var elem = document.getElementById("ratingid1");
	    elem.value = i;
	    elem = document.getElementById("ratingid2");
	    elem.value = i;
	    elem = document.getElementById("ratingid3");
	    elem.value = i;
	    elem = document.getElementById("ratingid4");
	    elem.value = i;
	}
	</script>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>
	<div class="container">
		<div style="text-align: center">
			<h1><%= quiz.getName() %> Result</h1>
			<h3>Rate Quiz:</h3>
			<div class="btn-group">
				<img src="quizImages/emptystar.jpg" id="1" onClick="starClick('1')"></img>
				<img src="quizImages/emptystar.jpg" id="2" onClick="starClick('2')"></img>
				<img src="quizImages/emptystar.jpg" id="3" onClick="starClick('3')"></img>
				<img src="quizImages/emptystar.jpg" id="4" onClick="starClick('4')"></img>
				<img src="quizImages/emptystar.jpg" id="5" onClick="starClick('5')"></img>
			</div>
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
        							<td id="td_id_1" class="td-class-1"><%= i + 1%></td>
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
					<h1>Your Performance</h1>
					<p>personal best: <br> </p>
					<p><%=  result.onScreen() %></p>
					<p> <br> rank: <%= quiz.getStatistics().getRankOf(result) %></p>
					
				</div>
				<div class="well">
				<h2>Question Results:</h2>
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
						<table id="table" class="table table-striped table-bordered">
							<thead>
								<tr>
								<%
								if((quiz.getQuestions().get(i)).type() == 0 || (quiz.getQuestions().get(i)).type() == 1 || (quiz.getQuestions().get(i)).type() == 3){
								%>
									<th>Your Answer</th>
									<th>Correct Answers</th>
								<%
								}
								else if((quiz.getQuestions().get(i)).type() == 2 || (quiz.getQuestions().get(i)).type() == 5 || (quiz.getQuestions().get(i)).type() == 6){
								%>
									<th>Your Answer</th>
									<th>Correct Answer</th>
								<%
								}
								else{
								%>
									<th>Your Answers</th>
									<th>Correct Answers</th>
								<%
								}
								%>
								</tr>
							</thead>
							<tbody>
								<%
								if((quiz.getQuestions().get(i)).type() == 0){
									for(int j = 0; j < ((ResponseQuestion)(quiz.getQuestions().get(i))).getAnswers().size(); j++){
								%>
								<tr id="tr_id_1" class="tr-class-1">
									<%
										String currAnswer = "";
										if(j == 0)
											currAnswer = answers.get(quiz.getQuestions().get(i)).get(j);
									%>
        							<td id="td_id_1" class="td-class-1"><%=currAnswer%></td>
       								<td><%= ((ResponseQuestion)(quiz.getQuestions().get(i))).getAnswers().get(j)%></td>
   								</tr>
   								<%
									}
   								}
								else if((quiz.getQuestions().get(i)).type() == 1){
									for(int j = 0; j < ((FillBlankQuestion)(quiz.getQuestions().get(i))).getAnswers().size(); j++){
								%>
								<tr id="tr_id_1" class="tr-class-1">
									<%
										String currAnswer = "";
										if(j == 0)
											currAnswer = answers.get(quiz.getQuestions().get(i)).get(j);
									%>
        							<td id="td_id_1" class="td-class-1"><%=currAnswer%></td>
       								<td><%= ((FillBlankQuestion)(quiz.getQuestions().get(i))).getAnswers().get(j)%></td>
   								</tr>
   								<%
									}
   								}
								else if((quiz.getQuestions().get(i)).type() == 2){
								%>
								<tr id="tr_id_1" class="tr-class-1">
								<%
									String currAnswer = answers.get(quiz.getQuestions().get(i)).get(0);
								%>
        							<td id="td_id_1" class="td-class-1"><%=currAnswer%></td>
       								<td><%= ((MultipleChoice)(quiz.getQuestions().get(i))).getAnswer()%></td>
   								</tr>
   								<%
								}
								else if((quiz.getQuestions().get(i)).type() == 3){
									for(int j = 0; j < ((PictureResponseQuestion)(quiz.getQuestions().get(i))).getAnswers().size(); j++){
								%>
								<tr id="tr_id_1" class="tr-class-1">
									<%
										String currAnswer = "";
										if(j == 0)
											currAnswer = answers.get(quiz.getQuestions().get(i)).get(j);
									%>
        							<td id="td_id_1" class="td-class-1"><%=currAnswer%></td>
       								<td><%= ((PictureResponseQuestion)(quiz.getQuestions().get(i))).getAnswers().get(j)%></td>
   								</tr>
   								<%
									}
   								}
								else if((quiz.getQuestions().get(i)).type() == 4){
									HashMap<String, String> pairs = ((MatchingQuestion)(quiz.getQuestions().get(i))).getAnswers();
		    						Iterator<String> keysIter = pairs.keySet().iterator();
		    						int j = 0;
		    						while (keysIter.hasNext()) {
		    							String key = keysIter.next();
		    							String correct = key + ";  " + pairs.get(key);
									%>
									<tr id="tr_id_1" class="tr-class-1">
        							<td id="td_id_1" class="td-class-1"><%=answers.get(quiz.getQuestions().get(i)).get(j)%></td>
       								<td><%= correct%></td>
   								</tr>
   								<%
   										j++;
									}
   								}
								else if((quiz.getQuestions().get(i)).type() == 5){
									%>
									<tr id="tr_id_1" class="tr-class-1">
									<%
										String currAnswer = answers.get(quiz.getQuestions().get(i)).get(0);
									%>
	        							<td id="td_id_1" class="td-class-1"><%=currAnswer%></td>
	       								<td><%= ((EstimateQuestion)(quiz.getQuestions().get(i))).getAnswer()%></td>
	   								</tr>
	   								<%
								}
								else if((quiz.getQuestions().get(i)).type() == 6){
									%>
									<tr id="tr_id_1" class="tr-class-1">
									<%
										String currAnswer = answers.get(quiz.getQuestions().get(i)).get(0);
									%>
	        							<td id="td_id_1" class="td-class-1"><%=currAnswer%></td>
	       								<td><%= ((RandomMathQuestion)(quiz.getQuestions().get(i))).getAnswer()%></td>
	   								</tr>
	   								<%
									}
								else if((quiz.getQuestions().get(i)).type() == 8){
									int maxAnswers;
									if(answers.get(quiz.getQuestions().get(i)).size() > ((MultiChoiceMultiAnswer)(quiz.getQuestions().get(i))).getAnswers().size())
										maxAnswers = answers.get(quiz.getQuestions().get(i)).size();
									else
										maxAnswers = ((MultiChoiceMultiAnswer)(quiz.getQuestions().get(i))).getAnswers().size();
									for(int j = 0; j < maxAnswers; j++){
									%>
									<tr id="tr_id_1" class="tr-class-1">
									<%
										String currAnswer = "";
										if(answers.get(quiz.getQuestions().get(i)).size() > j)
											currAnswer = answers.get(quiz.getQuestions().get(i)).get(j);
									%>
	        							<td id="td_id_1" class="td-class-1"><%=currAnswer%></td>
	        						<%
	        							currAnswer = "";
	        							if(((MultiChoiceMultiAnswer)(quiz.getQuestions().get(i))).getAnswers().size() > j)
											currAnswer = ((MultiChoiceMultiAnswer)(quiz.getQuestions().get(i))).getAnswers().get(j);
	        						%>
	        						
	       								<td><%= currAnswer%></td>
	   								</tr>
	   								<%
									}
								}
								else{
									for(int j = 0; j < ((MultiAnswerQuestion)(quiz.getQuestions().get(i))).getAnswers().size(); j++){
								%>
										<tr id="tr_id_1" class="tr-class-1">
										<%
											String currAnswer = "";
											if(answers.get(quiz.getQuestions().get(i)).size() > j)
												currAnswer = answers.get(quiz.getQuestions().get(i)).get(j);
											%>
		        							<td id="td_id_1" class="td-class-1"><%=currAnswer%></td>
		        							<%
		        							String possAnswers = "";
		        							for(int k = 0; k <((MultiAnswerQuestion)(quiz.getQuestions().get(i))).getAnswers().get(j).size(); k++){
		       									possAnswers += ((MultiAnswerQuestion)(quiz.getQuestions().get(i))).getAnswers().get(j).get(k);
		       									if(k + 1 != ((MultiAnswerQuestion)(quiz.getQuestions().get(i))).getAnswers().get(j).size())
		       										possAnswers += ";";
		        							}
		        							%>
		       								<td><%= possAnswers%></td>
		   								</tr>
		   						<%
									}
								}
								%>
							</tbody>
						</table>
						<%
						if((quiz.getQuestions().get(i)).type() == 0){
						%>
							<p>Question Score: <%=((ResponseQuestion)quiz.getQuestions().get(i)).getPoints(answers.get(quiz.getQuestions().get(i)).get(0)) %> out of <%=quiz.getQuestions().get(i).getWorth() %>
						<%}
						else if((quiz.getQuestions().get(i)).type() == 1){
						%>
							<p>Question Score: <%=((FillBlankQuestion)quiz.getQuestions().get(i)).getPoints(answers.get(quiz.getQuestions().get(i)).get(0)) %> out of <%=quiz.getQuestions().get(i).getWorth() %>
						<%}
						else if((quiz.getQuestions().get(i)).type() == 2){
						%>
							<p>Question Score: <%=((MultipleChoice)quiz.getQuestions().get(i)).getPoints(answers.get(quiz.getQuestions().get(i)).get(0)) %> out of <%=quiz.getQuestions().get(i).getWorth() %>
						<%}
						else if((quiz.getQuestions().get(i)).type() == 3){
						%>
							<p>Question Score: <%=((PictureResponseQuestion)quiz.getQuestions().get(i)).getPoints(answers.get(quiz.getQuestions().get(i)).get(0)) %> out of <%=quiz.getQuestions().get(i).getWorth() %>
						<%}
						else if((quiz.getQuestions().get(i)).type() == 4){
							ArrayList<String> ans = answers.get(quiz.getQuestions().get(i));
							HashMap<String, String> ans2 = new HashMap<String, String>();
							for(int a = 0; a < ans.size(); a++){
								String input = ans.get(a);
								String key = input.split(";")[0];
								String val = input.split(";")[1].substring(1);
								ans2.put(key, val);
							}
							%>
								<p>Question Score: <%=((MatchingQuestion)quiz.getQuestions().get(i)).getPoints(ans2) %> out of <%=quiz.getQuestions().get(i).getWorth() %>
							<%}
						else if((quiz.getQuestions().get(i)).type() == 5){
						%>
							<p>Question Score: <%=((EstimateQuestion)quiz.getQuestions().get(i)).getPoints(answers.get(quiz.getQuestions().get(i)).get(0)) %> out of <%=quiz.getQuestions().get(i).getWorth() %>
						<%}
						else if((quiz.getQuestions().get(i)).type() == 6){
							%>
								<p>Question Score: <%=((RandomMathQuestion)quiz.getQuestions().get(i)).getPoints(answers.get(quiz.getQuestions().get(i)).get(0)) %> out of <%=quiz.getQuestions().get(i).getWorth() %>
							<%}
						else if((quiz.getQuestions().get(i)).type() == 7){
							%>
								<p>Question Score: <%=((MultiAnswerQuestion)quiz.getQuestions().get(i)).getPoints(answers.get(quiz.getQuestions().get(i))) %> out of <%=quiz.getQuestions().get(i).getWorth() %>
						<%}
						else if((quiz.getQuestions().get(i)).type() == 8){
							%>
								<p>Question Score: <%=((MultiChoiceMultiAnswer)quiz.getQuestions().get(i)).getPoints(answers.get(quiz.getQuestions().get(i))) %> out of <%=quiz.getQuestions().get(i).getWorth() %>
							<%}
						%>
				<%
					}
				%>
				</div>
  		    </div>
 		</div>
 		<div style="text-align: center">
 			<form action="QuizFinishedServlet" method="post">
 				<input name="choice" type="hidden" value = "again">
 				<input name="practiceMode" type="hidden" value= "!practice" >
 				<input name="rating" type="hidden" id="ratingid1" value = "">
 				<button type="submit" class="btn btn-success">Take Quiz Again</button>
 			</form>
 			<%if(quiz.getPracticeMode()){ %>
 			<form action="QuizFinishedServlet" method="post">
 				<input name="choice" type="hidden" value = "practice">
 				<input name="rating" type="hidden" id="ratingid2" value = "">
 				<button type="submit" class="btn btn-warning">Practice Quiz</button>
 			</form>
 			<%} %>
 			<form action="QuizFinishedServlet" method="post">
 				<input name="choice" type="hidden" value = "a">
 				<input name="rating" type="hidden" id="ratingid3" value = "">
 				<input name="finished" type="hidden" value="finished">
 				<button type="submit" class="btn btn-danger">Find Another</button>
 			</form>
 			<form action="QuizFinishedServlet" method="post">
 				<input name="choice" type="hidden" value = "stats">
 				<input name="rating" type="hidden" id="ratingid4" value = "">
 				<button type="submit" class="btn btn-info">Go to Quiz Stats</button>
 			</form>
 		</div>
	</div>
</body>
</html>