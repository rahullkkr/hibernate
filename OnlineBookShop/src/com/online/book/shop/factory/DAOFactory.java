package com.online.book.shop.factory;

import com.online.book.shop.dao.BookDAO;
import com.online.book.shop.dao.OrderDAO;
import com.online.book.shop.dao.UserDAO;
import com.online.book.shop.dao.impl.JDBCBookDAO;
import com.online.book.shop.dao.impl.JDBCOrderDAO;
import com.online.book.shop.dao.impl.JDBCUserDAO;

public class DAOFactory {
	
	private static UserDAO userDAO = null;
	private static BookDAO bookDAO = null;
	private static OrderDAO orderDAO = null;
	
	static {
		 userDAO = new JDBCUserDAO();
		 bookDAO = new JDBCBookDAO();
		 orderDAO = new JDBCOrderDAO();
		/*userDAO = new HibernateUserDAO();
		 bookDAO = new HibernateBookDAO();
		 orderDAO = new HibernateOrderDAO();*/
	}
	
	public static UserDAO getUserDAO(){
		return userDAO;
	}
	public static OrderDAO getOrderDAO(){
		return orderDAO;
	}
	public static BookDAO getBookDAO(){
		return bookDAO;
	}
}
