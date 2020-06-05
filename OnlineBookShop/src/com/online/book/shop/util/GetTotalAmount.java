package com.online.book.shop.util;

public class GetTotalAmount {

	public static String getTotalAmount(String value){
		String st="";
		double d = Double.parseDouble(value);
		long ln =(long)d;
		double frac = d-ln;
		if(frac > .01){
			st = ln+1+".00";
		}else{
			st=ln+".00";
		}
		return st;
	}
	
	
}
