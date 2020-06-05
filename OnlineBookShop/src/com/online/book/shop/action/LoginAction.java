package com.online.book.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.book.shop.delegate.UserDelegate;
import com.online.book.shop.to.UserTO;
import com.online.book.shop.validator.JLCDataValidator;
public class LoginAction {

	public String verifyUser(HttpServletRequest req, HttpServletResponse res){
		
		String page="index.jsp";
		String uname=req.getParameter("uname");
		String pass = req.getParameter("pass");
		boolean interactWithDB = true;
		
		if(! JLCDataValidator.validateRequired(uname)){
			req.setAttribute("uname", "Username is required.");
			interactWithDB = false;
		}
		if(! JLCDataValidator.validateRequired(pass)){
			req.setAttribute("pass", "Password is required.");
			interactWithDB = false;
		}
		if(interactWithDB){
			UserTO uto = UserDelegate.verifyUser(uname, pass);
			if(uto != null){
				req.getSession().setAttribute("USER_TO", uto);
				page = "userHomeDef.jsp";
			}else{
				req.setAttribute("loginError", "Username or Password is Invalid");
			}
		}
		
		return page;
	}
	
}
