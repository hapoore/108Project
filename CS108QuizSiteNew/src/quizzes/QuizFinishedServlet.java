package quizzes;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBase.DataBase;

/**
 * Servlet implementation class QuizFinishedServlet
 */
@WebServlet("/QuizFinishedServlet")
public class QuizFinishedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizFinishedServlet() {
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
		DataBase db = new DataBase();
		Quiz quiz = (Quiz)request.getSession().getAttribute("currQuiz");
		if(request.getParameter("rating")!= "")
			quiz.setRating(Integer.parseInt(request.getParameter("rating")));
		if(request.getParameter("finished")!= "null"){
			db.updateQuiz(quiz);
			//System.out.println(quiz.getNumPlays());
		}
		RequestDispatcher dispatch;
		String choice = request.getParameter("choice");
		if(choice.equals("another"))
			dispatch = request.getRequestDispatcher("quizListPage.jsp");
		if(choice.equals("again")){
			dispatch = request.getRequestDispatcher("quizInstructions.jsp");
			request.setAttribute("practiceMode", "!practice");
		}
		if(choice.equals("practice")){
			dispatch = request.getRequestDispatcher("quizInstructions.jsp");
			request.setAttribute("practiceMode", "practice");
		}
		else
			dispatch = request.getRequestDispatcher("quizStartPage.jsp");
		dispatch.forward(request, response);
	}

}
