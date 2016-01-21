package com.gasinfo.util;

import java.sql.Connection;
import java.sql.SQLException;
//数据库链接组件
public class ConnBean {
	private Connection conn=null;
	private boolean isUsed=false;
	public ConnBean(){}
	
	public ConnBean(Connection conn){
		if(conn!=null) {
			this.conn=conn;
		}
		
	}
	
	public Connection getConn() {
		return conn;
	}
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public boolean isUsed() {
		return isUsed;
	}
	
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
