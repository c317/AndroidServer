package com.manageTeaching.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.manageTeaching.util.DaoFactory;
import com.manageTeaching.util.ManagerDao;
import com.manageTeaching.util.Student;

public class GetAllStudentAction implements Action, ServletRequestAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getAllStudentAction() throws UnsupportedEncodingException {
		ManagerDao managerDao=DaoFactory.getInstance().getManagerDao();
		String cName=request.getParameter("cname");
		ArrayList<Student> students=managerDao.queryAllStudents(cName);
		if(students!=null) {
			System.out.println(students.size());
			for(int i=0;i<students.size();i++) {
				System.out.println("aa" +students.get(i).getSname());
			}
			request.setAttribute("students", students);
			return SUCCESS;
		}
		return ERROR;
	}
}
