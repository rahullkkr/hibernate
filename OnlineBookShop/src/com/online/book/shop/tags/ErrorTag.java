package com.online.book.shop.tags;

import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class ErrorTag extends TagSupport{

	private String property;
	Logger log = Logger.getLogger(this.getClass());
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public int doEndTag() throws JspException{
		
		try{
			Writer out =pageContext.getOut();
			Object obj = pageContext.getAttribute(property, PageContext.REQUEST_SCOPE);
			if(obj != null){
				out.write(obj.toString());
			}
		}catch(Exception e){
			log.error("Exception in Error Tag\t : "+e);
		}
		return EVAL_PAGE;
	}
	
}
