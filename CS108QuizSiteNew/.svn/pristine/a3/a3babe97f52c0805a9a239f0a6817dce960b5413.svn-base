<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="account.*"%>
<%@page import="java.util.*"%>
<%@page import="quizzes.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Inbox</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>

	<%
		String userName = (String) request.getParameter("requestName");
		if (userName != null) { //after a request from same page
			String decision = request.getParameter("decision");

			Account cur_account = (Account) session.getAttribute("Account");
			cur_account.setPassword("New Password!");
			System.out.println(decision + " " + userName + " "
					+ cur_account.getUserName());
			boolean param_dec = true;
			if (decision.equals("Deny")) {
				System.out.println("DENYING");
				param_dec = false;
			} else {
				System.out.println("ACCEPTING");
			}
			cur_account.acceptFriendRequest(param_dec, userName);
		}
	%>

	<%
		account.Account account = (account.Account) session
				.getAttribute("Account");
		System.out.println("Hello! Now in Inbox friend requests");

		//Account account = (Account) session.getAttribute("Account");
		if (account == null) {
			Account nacc = new Account("new", "wen", "djashk");
			nacc.setRating(0, 0);
			
			Challenge chal = new Challenge(69, "yourMom", "MathShit");
			LinkedList<Challenge> listTest = new LinkedList<Challenge>();
			listTest.add(chal);
			nacc.setChallenges(listTest);
			session.setAttribute("Account", nacc);
			account = (Account) session.getAttribute("Account");
			
		}
		System.out.println("TEST: " + account.getPassword());
	%>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="welcome-user.jsp">Home</a>
			</div>
			<a class="btn btn-default navbar-btn" href="quizHomePage.jsp">Browse
				Quizzes</a>
			<form class="navbar-form navbar-left" action="SearchUsersServlet"
				method="post">
				<div class="form-group">
					<input type="text" class="form-control" name="searchString"
						placeholder="Search Users">
				</div>
				<button type="submit" class="btn btn-default">Find User</button>
			</form>
			<form class="navbar-form navbar-left" action="SearchQuizzesServlet"
				method="post">
				<div class="form-group">
					<input type="text" class="form-control" name="searchString"
						placeholder="Search Quizzes">
				</div>
				<button type="submit" class="btn btn-default">Find Quiz</button>
			</form>
			<div class="nav navbar-nav navbar-right">
				<a class="btn btn-default navbar-btn" href="inbox.jsp">Inbox</a> <a
					class="btn btn-default navbar-btn" href="friends.jsp">My
					Friends</a> <a class="btn btn-default navbar-btn"
					href="account-settings.jsp">Account Settings</a> <a
					class="btn btn-default navbar-btn" href="LogoutServlet">Logout</a>
			</div>
		</div>
	</nav>




	<div class="container">
		<div class="row">

			<!--  <ul
				style="position: fixed; top: 51px; bottom: 0; left: 0; display: block; padding: 20px; overflow-y: auto; background-color: #f5f5f5; border-right: 1px solid #eee;"
				class="col-xs-2 nav nav-sidebar">
				<li role="presentation" style="text-align: center;"><font
					size="100px" color="black">Inbox</font></li>
				<li><br></li>
				<li role="presentation" class="active"><a href="#">Friend
						Requests >></a></li>
				<li role="presentation"><a href="inbox_challenges.jsp">Challenges</a></li>
				<li role="presentation"><a href="#">Messages</a></li>
			</ul>-->

			


			<div class="col-xs-10" style="top: 70px;">
				<h1>Friend Requests</h1>
				<br>
				<%
					Set<String> freqs = account.getFriendRequests();
					/*System.out.println("Here1");
					Set<String> freqs = new HashSet<String>();
					System.out.println("Here2");
					freqs.add("rob"); //failed here
					System.out.println("Here3");
					freqs.add("bitch");
					System.out.println("Here4");*/
					//account.setFriendRequests(freqs);
					//System.out.println("Here5");
					if (freqs.size() == 0) {
						out.println("You have no friend requests at this time.");
					}

					else {
						for (String cur : freqs) {
							out.println("<div class=\"well\">");

							Account current = Account.getOnlineAccount(cur);
							double rating = 0;
							String imag = "nope";
							if (current == null) {
								rating = 69;
								imag = "yes";
							} else { //online
								rating = current.getRating();
								imag = current.getProfilePic();
							}
							out.println("<img src=\""
									+ imag
									+ "\" style=\"width:50px;height:50px\">&nbsp;&nbsp;&nbsp;");
							out.println(cur + " &nbsp;| &nbsp;Rating: " + rating
									+ "/5.0");

							//out.println("<div class=\"btn-group\" role=\"group\"");
							out.println("<form style=\"float:right\" action=\"inbox.jsp\" method=\"Post\">");

							out.println("<input type=\"hidden\" name=\"requestName\" value=\""
									+ cur + "\">");

							out.println("<input class=\"btn btn-success\" type=\"submit\" name=\"decision\" value=\"Accept\">");
							out.println("<input class=\"btn btn-danger\" type=\"submit\" name=\"decision\" value=\"Deny\">");
							out.println("</form>");

							out.println("</div>");
						}
					}
				%>


			</div>
			
			<div class="col-xs-2 list-group" style="top: 70px; ">
			
				<!-- <a class="disabled" role="presentation" style="text-align: center;"><font
					size="100px" color="black">Inbox</font></a>-->
			
				<a href="inbox.jsp" class="list-group-item active">Friend Requests</a> 
				
				
				
				<a href="inbox_challenges.jsp"
					class="list-group-item">Challenges</a> <a href="#"
					class="list-group-item">Messages</a> <a href="#"
					class="list-group-item">Back to Inbox</a> 
					<a href="#" class="list-group-item">Send Friend Request</a> 
					<a href="#" class="list-group-item">Send Challenge</a> 
					<a href="#" class="list-group-item">Send Message</a> 
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"
		type="text/javascript"></script>
	<script src="”js/bootstrap.js”" type="text/javascript"></script>
</body>
</html>