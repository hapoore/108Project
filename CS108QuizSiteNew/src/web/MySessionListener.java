package web;

import account.*;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dataBase.DataBase;

/**
 * Application Lifecycle Listener implementation class MySessionListener
 *
 */
@WebListener
public class MySessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public MySessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent event) {
        Account account = (Account) event.getSession().getAttribute("Account");
        if(account != null) {
        	DataBase db = new DataBase();
            db.updateAccount(account);
            db.close();
        	Account.deleteOnlineAccount(account);
        }
    }
	
}
