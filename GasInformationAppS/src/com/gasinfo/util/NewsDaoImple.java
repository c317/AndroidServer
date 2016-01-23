package com.gasinfo.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NewsDaoImple implements NewsDao {

	public ArrayList<News> getNewsList(int moduleID, int pageNum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<News> datas = new ArrayList<News>();
		String sql = null;
		News news = null;
		sql = "select temp.module,temp.pubTime,temp.rownumber,temp.id,temp.title,temp.siteSource "
				+ "from(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Yzrd)"
				+ "temp where temp.rownumber<=?";
		try {
			conn = ConnectionPoolManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pageNum);
			rs = ps.executeQuery();
			while (rs.next()) {
				news = new News();
				news.setOriginSource(rs.getString("siteSource"));
				news.setPubTime(rs.getString("pubTime"));
				news.setTitle(rs.getString("title"));
				news.setId(rs.getInt("id"));
				news.setModule(rs.getInt("module"));
				datas.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return datas;
	}

	public News getNewsContent(int moduleID, int newsID) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = null;
		News news = null;
		switch (moduleID) {
		case 1:
			sql = "select * from Gzdt where id=" + String.valueOf(newsID);
			break;
		case 2:
			sql = "select * from Kcgl where id=" + String.valueOf(newsID);
			break;
		case 3:
			sql = "select * from Zcfg where id=" + String.valueOf(newsID);
			break;
		case 4:
			sql = "select * from Yzrd where id=" + String.valueOf(newsID);
			break;
		case 5:
			sql = "select * from Gjhz where id=" + String.valueOf(newsID);
			break;
		case 6:
			sql = "select * from Kjjz where id=" + String.valueOf(newsID);
			break;
		case 7:
			sql = "select * from Tpxw where id=" + String.valueOf(newsID);
			break;
		case 8:
			sql = "select * from Lddt where id=" + String.valueOf(newsID);
			break;
		case 9:
			sql = "select * from Tjsj where id=" + String.valueOf(newsID);
			break;
		case 10:
			sql = "select * from Ktkf where id=" + String.valueOf(newsID);
			break;
		case 11:
			sql = "select * from Yjxx where id=" + String.valueOf(newsID);
			break;
		default:
			sql = null;
		}
		try {
			conn = ConnectionPoolManager.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				news = new News();
				news.setOriginSource(rs.getString("originSource"));
				news.setPubTime(rs.getString("pubTime"));
				news.setTitle(rs.getString("title"));
				news.setId(rs.getInt("id"));
				news.setModule(rs.getInt("module"));
				news.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, st, conn);
		}
		return news;
	}

	public int createFile(int userID, String fileName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "select * from UserFile where userID=? and fileName=?";
		String sql1 = "insert into UserFile(userID,fileName) values(?,?)";
		conn = ConnectionPoolManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setString(2, fileName);
			rs = ps.executeQuery();
			if (!rs.next()) {
				ps = conn.prepareStatement(sql1);
				ps.setInt(1, userID);
				ps.setString(2, fileName);
				num = ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return num;
	}

	public ArrayList<MyFile> getFileName(int userID) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MyFile> fileList = new ArrayList<MyFile>();
		MyFile file = new MyFile();
		String sql1 = "select fileName,fileID from UserFile where userID=?";
		conn = ConnectionPoolManager.getConnection();
		try {
			ps = conn.prepareStatement(sql1);
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			while (rs.next()) {
				file.setFileID(rs.getInt("fileID"));
				file.setFileName(rs.getString("fileName"));
				fileList.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return fileList;
	}

	public int saveNews(int fileID, int moduleID, int newsID) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "select * from FileNews where fileID=? and moduleID=? and newsID=?";
		String sql1 = "insert into FileNews(fileID,moduleID,newsID) values(?,?,?)";
		conn = ConnectionPoolManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fileID);
			ps.setInt(2, newsID);
			ps.setInt(3, moduleID);
			rs = ps.executeQuery();
			if (!rs.next()) {
				ps = conn.prepareStatement(sql1);
				ps.setInt(1, fileID);
				ps.setInt(2, newsID);
				ps.setInt(3, moduleID);
				num = ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return num;
	}

	public ArrayList<News> getSavedNews(int fileID, String fileName) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement st = null;
		ArrayList<News> newsList = new ArrayList<News>();
		String sql1 = "select * from FileNews where fileID=?";
		String sql = "";
		conn = ConnectionPoolManager.getConnection();
		try {
			ps = conn.prepareStatement(sql1);
			ps.setInt(1, fileID);
			rs = ps.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setId(rs.getInt("newsID"));
				news.setModule(rs.getInt("moduleID"));
				newsList.add(news);
			}
			for (int i = 0; i < newsList.size(); i++) {
				switch (newsList.get(i).getModule()) {
				case 1:
					sql = "select * from Gzdt where id="
							+ String.valueOf(newsList.get(i).getId());
					break;
				case 2:
					sql = "select * from Kcgl where id="
							+ String.valueOf(newsList.get(i).getId());
					break;
				case 3:
					sql = "select * from Zcfg where id="
							+ String.valueOf(newsList.get(i).getId());
					break;
				case 4:
					sql = "select * from Yzrd where id="
							+ String.valueOf(newsList.get(i).getId());
					break;
				case 5:
					sql = "select * from Gjhz where id="
							+ String.valueOf(newsList.get(i).getId());
					break;
				case 6:
					sql = "select * from Kjjz where id="
							+ String.valueOf(newsList.get(i).getId());
					break;
				case 7:
					sql = "select * from Tpxw where id="
							+ String.valueOf(newsList.get(i).getId());
					break;
				case 8:
					sql = "select * from Lddt where id="
							+ String.valueOf(newsList.get(i).getId());
					break;
				case 9:
					sql = "select * from Tjsj where id="
							+ String.valueOf(newsList.get(i).getId());
					break;
				case 10:
					sql = "select * from Ktkf where id="
							+ String.valueOf(newsList.get(i).getId());
					break;
				case 11:
					sql = "select * from Yjxx where id="
							+ String.valueOf(newsList.get(i).getId());
					break;
				default:
					sql = null;
				}
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					newsList.get(i).setTitle(rs.getString("title"));
					newsList.get(i).setPubTime(rs.getString("pubTime"));
					newsList.get(i).setSiteSource(rs.getString("siteSource"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return newsList;
	}

	public ArrayList<PhoneUser> getMember(int groupID) {
		ArrayList<PhoneUser> phoneUsers = new ArrayList<PhoneUser>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = ConnectionPoolManager.getConnection();
		String sql = "select userID,username,account,telephone,job,department from users where exists"
				+ " (select * from UserGroup where groupID=? and userID=users.userID );";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, groupID);
			rs = ps.executeQuery();
			while (rs.next()) {
				PhoneUser phoneUser = new PhoneUser();
				phoneUser.setAccount(rs.getString("account"));
				phoneUser.setDepartement(rs.getString("department"));
				phoneUser.setJob(rs.getString("job"));
				phoneUser.setTelephone(rs.getString("telephone"));
				phoneUser.setUsername(rs.getString("userName"));
				phoneUser.setUserID(rs.getInt("userID"));
				phoneUsers.add(phoneUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return phoneUsers;
	}

	public ArrayList<Group> getGroupName(int pageNum, String account) {
		ArrayList<Group> groups = new ArrayList<Group>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = ConnectionPoolManager.getConnection();
		String sql = "select top(?) groupeName,groupID from Groupe where exists "
				+ "( select * from UserGroup where userID="
				+ "(select userID from users where account=?) "
				+ "and groupID=Groupe.groupID)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pageNum);
			ps.setString(2, account);
			rs = ps.executeQuery();
			while (rs.next()) {
				Group group = new Group();
				group.setGroupID(rs.getInt("groupID"));
				group.setGroupName(rs.getString("groupeName"));
				groups.add(group);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return groups;
	}

	public int addMember(String account, int groupID) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 3;
		int userID = 0;
		Boolean isManager = false;
		String sql1 = "insert into UserGroup values(?,?,?)";
		String sql2 = "select * from userGroup where userID=? and groupID=?";
		String sql3 = "select userID from users where account=?";
		conn = ConnectionPoolManager.getConnection();
		try {
			ps = conn.prepareStatement(sql3);
			ps.setString(1, account);
			rs = ps.executeQuery();
			if (rs.next()) {
				userID = rs.getInt("userID");
			} else {
				// 无此人
				return result;
			}
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, userID);
			ps.setInt(2, groupID);

			rs = ps.executeQuery();
			if (!rs.next()) {
				result = 1;
				ps = conn.prepareStatement(sql1);
				ps.setInt(1, userID);
				ps.setInt(2, groupID);
				ps.setBoolean(3, isManager);
				ps.executeUpdate();
			} else {
				// 重复添加
				result = 4;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return result;
	}

	public int deleteMember(int groupID, int userID) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "delete from UserGroup where userID=? and groupID=?";
		conn = ConnectionPoolManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setInt(2, groupID);
			ps.executeUpdate();
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return result;
	}

	public ArrayList<Item> getNotCheckedItem(int handlerID, int totalItem) {
		ArrayList<Item> items = new ArrayList<Item>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select top(?) isRead,department,requestTime,title,userName from work,Users where handlerID=?"
				+ " and work.status=0 and work.isRead='false' and users.userID=work.requesterID order by requestTime DESC";
		conn = ConnectionPoolManager.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, totalItem);
			ps.setInt(2, handlerID);
			rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setRequestTime(rs.getString("requestTime"));
				item.setDepartment(rs.getString("department"));
				item.setrequestTitle(rs.getString("title"));
				item.setRequester(rs.getString("userName"));
				item.setRead(rs.getBoolean("isRead"));
				item.changeTime();
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return items;
	}

	public ArrayList<Item> getCheckedItem(int handlerID, int totalItem) {
		ArrayList<Item> items = new ArrayList<Item>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = ConnectionPoolManager.getConnection();
		String sql = "select top(?) isRead,department,responseTime,title,userName,affairID from work,Users where handlerID=? and work.status!=0 and work.isRead='false' and users.userID=work.requesterID order by responseTime DESC";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, totalItem);
			ps.setInt(2, handlerID);
			rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setRequestTime(rs.getString("responseTime"));
				item.setDepartment(rs.getString("department"));
				item.setrequestTitle(rs.getString("title"));
				item.setRequester(rs.getString("userName"));
				item.setRead(rs.getBoolean("isRead"));
				item.setItemId(rs.getInt("affairID"));
				item.changeTime();
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return items;
	}

	public ArrayList<Item> getRequestItem(int requesterID, int totalItem) {
		ArrayList<Item> items = new ArrayList<Item>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = ConnectionPoolManager.getConnection();
		String sql = "select top(?) affairID,requestTime,title,userName from work,Users where requesterID=? and work.isRead='false' and "
				+ "users.userID=work.handlerID  and roleID=1 order by requestTime  DESC";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, totalItem);
			ps.setInt(2, requesterID);
			rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setrequestTitle(rs.getString("title"));
				item.setApprover(rs.getString("userName"));
				item.setRequestTime(rs.getString("requestTime"));
				item.setItemId(rs.getInt("affairID"));
				item.changeTime();
				items.add(item);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return items;
	}

	public ArrayList<Item> officeReplyItems(int requesterID, int totalItem) {
		ArrayList<Item> items = new ArrayList<Item>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = ConnectionPoolManager.getConnection();
		String sql = "select top(?) affairID,responseTime,title,userName,status from work,Users "
				+ "where requesterID=? and work.status!=0  and work.isRead='false' and users.userID=work.handlerID  and roleID=1 order by responseTime  DESC";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, totalItem);
			ps.setInt(2, requesterID);
			rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setrequestTitle(rs.getString("title"));
				item.setApprover(rs.getString("userName"));
				item.setResponseTime(rs.getString("responseTime"));
				item.setItemId(rs.getInt("affairID"));
				item.setStatus(rs.getInt("status"));
				item.changeTime();
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return items;
	}

	public int usersLogin(String account, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int status = 3;
		String sqlForCheck = "select account from users where account='"
				+ account + "'";
		conn = ConnectionPoolManager.getConnection();
		try {
			ps = conn.prepareStatement(sqlForCheck);
			rs = ps.executeQuery();
			if (rs.next()) {
				String sql2 = "select account,password from users where account='"
						+ account + "'and password='" + password + "'";
				ps = conn.prepareStatement(sql2);
				rs = ps.executeQuery();
				if (rs.next()) {
					status = 1;
				}
				else{
					status = 2;
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.free(rs, ps, conn);
		}
		return status;
	}
}
