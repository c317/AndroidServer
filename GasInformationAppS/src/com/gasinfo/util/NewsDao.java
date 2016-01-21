package com.gasinfo.util;

import java.util.ArrayList;

public interface NewsDao {
	//�ȵ�����ģ��һ���ȵ��ȡ
	public ArrayList<News> getNewsList(int moduleID,int pageNum);
	//��ȡ��������
	public News getNewsContent(int moduleID,int newsID);
	//�û���¼
	//�û������ļ���
	public int createFile(int userID,String fileName);
	//��ȡ�û������ļ���
	public ArrayList<MyFile> getFileName(int userID);
	//�ղ��ļ�
	public int saveNews(int fileID,int moduleID,int newsID);
	//����ղص�����
	////////////����ģ��
	public ArrayList<News> getSavedNews(int fileID,String fileName);
	//��÷����е������û�
	public ArrayList<PhoneUser> getMember(int groupID);
	//��ȡ����
	public ArrayList<Group> getGroupName(int pageNum,String account);
	//�����Ա
	public int addMember(String account,int groupID);
	//ɾ����Ա
	public int deleteMember(int userID,int groupID);
	//
	////////////�칫ģ��
	//�鿴�Ѱ�����
	public ArrayList<Item> getNotCheckedItem(int handlerID,int totalItem); 
	//�鿴δ������
	public ArrayList<Item> getCheckedItem(int handlerID,int totalItem);
	//�鿴���������б�
	public ArrayList<Item> getRequestItem(int requesterID,int totalItem);
	//�鿴�칫�ظ�
	public ArrayList<Item> officeReplyItems(int requesterID,int totalItem);
}
