package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entitiy.Notice;
import com.newlecture.web.entitiy.NoticeView;

public class NoticeService {
	
	
	public List<NoticeView> getNoticeList(){
		return getNoticeList("title","",1); 
	}
	
	public List<NoticeView> getNoticeList(int page){
		return  getNoticeList("title","",page);  
	}
	
	public List<NoticeView> getNoticeList(String field, String query,int page){
		String sql = "SELECT *  FROM (SELECT ROWNUM RN,N.* FROM (SELECT * FROM NOTICE_VIEW WHERE "+field+ " LIKE ? ORDER BY REGDATE DESC) N )  WHERE RN BETWEEN ? AND ?";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String uid = "newlec";
		String pwd = "nexon0918,";
		
		List<NoticeView>list  = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, uid, pwd);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+ (page-1)*10);
			st.setInt(3, page*10);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writer_id = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				int cmtCount = rs.getInt("CMT");
				NoticeView notice = new NoticeView(id, title, writer_id, regdate, hit, files, cmtCount);
				list.add(notice);

			}

			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int getNoticeCount(){
		return getNoticeCount("title","");
	}
	
	public int getNoticeCount(String field, String query) {
		String sql = "SELECT COUNT(ID) COUNT FROM (SELECT ROWNUM RN,N.* FROM (SELECT * FROM NOTICE WHERE "+field+ " LIKE ? ORDER BY REGDATE DESC) N )  ";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String uid = "newlec";
		String pwd = "nexon0918,";
		int count = 0 ;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, uid, pwd);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");

			ResultSet rs = st.executeQuery();
			
			if(rs.next())
				count= rs.getInt("COUNT");	 
			
			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return count;
	}
	
	public Notice getNotice(int id) {
		String sql = "SELECT * FROM NOTICE WHERE ID= ?";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String uid = "newlec";
		String pwd = "nexon0918,";
		Notice notice = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, uid, pwd);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				int _id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writer_id = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");

				notice = new Notice(_id, title, writer_id, regdate, hit, files, content);

			}

			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return notice;
	}
	
	public Notice getNextNotice(int id) {
		String sql = "SELECT * FROM NOTICE WHERE ID =(N"
				+ "SELECT ID FROM NOTICE"
				+ "WHERE REGDATE> ( SELECT REGDATE FROM NOTICE WHERE ID =? ) AND ROWNUM=1)";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String uid = "newlec";
		String pwd = "nexon0918,";
		Notice notice = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, uid, pwd);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				int _id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writer_id = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");

				notice = new Notice(_id, title, writer_id, regdate, hit, files, content);

			}

			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return notice;
	}
	
	public Notice getPrevNotice(int id) {
		
		String sql = 
				"SELECT ID FROM ("
				+ "SELECT * FROM NOTICE WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE ID =?) ORDER BY REGDATE DESC) WHERE ROWNUM=1";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String uid = "newlec";
		String pwd = "nexon0918,";
		Notice notice = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, uid, pwd);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				int _id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writer_id = rs.getString("WRITER_ID");
				Date regdate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");

				notice = new Notice(_id, title, writer_id, regdate, hit, files, content);

			}

			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return notice;
	}
}
