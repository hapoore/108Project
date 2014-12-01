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
<style>
.sameLine form {
  float: left;
  background:;
}

.sameLine h1 {
  position: relative;
  top:;
  left: 30px;
}
</style>

<!-- <%
/*Account account = (Account) session.getAttribute("Account");
Set<String> friends = account.getFriendList();

LinkedList<String> other_friends = new LinkedList<String>();
for (String cur: friends) {
	other_friends.add(cur);
}*/

%>

<script type="text/javascript">
	function autofriends() {
		var elem = document.getElementById("autothing");
		var input = elem.value;
		
		var friendList = friends;
		var myArray = [];
		
		for (i = 0; i < friendList.length; i++) { 
		    var cur = friendList[i];
		    var int_value = cur.indexOf(input);
		    if (int_value != -1) {
		    	myArray.push(cur);
		    }
		}
		
		
	}
</script>-->

</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>

	<%
		String userName = "Test";
		//String userName = (String) request.getParameter("conversationName");
		/*if (userName != null) { //after a request from same page
			String decision = request.getParameter("decision");

			Account cur_account = (Account) session.getAttribute("Account");
			cur_account.setPassword("New Password!");
			System.out.println(decision + " " + userName + " "
					+ cur_account.getUserName());
			/*boolean param_dec = true;
			if (decision.equals("Deny")) {
				System.out.println("DENYING");
				param_dec = false;
			} else {
				System.out.println("ACCEPTING");
			}
			cur_account.acceptFriendRequest(param_dec, userName);
			 
		}*/
	%>

	<%
		//account.Account account = (account.Account) session
		//		.getAttribute("Account");
		/**System.out.println("Hello! Now in Inbox friend requests");

		Account account = (Account) session.getAttribute("Account");
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
		System.out.println("TEST: " + account.getPassword());*/
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
				<%
					//out.println("<div><h1>Conversation with " + userName);
					/*LinkedList<Message> messages = account.getConversation(userName)
							.getMessages();*/

					//text box will go here
				%>
				<div class="sameLine">
				<form style="" action="inbox.jsp" method="Post">
					<input class="btn btn-info" type="submit" name="action" value="Back">
				</form>
				<h1>Conversation with Test</h1>
				
				</div>
				<br>

				<form class="row">
					<div class="col-xs-12">
						<div class="input-group">
							<input type="text" class="form-control"> <span
								class="input-group-btn">
								<button class="btn btn-default" type="button">Send!</button>
							</span>
						</div>
						<!-- /input-group -->
					</div>
					<!-- /.col-lg-6 -->
				</form>
				<!-- /.row -->

				<div class="row">
					<br>
					<br>
					<%
						//get rif of this after
						out.println("Start a conversation with this person!");
						/**if (messages.size() == 0) {
							out.println("Start a conversation with this person!");
						} else {
							int size = messages.size();
							for (int i = 0; i < size; i++) {
								Message cur = messages.get(i);
								if (cur.newMessage()) {
									out.println("<div class=\"well\" style=\"background: rgb(22, 105, 173)\">");
								} else {
									out.println("<div class=\"well\">");
								}

								out.println("<div>");
								Account current = Account.getOnlineAccount(userName);
								String imag = "nope";
								if (current == null) {
									imag = "yes";
								} else { //online
									imag = current.getProfilePic();
								}

								out.println("<img src=\"" + imag
										+ "\" style=\"width:50px;height:50px\">");
								if (cur.who() == 'y') {
									out.println("<p>You</p></div>");
								} else {
									out.println("<p>" + userName + "</p></div>");
								}
								//ends div with account info

								out.println("<div>" + cur.getMessage());
								out.println("</div>");

								out.println("</div>");
							}
						}*/
					%>

				</div>
				<!-- ends second row -->

			</div>

			<div class="col-xs-2 list-group" style="top: 70px;">

				<!-- <a class="disabled" role="presentation" style="text-align: center;"><font
					size="100px" color="black">Inbox</font></a>-->

				<a href="inbox.jsp" class="list-group-item">Friend Requests</a> <a
					href="inbox_challenges.jsp" class="list-group-item">Challenges</a>
				<a href="#" class="list-group-item">Messages</a> <a href="#"
					class="list-group-item active">Back to Inbox</a> <a href="#"
					class="list-group-item">Send Friend Request</a> <a href="#"
					class="list-group-item">Send Challenge</a> <a href="#"
					class="list-group-item">Send Message</a>
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"
		type="text/javascript"></script>
	<script src="”js/bootstrap.js”" type="text/javascript"></script>
</body>
</html>