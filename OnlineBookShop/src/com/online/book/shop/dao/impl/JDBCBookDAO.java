package com.online.book.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.online.book.shop.dao.BookDAO;
import com.online.book.shop.to.BookTO;
import com.online.book.shop.util.JDBCUtil;

public class JDBCBookDAO implements BookDAO{

	Logger log = Logger.getLogger(this.getClass());
	public boolean addBook(BookTO bto) {
		
		boolean added = false;
		Connection con = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		try{
			con = JDBCUtil.getConnection();
			ps= con.prepareStatement("insert into book_table (bookName, author, cost, publication, edition) values (?,?,?,?,?)");
			ps.setString(1, bto.getBookName());
			ps.setString(2, bto.getAuthor());
			ps.setFloat(3, bto.getCost());
			ps.setString(4, bto.getPublication());
			ps.setString(5, bto.getEdition());
			int x = ps.executeUpdate();
			if(x>0){
				added=true;
			}
		}catch(Exception e){
			added =false;
			log.error("Exception in addBook");
		}finally{
			JDBCUtil.close(rs, ps, con);
		}
		return added;
	}

	public boolean alreadyExist(BookTO bto) {
		
		boolean exist =false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement("select * from book_table where bookName = ? and author=? and cost =? and publication =? and edition = ?");
			ps.setString(1, bto.getBookName());
			ps.setString(2, bto.getAuthor());
			ps.setFloat(3, bto.getCost());
			ps.setString(4, bto.getPublication());
			ps.setString(5, bto.getEdition());
			rs= ps.executeQuery();
			if(rs.next()){
				exist = true;
			}
		}catch(Exception e){
			exist = false;
			log.error("Exception in alreadyExist.");
		}finally{
			JDBCUtil.close(rs, ps, con);
		}
		
