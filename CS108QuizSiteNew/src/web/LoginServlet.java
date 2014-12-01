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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		HttpSession session = request.getSession();
		AccountManager mgr = (AccountManager) context.getAttribute("Account Manager");
		String username = (String) request.getParameter("username");
		String pw = (String) request.getParameter("password");
		Account account = mgr.getAccount(username);
		if(account != null) { //Valid username
			if(mgr.passwordMatch(username, pw)) {
				session.setAttribute("Account", account);
				Account.addOnlineAccount(account);
				RequestDispatcher dispatch = request.getRequestDispatcher("welcome-user.jsp"); //Password matches
				dispatch.forward(request, response);
			} else { //PW doesn't match
				RequestDispatcher dispatch = request.getRequestDispatcher("wrong-password.jsp");
				dispatch.forward(request, response);
			}
		} else { //Invalid username
			RequestDispatcher dispatch = request.getRequestDispatcher("username-does-not-exist.jsp");
			dispatch.forward(request, response);
		}
	}

}
