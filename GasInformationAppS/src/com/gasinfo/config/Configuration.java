package com.gasinfo.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

//�õ���ģʽ���
public class Configuration {
	private String SQL_driver;
	private String SQL_account;
	private String SQL_key;
	private String newsDaoClass;
	private static Configuration instance= null;
	
	public String getNewsDaoClass() {
		return newsDaoClass;
	}
	public void setNewsDaoClass(String newsDaoClass) {
		this.newsDaoClass = newsDaoClass;
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
	 * ��ȡ�����ļ����ʵ��
	 */
	public static Configuration getInstance() {
		if(instance==null) { 
			instance=new Configuration("daoconfig.properties"); 
		}
		return instance;
	}
	/**
	 * ��ʼ��Configuration��
	 */
	private Configuration(String fileName) {
		this.readConfiguration(fileName);
		
	}
	
	private void readConfiguration(String fileName) {
		 Properties prop = new Properties();// ���Լ��϶���  
		 FileInputStream inputFile=null;
			try {
				inputFile = new FileInputStream(this.getClass().getClassLoader()
						.getResource(fileName).getPath());
				prop.load(inputFile);
				this.SQL_account=prop.getProperty("SQL_account");
				this.SQL_driver=prop.getProperty("SQL_driver");
				this.SQL_key=prop.getProperty("SQL_key");
				this.newsDaoClass=prop.getProperty("newsDaoClass");
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
		System.out.println(SQL_driver);
	}
}
