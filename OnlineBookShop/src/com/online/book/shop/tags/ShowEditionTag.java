package com.online.book.shop.tags;

import java.io.Writer;
import java.util.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class ShowEditionTag extends TagSupport{

	Logger log = Logger.getLogger(this.getClass());
	public int doEndTag() throws JspException {

		Map m = new LinkedHashMap();
		m.put("Select Edition", "");
		m.put("1st Edition", "1st Edition");
		m.put("2nd Edition", "2nd Edition");
		m.put("3rd Edition", "3rd Edition");
		m.put("4th Edition", "4th Edition");
		m.put("5th Edition", "5th Edition");
		try{
			Writer out = pageContext.getOut();
			out.write("<select name=\"edition\" style=\"color:black;background-color:#b4e0d2;\">");
			Iterator it = m.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry ent = (Map.Entry)it.next();
				out.write("<option value=\""+ent.getValue()+" \"");
				Object obj =pageContext.getSession().getAttribute("EDITION");
				if(obj != null){
					String str = obj.toString();
					if(ent.getValue().equals(str))
						out.write("selected=\"selected\"");
				}
				out.write(">"+ent.getKey()+"</option>");
			}
		}catch(Exception e){
			log.error("Exception in ShowEditionTag\t : "+e);
		}
		return EVAL_PAGE;
	}
	
}
