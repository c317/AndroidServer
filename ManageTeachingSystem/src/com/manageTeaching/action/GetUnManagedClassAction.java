package com.manageTeaching.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.manageTeaching.util.Course;
import com.manageTeaching.util.DaoFactory;
import com.manageTeaching.util.ManagerDao;

public class GetUnManagedClassAction implements Action, ServletRequestAware{
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getUnManagedClassAction() {
		 ManagerDao managerDao=DaoFactory.getInstance().getManagerDao();
		ArrayList<Course> courses= managerDao.getUnManagedClass();
		if(courses!=null) {
			request.setAttribute("courses", courses);
			return SUCCESS;
		} 
		
		return ERROR;
	}
}
