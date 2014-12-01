package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quizzes.Quiz;

import dataBase.DataBase;

/**
 * Servlet implementation class ClearQuizServlet
 */
@WebServlet("/ClearQuizServlet")
public class ClearQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearQuizServlet() {
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
		String quizToClear = request.getParameter("quizToClear");
		String quizAuthor = request.getParameter("username");
		DataBase db = new DataBase();
		Quiz clearingHistory = db.getQuiz(quizToClear, quizAuthor);
		if(clearingHistory == null) {
			db.close();
			RequestDispatcher dispatch = request.getRequestDispatcher("clear-history-unsuccessful.jsp");
			dispatch.forward(request, response);
		} else {
			clearingHistory.clearStats();
			db.updateQuiz(clearingHistory);
			db.close();
			RequestDispatcher dispatch = request.getRequestDispatcher("clear-history-successful.jsp");
			dispatch.forward(request, response);
		}
	}

}