		return exist;
	}

	public List searchBook(BookTO bto, int start, int numberOfBook) {
		
		List bookList = null;
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		try{
			con= JDBCUtil.getConnection();
			String qry=prepareQuery(bto);
			ps = con.prepareStatement(qry, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			int i=1;
			if(bto.getBookName() != null && bto.getBookName().trim().length() != 0)
					ps.setString(i++, "%"+bto.getBookName().trim()+"%");
			if(bto.getCost() !=  0)
				ps.setFloat(i++, bto.getCost());
			if(bto.getAuthor() != null && bto.getAuthor().trim().length() != 0)
					ps.setString(i++, "%"+bto.getAuthor().trim()+"%");
			if(bto.getPublication() != null && bto.getPublication().trim().length() != 0)
					ps.setString(i++, "%"+bto.getPublication().trim()+"%");
			if(bto.getEdition() != null && bto.getEdition().trim().length() != 0)
					ps.setString(i++, bto.getEdition().trim());
			//System.out.println("Query in search Book: " +qry+"\t"+bto.getBookName().trim());
			//System.out.println("EditionInSearchBook"+bto.getEdition()+"Book");
			
			rs = ps.executeQuery();
			//System.out.println("rs.isBeforeFirst() : "+rs.isBeforeFirst());
			rs.absolute(start+1);
			//System.out.println("rs.isFirst() : "+rs.isFirst());
			int x=1;
			bookList = new ArrayList();
			do{
				
				BookTO bookTO = new BookTO(rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getFloat(4));
				bookTO.setBookId(rs.getInt(1));
				bookList.add(bookTO);
				x++;
			}while(rs.next() && x <= numberOfBook);
		}catch(Exception e){
				bookList =null;
				e.printStackTrace();
				log.error("Exception in search book."+e);
		}finally{
			JDBCUtil.close(rs, ps, con);
		}
		return bookList;
	}

	public String prepareQuery(BookTO bto){
		
		String qry = "select * from book_table ";
		boolean whereAdded =false;
		//System.out.println("bto.getBookName() : "+bto.getBookName());
		if(bto.getBookName() != null && bto.getBookName().length() != 0){
			qry+="where bookName like ? ";
			whereAdded = true;
		}
		//System.out.println("bto.getCost() : "+bto.getCost());
		if(bto.getCost() != 0){
			if(whereAdded){
				qry+="and cost=? ";
			}else{
				qry+="where cost=? ";
				whereAdded = true;
			}
		}
		//System.out.println("bto.getAuthor() : "+bto.getAuthor());
		if(bto.getAuthor() != null && bto.getAuthor().length() != 0){
			if(whereAdded){
				qry+="and author like ? ";
			}else {
				qry+="where author like ? ";
				whereAdded = true;
			}
		}
		//System.out.println("bto.getPublication() : "+bto.getPublication());
		if(bto.getPublication() != null && bto.getPublication().length() != 0){
			if(whereAdded){
				qry+="and publication like ? ";
			}else {
				qry+="where publication like ? ";
				whereAdded = true;
			}
		}
		//System.out.println("bto.getEdition() : "+bto.getEdition());
		if(bto.getEdition() != null && bto.getEdition().trim().length() != 0){
			if(whereAdded){
				qry+="and edition like ? ";
			}else {
				qry+="where edition like ? ";
				whereAdded = true;
			}
		}
		return qry;
	}

	public int getTotalNumberOfBook(BookTO bto){
		
		int total = 0;
		Connection con = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		try{
			con=JDBCUtil.getConnection();
			String qry = prepareQuery(bto);
			ps=con.prepareStatement(qry);
			int i=1;
			if(bto.getBookName() != null && bto.getBookName().trim().length() != 0)
				ps.setString(i++, "%"+bto.getBookName().trim()+"%");
			if(bto.getCost() !=  0)
				ps.setFloat(i++, bto.getCost());
			if(bto.getAuthor() != null && bto.getAuthor().trim().length() != 0)
				ps.setString(i++, "%"+bto.getAuthor().trim()+"%");
		if(bto.getPublication() != null && bto.getPublication().trim().length() != 0)
				ps.setString(i++, "%"+bto.getPublication().trim()+"%");
		if(bto.getEdition() != null && bto.getEdition().trim().length() != 0)
				ps.setString(i++, bto.getEdition().trim());
		//System.out.println("Query : " +qry+"\t"+bto.getBookName().trim());
		//System.out.println("Edition"+bto.getEdition()+"Book");
		rs = ps.executeQuery();
		while(rs.next()){
			total++;
			//System.out.println("rs.next()........."+rs.getString("bookName"));
			//System.out.println("In While**");
		}
		}catch(Exception e){
			e.printStackTrace();
		log.error("Error in getTotalNumberOfBook."+e);	
		}finally{
			JDBCUtil.close(rs, ps, con);
		}
		
		return total;
	}
	
	public boolean deleteBook(int bookId) {
		
		log.info(bookId+"");
		boolean deleted =false;
		Connection con = null ;
		PreparedStatement ps = null;
		try{
			con = JDBCUtil.getConnection();
			ps =con.prepareStatement("delete from book_table where bookId = ?");
			ps.setInt(1, bookId);
			int x = ps.executeUpdate();
			if(x>0){
				deleted =true;
			}
		}catch(Exception e){
				e.printStackTrace();
				log.equals("Error in deleteBook. "+e);
		}finally{
			JDBCUtil.close(null, ps, con);
		}
		return deleted;
	}

	public BookTO getBookById(String bid) {
		
		BookTO bto = null;
		Connection con = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		try{
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement("Select * from book_table where bookId = ?");
			ps.setInt(1, Integer.parseInt(bid));
			rs = ps.executeQuery();
			if(rs.next()){
				bto = new BookTO();
				bto.setBookId(rs.getInt(1));
				bto.setBookName(rs.getString(2));
				bto.setAuthor(rs.getString(3));
				bto.setCost(rs.getFloat(4));
				bto.setPublication(rs.getString(5));
				bto.setEdition(rs.getString("edition"));
			}
		}catch(Exception e){
			bto = null;
			log.error("Exception in getBookById : "+e);
		}finally{
			JDBCUtil.close(null, ps, con);
		}
		
		return bto;
	}

}
