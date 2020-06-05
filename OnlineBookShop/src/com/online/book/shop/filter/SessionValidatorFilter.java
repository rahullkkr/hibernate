package com.online.book.shop.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class SessionValidatorFilter implements Filter {
    
	public SessionValidatorFilter() {
		System.out.println("SessionValidatorFilter def Cons.**");
    }
	public void destroy() {
		System.out.println("SessionValidatorFilter destroy()**");
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hreq = (HttpServletRequest)request;
		//HttpServletResponse hres = (HttpServletResponse)response;
		//System.out.println("ContextPath : "+hreq.getContextPath());
		//System.out.println(hreq.getLocalAddr());
		String uri = hreq.getRequestURI();
		if(uri.endsWith("login.jlc") || uri.endsWith("register.jlc") || uri.endsWith("forgotPassword.jlc")){
			chain.doFilter(request, response);	
		}else{
			HttpSession session = hreq.getSession(false);
			if(session == null){
				hreq.setAttribute("loginError", "Session Timeout. Please try to login again.");
			}else{
				chain.doFilter(request, response);
			}
		}
		System.out.println("SessionValidatorFilter doChain()**");
	}
	public void init(FilterConfig fConfig) throws ServletException {
			System.out.println("SessionValidatorFilter init() **");
	}

}
