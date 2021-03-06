<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a New Question</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
 <style type="text/css">  	

#response{
	display: none;
}
  
#fill-blank{
  	display: none;
}
  
#estimate{
	display: none;
}

#matching{
	display: none;
}

#multi-answer{
	display: none;
}

#multi-multi{
	display: none;
}

#multi-choice{
	display: none;
}

#math {
	display: none;
}

#picture {
	display: none;
}
	
.check {
	width: 25px;
}
  
</style>
<script src="new-question.js" type="text/javascript">

</script>

</head>
<body>
<h1 id="top">Create a New Question</h1>
<select id="mySelect" onchange="javascript:showSelected();">
<option>-- Select Question Type --</option>
<option value="response">Response Question</option>
<option value="fill-blank">Fill-in-the-Blank Question</option>
<option value="estimate">Estimate Question</option>
<option value="matching">Matching Question</option>
<option value="multi-answer">Multiple Answer Question</option>
<option value="multi-multi">Multiple Choice with Multiple Answers</option>
<option value="multi-choice">Multiple Choice Question</option>
<option value="picture">Picture Question</option>
<option value="math">Random Math Question</option>
</select>
<div id="formDiv">
	<form action="NewQuestionServlet" id="response" method="post">
		<input type="hidden" name="qtype" value="response">
		<div class="form-group" display="none">
			<label for="question">Question:</label> <input
				type="text" name="question" class="form-control" id="question" placeholder="Question Text">
		</div>
		<div class="form-group" display="none">
			<label for="answer">Answer:</label>			
			<table id="response-table">
				<tr id="response-answer-1">
			<td><input type="text" name="left-1" class="form-control" id="answer" placeholder="Answer 1"></td>
			<td id="response-alt-1"><a float="right" href="javascript:addAlternate(1, 'response');">Add alternate answer</a></td>
				</tr>
			</table>
			</div>
		<div class="form-group" display="none">
			<label for="points">Points</label> <input
				type="number" name="points" class="form-control" id="points" placeholder="5">
		</div>
		<button type="submit" class="btn btn-success">Submit</button>
	</form>
	
	<form action="NewQuestionServlet" id="fill-blank" method="post">
	<input type="hidden" name="qtype" value="fill-blank">
		<div class="form-group">
			<label for="question">Question:</label> <input
				type="text" name="question" class="form-control" id="question" placeholder="Question Text">
		</div>
		<div class="form-group">
			<label for="answer">Answer:</label> <input
				type="text" name="answer" class="form-control" id="answer" placeholder="Answer">
		</div>
		<div class="form-group">
			<label for="points">Points</label> <input
				type="number" name="points" class="form-control" id="points" placeholder="5">
		</div>
		<button type="submit" class="btn btn-success">Submit</button>
	</form>
	
	<form action="NewQuestionServlet" id="estimate" method="post">
	<input type="hidden" name="qtype" value="estimate">
		<div class="form-group">
			<label for="question">Question:</label> <input
				type="text" name="question" class="form-control" id="question" placeholder="Question Text">
		</div>
		<div class="form-group">
			<label for="answer">Answer (number):</label> <input
				type="number" name="answer" class="form-control" id="answer" placeholder="Answer">
		</div>
		<div class="form-group">
			<label for="points">Points</label> <input
				type="number" name="points" class="form-control" id="points" placeholder="5">
		</div>
		<button type="submit" class="btn btn-success">Submit</button>
	</form>
	
	<form action="NewQuestionServlet" id="matching" method="post">
	<input type="hidden" name="qtype" value="matching">
		<div class="form-group">
			<label for="question">Question:</label> <input
				type="text" name="question" class="form-control" id="question" placeholder="Question Text">
		</div>
		<div class="form-group">
			<label for="answer">Matches:</label> 
			<table id="matches">
				<tr id="match-1">
			<td><input type="text" name="left-1" class="form-control" id="answer" placeholder="Match 1"></td>
			<td><input type="text" name="right-1" class="form-control" id="answer" placeholder="Match 1"></td>
				</tr>
				<tr id="match-2">
			<td><input type="text" name="left-2" class="form-control" id="answer" placeholder="Match 2"></td>
			<td><input type="text" name="right-2" class="form-control" id="answer" placeholder="Match 2"></td>
				</tr>				
				<tr id="match-3">
			<td id="col-1"><input type="text" name="left-3" class="form-control" id="answer" placeholder="Match 3"></td>
			<td><input type="text" name="right-3" class="form-control" id="answer" placeholder="Match 3"></td>
			<td id="more"><a float="right" href="javascript:addMatch();">Add another match</a></td>
				</tr>
			</table>
			
		</div>
		<div class="form-group">
			<label for="points">Points</label> <input
				type="number" name="points" class="form-control" id="points" placeholder="5">
		</div>
		<button type="submit" class="btn btn-success">Submit</button>
	</form>
	
	<form action="NewQuestionServlet" id="multi-answer" method="post">
	<input type="hidden" name="qtype" value="multi-answer">
		<div class="form-group">
			<label for="question">Question:</label> <input
				type="text" name="question" class="form-control" id="question" placeholder="Question Text">
		</div>
		<div class="form-group">
			<label for="answer">Answers:</label> 
			<table id="multi-answers">
				<tr id="multi-answer-1">
			<td><input type="text" name="left-1" class="form-control" id="answer" placeholder="Answer 1"></td>
			<td id="multi-alt-1"><a float="right" href="javascript:addAlternate(1, 'multi');">Add alternate answer</a></td>
				</tr>
				<tr id="multi-answer-2">
			<td><input type="text" name="left-2" class="form-control" id="answer" placeholder="Answer 2"></td>
			<td id="multi-alt-2"><a float="right" href="javascript:addAlternate(2, 'multi');">Add alternate answer</a></td>
				</tr>				

			</table>
			<a float="right" href="javascript:addAnswer();">Add another answer</a>
			
		</div>
		<div class="form-group">
			<label for="points">Points</label> <input
				type="number" name="points" class="form-control" id="points" placeholder="5">
		</div>
		<button type="submit" class="btn btn-success">Submit</button>
	</form>
	
		<form action="NewQuestionServlet" id="multi-multi" method="post">
		<input type="hidden" name="qtype" value="multi-multi">
		<div class="form-group">
			<label for="question">Question:</label> <input
				type="text" name="question" class="form-control" id="question" placeholder="Question Text">
		</div>
		<div class="form-group">
			<label for="answer">Options - Check Correct Answers</label> 
			<table id="multi-options">
				<tr id="multi-option-1">
			<td class="check"><input type="checkbox" name="correct" value="option1"></td>
			<td><input type="text" name="left-1" class="form-control" id="answer" placeholder="Option 1"></td>
				</tr>
				<tr id="multi-option-2">
			<td class="check"><input type="checkbox" name="correct" value="option2"></td>
			<td><input type="text" name="left-2" class="form-control" id="answer" placeholder="Option 2"></td>
				</tr>				

			</table>
			<a float="right" href="javascript:addCheck();">Add another answer</a>
			
		</div>
		<div class="form-group">
			<label for="points">Points</label> <input
				type="number" name="points" class="form-control" id="points" placeholder="5">
		</div>
		<button type="submit" class="btn btn-success">Submit</button>
	</form>
	
			<form action="NewQuestionServlet" id="multi-choice" method="post">
			<input type="hidden" name="qtype" value="multi-choice">
		<div class="form-group">
			<label for="question">Question:</label> <input
				type="text" name="question" class="form-control" id="question" placeholder="Question Text">
		</div>
		<div class="form-group">
			<label for="answer">Options - Check Correct Answer</label> 
			<table id="multi-choices">
				<tr id="multi-choice-1">
			<td class="check"><input type="radio" name="correct" value="choice1"></td>
			<td><input type="text" name="left-1" class="form-control" id="answer" placeholder="Choice 1"></td>
				</tr>
				<tr id="multi-choice-2">
			<td class="check"><input type="radio" name="correct" value="choice2"></td>
			<td><input type="text" name="left-2" class="form-control" id="answer" placeholder="Choice 2"></td>
				</tr>				

			</table>
			<a float="right" href="javascript:addChoice();">Add another answer</a>
			
		</div>
		<div class="form-group">
			<label for="points">Points</label> <input
				type="number" name="points" class="form-control" id="points" placeholder="5">
		</div>
		<button type="submit" class="btn btn-success">Submit</button>
	</form>
	
	<form action="NewQuestionServlet" id="picture" method="post">
	<input type="hidden" name="qtype" value="picture">
		<div class="form-group" display="none">
			<label for="question">Question:</label> <input
				type="text" name="question" class="form-control" id="question" placeholder="Question Text">
		</div>
		<div class="form-group" display="none">
			<label for="picture">Picture:</label> <input
				type="url" name="picture" class="form-control" id="pic" placeholder="Picture URL">
		</div>
		<div class="form-group" display="none">
			<label for="answer">Answer:</label> <input
				type="text" name="answer" class="form-control" id="answer" placeholder="Answer">
		</div>
		<div class="form-group" display="none">
			<label for="points">Points</label> <input
				type="number" name="points" class="form-control" id="points" placeholder="5">
		</div>
		<button type="submit" class="btn btn-success">Submit</button>
	</form>
	
	<form action="NewQuestionServlet" id="math" method="post">
	<input type="hidden" name="qtype" value="math">
		<div class="form-group" display="none">
			<label for="question">Select Difficulty:</label>
		</div>
		<div class="form-group" display="none">
			<select id="difficulty">
			<option value="easy">Easy</option>
			<option value="medium">Medium</option>
			<option value="hard">Hard</option>
			</select>
		</div>
		<div class="form-group" display="none">
			<label for="points">Points</label> <input
				type="number" name="points" class="form-control" id="points" placeholder="5">
		</div>
		<button type="submit" class="btn btn-success">Submit</button>
	</form>
</div>

</body>
</html>