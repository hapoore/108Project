<%@page import="quizzes.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	QuizResult result = (QuizResult)request.getAttribute("result");
%>
<title>QuizResultPage</title>
</head>
<h1>Results</h1>
<body>
<p> user = <%= result.user %> </p>
<p> score = <%= result.score %> </p>
<p> percentage = <%= result.percentage %> </p>
<p> time needed = <%= result.timeNeeded / 1000 %> seconds </p>
<a href="quizHomePage.jsp">Take Quiz Again</a>
<a href="quizStartPage.jsp">Look at Stats</a>
</body>
</html>