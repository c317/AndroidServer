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
	//初始时最大链接数
	private int initialSize=2;
	//最大链接数
	private int maxSize=4;
	//链接最大等待时间
	private long maxWaitTime=12000;
	private Vector<ConnBean> pool=new Vector<ConnBean>();
	private static long waitTime=0;
	public static long getWaitTime() {
		return waitTime;
	}

	public static void addWaitTime(long waitTime) {
		ConnPool.waitTime += waitTime;
	}

	//获取初始化数目的链接
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
	
	//获取链接
	private Connection creatConnection() {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=ManageTeachingSystem;",SQL_account,SQL_key);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return conn;
	}
	//获取链接实现对链接池中的链接管理与分配
	public synchronized Connection getConnection() {
		ConnBean connBean=null;
		for(int i=0;i<this.pool.size();i++) {
			connBean=pool.get(i);
			if(connBean.isUsed()==false) {
				connBean.setUsed(true);
				Connection conn=connBean.getConn();
				System.out.println("第"+i+"个链接没用");
				return conn;
			}
		}
		//超过最大链接数则等待
		if(this.pool.size()>=this.maxSize) {
			System.out.println("总链接数大于50一直睡眠直到有链接没有被使用");
			long startTime=new Date().getTime();
			long waitTime;
			try {
				System.out.println("睡眠");
				wait(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			//用户请求超时等待
			long endTime=new Date().getTime();
			waitTime=endTime-startTime;
			ConnPool.addWaitTime(waitTime);
			if(ConnPool.getWaitTime()>=maxWaitTime) {
				return null;
			}
			//循环调用
			Connection conn=getConnection();
			return conn;
		}
		//不超过最大链接数则创建新的链接
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
	//释放链接时不用的返回连接池中，并唤醒其他正在wait的用户
	public synchronized void releaseConnection(Connection conn) {
		for(int i=0;i<pool.size();i++) {
			System.out.println("不再使用的链接返回给链接池");
			ConnBean connBean=pool.elementAt(i);
			if(connBean.getConn()==conn) {
				connBean.setUsed(false);
				notifyAll();
				break;
			}
		}
	}
	//清空所有链接
	public synchronized void emptyConnectionPool() throws SQLException,
	InterruptedException { 
		for(int i=0;i<pool.size();i++) {
			System.out.println("释放第i个程序"+"i");
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
