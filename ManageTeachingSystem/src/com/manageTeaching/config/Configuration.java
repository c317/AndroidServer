package com.manageTeaching.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

//用单例模式设计
public class Configuration {
	private String SQL_driver;
	private String SQL_account;
	private String SQL_key;
	private String studentsDaoClass;
	private String managerDaoClass;
	public String getManagerDaoClass() {
		return managerDaoClass;
	}
	public void setManagerDaoClass(String managerDaoClass) {
		this.managerDaoClass = managerDaoClass;
	}
	private static Configuration instance= null;
	
	public String getStudentsDaoClass() {
		return studentsDaoClass;
	}
	public void setStudentsDaoClass(String studentsDaoClass) {
		this.studentsDaoClass = studentsDaoClass;
	}	
	public String getSQL_driver() {
		return SQL_driver;
	}
	public void setSQL_driver(String sQL_driver) {
		SQL_driver = sQL_driver;
	}
	public String getSQL_account() {
		return SQL_account;
	}
	public void setSQL_account(String sQL_account) {
		SQL_account = sQL_account;
	}
	public String getSQL_key() {
		return SQL_key;
	}
	public void setSQL_key(String sQL_key) {
		SQL_key = sQL_key;
	}
	public static void setInstance(Configuration instance) {
		Configuration.instance = instance;
	}

	/**
	 * 获取配置文件类的实例
	 */
	public static Configuration getInstance() {
		if(instance==null) { 
			instance=new Configuration("daoconfig.properties"); 
		}
		return instance;
	}
	/**
	 * 初始化Configuration类
	 */
	private Configuration(String fileName) {
		this.readConfiguration(fileName);
		
	}
	
	private void readConfiguration(String fileName) {
		 Properties prop = new Properties();// 属性集合对象  
		 FileInputStream inputFile=null;
			try {
				inputFile = new FileInputStream(this.getClass().getClassLoader()
						.getResource(fileName).getPath());
				prop.load(inputFile);
				this.SQL_account=prop.getProperty("SQL_account");
				this.SQL_driver=prop.getProperty("SQL_driver");
				this.SQL_key=prop.getProperty("SQL_key");
				this.studentsDaoClass=prop.getProperty("studentsDaoClass");
				this.managerDaoClass=prop.getProperty("managerDaoClass");
				inputFile.close();		
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	}
	public static void main(String[] args) throws IOException {
		Configuration config=Configuration.getInstance();
		String SQL_driver =config.getSQL_driver();
		System.out.println(config.managerDaoClass);
	}
}
