package com.gasinfo.action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.News;
import com.gasinfo.util.NewsDao;
import com.gasinfo.util.PageRoll;

public class GetNewsListAction implements Action, ServletRequestAware {

	private HttpServletRequest request;
	private JSONObject dataMap;
	
	public String getNewsListAction() {
		dataMap = new JSONObject();
		dataMap.clear();
		NewsDao newsDao = DaoFactory.getInstance().getNewsDao();
		int moduleID = Integer.parseInt(request.getParameter("module"));
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		PageRoll pageRoll=new PageRoll();
		int totalNews=pageRoll.getTotalPage(pageNum);
		ArrayList<News> datas= newsDao.getNewsList(moduleID,totalNews);
		JSONArray news = JSONArray.fromObject(datas);
		int status=1;
		dataMap.put("status", status);
		dataMap.put("news", news);
		return SUCCESS;
	}
	public String getNewsContentAction() {
		dataMap = new JSONObject();
		dataMap.clear();
		NewsDao newsDao = DaoFactory.getInstance().getNewsDao();
		int moduleID = Integer.parseInt(request.getParameter("module"));
		int newsID = Integer.parseInt(request.getParameter("id"));
		News news =newsDao.getNewsContent(moduleID, newsID);
		JSONObject JSONNews=JSONObject.fromObject(news);
		int status=1;
		dataMap.put("status", status);
		dataMap.put("news", JSONNews);
		return SUCCESS;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public JSONObject getDataMap() {
		return dataMap;
	}
	public void setDataMap(JSONObject dataMap) {
		this.dataMap = dataMap;
	}
}
