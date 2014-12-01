package quizzes;

import java.io.IOException;
import java.util.Date;

import account.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuizCreatorServlet
 */
@WebServlet("/QuizCreatorServlet")
public class QuizCreatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizCreatorServlet() {
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
		Quiz quiz = new Quiz();
		if(request.getParameter("practice").equals("practice"))
			quiz.setPracticeMode(true);
		else
			quiz.setPracticeMode(false);
		quiz.setCreator(((Account)request.getSession().getAttribute("Account")).getUserName());
		if(request.getParameter("multiple").equals("multiple"))
			quiz.setMultiplePages(true);
		else
			quiz.setMultiplePages(false);
		quiz.setCreated(new Date());
		quiz.setName(request.getParameter("name"));
		if(request.getParameter("randomized").equals("randomized"))
			quiz.setRandomized(true);
		else
			quiz.setRandomized(false);
		if(request.getParameter("immediate").equals("immediate"))
			quiz.setImmediateCorrection(true);
		else
			quiz.setImmediateCorrection(false);
		quiz.setDescription(request.getParameter("description"));
		quiz.setTag(request.getParameter("tag"));
		RequestDispatcher dispatch = request.getRequestDispatcher("newQuestion.jsp");
		dispatch.forward(request, response);
		
	}

}
