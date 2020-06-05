package com.online.book.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.book.shop.delegate.UserDelegate;
import com.online.book.shop.to.UserTO;
import com.online.book.shop.validator.JLCDataValidator;
public class ChangePasswordAction {

	public String changepassword(HttpServletRequest req, HttpServletResponse res){
		
		String page = "changePasswordDef.jsp";
		String newpass = req.getParameter("newpass");
		String currpass = req.getParameter("currpass");
		String confpass = req.getParameter("confpass");
		
		boolean compareEquals =true;
		boolean interactWithDB = true;
		
		//VALIDATING NEW PASSWORD
		if(!JLCDataValidator.validateRequired(newpass)){
			req.setAttribute("newpass", "New Password is required.");
			interactWithDB=false;
			compareEquals=false;
		}else if(!JLCDataValidator.minLength(newpass, 6)){
			req.setAttribute("newpass", "New Password must be minimum 6 character long.");
			interactWithDB=false;
			compareEquals=false;
		}else if(!JLCDataValidator.maxLength(newpass, 15)){
			req.setAttribute("newpass", "New Password must be maximum 15 character long.");
			interactWithDB=false;
			compareEquals=false;
		}
		//VALIDATING CURRENT PASSWORD
		if(!JLCDataValidator.validateRequired(currpass)){
			req.setAttribute("currpass", "Current Password is required.");
			interactWithDB=false;
			compareEquals=false;
		}
		
		//VALIDATING CONFIRM PASSWORD
		if(!JLCDataValidator.validateRequired(confpass)){
			req.setAttribute("confpass", "Confirm Password is required.");
			interactWithDB=false;
			compareEquals=false;
		}else if(!JLCDataValidator.minLength(confpass, 6)){
			req.setAttribute("confpass", "Confirm Password must be minimum 6 character long.");
			interactWithDB=false;
			compareEquals=false;
		}else if(!JLCDataValidator.maxLength(confpass, 15)){
			req.setAttribute("confpass", "Confirm Password must be maximum 15 character long.");
			interactWithDB=false;
			compareEquals=false;
		}
		if(compareEquals){
			if(! newpass.equals(confpass)){
				interactWithDB=false;
				req.setAttribute("changePasswordError", "New & Confirm password doesn't match.");
			}
		}
		if(interactWithDB){
			Object obj = req.getSession().getAttribute("USER_TO");
			UserTO usto = (UserTO)obj;
			if(currpass.equals(usto.getPassword())){
				UserTO uto = UserDelegate.changePassword(usto, newpass);
				if(uto != null){
					req.getSession().setAttribute("USER_TO", uto);
					req.setAttribute("changePasswordError", "Password updated successfully.");
					req.setAttribute("CHANGED_PASSWORD", "ok");
				}else{
					req.setAttribute("changePasswordError", "Error in changing password.");
				}
			}else{
				req.setAttribute("changePasswordError", "Current Password is invalid.");
			}
		}
		return page;
	}
}
