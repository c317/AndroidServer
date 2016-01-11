package com.manageTeaching.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class ManagerDaoImple implements ManagerDao {
	public ArrayList<Course> getAllCourse() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from course ";
		conn = ConnectionPoolManager.getConnection();
		ArrayList<Course> courses = new ArrayList<Course>();
//		LinkedList<Course> coursese=null;
//		Iterator iter=coursese.iterator();
		HashMap<String,Course> map=null;
		try {
			conn = ConnectionPoolManager.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Course course = new Course();
				course.setCName(rs.getString("cname"));
				course.setBeginTime(rs.getString("beginTime"));
				course.setEndTime(rs.getString("endTime"));
				course.setGrade(rs.getString("grade"));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, st, conn);
		}
		return courses;
	}

	public void insertCourse(String cName, String beginTime, String endTime,
			String grade) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into Course values(?,?,?,?)";
		try {
			conn = ConnectionPoolManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cName);
			ps.setString(2, beginTime);
			ps.setString(3, endTime);
			ps.setString(4, grade);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
	}

	public void deleteCourse(String cName) {

	}

	// 功能暂时未实现
	public void updateCourse(String cName, String beginTime, String endTime,
			String grade) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "update course Course values(?,?,?,?)";
		try {
			conn = ConnectionPoolManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cName);
			ps.setString(2, beginTime);
			ps.setString(3, endTime);
			ps.setString(4, grade);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
	}

	public boolean manageCourse(String cName, String rName, String day,
			String startTime, String endTime, String classTime, String tName) {
		boolean type = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql1 = "select cid from course where cname=?";
		String sql3 = "insert into ManageClass (cid,time,day,startTime,endTime,rname,tname) values(?,?,?,?,?,?,?)";
		conn = ConnectionPoolManager.getConnection();
		try {
			ps = conn.prepareStatement(sql1);
			ps.setString(1, cName);
			rs = ps.executeQuery();
			if (rs.next()) {
				type = true;
				int cid = rs.getInt("cid");
				ps = conn.prepareStatement(sql3);
				ps.setInt(1, cid);
				ps.setString(2, classTime);
				ps.setString(3, day);
				ps.setString(4, startTime);
				ps.setString(5, endTime);
				ps.setString(6, rName);
				ps.setString(7, tName);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return type;
	}

	public ArrayList<Score> queryCourseScore(String cName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Score> scores = new ArrayList<Score>();
		String sql = "select sname,score,student.grade,course.cname from "
				+ "Student,SC,Course where sc.sid=Student.sid and "
				+ "Course.cid=sc.cid and sc.cid in (select cid from course "
				+ "where cname=?)";
		try {
			conn = ConnectionPoolManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cName);
			rs = ps.executeQuery();
			while (rs.next()) {
				Score score = new Score();
				score.setcName(rs.getString("cname"));
				score.setScore(rs.getFloat("score"));
				score.setsName(rs.getString("sname"));
				scores.add(score);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return scores;
	}

	public ArrayList<Student> queryAllStudents(String cName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Student> students = null;
		String sql = "select * from Student,SC,Course where SC.cid=Course.cid and sc.sid=Student.sid and sc.cid in(select cid from Course where Course.cName=?)";
		try {
			conn = ConnectionPoolManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cName);
			rs = ps.executeQuery();
			if (rs.next()) {
				students = new ArrayList<Student>();
				Student student = new Student();
				student.setSname(rs.getString("sname"));
				student.setSage(rs.getInt("sage"));
				student.setGrade(rs.getString("grade"));
				student.setSid(rs.getInt("sid"));
				students.add(student);
				while (rs.next()) {
					Student student1 = new Student();
					student1.setSname(rs.getString("sname"));
					student1.setSage(rs.getInt("sage"));
					student1.setGrade(rs.getString("grade"));
					student1.setSid(rs.getInt("sid"));
					students.add(student1);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return students;
	}

	public ArrayList<Course> getUnManagedClass() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from course where course.cid not "
				+ "in(select distinct cid from ManageClass)";
		ArrayList<Course> courses = null;
		try {
			conn = ConnectionPoolManager.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				courses = new ArrayList<Course>();
				Course course = new Course();
				course.setCid(rs.getInt("cid"));
				course.setcName(rs.getString("cname"));
				course.setGrade(rs.getString("grade"));
				courses.add(course);
				while (rs.next()) {
					Course course1 = new Course();
					course1.setCid(rs.getInt("cid"));
					course1.setcName(rs.getString("cname"));
					course1.setGrade(rs.getString("grade"));
					courses.add(course1);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, st, conn);
		}
		return courses;
	}

	public ArrayList<Score> getStudentScore(String cName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Score> scores = new ArrayList<Score>();
		String sql = "select * from Student,SC where SC.cid "
				+ "in(select cid from Course where course.cname=?) and Student.sid=sc.sid";
		try {
			conn = ConnectionPoolManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cName);
			rs = ps.executeQuery();
			while (rs.next()) {
				Score score = new Score();
				score.setsName(rs.getString("sname"));
				score.setScore(rs.getFloat("score"));
				score.setSid(rs.getInt("sid"));
				scores.add(score);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return scores;
	}

	public void setScore(ArrayList<Score> scores,int cid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="update SC set score=? where sid=? and cid=? ";
		try {
			conn = ConnectionPoolManager.getConnection();
			ps=conn.prepareStatement(sql);
			for (int i = 0; i < scores.size(); i++) {
				ps.setFloat(1, scores.get(i).getScore());
				ps.setInt(2, scores.get(i).getSid());
				ps.setInt(3, cid);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
	}

	public int getCid(String cName) {
		int cid=0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="select cid from course where cname=?";
		conn = ConnectionPoolManager.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, cName);
			rs=ps.executeQuery();
			while(rs.next()) {
				cid=rs.getInt("cid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return cid;
	}

}
