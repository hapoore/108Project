<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="quizzes.*, questions.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<% 
	Quiz quiz = (Quiz)session.getAttribute("currQuiz");
	Question question = quiz.getSession().currQuestion;
	int score = quiz.getSession().score;
	int totalQuestions = quiz.getQuestions().size();
	int questionsCompleted = totalQuestions - quiz.getSession().remainingQuestions.size();
	long quizStart = quiz.getSession().startTime;
	long questionStart = System.currentTimeMillis();
	int addnumber = (int)(Math.random() * 10) + 65;
	String addpath = "quizImages/ad" + (char)addnumber + ".JPG";
%>
<meta charset="utf-8">
<title>Quiz Home</title>
<link rel="stylesheet"
      href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
<link rel="stylesheet"
     href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap-theme.min.css">
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function answerClick(i){
	    var elem = document.getElementById("answer" + i);
	    if(elem.value == "yes")
	    	elem.value = "no";
	    else
	    	elem.value = "yes";
	    elem = document.getElementById(i);
	    if(elem.className== "btn btn-warning")
	    	elem.className = "btn btn-info";
	    else
	    	elem.className = "btn btn-warning";
	    		
	}
	function matched(i){
	    var elem = document.getElementById("matching" + i);
	    var elem3 = document.getElementById("match" + i);
	    var input = elem3.value;
	    var elem2 = document.getElementById("matched" + input);
	    if(input%5 == 0){
	    	elem.className = "btn btn-info";
	    	elem2.className = "btn btn-info";
	    }
	    else if(input%5 == 1){
	    	elem.className = "btn btn-success";
	    	elem2.className = "btn btn-success";
	    }
	    else if(input%5 == 2){
	    	elem.className = "btn btn-warning";
	    	elem2.className = "btn btn-warning";
	    }
	    else if(input%5 == 3){
	    	elem.className = "btn btn-danger";
	    	elem2.className = "btn btn-danger";
	    }
	    else if(input%5 == 4){
	    	elem.className = "btn btn-primary";
	    	elem2.className = "btn btn-primary";
	    }
	}
	</script>
	<script type="text/javascript" >
		setInterval(function () {
    		var currentTime = new Date().getTime();
    		var minutes1 = Math.floor(((currentTime - <%= questionStart%>) / 1000) / 60);
    		var seconds1 = Math.round((((currentTime - <%= questionStart%>) / 1000)%60));
    		if(seconds1 < 10)
    			seconds1 = "0" + seconds1;
    		document.getElementById('questionTimer').innerHTML = "Time Taken on This Question: " + "<br>" + "<h2>" + minutes1 + ":" + seconds1 + "</h2>";
    		var minutes2 = Math.floor(((currentTime - <%= quizStart%>) / 1000) / 60);
    		var seconds2 = Math.round((((currentTime - <%= quizStart%>) / 1000)%60));
    		if(seconds2 < 10)
    			seconds2 = "0" + seconds2;
    		document.getElementById('quizTimer').innerHTML = "Time Taken on This Quiz: " + "<br>" + "<h2>" + minutes2 + ":" + seconds2 + "</h2>";
		}, 1);
		$( document ).ready(function() {
	    	var heights = $(".well").map(function() {
	        	return $(this).height();
	    	}).get(),

	    	maxHeight = Math.max.apply(null, heights);

	    	$(".well").height(maxHeight);
		});
	</script>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>QuestionPage</title>
