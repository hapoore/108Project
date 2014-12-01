<%@page import="quizzes.*"%>
<%@page import="questions.*"%>
<%@page import = "java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 
Quiz quiz = new Quiz();
ArrayList<String> questions = new ArrayList<String>();
questions.add("bhenchod");
questions.add("snazzlers");
Question question = new ResponseQuestion("How bhenchod is Austin?", questions, 0, 1);
quiz.addQuestion(question);

ArrayList<String> questions3 = new ArrayList<String>();
questions3.add("bae");
questions3.add("barn");
questions3.add("bart");
Question question2 = new MultipleChoice("BETA ALPHA EPSILON?", "bae", questions3, 1, 3);
quiz.addQuestion(question2);

ArrayList<String> questions2 = new ArrayList<String>();
questions2.add("80085");
Question question3 = new FillBlankQuestion("Brad has given _ bjs", questions2, 2, 1);
quiz.addQuestion(question3);

ArrayList<ArrayList<String>> questions4 = new ArrayList<ArrayList<String>>();
questions4.add(new ArrayList<String>(Arrays.asList("snizzle")));
questions4.add(new ArrayList<String>(Arrays.asList("snazzle")));
Question question4 = new MultiAnswerQuestion("sn?", questions4, false, 3, 5);
quiz.addQuestion(question4);

ArrayList<String> questions5 = new ArrayList<String>();
questions5.add("biggie");
questions5.add("big");
Question question5 = new PictureResponseQuestion("Who Dat?", "http://edge-img.datpiff.com/m5a56299/Biggie_Smalls_March_9th-front-large.jpg", questions5, 4, 3);
quiz.addQuestion(question5);

Question question6 = new EstimateQuestion("Between 6 and 7", 5, 4, 3);
quiz.addQuestion(question6);

ArrayList<String> questions6 = new ArrayList<String>();
questions6.add("one");
questions6.add("three");
ArrayList<String> questions7 = new ArrayList<String>();
questions7.add("one");
questions7.add("two");
questions7.add("three");
Question question7 = new MultiChoiceMultiAnswer("Which ones?", questions6, questions7, 6, 5);
quiz.addQuestion(question7);

Question question8 = new RandomMathQuestion(0, 7, 2);
quiz.addQuestion(question8);

HashMap<String, String> pairs = new HashMap<String, String>();
pairs.put("1", "1");
pairs.put("2", "2");
pairs.put("3", "3");
pairs.put("4", "4");
pairs.put("5", "5");
pairs.put("6", "6");
Question question9 = new MatchingQuestion("Match the numbers", pairs, 8, 15);
quiz.addQuestion(question9);


quiz.setName("testQuiz");
quiz.setRandomized(false);
quiz.setCreator("Based God");
quiz.setImmediateCorrection(true);
quiz.setCreated(new Date());
quiz.setPracticeMode(true);
session.setAttribute("quiz", quiz);	
%>
<title>QuizSetUpPage</title>
</head>
<body>
<form action="quizListPage.jsp">
 	<button type="submit" class="btn btn-success">SetUp</button>
</form>
<% 
	List<QuizResult> recents =  quiz.getRecentResults();
	if(recents != null){
		for(int i = 0; i < recents.size(); i++){
%>
<p><%= recents.get(i) %>
<%
		}
	}
%>


</body>
</html>