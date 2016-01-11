package com.manageTeaching.util;

public class Course {
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Course() {
		this.cid = 0;
		this.cName =null;
		this.beginTime =null;
		this.teacher = null;
		this.rName =null;
		this.day = null;
		this.time =null;
		this.rid =0;
		this.endTime =null;
		this.grade = null;
	}
	private int cid;
	private String cName;
	private String beginTime;
	private String teacher;
	private String rName;
	private String day;
	private String  time;
	public int rid;
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	private String endTime;
	private String grade;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cName;
	}
	public void setCName(String cname) {
		this.cName = cname;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
