package com.manageTeaching.util;

import java.util.ArrayList;

public interface StudentsDao {
	//��ȡ���пγ�
	public ArrayList<Course> getAllClass(String grade);
	//ѧ�����ĳ�ſγ�
	public void addCourse(int cid,int sid);
	//�鿴ĳѧ��������ӿγ�
	public ArrayList<Course> getStudentAllClass(int sid);
	//ѧ��ɾ��ĳ�ſγ�
	public void deleteCourse(int cid,int sid);
	//ѧ�����пγ̳ɼ���ѯ
	public ArrayList<Score> queryAllScore(int sid);
	//�鿴ĳ�ſγ̳ɼ�
	public Student studentsLogin(int sid,String password);
	//���ѧ���α�
	public ArrayList<Course> getStudentClassTable(int sid);
}
