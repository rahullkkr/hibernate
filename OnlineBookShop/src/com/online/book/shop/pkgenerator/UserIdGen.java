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

public class UserIdGen implements IdentifierGenerator  {

	Logger log = Logger.getLogger(UserIdGen.class);
	@Override
	public Serializable generate(SessionImplementor si, Object obj)
			throws HibernateException {
		String userId = "BLR-U-0001";
		SessionFactory factory;
		Transaction tx = null;
		String hql = "from User u ";
		try{
			
			factory = CHibernateUtil.getSessionFactory();
			Session session = factory.openSession();
			tx = session.beginTransaction();
			
			Query qry = session.createQuery(hql);
			int size = qry.list().size();
			if(size != 0){
				hql = "select max(u.userId) from User u";
				Query qry1 = session.createQuery(hql);
				String uid = qry1.list().get(0).toString();
				int id = Integer.parseInt(uid.substring(6));
				id++;
				if(id<=9){
					userId = "BLR-U-000"+id;
				}else if(id<=99){
					userId = "BLR-U-00"+id;
				} else if(id<=999){
					userId = "BLR-U-0"+id;
				}else{
					userId = "BLR-U-"+id;
				}
			}else{
				userId = "BLR-U-0001";
			}
				tx.commit();
			
		}catch(Exception e){
			log.error("Exception in getUserId()\t : "+e);
		}finally{
			if(tx != null) tx.rollback();
		}
		return userId;
	}

}
