package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import questions.*;

/**
 * Servlet implementation class NewQuestionServlet
 */
@WebServlet("/NewQuestionServlet")
public class NewQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public NewQuestionServlet() {
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
		String type = request.getParameter("qtype");
		
		Question newQuestion = createQuestion(type, request, response);
//		if (type.equals("response")) request.getRequestDispatcher("new-question.jsp").forward(request, response);
	}

	private Question createQuestion(String type, HttpServletRequest request, HttpServletResponse response) {
		if (type.equals("response")) return createResponse(request, response);
		if (type.equals("fill-blank")) return createFillBlank(HttpServletRequest request, HttpServletResponse response);
	
	}

	private ResponseQuestion createResponse(HttpServletRequest request, HttpServletResponse response) {
		String question = request.getParameter("question");
		return null;
	}
}
