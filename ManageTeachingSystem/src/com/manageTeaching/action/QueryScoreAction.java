package com.manageTeaching.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.manageTeaching.util.DaoFactory;
import com.manageTeaching.util.Score;
import com.manageTeaching.util.Student;
import com.manageTeaching.util.StudentsDao;

public class QueryScoreAction implements Action, ServletRequestAware{
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String queryScoreAction() {
		HttpSession session = request.getSession();
		Student student= (Student) session.getAttribute("curStudent");
		int sid=student.getSid();
		StudentsDao studentDao=DaoFactory.getInstance().getStudentsdao();
		ArrayList<Score> scores=studentDao.queryAllScore(sid);
		request.setAttribute("scores", scores);
		return SUCCESS;
	}
}
