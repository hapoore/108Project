package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.Account;

import dataBase.DataBase;

/**
 * Servlet implementation class MakeAdminServlet
 */
@WebServlet("/MakeAdminServlet")
public class MakeAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeAdminServlet() {
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
		String userToPromote = request.getParameter("userToPromote");
		DataBase db = new DataBase();
		if(db.accountExists(userToPromote)) {
			Account promoting = db.getAccount(userToPromote);
			promoting.setAdministratorStatus(true);
			db.updateAccount(promoting);
			db.close();
			RequestDispatcher dispatch = request.getRequestDispatcher("promotion-successful.jsp");
			dispatch.forward(request, response);
		} else {
			db.close();
			RequestDispatcher dispatch = request.getRequestDispatcher("promotion-unsuccessful.jsp");
			dispatch.forward(request, response);
		}
	}

}
