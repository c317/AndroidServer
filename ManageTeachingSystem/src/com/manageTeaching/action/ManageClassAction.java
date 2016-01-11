package com.manageTeaching.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.manageTeaching.util.DaoFactory;
import com.manageTeaching.util.ManagerDao;

public class ManageClassAction implements Action, ServletRequestAware{
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String manageClassAction() {
		String cName=request.getParameter("cname");
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		String rName=request.getParameter("classRoom");
		String day=request.getParameter("day");
		String classTime=request.getParameter("class");
		String tName=request.getParameter("tname");
		ManagerDao managerDao=DaoFactory.getInstance().getManagerDao();
		boolean type=managerDao.manageCourse(cName, rName, day, startTime, endTime, classTime,tName);
		if(type==true) {
			return SUCCESS;
		}else {
			return ERROR;
		}
		
	}
}
