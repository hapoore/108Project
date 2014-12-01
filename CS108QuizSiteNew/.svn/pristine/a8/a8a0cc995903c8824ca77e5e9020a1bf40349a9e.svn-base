<%@page import="account.Account, web.AccountManager"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Wrong Security Answer</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
				<div class="well">
					<%
String username = request.getParameter("id");
ServletContext context = request.getServletContext();
AccountManager mgr = (AccountManager) context.getAttribute("Account Manager");
Account currAccount = mgr.getAccount(username);
String secQuestion = currAccount.getSecurityQuestion();
if(secQuestion == null) {
	out.println("<h1>Oops, looks like you don't have a security question.</h1>");
	out.println("<p><a class=\"btn btn-warning\" href=\"homepage.jsp\">Return to homepage</a></p>");
}
else {
	out.println("<h1>Incorrect answer to security question. Please try again.</h1>");
	out.println("<h1>Your security question is " + secQuestion + "</h1>");
	out.println("<p>Please provide the answer to your security question.</p>");
	out.println("<form action=\"CheckSecurityQuestionServlet\" method=\"post\">");
	out.println("<label for=\"securityAnswer\">Security answer (case sensitive):</label> <input type=\"text\" class=\"form-control\" name=\"securityAnswer\" id=\"securityAnswer\" placeholder=\"Security Answer\">");
	out.println("<button type=\"submit\" class=\"btn btn-success\">Submit</button>");
	out.println("<input name=\"username\" type=\"hidden\" value=\"" + username + "\">"); //Pass hidden username
	out.println("</form>");
}
%>
					<a class="btn btn-warning"
						href="create-account-username-password.jsp">Create New Account</a>
					<a class="btn btn-danger" href="GuestInitializeServlet">Continue
						Without Logging In </a>

				</div>
			</div>
			<div class="col-xs-2"></div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"
		type="text/javascript"></script>
	<script src="”js/bootstrap.js”" type="text/javascript"></script>
</body>
</html>