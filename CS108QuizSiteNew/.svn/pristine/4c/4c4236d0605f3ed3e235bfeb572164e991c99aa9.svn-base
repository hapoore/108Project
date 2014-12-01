package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBase.DataBase;

import account.Account;

/**
 * Servlet implementation class ChangeProfilePictureServlet
 */
@WebServlet("/ChangeProfilePictureServlet")
public class ChangeProfilePictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeProfilePictureServlet() {
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
		Account currAccount = (Account)request.getSession().getAttribute("Account");
		currAccount.setProfilePic(request.getParameter("profPic"));
		DataBase db = new DataBase();
		db.updateAccountProfilePic(currAccount.getUserName(), request.getParameter("profPic"));
		RequestDispatcher dispatch = request.getRequestDispatcher("welcome-user.jsp");
		dispatch.forward(request, response);
	}

}
