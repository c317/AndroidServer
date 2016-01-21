package com.gasinfo.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.Item;
import com.gasinfo.util.NewsDao;
import com.gasinfo.util.PageRoll;

public class WorkAction implements Action, ServletRequestAware{
	private HttpServletRequest request;
	private JSONObject dataMap;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public JSONObject getDataMap() {
		return dataMap;
	}
	public void setDataMap(JSONObject dataMap) {
		this.dataMap = dataMap;
	}
	//获取代办事项列表
	public String checkToDoList() {
		dataMap = new JSONObject();
		dataMap.clear();
		int userId=Integer.parseInt(request.getParameter("userID"));
//		String token=request.getParameter("token");
		int page=Integer.parseInt(request.getParameter("pageNum"));
		PageRoll pageRoll=new PageRoll();
		int totalItem=pageRoll.getTotalPage(page);
		NewsDao newsDao = DaoFactory.getInstance().getNewsDao();
		ArrayList<Item> toDoItems =newsDao.getNotCheckedItem(userId, totalItem);
		int status=1;
		dataMap.put("toDoItems",toDoItems);
		dataMap.put("status", status);
		return SUCCESS;
	} 
	//获取已经处理的事项列表
	public String checkHaveDoneList() {
		dataMap = new JSONObject();
		dataMap.clear();
		int userId=Integer.parseInt(request.getParameter("userID"));
		int page=Integer.parseInt(request.getParameter("pageNum"));
		PageRoll pageRoll=new PageRoll();
		int totalItem=pageRoll.getTotalPage(page);
		NewsDao newsDao = DaoFactory.getInstance().getNewsDao();
		ArrayList<Item> haveDoneContent =newsDao.getCheckedItem(userId, totalItem);
		int status=1;
		dataMap.put("haveDoneContent",haveDoneContent);
		dataMap.put("status", status);
		return SUCCESS;
	}
	//获取请求事项列表
	public String checkOfficeRequestList() {
		dataMap = new JSONObject();
		dataMap.clear();
		int userId=Integer.parseInt(request.getParameter("userID"));
		int page=Integer.parseInt(request.getParameter("pageNum"));
		PageRoll pageRoll=new PageRoll();
		int totalItem=pageRoll.getTotalPage(page);
		NewsDao newsDao = DaoFactory.getInstance().getNewsDao();
		ArrayList<Item> officeRequestItems =newsDao.getRequestItem(userId, totalItem);
		int status=1;
		dataMap.put("officeRequestItems",officeRequestItems);
		dataMap.put("status", status);
		return SUCCESS;
	}
	public String checkOfficeReplyList() {
		dataMap = new JSONObject();
		dataMap.clear();
		int userId=Integer.parseInt(request.getParameter("userID"));
		int page=Integer.parseInt(request.getParameter("pageNum"));
		PageRoll pageRoll=new PageRoll();
		int totalItem=pageRoll.getTotalPage(page);
		NewsDao newsDao = DaoFactory.getInstance().getNewsDao();
		ArrayList<Item> officeReplyItems =newsDao.officeReplyItems(userId, totalItem);
		int status=1;
		dataMap.put("officeReplyItems",officeReplyItems);
		dataMap.put("status", status);
		return SUCCESS;
	}
}
