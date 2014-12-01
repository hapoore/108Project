package web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletContextListener
 *
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyServletContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see myServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
    	
    	AccountManager mgr = new AccountManager();
        ServletContext context = event.getServletContext();
        context.setAttribute("Account Manager", mgr);
        
    }

	/**
     * @see myServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        AccountManager mgr = (AccountManager) context.getAttribute("Account Manager");
        mgr.closeDBConnection();
    }
	
}
