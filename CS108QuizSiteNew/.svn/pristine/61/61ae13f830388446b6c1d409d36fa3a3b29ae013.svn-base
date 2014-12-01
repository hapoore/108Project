package web;

import account.*;
import dataBase.*;
import questions.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckSecurityQuestionServlet
 */
@WebServlet("/CheckSecurityQuestionServlet")
public class CheckSecurityQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckSecurityQuestionServlet() {
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
		ServletContext context = request.getServletContext();
		AccountManager mgr = (AccountManager) context.getAttribute("Account Manager");
		String username = (String) request.getParameter("username");
		String attemptedSecAnswer = (String) request.getParameter("securityAnswer");
		Account account = mgr.getAccount(username);
		if(account.getSecurityAnswer().equals(attemptedSecAnswer)) {
			RequestDispatcher dispatch = request.getRequestDispatcher("forgot-password.jsp"); //Answered question, time to display password hint
			dispatch.forward(request, response);
		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("wrong-security-answer.jsp"); //Answered question wrong, redirect
			dispatch.forward(request, response);
		}
	}

}
