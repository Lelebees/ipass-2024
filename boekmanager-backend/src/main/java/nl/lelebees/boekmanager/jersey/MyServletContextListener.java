package nl.lelebees.boekmanager.jersey;

import nl.lelebees.boekmanager.security.domain.Account;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("initializing application");
        Account defaultUser = new Account("admin", "admin", "admin");
        // This is basically main now
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("terminating application");
    }
}
