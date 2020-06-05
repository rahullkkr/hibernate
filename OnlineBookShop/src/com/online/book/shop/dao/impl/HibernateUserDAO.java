package com.online.book.shop.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.online.book.shop.dao.UserDAO;
import com.online.book.shop.model.Login;
import com.online.book.shop.model.User;
import com.online.book.shop.to.UserTO;
import com.online.book.shop.util.CHibernateUtil;

public class HibernateUserDAO implements UserDAO {

	Logger log = Logger.getLogger(this.getClass());
	public UserTO verifyUser(String username, String password) {
		
		UserTO uto = null;
		SessionFactory fac;
		Transaction tx = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
			
			String hql = "from Login lg where username=? and password=?";
			Query qr = session.createQuery(hql);
			qr.setParameter(0, username);
			qr.setParameter(1, password);
			Login lg = (Login) qr.uniqueResult();
			if(lg != null){
				if(password.equals(lg.getPassword()) && username.equals(lg.getUsername())){
					User user = lg.getUser();
					String fName = user.getFirstName();
					String mName = user.getMiddleName();
					String lName = user.getLastName();
					String email = user.getEmail();
					long phone = user.getPhone();
					String role = lg.getRole();
					uto = new UserTO(fName, mName, lName, email, phone, username, password, role);
					uto.setUserId(user.getUserId());
					uto.setLoginId(lg.getLoginId());
				}
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
		}finally{
			if(tx != null) tx.rollback();
		}
		return uto;
	}

	public UserTO changePassword(UserTO uto, String password) {
		
		SessionFactory fac;
		Transaction tx = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
	
			Login lg = (Login)session.load(Login.class, uto.getLoginId());
			lg.setPassword(password);
			session.update(lg);
			uto.setPassword(password);
			
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
		}finally{
			if(tx != null) tx.rollback();
		}
		return uto;
	}

	public String searchPassword(String username, String email) {
		
		String password = null;
		SessionFactory fac;
		Transaction tx = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
	
			String hql = "from Login l where l.username = ?";
			Query qr = session.createQuery(hql);
			qr.setParameter(0, username);
			List<Login> lgList = qr.list();
			for(Login lg : lgList){
				User user = lg.getUser();
				if(email.equals(user.getEmail()))
					password = lg.getPassword();
			}
			
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
		}finally{
			if(tx != null) tx.rollback();
		}

		return password;
	}

	public boolean registerUser(UserTO uto) {

		boolean registered = false;
		SessionFactory fac;
		Transaction tx = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
			
			User user = new User(uto.getFirstName(), uto.getMiddleName(), uto.getLastName(), uto.getEmail(), uto.getPhone());
			Login lg = new Login(uto.getUsername(), uto.getPassword(), uto.getRole());
			lg.setUser(user);
			//user.setLoginId(lg);
			session.save(user);
			session.save(lg);
			registered = true;
			
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
		}finally{
			if(tx != null) tx.rollback();
		}
		return registered;
	}

	public boolean alreadyExist(String username) {
		
		boolean exist = false;
		SessionFactory fac;
		Transaction tx = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
	
			String hql = "from Login l where l.username=?";
			Query qr = session.createQuery(hql);
			qr.setParameter(0, username);
			int size = qr.list().size();
			if(size > 0){
				exist = true;
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
		}finally{
			if(tx != null) tx.rollback();
		}
		return exist;
	}

	public boolean updateUserInfo(String userId, String email, long phone) {
		boolean updated = false;
		SessionFactory fac;
		Transaction tx = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
	
			User user = (User)session.load(User.class, userId);
			user.setEmail(email);
			user.setPhone(phone);
			session.update(user);
			updated = true;
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
		}finally{
			if(tx != null) tx.rollback();
		}
		return updated;
	}

	public UserTO getUserInfoById(String userId) {
		UserTO uto = null;
		SessionFactory fac;
		Transaction tx = null;
		try{
			fac = CHibernateUtil.getSessionFactory();
			Session session = fac.openSession();
			tx  = session.beginTransaction();
	
			User user = (User)session.load(User.class, userId);
			if(user != null){
				String fName = user.getFirstName();
				String mName = user.getMiddleName();
				String lName = user.getLastName();
				String email = user.getEmail();
				long phone = user.getPhone();
				uto = new UserTO(fName, mName, lName, email, phone, null, null, null);
				uto.setUserId(user.getUserId());
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
		}finally{
			if(tx != null) tx.rollback();
		}
		return uto;
	}

}
