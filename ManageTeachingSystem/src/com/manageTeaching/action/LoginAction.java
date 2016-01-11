package com.manageTeaching.action;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.manageTeaching.util.DaoFactory;
import com.manageTeaching.util.Student;
import com.manageTeaching.util.StudentsDao;

public class LoginAction implements Action, ServletRequestAware {

	private static final long serialVersionUID = 5912638975542232044L;
	private HttpServletRequest request;
	public String execute() throws Exception {
		int sid=Integer.parseInt(request.getParameter("sid"));
		String password=request.getParameter("password");
		StudentsDao studentsdao=DaoFactory.getInstance().getStudentsdao();
		Student us=studentsdao.studentsLogin(sid, password);
		System.out.println("aaaaaa");
		if(us!=null && us.getSid()==sid && us.getPassword().equals(password)) {
			HttpSession session=request.getSession();
			session.setAttribute("curStudent", us);
			Student student= (Student) session.getAttribute("curStudent");
			System.out.println(student.getGrade());
			return SUCCESS;
		}
		
		return ERROR;
	 }


	public void setServletRequest(HttpServletRequest hrs) {
		this.request=hrs;
	}
                                                   
}
