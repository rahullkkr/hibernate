package com.online.book.shop.tags;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class RemoveSearchInfoFromSessionTag extends TagSupport{

	Logger log = Logger.getLogger(this.getClass());
	public int doEndTag() throws JspException {

		try{
			HttpSession session = pageContext.getSession();
			if(session != null){
				session.removeAttribute("BOOK_LIST");
				session.removeAttribute("BOOK_NAME");
				session.removeAttribute("AUTHOR");
				session.removeAttribute("PUBLICATION");
				session.removeAttribute("COST");
				session.removeAttribute("EDITION");
				session.removeAttribute("START");
				session.removeAttribute("END");
				session.removeAttribute("TOTAL");
				session.removeAttribute("SELECTED_BOOK_LIST");
				session.removeAttribute("TOTAL_BOOK_AMOUNT");
				session.removeAttribute("TOTAL_BOOK_QUANTITY");
			}
		}catch(Exception e){
			log.error("Exception in RemoveSearchInfoFromSessionTag\t:"+e);
		}
		return EVAL_PAGE;
	}
	
}
