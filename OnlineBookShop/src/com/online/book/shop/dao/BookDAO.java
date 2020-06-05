package com.online.book.shop.dao;

import java.util.List;

import com.online.book.shop.to.BookTO;

public interface BookDAO {

	public boolean addBook(BookTO bto);
	public boolean alreadyExist(BookTO bto);
	public List searchBook(BookTO bto, int start, int numberOfBook);
	public int getTotalNumberOfBook(BookTO bto);
	public boolean deleteBook(int bookId);
	public BookTO getBookById(String bid);
}
