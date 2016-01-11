package com.manageTeaching.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.manageTeaching.util.DaoFactory;
import com.manageTeaching.util.Student;
import com.manageTeaching.util.StudentsDao;

public class DeleteClassAction implements Action, ServletRequestAware  {
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String deleteClassAction() {
		StudentsDao studentDao=DaoFactory.getInstance().getStudentsdao();
		int cid=Integer.parseInt(request.getParameter("cid"));
		HttpSession session = request.getSession();
		Student student= (Student) session.getAttribute("curStudent");
		studentDao.deleteCourse(cid, student.getSid());
		return SUCCESS;
	}
}
