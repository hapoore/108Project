package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dataBase.*;
import quizzes.*;

/**
 * Servlet implementation class RemoveQuizServlet
 */
@WebServlet("/RemoveQuizServlet")
public class RemoveQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveQuizServlet() {
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
		String quizName = request.getParameter("quizToRemove");
		String username = request.getParameter("username");
		DataBase db = new DataBase();
		Quiz removing = db.getQuiz(quizName, username);
		if(removing == null) {
			db.close();
			RequestDispatcher dispatch = request.getRequestDispatcher("quiz-removal-unsuccessful.jsp");
			dispatch.forward(request, response);
		} else {
			db.removeQuiz(removing);
			db.close();
			RequestDispatcher dispatch = request.getRequestDispatcher("quiz-removal-successful.jsp");
			dispatch.forward(request, response);
		}
		
	}

}
