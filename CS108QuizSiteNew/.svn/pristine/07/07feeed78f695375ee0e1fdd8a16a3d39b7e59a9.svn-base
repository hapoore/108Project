package web;

import account.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewUsernameServlet
 */
@WebServlet("/NewUsernameServlet")
public class NewUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUsernameServlet() {
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
		String pw = (String) request.getParameter("password");
		if(username.contains("-") || username.contains(" ") || username.contains(".")) {
			RequestDispatcher dispatch = request.getRequestDispatcher("username-illegal-character.jsp");
			dispatch.forward(request, response);
		} else if(mgr.getAccount(username) != null) { //Can't make that account, already exists
			RequestDispatcher dispatch = request.getRequestDispatcher("username-in-use.jsp");
			dispatch.forward(request, response);
		} else { //Create new account
			mgr.addNewAccount(username, pw);
			Account newAccount = mgr.getAccount(username);
			request.getSession().setAttribute("Account", newAccount);
			RequestDispatcher dispatch = request.getRequestDispatcher("account-setup.jsp");
			dispatch.forward(request, response);
		}
	}

}
