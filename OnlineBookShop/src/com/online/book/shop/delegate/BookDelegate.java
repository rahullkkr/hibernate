package com.online.book.shop.delegate;

import java.util.List;

import com.online.book.shop.dao.BookDAO;
import com.online.book.shop.factory.DAOFactory;
import com.online.book.shop.to.BookTO;

public class BookDelegate {

	private static BookDAO bookDAO = null;
			static{
		bookDAO = DAOFactory.getBookDAO();
	}
	
	public static boolean addBook(BookTO bto){
		return bookDAO.addBook(bto);
	}
	public static boolean alreadyExist(BookTO bto){
		return bookDAO.alreadyExist(bto);
	}
	public static List searchBook(BookTO bto, int start, int numberOfBook){
		return bookDAO.searchBook(bto, start, numberOfBook);
	}
	public static int getTotalNumberOfBook(BookTO bto){
		return bookDAO.getTotalNumberOfBook(bto);
	}
	public static boolean deleteBook(int bookId){
		return bookDAO.deleteBook(bookId);
	}
	public static BookTO getBookById(String bid){
		return bookDAO.getBookById(bid);
	}
	
}
