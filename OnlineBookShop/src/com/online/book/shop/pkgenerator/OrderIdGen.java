package com.online.book.shop.pkgenerator;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class OrderIdGen implements IdentifierGenerator{

	@Override
	public Serializable generate(SessionImplementor arg0, Object arg1)
			throws HibernateException {

		String date = new Date().toString();
		String ip = "127.0.0.1";
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

}
