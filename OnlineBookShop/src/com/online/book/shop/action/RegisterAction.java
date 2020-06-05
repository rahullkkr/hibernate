package com.online.book.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.book.shop.delegate.UserDelegate;
import com.online.book.shop.to.UserTO;
import com.online.book.shop.validator.JLCDataValidator;
public class RegisterAction {

	public String registerStudent(HttpServletRequest req, HttpServletResponse res){
		
		String page = "registerDef.jsp";
		
		boolean isError = false;
		String fname = req.getParameter("fname");
		String mname = req.getParameter("mname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String gender = req.getParameter("gender");
		String username = req.getParameter("uname");
		String password = req.getParameter("pass");
		
		//VALIDATING FIRST NAME
		if(! JLCDataValidator.validateRequired(fname)){
			req.setAttribute("fname", "First name is required.");
			isError=true;
		}else if(! JLCDataValidator.validateFirstCharacterAsUpper(fname)){
			req.setAttribute("fname", "First name must start with capital letter.");
			isError = true;
		}else if(! JLCDataValidator.minLength(fname, 3)){
			req.setAttribute("fname", "First name  must be of minimum 3 character");
			isError = true;
		}else if(! JLCDataValidator.maxLength(fname, 10)){
			req.setAttribute("fname", "First name must be of maximum  10 character");
			isError = true;
		}else if(! JLCDataValidator.validateName(fname)){
			req.setAttribute("fname", "First name must be in characters only.");
			isError = true;
		}
		
		// VALIDATING MIDDLE NAME
		if(mname != null && mname.length() >0){
		
			if(! JLCDataValidator.validateFirstCharacterAsUpper(mname)){
				req.setAttribute("mname", "Middle name must start with capital letter.");
				isError = true;
			}else if(! JLCDataValidator.minLength(mname, 3)){
				req.setAttribute("mname", "Middle name  must be of minimum 3 character");
				isError = true;
			}else if(! JLCDataValidator.maxLength(mname, 10)){
				req.setAttribute("mname", "Middle name must be of maximum  10 character");
				isError = true;
			}else if(! JLCDataValidator.validateName(mname)){
				req.setAttribute("mname", "Middle name must be in characters only.");
				isError = true;
			}
		}
		
		// VALIDATING LAST NAME
		
		if(lname != null && lname.length() >0){
			
			if(! JLCDataValidator.validateFirstCharacterAsUpper(lname)){
				req.setAttribute("lname", "Last name must start with capital letter.");
				isError = true;
			}else if(! JLCDataValidator.minLength(lname, 3)){
				req.setAttribute("lname", "Last name  must be of minimum 3 character");
				isError = true;
			}else if(! JLCDataValidator.maxLength(lname, 10)){
				req.setAttribute("lname", "Last name must be of maximum  10 character");
				isError = true;
			}else if(! JLCDataValidator.validateName(lname)){
				req.setAttribute("lname", "Last name must be in characters only.");
				isError = true;
			}
		}
		
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
		
		//VALIDATEING GENDER
		if(! JLCDataValidator.validateRequired(gender)){
			req.setAttribute("gender", "Please select the gender.");
			isError=true;
		}
		
		//VALIDATING USERNAME
		if(! JLCDataValidator.validateRequired(username)){
			req.setAttribute("uname", "Username is required.");
			isError=true;
		}else if(! JLCDataValidator.minLength(username, 6)){
			req.setAttribute("uname", "Username  must be of minimum 6 character");
			isError = true;
		}else if(! JLCDataValidator.maxLength(username, 15)){
			req.setAttribute("uname", "Username must be of maximum  15 character");
			isError = true;
		}else if(UserDelegate.alreadyExist(username)){
			req.setAttribute("uname", "Username already exist. Please use a different one.");
			isError = true;
		}
		
		//VALIDATING PASSWORD
		if(! JLCDataValidator.validateRequired(password)){
			req.setAttribute("pass", "Password is required.");
			isError=true;
		}else if(! JLCDataValidator.minLength(password, 6)){
			req.setAttribute("pass", "Password  must be of minimum 6 character");
			isError = true;
		}else if(! JLCDataValidator.maxLength(password, 15)){
			req.setAttribute("pass", "Password must be of maximum  15 character");
			isError = true;
		}
		
		if(! isError){
			boolean registered = false;
			UserTO uto = new UserTO(fname, mname, lname, email, Long.parseLong(phone), username, password, "User");
			registered =UserDelegate.registerUser(uto);
			if(registered){
				req.setAttribute("registrationError", "Registration Completed Successfully.");
				page="login.jlc?uname="+username+"&pass="+password;
			}else{
				req.setAttribute("REGISTERED", "OK");
				req.setAttribute("registrationError", "Error occurred while registering. Please try again.");
			}
		}
		return page;
	}	
}
