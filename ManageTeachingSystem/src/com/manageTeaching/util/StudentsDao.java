package com.manageTeaching.util;

import java.util.ArrayList;

public interface StudentsDao {
	//获取所有课程
	public ArrayList<Course> getAllClass(String grade);
	//学生添加某门课程
	public void addCourse(int cid,int sid);
	//查看某学生所有添加课程
	public ArrayList<Course> getStudentAllClass(int sid);
	//学生删除某门课程
	public void deleteCourse(int cid,int sid);
	//学生所有课程成绩查询
	public ArrayList<Score> queryAllScore(int sid);
	//查看某门课程成绩
	public Student studentsLogin(int sid,String password);
	//获得学生课表
	public ArrayList<Course> getStudentClassTable(int sid);
}