</head>
<body>
	<div class="container container-xs-height">
		<div style="text-align: center">
			<h1><%= quiz.getName() %></h1>
			<%
			if(!quiz.getSession().practiceMode){
			%>
			<h3><%=questionsCompleted - 1 %> / <%= totalQuestions %> Questions Completed</h3>
			<div class="progress progress-striped">
    			<div class="progress-bar" style="width: <%=((double)(100 * (questionsCompleted - 1))) / totalQuestions %>%;"></div>
			</div>
			<%} %>
		</div>
 		<div class="row row-xs-height">
   			<div class="col-xs-2 col-xs-height">
    			<div class="well">
    				<div id="questionTimer"></div>
    				<br>
    				<p>Possible Question Points: </p>
    				<h2> <%=question.getWorth() %></h2>
    				<br><br><br><br><br><br>
    				<img src="quizImages/jabba.JPG">
    			</div>
    		</div>
    		<div class="col-xs-8 col-xs-height col-top">
    			<div class="well">
    				<div style="text-align: center">
    					<%
						if(!quiz.getSession().practiceMode){
						%>
    					<h2>Question #<%=questionsCompleted %></h2>
    					<%}
    					if(question.type() == 0 || question.type() == 5 || question.type() == 6){
    					%>
    						<h2><%= question.getQuestion()%></h2>
    						<br>
							<form action="QuizServlet" method="post">
							Answer: <input type="text" name="answer"><br><br>
							<button type="submit" class="btn btn-success">Submit</button>
							</form>
						<%
    					}
    					else if(question.type() == 1){
    						String questionpart1 = question.getQuestion().split("_")[0];
    						String questionpart2 = question.getQuestion().split("_")[1];
        				%>
    						<form action="QuizServlet" method="post">
    						<%=questionpart1 %> <input type="text" name="answer"><%=questionpart2%><br><br>
    						<button type="submit" class="btn btn-success">Submit</button>
    						</form>
    					<%
        				}
						else if(question.type() == 2){
	    				%>
	    						<h2><%= question.getQuestion()%></h2>
	    						<br>
	    						<%for(int i = 0; i < ((MultipleChoice)question).getChoices().size(); i++){
	    						%>
								<form action="QuizServlet" method="post">
									<input name="answer" type="hidden" value= <%=((MultipleChoice)question).getChoices().get(i) %> >
									<button type="submit" class="btn btn-info"><%=((MultipleChoice)question).getChoices().get(i) %></button>
								</form>
								<br>
						<%
	    						}
						}
						else if(question.type() == 3){
		    			%>
		    				<h2><%= question.getQuestion()%></h2>
		    				<img src=<%=((PictureResponseQuestion)question).getURL()%>>
		    				<br><br><br><br>
		    				<form action="QuizServlet" method="post">
							Answer: <input type="text" name="answer"><br><br>
							<button type="submit" class="btn btn-success">Submit</button>
							</form>
						<%
						}
    					//Collections.shuffle(fileList, new Random(seed));
    					else if(question.type() == 4){
    						HashMap<String, String> pairs = ((MatchingQuestion)question).getAnswers();
    						Iterator<String> keysIter = pairs.keySet().iterator();
    						Iterator<String> valuesIter = pairs.values().iterator();
    						ArrayList<String> keys = new ArrayList<String>();
    						ArrayList<String> values = new ArrayList<String>();
    						while (keysIter.hasNext() && valuesIter.hasNext()) {
    							keys.add(keysIter.next());
    							values.add(valuesIter.next());
    						}
    						Collections.shuffle(values, new Random());
    						String valuesString = "";
    						for(int i = 0; i < values.size(); i++){
    							valuesString += values.get(i) + ";";
    						}
    						valuesString = valuesString.substring(0,valuesString.length() - 1);
    						int num = keys.size();
    		    			%>
    		    				<h2><%= question.getQuestion()%></h2>
    		    				<h3>Type the ID of the value you would like to match with in the box next to each key</h3>
    		    				<br>
    		    				<div class="col-xs-3">
    		    					<%for(int i = 0; i < keys.size(); i++){
    		    						String matchingID = "matching" + i;
    		    					%>
    		    						<button class="btn" style="height:44px; width:100px" name=<%=matchingID %> id=<%=matchingID %>><%=keys.get(i) %></button>
    		    						<br><br>
    		    					<%} %>
    		    				</div>
    		    				<div class="col-xs-3">
    		    					<form action="QuizServlet" method="post">
    		    					<%for(int i = 0; i < keys.size(); i++){
    		    						String matchingID = "match" + i;
    		    					%>
    		    						
    		    						Match:<input type="text" size="5" name=<%=matchingID %> id=<%=matchingID %> onkeyup="matched(<%=i%>)">
    		    						<br><br><br>
    		    					<%} %>
    		    						<input type="hidden" name="valuesString" value=<%=valuesString %>>
    								<button type="submit" class="btn btn-success">Submit</button>
    								</form>
    		    				</div>
    		    				<div class="col-xs-3">
    		    				</div>
    		    				<div class="col-xs-3">
    		    					<%for(int i = 0; i < values.size(); i++){
    		    						String matchingID = "matched" + i;
    		    						String buttonText = "#" + Integer.toString(i) + ":            " + values.get(i);
    		    					%>
    		    						<button class="btn" style="height:44px; width:300px text-align:left" name=<%=matchingID %> id=<%=matchingID %>><%=buttonText %></button>
    		    						<br><br>
    		    					<%} %>
    		    				</div>
    						<%
    						}
						else if(question.type() == 7){
							int numAnswers = ((MultiAnswerQuestion)question).getAnswers().size();
	    				%>
    						<h2><%= question.getQuestion()%></h2>
    						<br>
    						<form action="QuizServlet" method="post">
    					<%
    						for(int i = 0; i < numAnswers; i++){
    							String name = "answer" + i;
    					%>
								<%=i %>: <input type="text" name=<%=name%>><br><br>
						<%
    						}
    					%>
    						<button type="submit" class="btn btn-success">Submit</button>
							</form>
						<%
    					}
    					else{
							int numAnswers = ((MultiChoiceMultiAnswer)question).getAllChoices().size();
		    				%>
	    						<h2><%= question.getQuestion()%></h2>
	    						<br>
	    					<%
	    						for(int i = 0; i < numAnswers; i++){
	    					%>
									<button class="btn btn-warning" id=<%=i %> onClick="answerClick(<%=i%>)"><%=((MultiChoiceMultiAnswer)question).getAllChoices().get(i)%></button>
									<br>
							<%	}
    						%>
	    						<form action="QuizServlet" method="post">
	    					<%
	    						for(int i = 0; i < numAnswers; i++){
	    							String name = "answer" + i;
	    					%>
									<input name=<%=name %> type="hidden" id=<%=name %> value ="no">
						<%		}
    					%>
    							<br>
    							<button type="submit" class="btn btn-success">Submit</button>
								</form>
						<%
						}
						String message = "Quit Quiz";
						if(quiz.getSession().practiceMode){
							message = "Stop Practicing";
						}
						%>
													<br>
							<form action="quizStartPage.jsp">
								<input name="sessionDone" type="hidden" value= "done" >
 								<button type="submit" class="btn btn-danger"><%=message %></button>
							</form>
						<br>
						<img src = <%=addpath %> style="height: 300"></img>
						<p> </p>
					</div>
    			</div>
    		</div>
    		<div class="col-xs-2 col-xs-height col-top">
    			<div class="well">
    				<div id="quizTimer"></div>
    				<br>
    				<p>Current Points: </p>
    				<h2><%=quiz.getSession().score %></h2>
    				<br>
    				<p>Total Quiz Points: </p>
    				<h2><%=quiz.getMaxScore() %></h2>
    				<br>
    				<div class="embed-responsive embed-responsive-4by3">
  					<iframe width="123" height="125" class="embed-responsive-item" src="//www.youtube.com/embed/dQw4w9WgXcQ?autoplay=1"></iframe>
					</div>
					<br><br><br><br><br><br><br>
    			</div>
    		</div>
    	</div>
    </div>
</body>
</html>