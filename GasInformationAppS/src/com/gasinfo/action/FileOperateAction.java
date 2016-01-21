package com.gasinfo.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import com.gasinfo.util.MyFile;
import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.NewsDao;

public class FileOperateAction implements Action, ServletRequestAware{
	private HttpServletRequest request;
	private JSONObject dataMap;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public  String createFileAction() throws UnsupportedEncodingException {
		dataMap = new JSONObject();
		dataMap.clear();
		String fileName=new String(request.getParameter("fileName"));
		fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
		int userID=Integer.parseInt(request.getParameter("userID"));
		NewsDao newsDao=DaoFactory.getInstance().getNewsDao();
		int num=newsDao.createFile(userID, fileName);
		dataMap.put("num", num);
		return SUCCESS;
	}
	public String getFileNameAction(){ 
		//可以由session取到，也可以由request取到
		dataMap = new JSONObject();
		dataMap.clear();
		int userID=Integer.parseInt(request.getParameter("userID"));
		NewsDao newsDao=DaoFactory.getInstance().getNewsDao();
		ArrayList<MyFile> fileList=newsDao.getFileName(userID);
		JSONArray jSONArray=JSONArray.fromObject(fileList);
		dataMap.put("fileList", jSONArray);
		return SUCCESS;
	}
	public String saveNewsAction() {
		dataMap = new JSONObject();
		dataMap.clear();
		int userID=Integer.parseInt(request.getParameter("userID"));
		int moduleID=Integer.parseInt(request.getParameter("moduleID"));
		NewsDao newsDao=DaoFactory.getInstance().getNewsDao();
		ArrayList<MyFile> fileList=newsDao.getFileName(userID);
		return SUCCESS;
	}
	public JSONObject getDataMap() {
		return dataMap;
	}

	public void setDataMap(JSONObject dataMap) {
		this.dataMap = dataMap;
	}
}
