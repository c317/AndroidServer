package com.gasinfo.util;

import com.gasinfo.config.Configuration;


public class DaoFactory {
	private static NewsDao newsdao =null;
	private static DaoFactory instance =new DaoFactory();
	
	private DaoFactory(){
		try {
			Configuration rc =Configuration.getInstance() ;
			String newsDaoClass = rc.getNewsDaoClass();
			Class<?> clazz = Class.forName(newsDaoClass);
			newsdao =(NewsDao) clazz.newInstance();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static DaoFactory getInstance(){
		return instance;
	}
	
	public  NewsDao getNewsDao(){
		return newsdao;
	}
}