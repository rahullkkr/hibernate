package com.online.book.shop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.online.book.shop.dao.BookDAO;
import com.online.book.shop.model.Book;
import com.online.book.shop.to.BookTO;
import com.online.book.shop.util.CHibernateUtil;

public class HibernateBookDAO  implements BookDAO{

	Logger log = Logger.getLogger(this.getClass());
	public boolean addBook(BookTO bto) {
		
		SessionFactory fac;
		Transaction tx = null;
		boolean bkAdded = false;
		try{
			
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
			
			Book bk = new Book(bto.getBookName(), bto.getAuthor(),  bto.getPublication(), bto.getEdition(), bto.getCost());
			session.save(bk);
			bkAdded = true;
			tx.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e);
		}
		
		return bkAdded;
	}

	public boolean alreadyExist(BookTO bto) {
		
		boolean exist = false;
		SessionFactory fac;
		Transaction tx = null;
		try{
			
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
			
			String hql = "from Book bk where bk.bookName = ? and bk.author=? and bk.edition=? and bk.publication = ? and bk.cost = ?";
			Query qr = session.createQuery(hql);
			qr.setParameter(0, bto.getBookName());
			qr.setParameter(1, bto.getAuthor());
			qr.setParameter(2, bto.getEdition());
			qr.setParameter(3, bto.getPublication() );
			qr.setParameter(4, bto.getCost());
			int size = qr.list().size();
			if(size > 0){
				exist = true;
			}
			tx.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e);
		}
		return exist;
	}

	public List searchBook(BookTO bto, int start, int numberOfBook) {
		
		SessionFactory fac;
		Transaction tx = null;
		List bkList = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
		
			String hql = prepareQuery(bto);
			System.out.println("HQL : "+hql);
			Query qr = session.createQuery(hql);
			int i = 0;
			if(bto.getBookName() != null && bto.getBookName().trim().length() != 0)
				qr.setString(i++, "%"+bto.getBookName().trim()+"%");
			if(bto.getCost() !=  0)
				qr.setFloat(i++, bto.getCost());
			if(bto.getAuthor() != null && bto.getAuthor().trim().length() != 0)
				qr.setString(i++, "%"+bto.getAuthor().trim()+"%");
			if(bto.getPublication() != null && bto.getPublication().trim().length() != 0)
				qr.setString(i++, "%"+bto.getPublication().trim()+"%");
			if(bto.getEdition() != null && bto.getEdition().trim().length() != 0)
				qr.setString(i++, bto.getEdition().trim());

			qr.setFirstResult(start);
			qr.setMaxResults(numberOfBook);
			List<Book> blist = qr.list();
			bkList  = new ArrayList();
			for(Book bk : blist){
				BookTO bt = new BookTO(bk.getBookName(), bk.getAuthor(), bk.getPublication(), bk.getEdition(), bk.getCost());
				bt.setBookId(bk.getBookId());
				bkList.add(bt);
			}
			tx.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e);
		}
		return bkList;
	}
	
public String prepareQuery(BookTO bto){
		
		String qry = "from Book bk ";
		boolean whereAdded =false;
		//System.out.println("bto.getBookName() : "+bto.getBookName());
		if(bto.getBookName() != null && bto.getBookName().length() != 0){
			qry+="where bk.bookName like ? ";
			whereAdded = true;
		}
		//System.out.println("bto.getCost() : "+bto.getCost());
		if(bto.getCost() != 0){
			if(whereAdded){
				qry+="and bk.cost=? ";
			}else{
				qry+="where bk.cost=? ";
				whereAdded = true;
			}
		}
		//System.out.println("bto.getAuthor() : "+bto.getAuthor());
		if(bto.getAuthor() != null && bto.getAuthor().length() != 0){
			if(whereAdded){
				qry+="and bk.author like ? ";
			}else {
				qry+="where bk.author like ? ";
				whereAdded = true;
			}
		}
		//System.out.println("bto.getPublication() : "+bto.getPublication());
		if(bto.getPublication() != null && bto.getPublication().length() != 0){
			if(whereAdded){
				qry+="and bk.publication like ? ";
			}else {
				qry+="where bk.publication like ? ";
				whereAdded = true;
			}
		}
		//System.out.println("bto.getEdition() : "+bto.getEdition());
		if(bto.getEdition() != null && bto.getEdition().trim().length() != 0){
			if(whereAdded){
				qry+="and bk.edition like ? ";
			}else {
				qry+="where bk.edition like ? ";
				whereAdded = true;
			}
		}
		return qry;
	}

	public int getTotalNumberOfBook(BookTO bto) {
		
		SessionFactory fac;
		Transaction tx = null;
		int total = 0;
		try{
			
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
			
			String hql = prepareQuery(bto);
			Query qr = session.createQuery(hql);
			int i = 0;
			if(bto.getBookName() != null && bto.getBookName().trim().length() != 0)
				qr.setString(i++, "%"+bto.getBookName().trim()+"%");
			if(bto.getCost() !=  0)
				qr.setFloat(i++, bto.getCost());
			if(bto.getAuthor() != null && bto.getAuthor().trim().length() != 0)
				qr.setString(i++, "%"+bto.getAuthor().trim()+"%");
			if(bto.getPublication() != null && bto.getPublication().trim().length() != 0)
				qr.setString(i++, "%"+bto.getPublication().trim()+"%");
			if(bto.getEdition() != null && bto.getEdition().trim().length() != 0)
				qr.setString(i++, bto.getEdition().trim());
			
			total = qr.list().size();
			
			tx.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e);
		}
		
		return total;
	}

	public boolean deleteBook(int bookId) {
		
		log.info("bookId : "+bookId);
		boolean deleted =false;
		SessionFactory fac;
		Transaction tx = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx = session.beginTransaction();
			
			Object obj = session.load(Book.class, bookId);
			System.out.println("BOOK ID : "+bookId);
			session.delete(obj);
			deleted = true;
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e);
		}finally{
			if(tx != null) tx.rollback();
		}
		
		return deleted;
	}

	public BookTO getBookById(String bid) {
		
		BookTO bto = null;
		SessionFactory fac;
		Transaction tx = null;
		try{
			
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx = session.beginTransaction();
			int bookId = Integer.parseInt(bid);
			Book bk = (Book)session.load(Book.class, bookId);
			if(bk != null){
				bto = new BookTO(bk.getBookName(), bk.getAuthor(), bk.getPublication(), bk.getEdition(), bk.getCost());
				bto.setBookId(bk.getBookId());
			}
			
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e);
		}finally{
			if(tx != null) tx.rollback();
		}
		return bto;
	}

}
