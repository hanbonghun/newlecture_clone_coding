package com.newlecture.web.controller.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entitiy.NoticeView;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/list")
public class ListController extends HttpServlet {
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String field = (req.getParameter("f") == null ||req.getParameter("f") == "")? "title" : req.getParameter("f");
		String query = (req.getParameter("q") == null ||req.getParameter("q") == "")? "" : req.getParameter("q");
		String _page = (req.getParameter("p") == null ||req.getParameter("p") == "")? "1" : req.getParameter("p");
		int page = Integer.parseInt(_page);
		
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getPubNoticeList(field,query,page);
		int count= service.getNoticeCount(field,query);
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		
		// fowward : detail.jsp도 쓸 수 있게 req,resp 객체를 통해
		req.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(req, resp);
	}
}
