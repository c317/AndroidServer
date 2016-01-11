package com.manageTeaching.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import com.manageTeaching.config.Configuration;

public class ConnPool {
	private Configuration config =Configuration.getInstance();
	private String url="jdbc:microsoft.sqlserver://localhost:1433;DatabaseName=ManageTeachingSystem";
	private String SQL_account=config.getSQL_account();
	private String SQL_key=config.getSQL_key();
	//��ʼʱ���������
	private int initialSize=2;
	//���������
	private int maxSize=4;
	//�������ȴ�ʱ��
	private long maxWaitTime=12000;
	private Vector<ConnBean> pool=new Vector<ConnBean>();
	private static long waitTime=0;
	public static long getWaitTime() {
		return waitTime;
	}

	public static void addWaitTime(long waitTime) {
		ConnPool.waitTime += waitTime;
	}

	//��ȡ��ʼ����Ŀ������
	public ConnPool(String SQL_account,String SQL_key) {
		this.SQL_key=SQL_key;
		this.SQL_account=SQL_account;
		for(int i=0;i<this.initialSize;i++) {
			Connection con=this.creatConnection();
			if(con!=null){ 
				ConnBean conn=new ConnBean(con);
				pool.add(conn);
			}
		}
	}
	
	//��ȡ����
	private Connection creatConnection() {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=ManageTeachingSystem;",SQL_account,SQL_key);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return conn;
	}
	//��ȡ����ʵ�ֶ����ӳ��е����ӹ��������
	public synchronized Connection getConnection() {
		ConnBean connBean=null;
		for(int i=0;i<this.pool.size();i++) {
			connBean=pool.get(i);
			if(connBean.isUsed()==false) {
				connBean.setUsed(true);
				Connection conn=connBean.getConn();
				System.out.println("��"+i+"������û��");
				return conn;
			}
		}
		//���������������ȴ�
		if(this.pool.size()>=this.maxSize) {
			System.out.println("������������50һֱ˯��ֱ��������û�б�ʹ��");
			long startTime=new Date().getTime();
			long waitTime;
			try {
				System.out.println("˯��");
				wait(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			//�û�����ʱ�ȴ�
			long endTime=new Date().getTime();
			waitTime=endTime-startTime;
			ConnPool.addWaitTime(waitTime);
			if(ConnPool.getWaitTime()>=maxWaitTime) {
				return null;
			}
			//ѭ������
			Connection conn=getConnection();
			return conn;
		}
		//����������������򴴽��µ�����
		else { 
			Connection conn=this.creatConnection();
			connBean=new ConnBean(conn);
			connBean.setUsed(true);
			pool.add(connBean);
			return connBean.getConn();
		}
	}
	private void addConnection(ConnBean connBean ) {
		if(pool==null) {
			pool=new Vector<ConnBean>();
		}
		pool.addElement(connBean);
	}
	//�ͷ�����ʱ���õķ������ӳ��У���������������wait���û�
	public synchronized void releaseConnection(Connection conn) {
		for(int i=0;i<pool.size();i++) {
			System.out.println("����ʹ�õ����ӷ��ظ����ӳ�");
			ConnBean connBean=pool.elementAt(i);
			if(connBean.getConn()==conn) {
				connBean.setUsed(false);
				notifyAll();
				break;
			}
		}
	}
	//�����������
	public synchronized void emptyConnectionPool() throws SQLException,
	InterruptedException { 
		for(int i=0;i<pool.size();i++) {
			System.out.println("�ͷŵ�i������"+"i");
			ConnBean connBean=pool.elementAt(i);
			if(connBean.isUsed()==false) {
				connBean.getConn().close();
			}else {
				java.lang.Thread.sleep(10000);
				connBean.getConn().close();
			}
		}
	}
	
	
}
