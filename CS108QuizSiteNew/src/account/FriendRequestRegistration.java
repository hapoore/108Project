package account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class FriendRequestRegistration
 */
@WebServlet("/FriendRequestRegistration")
public class FriendRequestRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendRequestRegistration() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Does not get here
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession cur_session = request.getSession();
		String decision = request.getParameter("decision");
		String userName = (String) request.getParameter("requestName");
		Account cur_account = (Account) cur_session.getAttribute("Account");
		cur_account.setPassword("New Password!");
		System.out.println(decision + " " + userName + " " + cur_account.getUserName());
		boolean param_dec = true;
		if (decision.equals("Deny")) {
			System.out.println("DENYING");
			param_dec = false;
		} else { System.out.println("ACCEPTING"); }
		cur_account.acceptFriendRequest(param_dec, userName);
		
		System.out.println("NOW GOING TO JSP");
		//RequestDispatcher rd = request.getRequestDispatcher("inbox.jsp");
		//rd.forward(request, response);
		
	}

}
