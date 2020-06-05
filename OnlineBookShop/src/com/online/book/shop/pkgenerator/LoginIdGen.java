package com.online.book.shop.pkgenerator;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.online.book.shop.util.CHibernateUtil;

public class LoginIdGen implements IdentifierGenerator  {

	Logger log = Logger.getLogger(LoginIdGen.class);
	@Override
	public Serializable generate(SessionImplementor si, Object obj)
			throws HibernateException {
		String loginId = "BLR-L-0001";
		SessionFactory factory;
		Transaction tx = null;
		String hql = "from Login u ";
		try{
			
			factory = CHibernateUtil.getSessionFactory();
			Session session = factory.openSession();
			tx = session.beginTransaction();
			
			Query qry = session.createQuery(hql);
			int size = qry.list().size();
			if(size != 0){
				hql = "select max(l.loginId) from Login l";
				Query qry1 = session.createQuery(hql);
				String uid = qry1.list().get(0).toString();
				int id = Integer.parseInt(uid.substring(6));
				id++;
				if(id<=9){
					loginId = "BLR-L-000"+id;
				}else if(id<=99){
					loginId = "BLR-L-00"+id;
				} else if(id<=999){
					loginId = "BLR-L-0"+id;
				}else{
					loginId = "BLR-L-"+id;
				}
			}else{
				loginId = "BLR-L-0001";
			}
				tx.commit();
			
		}catch(Exception e){
			log.error("Exception in getloginId()\t : "+e);
		}finally{
			if(tx != null) tx.rollback();
		}
		return loginId;
	}

}
