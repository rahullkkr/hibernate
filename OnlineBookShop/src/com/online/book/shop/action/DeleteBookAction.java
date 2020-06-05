package com.online.book.shop.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.online.book.shop.delegate.BookDelegate;
import com.online.book.shop.to.BookTO;
import com.online.book.shop.util.BookUtil;
public class DeleteBookAction {

	public String deleteBook(HttpServletRequest req, HttpServletResponse res){
		
		String page="searchBookDef.jsp";
		String bid = req.getParameter("bookId");
		HttpSession session = req.getSession();
		String bnm = session.getAttribute("BOOK_NAME").toString();
		String author =session.getAttribute("AUTHOR").toString();
		String pub =session.getAttribute("PUBLICATION").toString();
		String edi =session.getAttribute("EDITION").toString();
		int start = Integer.parseInt(session.getAttribute("START").toString());
		int end = Integer.parseInt(session.getAttribute("END").toString());
		String str = session.getAttribute("COST").toString();
		float bcost = 0.0f;
		if(str.length() > 0)
					bcost = Float.parseFloat(str);
		boolean deleted = BookDelegate.deleteBook(Integer.parseInt(bid));
		if(deleted){
			BookTO bto = new BookTO(bnm, author, pub, edi, bcost);
			int total = BookDelegate.getTotalNumberOfBook(bto);
			if(total > 0){
				if(total <= end ){
					end=total;
				}
				if(start+1 > total)
						start = start - BookUtil.NUMBER_OF_BOOK;
				session.setAttribute("START", new Integer(start));
				session.setAttribute("TOTAL", new Integer(total));
				session.setAttribute("END", new Integer(end));
				List bookList = BookDelegate.searchBook(bto, start, BookUtil.NUMBER_OF_BOOK);
				session.setAttribute("BOOK_LIST", bookList);
				session.setAttribute("DELETE_MESSAGE", "Book information deleted successfully.");
			}else{
				req.setAttribute("searchingBookInfo", "No book found with specified Information.");
				session.removeAttribute("BOOK_LIST");
			}
		}else{
			req.setAttribute("DELETE_MESSAGE", "Error in deleting book information.");
		}
		return page;
	}
}
