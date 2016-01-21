package com.gasinfo.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.Group;
import com.gasinfo.util.NewsDao;
import com.gasinfo.util.PageRoll;
import com.gasinfo.util.PhoneUser;

public class ManageGroupAction implements Action, ServletRequestAware {
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
	public String getMembersAction(){ 
//		String account =request.getParameter("account");
		dataMap = new JSONObject();
		dataMap.clear();
		int groupID=Integer.parseInt(request.getParameter("groupID"));
//		String token=request.getParameter("token");
		HttpSession session=request.getSession();
//		session.setAttribute("token", token);
		NewsDao newsDao = DaoFactory.getInstance().getNewsDao();
		ArrayList<PhoneUser> phoneUsers=newsDao.getMember(groupID);
		JSONArray users=JSONArray.fromObject(phoneUsers);
		dataMap.put("users",users );
		int status=1;
		dataMap.put("status", status);
//		dataMap.put("token", token);
		return SUCCESS;
	}
	public String getGroupListAction() {
		dataMap = new JSONObject();
		dataMap.clear();
		String account=request.getParameter("account");
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		PageRoll pageRoll=new PageRoll();
		int totalGroup=pageRoll.getTotalPage(pageNum);
		NewsDao newsDao = DaoFactory.getInstance().getNewsDao();
		ArrayList<Group> groups =newsDao.getGroupName(totalGroup , account);
		JSONArray group=JSONArray.fromObject(groups);
		int status=1;
		dataMap.put("status", status);
		dataMap.put("group", group);
		return SUCCESS;
	}
	public String manageMemberAction()
	{
		dataMap = new JSONObject();
		dataMap.clear();
		int mtype=Integer.parseInt(request.getParameter("mtype"));
		if(mtype==1) {
			String account =request.getParameter("memberaccount");
			int groupID =Integer.parseInt(request.getParameter("groupID"));
//		int memberID=Integer.parseInt(request.getParameter("memberid"));
			NewsDao newsDao = DaoFactory.getInstance().getNewsDao();
//			Boolean result= newsDao.addMember(account, groupID, memberID);
			//int返回值为零时不存在这个用户，返回值为2时分组表中已经存在此用户无法添加，返回值为2时添加成功
			int status= newsDao.addMember(account, groupID);
			dataMap.put("status", status);
			
		}else if(mtype==0){
//			String account =request.getParameter("memberaccount");
			int groupid =Integer.parseInt(request.getParameter("groupID"));
			int membersid=Integer.parseInt(request.getParameter("memberid"));
			NewsDao newsDao = DaoFactory.getInstance().getNewsDao();
//			int status=newsDao.deleteMember(account, groupid, memberid);
			int status=newsDao.deleteMember( groupid,membersid);
			dataMap.put("status", status);
		}
		return SUCCESS;
	}
}
