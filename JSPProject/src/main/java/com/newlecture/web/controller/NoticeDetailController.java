package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entitiy.Notice;


@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String sql = "SELECT * FROM NOTICE WHERE ID = ?";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String uid = "newlec";
		String pwd = "nexon0918,";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, uid, pwd);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			rs.next();
			
		
			String title =rs.getString("TITLE");
			String writer_id =rs.getString("WRITER_ID");
			Date regdate =rs.getDate("REGDATE");
			String hit =rs.getString("HIT") ;
			String files =rs.getString("FILES") ;
			String content =rs.getString("CONTENT") ;
			
			Notice notice = new Notice(id,title,writer_id,regdate,hit,files,content);
			
			req.setAttribute("n", notice);
			
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
		
		//fowward : detail.jsp도 쓸 수 있게 req,resp 객체를 통해 
		req.getRequestDispatcher("/notice/detail.jsp").forward(req, resp);
		
	}
}	
