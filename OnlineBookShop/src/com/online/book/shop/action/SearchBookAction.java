package com.online.book.shop.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.online.book.shop.delegate.BookDelegate;
import com.online.book.shop.to.BookTO;
import com.online.book.shop.util.BookUtil;

public class SearchBookAction {

	public String searchBookInfo(HttpServletRequest req, HttpServletResponse res){

		String page = "searchBookDef.jsp";
		String bnm = req.getParameter("bname");
		String author = req.getParameter("author");
		String cost = req.getParameter("cost");
		String pub = req.getParameter("publication");
		String edi = req.getParameter("edition");
		float bcost = 0.0f;
		if(cost != null && cost.length() != 0){
			try{
				bcost = Float.parseFloat(cost);
			}catch(NumberFormatException e){
				req.setAttribute("searchingBookError", "Cost is not valid.");
			}
		}
		//System.out.println("**********"+bnm+"\t"+author+"\t"+pub+"\t"+edi+"\t"+cost);
			BookTO bto = new BookTO(bnm, author, pub, edi, bcost);
			int start =0;
			int noBook = BookUtil.NUMBER_OF_BOOK;
			int total =BookDelegate.getTotalNumberOfBook(bto);
			List bookList = BookDelegate.searchBook(bto, start, noBook);
			HttpSession session = req.getSession();
			session.setAttribute("BOOK_NAME", bnm);
			session.setAttribute("AUTHOR", author);
			session.setAttribute("PUBLICATION", pub);
			session.setAttribute("EDITION", edi);
			//session.setAttribute("EDITION", edi);
			session.setAttribute("START", new Integer(start));
			
			int end = start+noBook;
			if(total <= end){
				end=total;
			}
			session.setAttribute("END", new Integer(end));
			session.setAttribute("TOTAL", new Integer(total));
			if(bcost != 0){
				session.setAttribute("COST", new Float(bcost));
			}else{
				session.setAttribute("COST", "");
			}
			if(bookList == null){
				req.setAttribute("searchingBookError", "No book found with specified Information.");
				session.removeAttribute("BOOK_LIST");
			}else{
				session.setAttribute("BOOK_LIST", bookList);
			}
		return page;
	}
}
