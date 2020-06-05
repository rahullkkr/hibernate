package com.online.book.shop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.book.shop.delegate.BookDelegate;
import com.online.book.shop.to.BookTO;
import com.online.book.shop.validator.JLCDataValidator;

public class AddBookAction {

	public String addBookInfo(HttpServletRequest req, HttpServletResponse res){
		
		String page = "addBookDef.jsp";
		String bnm = req.getParameter("bname");
		String author = req.getParameter("author");
		String cost = req.getParameter("cost");
		String pub = req.getParameter("publication");
		String edi = req.getParameter("edition");
		float bcost = 0.0f;
		boolean convertCost=true;
		boolean interactWithDB =true;
		
		//VALIDATING BOOK NAME
		if(! JLCDataValidator.validateRequired(bnm)){
			req.setAttribute("bname", "Book Name is required.");
			interactWithDB=false;
		}else if(! JLCDataValidator.minLength(bnm, 3)){
			req.setAttribute("bname", "Book name must be of minimum 3 character");
			interactWithDB=false;
		}else if(! JLCDataValidator.maxLength(bnm, 30)){
			req.setAttribute("bname", "Book name must be of maximum  30 character");
			interactWithDB=false;
		}
		
		//VALIDATING AUTHOR NAME
		if(! JLCDataValidator.validateRequired(author)){
			req.setAttribute("author", "Author Name is required.");
			interactWithDB=false;
		}else if(! JLCDataValidator.minLength(bnm, 3)){
			req.setAttribute("author", "Author name must be of minimum 3 character");
			interactWithDB=false;
		}else if(! JLCDataValidator.maxLength(bnm, 50)){
			req.setAttribute("author", "Author name must be of maximum  50 character");
			interactWithDB=false;
		}
		//VALIDATING COST
		if(! JLCDataValidator.validateRequired(cost)){
			req.setAttribute("cost", "Cost is required.");
			interactWithDB=false;
		}
		//VALIDATING EDITION
		if(! JLCDataValidator.validateRequired(edi)){
			req.setAttribute("edi", "Book edition is required.");
			interactWithDB=false;
		}
		//VALIDATING PUBLICATION
		if(! JLCDataValidator.validateRequired(pub)){
			req.setAttribute("publication", "Book publication is required.");
			interactWithDB=false;
		}else if(! JLCDataValidator.minLength(pub, 3)){
			req.setAttribute("publication", "Book publication must be of minimum 3 character");
			interactWithDB=false;
		}else if(! JLCDataValidator.maxLength(bnm, 50)){
			req.setAttribute("publication", "Book publication must be of maximum  50 character");
			interactWithDB=false;
		}
		
		if(convertCost){
			try{
				bcost=Float.parseFloat(cost);
			}catch(NumberFormatException e){
				req.setAttribute("cost", "Cost is not valid.");
				interactWithDB=false;
			}
		}
		if(interactWithDB){
			BookTO bto = new BookTO(bnm, author, pub, edi, bcost);
			if(BookDelegate.alreadyExist(bto)){
				req.setAttribute("addingBookError", "Book information already available.");
			}else{
				boolean added = BookDelegate.addBook(bto);
				if(added){
					req.setAttribute("addingBookError", "Book information added successfully.");
					req.setAttribute("SHOW_ADD_BOOK", "OK");
				}else{
					req.setAttribute("addingBookError", "Error in adding book information.");
				}
			}
		}
		return page;
	}
}
