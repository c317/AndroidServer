package com.manageTeaching.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.manageTeaching.util.Course;
import com.manageTeaching.util.DaoFactory;
import com.manageTeaching.util.Student;
import com.manageTeaching.util.StudentsDao;


public class GetAllCourseAction implements Action, ServletRequestAware {

	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getAllCourseAction(){ 
		StudentsDao studentDao=DaoFactory.getInstance().getStudentsdao();
		HttpSession session = request.getSession();
		Student student= (Student) session.getAttribute("curStudent");
		System.out.println(student.getGrade());
		ArrayList<Course> courses= studentDao.getAllClass(student.getGrade());
		request.setAttribute("courses", courses);
		return SUCCESS;
	}
}
