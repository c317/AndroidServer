package com.manageTeaching.util;

import java.util.ArrayList;

public interface ManagerDao {
	//增加一门课程
	public void insertCourse(String cName,String beginTime,String endTime,String grade);
	//删除一门课程(用到触发器)
	public void deleteCourse(String cName);
	//修改一门课程
	public void updateCourse(String cName,String beginTime,String endTime,String grade);
	//排课
	public boolean manageCourse(String cName, String rName, String day, String startTime,String endTime,String classTime,String tName);
	//查某一门课所有学生成绩
	public ArrayList<Score> queryCourseScore(String cName);
	//查某一课程所有学生名单
	public ArrayList<Student> queryAllStudents(String cName);
	//获得所有课程
	public ArrayList<Course> getAllCourse();
	//获得安排的课程
	public ArrayList<Course> getUnManagedClass();
	public ArrayList<Score> getStudentScore(String cName);
	public void setScore(ArrayList<Score> scores,int cid);
	public int getCid(String cName);
}
