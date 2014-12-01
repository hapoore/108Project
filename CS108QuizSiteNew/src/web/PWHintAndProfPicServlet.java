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
 * Servlet implementation class PWHintAndProfPicServlet
 */
@WebServlet("/PWHintAndProfPicServlet")
public class PWHintAndProfPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PWHintAndProfPicServlet() {
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
		Account account = (Account) request.getSession().getAttribute("Account");
		String username = account.getUserName();
		String secQuestion = (String) request.getParameter("securityQuestion");
		String secAnswer = (String) request.getParameter("securityAnswer");
		String pwHint = (String) request.getParameter("passwordHint");
		String profPicLink = (String) request.getParameter("profilePicture");
		if(secQuestion.equals("")) {
			account.setSecurityQuestion(null);
			account.setSecurityAnswer(null);
			account.setPasswordHint(null);
		} else {
			account.setSecurityQuestion(secQuestion);
			account.setSecurityAnswer(secAnswer);
			account.setPasswordHint(pwHint);
		}
		if(profPicLink.equals("")) {
			account.setProfilePic("https://tracker.moodle.org/secure/attachment/30912/f3.png");
		} else {
			account.setProfilePic(profPicLink);
		}
		mgr.updateSetUp(username, account.getPasswordHint(), account.getSecurityQuestion(), account.getSecurityAnswer(), account.getProfilePic());
		request.getSession().setAttribute("Account", mgr.getAccount(username));
		RequestDispatcher dispatch = request.getRequestDispatcher("account-setup-success.jsp");
		dispatch.forward(request, response);
	}

}
