package com.manageTeaching.util;

import com.manageTeaching.config.Configuration;


public class DaoFactory {
	private static StudentsDao studentsdao =null;
	private static ManagerDao managerDao=null;
	private static DaoFactory instance =new DaoFactory();
	
	private DaoFactory(){
		try {
			Configuration rc =Configuration.getInstance() ;
			String studentsDaoClass = rc.getStudentsDaoClass();
			String managerDaoClass=rc.getManagerDaoClass();
			//java反射机制获取实例
			Class<?> clazz = Class.forName(studentsDaoClass);
			Class<?> clazz2=Class.forName(managerDaoClass);
			studentsdao =(StudentsDao) clazz.newInstance();
			managerDao=(ManagerDao)clazz2.newInstance();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public ManagerDao getManagerDao() {
		return managerDao;
	}
	public static DaoFactory getInstance(){
		return instance;
	}
	
	public  StudentsDao getStudentsdao(){
		return studentsdao;
	}
}