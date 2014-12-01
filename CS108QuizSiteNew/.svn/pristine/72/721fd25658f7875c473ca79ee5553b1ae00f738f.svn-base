package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.Account;

import quizzes.Quiz;
import dataBase.DataBase;

/**
 * Servlet implementation class RemoveUserServlet
 */
@WebServlet("/RemoveUserServlet")
public class RemoveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveUserServlet() {
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
		String userToRemove = request.getParameter("userToRemove");
		DataBase db = new DataBase();
		if(!db.accountExists(userToRemove)) {
			db.close();
			RequestDispatcher dispatch = request.getRequestDispatcher("user-removal-unsuccessful.jsp");
			dispatch.forward(request, response);
		} else {
			db.removeAccount(userToRemove);
			db.close();
			RequestDispatcher dispatch = request.getRequestDispatcher("user-removal-successful.jsp");
			dispatch.forward(request, response);
		}
	}

}
