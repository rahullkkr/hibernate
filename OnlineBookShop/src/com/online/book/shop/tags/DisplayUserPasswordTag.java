package com.online.book.shop.tags;

import java.io.Writer;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.online.book.shop.to.UserTO;

public class DisplayUserPasswordTag extends TagSupport{

	Logger log = Logger.getLogger(this.getClass());
	public int doEndTag()throws JspException{
		
		try{
			HttpSession session = (HttpSession)pageContext.getSession();
			Object obj =session.getAttribute("USER_TO");
			if(obj != null){
				UserTO uto = (UserTO)obj;
				int len = uto.getPassword().length();
				Writer out = pageContext.getOut();
				for(int i=0; i<len;i++){
					out.write("*");
				}
			}
		}catch(Exception e){
			log.error("Exception in DisplayUserPasswordTag\t"+e);
		}
		return EVAL_PAGE;
	}
}
