package com.gasinfo.util;

import java.util.ArrayList;

public interface NewsDao {
	//热点新闻模块一周热点获取
	public ArrayList<News> getNewsList(int moduleID,int pageNum);
	//获取新闻内容
	public News getNewsContent(int moduleID,int newsID);
	//用户登录
	//用户创建文件夹
	public int createFile(int userID,String fileName);
	//获取用户所有文件夹
	public ArrayList<MyFile> getFileName(int userID);
	//收藏文件
	public int saveNews(int fileID,int moduleID,int newsID);
	//获得收藏的新闻
	////////////分组模块
	public ArrayList<News> getSavedNews(int fileID,String fileName);
	//获得分组中的所有用户
	public ArrayList<PhoneUser> getMember(int groupID);
	//获取组名
	public ArrayList<Group> getGroupName(int pageNum,String account);
	//添加组员
	public int addMember(String account,int groupID);
	//删除成员
	public int deleteMember(int userID,int groupID);
	//
	////////////办公模块
	//查看已办事项
	public ArrayList<Item> getNotCheckedItem(int handlerID,int totalItem); 
	//查看未办事项
	public ArrayList<Item> getCheckedItem(int handlerID,int totalItem);
	//查看代办事项列表
	public ArrayList<Item> getRequestItem(int requesterID,int totalItem);
	//查看办公回复
	public ArrayList<Item> officeReplyItems(int requesterID,int totalItem);
}
