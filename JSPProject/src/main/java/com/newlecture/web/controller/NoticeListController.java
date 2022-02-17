package com.newlecture.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entitiy.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		String field = req.getParameter("f") == null? "title" : req.getParameter("f");
		String query = req.getParameter("q") == null? "" : req.getParameter("q");
		
		NoticeService service = new NoticeService();
		List<Notice> list = service.getNoticeList(field,query,1);
		req.setAttribute("list", list);
		
		// fowward : detail.jsp도 쓸 수 있게 req,resp 객체를 통해
		req.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(req, resp);
	}
}
