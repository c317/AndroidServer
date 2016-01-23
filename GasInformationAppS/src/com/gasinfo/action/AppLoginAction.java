package com.gasinfo.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.NewsDao;
import com.opensymphony.xwork2.ActionSupport;

public class AppLoginAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 5912638975542232044L;
	private String account;
	private String password;
	private JSONObject dataMap;
	private HttpServletRequest request;
	private String token;

	public String execute() throws Exception {

		NewsDao newsDao = DaoFactory.getInstance().getNewsDao();
		int status = newsDao.usersLogin(account, password);
		token = request.getSession().getId();
		dataMap = new JSONObject();
		dataMap.clear();
		dataMap.put("status", status);
		dataMap.put("token", token);
		return SUCCESS;
	}

	public JSONObject getDataMap() {
		return dataMap;
	}

	public void setDataMap(JSONObject dataMap) {
		this.dataMap = dataMap;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.setRequest(arg0);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
