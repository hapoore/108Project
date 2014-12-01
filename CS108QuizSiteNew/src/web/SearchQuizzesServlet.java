package web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quizzes.Quiz;

import dataBase.DataBase;

/**
 * Servlet implementation class SearchQuizzesServlet
 */
@WebServlet("/SearchQuizzesServlet")
public class SearchQuizzesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchQuizzesServlet() {
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
		HttpSession session = request.getSession();
		DataBase db = new DataBase();
		Set<Quiz> results = db.getQuizzes(request.getParameter("searchString"));
		session.setAttribute("Search Results", results);
		db.close();
		RequestDispatcher dispatch = request.getRequestDispatcher("search-quizzes-results.jsp");
		dispatch.forward(request, response);
	}

}
