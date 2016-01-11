package com.manageTeaching.util;

import java.util.ArrayList;

public interface ManagerDao {
	//����һ�ſγ�
	public void insertCourse(String cName,String beginTime,String endTime,String grade);
	//ɾ��һ�ſγ�(�õ�������)
	public void deleteCourse(String cName);
	//�޸�һ�ſγ�
	public void updateCourse(String cName,String beginTime,String endTime,String grade);
	//�ſ�
	public boolean manageCourse(String cName, String rName, String day, String startTime,String endTime,String classTime,String tName);
	//��ĳһ�ſ�����ѧ���ɼ�
	public ArrayList<Score> queryCourseScore(String cName);
	//��ĳһ�γ�����ѧ������
	public ArrayList<Student> queryAllStudents(String cName);
	//������пγ�
	public ArrayList<Course> getAllCourse();
	//��ð��ŵĿγ�
	public ArrayList<Course> getUnManagedClass();
	public ArrayList<Score> getStudentScore(String cName);
	public void setScore(ArrayList<Score> scores,int cid);
	public int getCid(String cName);
}
