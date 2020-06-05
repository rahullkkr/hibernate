package com.online.book.shop.action;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.online.book.shop.to.BookTO;
import com.online.book.shop.validator.JLCDataValidator;

public class RemoveFromCartAction {

	public String removeFromCart(HttpServletRequest req, HttpServletResponse res){

		String page="showCartDef.jsp";
		String bid = req.getParameter("bookId");
		
		if(! JLCDataValidator.validateRequired(bid)){
			req.setAttribute("bookId", "Please select a book to remove.");
		}else{
			int bookId = Integer.parseInt(bid);
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("SELECTED_BOOK_LIST");
			Set selectedBookList = null;
			BookTO bto = null;
			
			if(obj != null){
				selectedBookList = (Set)obj;
				Iterator it = selectedBookList.iterator();
				while(it.hasNext()){
					BookTO bookTO =(BookTO)it.next();
					if(bookTO.getBookId()==bookId){
						bto = bookTO;
						it.remove();
						break;
					}
				}
				if(selectedBookList.size() > 0){
					session.setAttribute("SELECTED_BOOK_LIST", selectedBookList);
				}else{
					session.removeAttribute("SELECTED_BOOK_LIST");
					req.setAttribute("REMOVED_TOTAL", "Book Removed from cart. No Book available in cart.");
				}
				req.setAttribute("REMOVED_BOOK", bto);
			}
		}
		return page;
	}
}
