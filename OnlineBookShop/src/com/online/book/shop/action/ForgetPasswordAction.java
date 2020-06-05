package com.online.book.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.book.shop.delegate.UserDelegate;
import com.online.book.shop.validator.JLCDataValidator;
public class ForgetPasswordAction {

	public String searchPassword(HttpServletRequest req, HttpServletResponse res){
		
		String page = "forgetPasswordDef.jsp";
		String uname = req.getParameter("uname");
		String email = req.getParameter("email");
		boolean interactWithDB = true;
		if(! JLCDataValidator.validateRequired(uname)){
			req.setAttribute("uname", "Username is Required.");
			interactWithDB =false;
		}
		if(! JLCDataValidator.validateRequired(email)){
			req.setAttribute("email", "Email is Required.");
			interactWithDB =false;
		}else if(! JLCDataValidator.validateEmail(email)){
			req.setAttribute("email", "Please Enter a valid email ID.");
			interactWithDB = false;
		}
		if(interactWithDB){
			String password = UserDelegate.searchPassword(uname, email);
			if(password != null){
				req.setAttribute("PASSWORD", password);
			}else{
				req.setAttribute("forgetPasswordError", "Provided Information is invalid.");
			}
		}
		return page;
	}
	
}
