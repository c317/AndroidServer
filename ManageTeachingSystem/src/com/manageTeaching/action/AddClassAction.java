package com.manageTeaching.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.manageTeaching.util.DaoFactory;
import com.manageTeaching.util.Student;
import com.manageTeaching.util.StudentsDao;

public class AddClassAction implements Action, ServletRequestAware {

	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String addClassAction() {
		HttpSession session = request.getSession();
		Student student= (Student) session.getAttribute("curStudent");
		int sid=student.getSid();
		int cid=Integer.parseInt(request.getParameter("cid"));
		StudentsDao studentDao=DaoFactory.getInstance().getStudentsdao();
		studentDao.addCourse(cid, sid);
		return SUCCESS;
	}

}
