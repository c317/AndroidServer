package com.manageTeaching.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.manageTeaching.util.DaoFactory;
import com.manageTeaching.util.ManagerDao;
import com.manageTeaching.util.Score;

public class GetStudentScoreAction  implements Action, ServletRequestAware {
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getStudentScoreAction() {
		ManagerDao managerDao=DaoFactory.getInstance().getManagerDao();
		String cName=request.getParameter("cname");
		int cid= managerDao.getCid(cName);
		HttpSession session=request.getSession();
		session.setAttribute("cid",cid);
		ArrayList<Score> scores=managerDao.getStudentScore(cName);
		request.setAttribute("scores", scores);
		request.setAttribute("cid", cid);
		for(int i=0;i<scores.size();i++) {
			System.out.println(scores.get(i).getsName());
		}
		return SUCCESS;
	}
}
