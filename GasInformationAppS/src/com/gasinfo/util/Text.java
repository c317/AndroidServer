package com.gasinfo.util;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Text {
	public static void main(String[] args) throws ClassNotFoundException {
//		NewsDao newsDao = DaoFactory.getInstance().getNewsDao();
//		ArrayList<Item> items =newsDao.officeReplyItems(52, 10);
//		for(int i=0;i<items.size();i++) {
//			System.out.println(items.get(i).getItemId());
//		}
//		
		String requestTime ="2016-01-19 11:53:13.000";
		int a= requestTime.lastIndexOf(":");
		requestTime= requestTime.substring(0, a);
		System.out.println(requestTime);
	}
}
