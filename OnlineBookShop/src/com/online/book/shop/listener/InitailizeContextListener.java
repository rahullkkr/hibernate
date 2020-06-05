package com.online.book.shop.listener;

import javax.servlet.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class InitailizeContextListener implements ServletContextListener {

   
    public InitailizeContextListener() {
    	System.out.println("InitailizeContextListener Def Cons.==");
    }

    public void contextInitialized(ServletContextEvent event) {
    	
    	ServletContext ctx= event.getServletContext();
    	String path = ctx.getRealPath("/")+"logs\\";
    	System.setProperty("jlcindia.root.path",path);
    	String str = ctx.getRealPath("/WEB-INF/classes/com/online/log4j.properties");
    	PropertyConfigurator.configure(str);
    	Logger log = Logger.getLogger(this.getClass());
    	log.info("Properties file :"+str);
    	System.out.println("JLCindia Context Listener---contextInitialized()==");
    	
    	
    	
    }
    public void contextDestroyed(ServletContextEvent arg0) {
    	System.out.println("JLCindia Context Listener---contextDestroyed()==");
    }
	
}
