package com.manageTeaching.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.manageTeaching.util.ConnectionPoolManager;

public class StudentsDaoImple implements StudentsDao {

	public ArrayList<Course> getAllClass(String grade) {
		Connection conn=null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		String sql="select Course.cid,cname,tname from Course,TC,Teacher " +
				"where Course.cid=Tc.cid and Teacher.tid=TC.tid and grade=?";
		ArrayList<Course> courses=new ArrayList<Course>();
		try {
			conn=ConnectionPoolManager.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1,grade);
			rs=ps.executeQuery();
			while(rs.next()) {
				Course course =new Course();
				course.setCName(rs.getString("cName"));
				course.setTeacher(rs.getString("tname"));
				course.setCid(rs.getInt("cid"));
				courses.add(course);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return courses;
	}
//	public ArrayList<Course> getAllClass(String grade) {
//		Connection conn=null;
//		PreparedStatement ps =null;
//		ResultSet rs=null;
//		String sql="select Course.cid,cname,grade,tname,time,day,startTime,endTime,rname " +
//				"from Course,TC,Teacher,ManageClass,ClassRoom " +
//				"where classRoom.rid=ManageClass.rid and Course.cid=Tc.cid and Teacher.tid=TC.tid and ManageClass.cid=Course.cid and grade=?";
//		ArrayList<Course> courses=new ArrayList<Course>();
//		try {
//			conn=ConnectionPoolManager.getConnection();
//			ps=conn.prepareStatement(sql);
//			ps.setString(1,grade);
//			rs=ps.executeQuery();
//			while(rs.next()) {
//				Course course =new Course();
//				course.setEndTime(rs.getString("endTime"));
//				course.setBeginTime(rs.getString("startTime"));
//				course.setCName(rs.getString("cName"));
//				course.setTeacher(rs.getString("tname"));
//				course.setrName(rs.getString("rname"));
//				course.setDay(rs.getString("day"));
//				course.setTime(rs.getString("time"));
//				course.setCid(rs.getInt("cid"));
//				courses.add(course);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			ConnectionPoolManager.free(rs, ps, conn);
//		}
//		return courses;
//	}
	public void addCourse(int cid,int sid) {
		Connection conn=null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		boolean type=false;
		String sql="select * from SC where sid=? and cid=?";
		try {
			conn=ConnectionPoolManager.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,sid);
			ps.setInt(2,cid);
			rs=ps.executeQuery();
			if(rs.next()) {
				type=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		if(type==false) {
			String sql1="insert into SC values(?,?,0)";
			try {
				conn=ConnectionPoolManager.getConnection();
				ps=conn.prepareStatement(sql1);
				ps.setInt(1,sid);
				ps.setInt(2,cid);
				ps.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				ConnectionPoolManager.free(rs, ps, conn);
			}
		}
	}

	public void deleteCourse(int cid,int sid) {
		Connection conn=null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		String sql="delete from SC where cid=? and sid=?";
		try {
			conn=ConnectionPoolManager.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,cid);
			ps.setInt(2,sid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
	}

	public ArrayList<Course> getStudentClassTable(int sid) {
		Connection conn=null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		String sql1="select ManageClass.tname,ManageClass.rname,ManageClass.day,ManageClass.cid,ManageClass.endTime,ManageClass.startTime,manageClass.time from ManageClass where " +
				"exists(select * from SC where ManageClass.cid=SC.cid and SC.sid=?)";
		String sql2="select cname from course where cid=?";
		ArrayList<Course> courses=new ArrayList<Course>();
		try {
			conn=ConnectionPoolManager.getConnection();
			ps=conn.prepareStatement(sql1);
			ps.setInt(1,sid);
			rs=ps.executeQuery();
			while(rs.next()) {
				Course course =new Course();
				course.setEndTime(rs.getString("endTime"));
				course.setBeginTime(rs.getString("startTime"));
				course.setDay(rs.getString("day"));
				course.setTime(rs.getString("time"));
				course.setCid(rs.getInt("cid"));
				course.setrName(rs.getString("rName"));
				course.setTeacher(rs.getString("tname"));
				courses.add(course);
			}
			ps=conn.prepareStatement(sql2);
			for(int i=0;i<courses.size();i++) {
				ps.setInt(1,courses.get(i).getCid());
				rs=ps.executeQuery();
				while(rs.next()) {
					courses.get(i).setcName(rs.getString("cname"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return courses;
	}

	public ArrayList<Score> queryAllScore(int sid) {
		Connection conn=null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		String sql="select * from SC,Course,Student where Student.sid=SC.sid and SC.cid=Course.cid and SC.sid=?";
		ArrayList<Score> scores=new ArrayList<Score>();
		try {
			conn=ConnectionPoolManager.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,sid);
			rs=ps.executeQuery();
			while(rs.next()) {
				Score score =new Score();
				score.setsName(rs.getString("sname"));
				score.setcName(rs.getString("cname"));
				score.setScore(rs.getFloat("score"));
				scores.add(score);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return scores;
	}

	public Student studentsLogin(int sid,String password) {
			Connection conn = null;
			Statement ps = null;
			ResultSet rs = null;
			Student us = new Student();
			String sql = "select * from student";
			try {
				conn = ConnectionPoolManager.getConnection();
				ps = conn.createStatement();
				rs = ps.executeQuery(sql);
				while (rs.next()) {
					if (sid==rs.getInt("sid")&& 
							password.equals(rs.getString("password"))) {
						us.setSid(sid);
						us.setPassword(password);
						us.setGrade(rs.getString("grade"));
						us.setSage(rs.getInt("sage"));
						us.setSname(rs.getString("sname"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectionPoolManager.free(rs, ps, conn);
			}
			return us;
		}
	public ArrayList<Course> getStudentAllClass(int sid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql ="select cname,course.cid from course,SC " +
				"where course.cid=SC.cid and sc.sid=?";
		conn = ConnectionPoolManager.getConnection();
		ArrayList<Course> courses=new ArrayList<Course>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sid);
			rs=ps.executeQuery();
			while(rs.next()) {
				Course course =new Course();
				course.setcName(rs.getString("cname"));
				course.setCid(rs.getInt("cid"));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return courses;
	}

}
