package com.manageTeaching.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.manageTeaching.util.DaoFactory;
import com.manageTeaching.util.ManagerDao;
import com.manageTeaching.util.Score;

public class SetStudentScoreAction  implements Action, ServletRequestAware {
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String setStudentScoreAction() {
		ManagerDao managerDao=DaoFactory.getInstance().getManagerDao();
		int num=Integer.parseInt(request.getParameter("count"));
		HttpSession session=request.getSession();
		int cid= (Integer)session.getAttribute("cid");
		ArrayList<Score> scores=new ArrayList<Score>();
		for(int i=0;i<num;i++) {
			Score scoreTemp=new Score();
			String temp=String.valueOf(i);
			String name="name" +temp;
			String sid="sid" +temp;
			String score="score" +temp;
			scoreTemp.setsName(request.getParameter(name));
			scoreTemp.setSid(Integer.parseInt(request.getParameter(sid)));
			scoreTemp.setScore(Float.parseFloat(request.getParameter(score)));
			scores.add(scoreTemp);
		}
		managerDao.setScore(scores,cid);
		for(int i=0;i<num;i++){ 
			System.out.println(scores.get(i).getsName());
			System.out.println(scores.get(i).getSid());
			System.out.println(scores.get(i).getScore());
		}
		return SUCCESS;
		
	}
}
