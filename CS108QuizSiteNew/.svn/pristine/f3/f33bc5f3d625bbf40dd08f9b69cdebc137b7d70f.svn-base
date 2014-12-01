package quizzes;

import account.*;
import questions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuizServlet
 */
@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizServlet() {
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
		Quiz quiz = (Quiz)request.getSession().getAttribute("currQuiz");
		quizzes.Quiz.QuizSession quizSession;
		String username = null;
		RequestDispatcher dispatch = null;
		if(request.getSession().getAttribute("Account") != null) {
			username = ((Account)request.getSession().getAttribute("Account")).getUserName();
		} else {
			username = "Guest";
		}
		if(!quiz.inProgress){
			if(request.getParameter("practiceMode").equals("practice"))
				quiz.startSession(true, username);
			else
				quiz.startSession(false, username);
			quizSession = quiz.getSession();
		}
		else if(request.getParameter("corrected") == null){
			quizSession = quiz.getSession();
			Object answers = null;
			//ArrayList<String> answers = new ArrayList<String>();
			if(quizSession.currQuestion.type() != 4)
				answers = new ArrayList<String>();
			else
				answers =  new HashMap<String, String>();
			if(quizSession.currQuestion.type() == 0 || quizSession.currQuestion.type() == 1 || quizSession.currQuestion.type() == 2 || quizSession.currQuestion.type() == 3 || quizSession.currQuestion.type() == 5 || quizSession.currQuestion.type() == 6)
				((ArrayList<String>)answers).add(request.getParameter("answer"));
			else if(quizSession.currQuestion.type() == 4){
				HashMap<String, String> pairs = ((MatchingQuestion)(quizSession.currQuestion)).getAnswers();
				Iterator<String> keysIter = pairs.keySet().iterator();
				ArrayList<String> keys = new ArrayList<String>();
				while (keysIter.hasNext()){
					keys.add(keysIter.next());
				}
				String valuesString = request.getParameter("valuesString");
				String[] valueArray = valuesString.split(";");
				ArrayList<String> values = new ArrayList<String>();
				for(int i = 0; i< valueArray.length; i++){
					values.add(valueArray[i]);
				}
				int numAnswers = ((MatchingQuestion)(quizSession.currQuestion)).getAnswers().size();
				for(int i = 0; i < numAnswers; i++){
					String answerparam = "match" + i;
					String input = request.getParameter(answerparam);
					if(input.equals(""))
						input = "0";
					String match = values.get(Integer.parseInt(input));
					((HashMap<String, String>)answers).put(keys.get(i), match);
				}
			}
			else if(quizSession.currQuestion.type() == 7){
				int numAnswers = ((MultiAnswerQuestion)(quizSession.currQuestion)).getAnswers().size();
				for(int i = 0; i < numAnswers; i++){
					String answerparam = "answer" + i;
					((ArrayList<String>)answers).add(request.getParameter(answerparam));
				}
			}
			else if(quizSession.currQuestion.type() == 8){
				int numChoices = ((MultiChoiceMultiAnswer)(quizSession.currQuestion)).getAllChoices().size();
				for(int i = 0; i < numChoices; i++){
					String answerparam = "answer" + i;
					if(request.getParameter(answerparam).equals("yes"))
						((ArrayList<String>)answers).add(((MultiChoiceMultiAnswer)(quizSession.currQuestion)).getAllChoices().get(i));
				}
			}
			double questionPoints = quizSession.getQuestionScore(answers);
			if(quizSession.currQuestion.type() == 4){
				Iterator<String> keysIter = ((HashMap<String, String>)answers).keySet().iterator();
				ArrayList<String> asString = new ArrayList<String>();
				while (keysIter.hasNext()){
					String key = keysIter.next();
					asString.add(key + "; " + ((HashMap<String, String>)answers).get(key));
				}
				answers = asString;
			}
			quizSession.score += questionPoints;
			if(quiz.getImmediateCorrection()){
				dispatch = request.getRequestDispatcher("questionCorrectionPage.jsp");
				request.setAttribute("answers", answers);
				double maxPoints = quizSession.currQuestion.getWorth();
				if(questionPoints == maxPoints)
					request.setAttribute("result", "success");
				else if(questionPoints == 0)
					request.setAttribute("result", "failure");
				else
					request.setAttribute("result", "not bad");
				request.setAttribute("questionPoints", questionPoints);
				dispatch.forward(request, response);
				return;
			}
		}
		else{
			quizSession = quiz.getSession();
		}
		if(quizSession.getNextQuestion()){
			dispatch = request.getRequestDispatcher("questionPage.jsp");
			dispatch.forward(request, response);
		}
		else{
			quizSession.setResults();
			if(quizSession.practiceMode){
				dispatch = request.getRequestDispatcher("quizPracResPage.jsp");
				request.setAttribute("result", quizSession.practiceRes);
			}
			else{
				dispatch = request.getRequestDispatcher("quizResPage.jsp");
				request.setAttribute("result", quizSession.result);
				request.setAttribute("answers", quizSession.answers);
			}
			quiz.endSession();
			dispatch.forward(request, response);
		}
	}

}