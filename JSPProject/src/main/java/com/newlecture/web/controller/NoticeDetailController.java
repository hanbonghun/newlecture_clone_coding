package com.newlecture.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entitiy.Notice;
import com.newlecture.web.service.NoticeService;


@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String _id = req.getParameter("id");
		int id = Integer.parseInt(_id);
		Notice notice =null;
		NoticeService service = new NoticeService();
		notice = service.getNotice(id);
		req.setAttribute("n", notice);
		//fowward : detail.jsp도 쓸 수 있게 req,resp 객체를 통해 
		req.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(req, resp);
		
	}
}	
