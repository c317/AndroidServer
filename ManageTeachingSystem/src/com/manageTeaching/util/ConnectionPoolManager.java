package com.manageTeaching.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.manageTeaching.config.Configuration;

public class ConnectionPoolManager {
	private static Configuration config =Configuration.getInstance();
	private static String SQL_driver =config.getSQL_driver();
	private static String SQL_account =config.getSQL_account();
	private static String SQL_key =config.getSQL_key();
	private static ConnectionPoolManager poolManagerInstance =null;
	private static ConnPool pool=null;
	static {
		try {
			Class.forName(SQL_driver);
			 pool=new ConnPool(SQL_account,SQL_key);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//私有化构造函数保证只有一个单例
	private ConnectionPoolManager() {
		
	}
	//获得连接池管理类实例
	public static ConnectionPoolManager getInstance() {
		if(poolManagerInstance==null) {
			synchronized(ConnectionPoolManager.class) {
				if(poolManagerInstance==null) {
					poolManagerInstance=new ConnectionPoolManager();
				}
			}
		}
		return poolManagerInstance;
	}
	
	public static Connection getConnection() {
		return pool.getConnection();
	}
	
	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					 st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					try {
						pool.releaseConnection(conn);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
	} 
}
