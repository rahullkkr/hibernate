package com.online.book.shop.tags;

import javax.servlet.http.*;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class ValidateSessionTag extends TagSupport{

	Logger log = Logger.getLogger(this.getClass());
	public int doEndTag() throws JspException {

		try{
				HttpServletRequest req =(HttpServletRequest)pageContext.getRequest();
				HttpServletResponse res =(HttpServletResponse)pageContext.getResponse();
				if((req.getSession().getAttribute("USER_TO") == null)){
					req.setAttribute("loginError", "Session timeout. Please login again.");
					req.getRequestDispatcher("index.jsp").forward(req, res);
				}
		}catch(Exception e){
			log.error("Exception in ValidateSessionTag\t:"+e);
		}
		
		return EVAL_PAGE;
	}
	
}
