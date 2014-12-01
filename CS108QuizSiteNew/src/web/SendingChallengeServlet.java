package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.Account;
import dataBase.DataBase;

/**
 * Servlet implementation class SendingChallengeServlet
 */
@WebServlet("/SendingChallengeServlet")
public class SendingChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendingChallengeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String challenging = request.getParameter("username");
		Account loggedIn = (Account)request.getSession().getAttribute("Account");

		loggedIn.sendChallenge()
		DataBase db = new DataBase();
		db.updateAccount(loggedIn);
		db.close();
		String returnURL = "display-user-page.jsp?id=" + addingName;
		RequestDispatcher dispatch = request.getRequestDispatcher(returnURL);
		dispatch.forward(request, response);
	}

}
