package com.online.book.shop.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {

	static int TV;
	static int CA;
	
    public MySessionListener() {
    }
    public void sessionCreated(HttpSessionEvent event) {

    	ServletContext ctx= event.getSession().getServletContext();
    	
    	System.out.println("Session Created************");
    	Object obj1 = ctx.getAttribute("TV");
    	Object obj2 = ctx.getAttribute("CA");
    	if(obj1 != null){
    		TV = (Integer)obj1;
    		CA=(Integer)obj2;
    		TV++;
    		CA++;
    		ctx.setAttribute("TV", TV);
    		ctx.setAttribute("CA", CA);
    	}else{
    		TV++;
    		CA++;
    		ctx.setAttribute("TV", TV);
    		ctx.setAttribute("CA", CA);
    	}
    }

    public void sessionDestroyed(HttpSessionEvent event) {

    	ServletContext ctx = event.getSession().getServletContext();
    	Object obj2 = ctx.getAttribute("CA");
    	if(obj2 != null){
    		CA=(Integer)obj2;
    		CA--;
    		ctx.setAttribute("CA", CA);
    	}
    	System.out.println("Session Destroyed************");
    }
	
}
