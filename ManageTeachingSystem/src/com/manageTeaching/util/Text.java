package com.manageTeaching.util;

import java.util.ArrayList;

public class Text {

	public static void main(String[] args) {
		ManagerDao managerDao=DaoFactory.getInstance().getManagerDao();
		managerDao.getStudentScore("ÍøÂç±à³Ì");
	}
}
