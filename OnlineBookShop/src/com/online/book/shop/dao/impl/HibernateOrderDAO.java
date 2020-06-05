package com.online.book.shop.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.online.book.shop.dao.OrderDAO;
import com.online.book.shop.model.Book;
import com.online.book.shop.model.Order;
import com.online.book.shop.model.OrderItem;
import com.online.book.shop.model.User;
import com.online.book.shop.to.BookTO;
import com.online.book.shop.to.OrderTO;
import com.online.book.shop.util.CHibernateUtil;

public class HibernateOrderDAO  implements OrderDAO{

	Logger log = Logger.getLogger(this.getClass());
	public String placeOrder(OrderTO oto, String ip) {
		
		String orderId = null;
		SessionFactory fac;
		Transaction tx = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
	
			orderId = getOrderId(oto.getOrderDate(), ip);
			Order order = new Order();
			order.setOrderId(orderId);
			order.setAddress(oto.getAddress());
			order.setStreet(oto.getStreet());
			order.setState(oto.getState());
			order.setCity(oto.getCity());
			order.setCountry(oto.getCountry());
			order.setZip(oto.getZip());
			order.setCardNo(oto.getCardNo());
			order.setExpDate(oto.getExpDate());
			order.setTotalAmount(oto.getTotalAmount());
			order.setTotalItem(oto.getTotalItem());
			order.setOrderDate(oto.getOrderDate());
			order.setStatus("NEW");
			
			User user = new User();
			user.setUserId(oto.getUserId());
			order.setUserId(user);
			session.save(order);
			
			Set oiList = new HashSet();
			Set<BookTO> iList  = oto.getOrderItemList();
			for(BookTO bto : iList){
				OrderItem oi = new OrderItem();
				Book bk = (Book)session.load(Book.class, bto.getBookId());
				oi.setBook(bk);
				oi.setQuantity(bto.getSelectedNumberOfBook());
				oi.setorderId(order);
				oiList.add(oi);
				session.save(oi);
			}
			order.setOrderItems(oiList);
			session.update(order);
			
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
		}finally{
			if(tx != null) tx.rollback();
		}
		return orderId;
	}
	
	public String getOrderId(String date, String ip){
		String id="";
		Calendar cal = Calendar.getInstance();
		String str[] = date.split("/");
		String hh = cal.get(Calendar.HOUR)+"";
		String mm = cal.get(Calendar.MINUTE)+"";
		String ss = cal.get(Calendar.SECOND)+"";
		String dt = str[0]+str[1]+str[2];
		String time = hh+mm+ss;
		String ips[] =ip.split("\\.");
		ip = ips[0]+ips[1]+ips[2]+ips[3];
		String hexDate = Long.toHexString(Long.parseLong(dt));
		String hexTime = Long.toHexString(Long.parseLong(time));
		String hexIp = Long.toHexString(Long.parseLong(ip));
		id=hexDate+"J"+hexIp+"J"+hexTime;
		return id;
	}


	public List getOrderByUserId(String userId) {
		SessionFactory fac;
		Transaction tx = null;
		List oList = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
	
			User user = (User)session.load(User.class, userId);
			Set<Order> orders = user.getOrder();
			if(orders.size() > 0){
				oList = new ArrayList();
				for(Order ord : orders){
					OrderTO oto = new OrderTO();
					oto.setAddress(ord.getAddress());
					oto.setCardNo(ord.getCardNo());
					oto.setCity(ord.getCity());
					oto.setCountry(ord.getCountry());
					oto.setExpDate(ord.getExpDate());
					oto.setOrderDate(ord.getOrderDate());
					oto.setTotalAmount(ord.getTotalAmount());
					oto.setTotalItem(ord.getTotalItem());
					oto.setStatus(ord.getStatus());
					oto.setOrderId(ord.getOrderId());
					oto.setState(ord.getState());
					oto.setStreet(ord.getStreet());
					oto.setUserId(userId);
					oto.setZip(ord.getZip());
					oList.add(oto);
				}
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
		}finally{
			if(tx != null) tx.rollback();
		}
		return oList;
	}

	public List getAllOrderInfo() {
		
		List orderList = null;
		SessionFactory fac;
		Transaction tx = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
	
			String hql = "from Order o";
			Query qr = session.createQuery(hql);
			List<Order> orders = qr.list();
			if(orders.size() > 0){
				orderList  = new ArrayList();
				for(Order ord : orders){
					OrderTO oto = new OrderTO();
					oto.setAddress(ord.getAddress());
					oto.setCardNo(ord.getCardNo());
					oto.setCity(ord.getCity());
					oto.setCountry(ord.getCountry());
					oto.setExpDate(ord.getExpDate());
					oto.setOrderDate(ord.getOrderDate());
					oto.setTotalAmount(ord.getTotalAmount());
					oto.setTotalItem(ord.getTotalItem());
					oto.setStatus(ord.getStatus());
					oto.setOrderId(ord.getOrderId());
					oto.setState(ord.getState());
					oto.setStreet(ord.getStreet());
					oto.setUserId(ord.getUserId().getUserId());
					oto.setZip(ord.getZip());
					orderList.add(oto);
			}
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
		}finally{
			if(tx != null) tx.rollback();
		}
		return orderList;
	}

	public void updateStatus(String orderId, String status) {
		
		SessionFactory fac;
		Transaction tx = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
			
			Order order = (Order)session.load(Order.class, orderId);
			order.setStatus(status);
			session.update(order);
	
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
		}finally{
			if(tx != null) tx.rollback();
		}
	}

	public OrderTO getOrderByOrderId(String orderId) {
		
		SessionFactory fac;
		Transaction tx = null;
		OrderTO oto = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
	
			Order ord = (Order)session.load(Order.class, orderId);
			
			oto = new OrderTO();
			oto.setAddress(ord.getAddress());
			//oto.setCardNo(ord.getCardNo());
			oto.setCity(ord.getCity());
			oto.setCountry(ord.getCountry());
			//oto.setExpDate(ord.getExpDate());
			oto.setOrderDate(ord.getOrderDate());
			oto.setTotalAmount(ord.getTotalAmount());
			oto.setTotalItem(ord.getTotalItem());
			oto.setStatus(ord.getStatus());
			oto.setOrderId(ord.getOrderId());
			oto.setState(ord.getState());
			oto.setStreet(ord.getStreet());
			//oto.setUserId(ord.getUserId().getUserId());
			oto.setZip(ord.getZip());
			
			Set<OrderItem> orderItems = ord.getOrderItems();
			HashSet hs = new HashSet();
			if(orderItems.size() > 0){
				for(OrderItem oi : orderItems){
					Book bk = oi.getBook();
					BookTO bto = new BookTO();
					if(bk != null){
						bto.setBookId(bk.getBookId());
						bto.setAuthor(bk.getAuthor());
						bto.setBookName(bk.getBookName());
						bto.setCost(bk.getCost());
						bto.setEdition(bk.getEdition());
						bto.setPublication(bk.getPublication());
						bto.setSelectedNumberOfBook(oi.getQuantity());
						hs.add(bto);
					}
				}
				oto.setOrderItemList(hs);
			}
			
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
		}finally{
			if(tx != null) tx.rollback();
		}
		return oto;
	}

}
