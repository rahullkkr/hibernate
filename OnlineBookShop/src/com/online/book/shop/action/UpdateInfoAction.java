package com.online.book.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.book.shop.delegate.UserDelegate;
import com.online.book.shop.to.UserTO;
import com.online.book.shop.validator.JLCDataValidator;

public class UpdateInfoAction {

	public String updateUserInfo(HttpServletRequest req, HttpServletResponse res){

		boolean isError=false;
		String page = "detailsDef.jsp";
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		req.setAttribute("EMAIL", email);
		req.setAttribute("PHONE", phone);
		
		//VALIDATING EMAIL
		if(! JLCDataValidator.validateRequired(email)){
			req.setAttribute("email", "Email is required.");
			isError=true;
		}else if(! JLCDataValidator.maxLength(email, 30)){
			req.setAttribute("email", "Email must be of maximum  30 character");
			isError = true;
		}else if(! JLCDataValidator.validateEmail(email)){
			req.setAttribute("email", "Please enter valid Email ID");
			isError = true;
		}
		
		//VALIDATING PHONE
		if(! JLCDataValidator.validateRequired(phone)){
			req.setAttribute("phone", "Phone number  is required.");
			isError=true;
		}else if(! JLCDataValidator.validateLong(phone)){
			req.setAttribute("phone", "Phone number must be in digits only.");
			isError = true;
		}else if(! (JLCDataValidator.minLength(phone, 10) &&  JLCDataValidator.minLength(phone, 10))){
			req.setAttribute("phone", "Only 10 digits are required in phone number.");
			isError = true;
		}
		if(! isError){
			boolean updated = false;
			Object obj = req.getSession().getAttribute("USER_TO");
			UserTO	uto = (UserTO)obj;
			updated = UserDelegate.updateUserInfo(uto.getUserId(), email, Long.parseLong(phone));
			if(updated){
				req.setAttribute("updateError", "Information Updated Successfully.");
				req.getSession().removeAttribute("EDIT_INFO");
				uto.setEmail(email);
				uto.setPhone(Long.parseLong(phone));
				req.getSession().setAttribute("USER_TO", uto);
			}else{
				req.setAttribute("updateError", "Error occurred while updating the information. Please try later.");
			}
		}
		return page;
	}	
}
