package com.newlecture.web.controller.admin.notice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entitiy.NoticeView;
import com.newlecture.web.service.NoticeService;

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet {
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String field = (req.getParameter("f") == null ||req.getParameter("f") == "")? "title" : req.getParameter("f");
		String query = (req.getParameter("q") == null ||req.getParameter("q") == "")? "" : req.getParameter("q");
		String _page = (req.getParameter("p") == null ||req.getParameter("p") == "")? "1" : req.getParameter("p");
		int page = Integer.parseInt(_page);
		
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getNoticeList(field,query,page);
		int count= service.getNoticeCount(field,query);
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		
		// fowward : detail.jsp도 쓸 수 있게 req,resp 객체를 통해
		req.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] openIds =req.getParameterValues("open-id");
		String [] delIds =req.getParameterValues("del-id");
		
		String cmd = req.getParameter("cmd");
		String _ids = req.getParameter("ids");
		String[] ids = _ids.trim().split(" ");
		NoticeService service = new NoticeService();

		
		switch(cmd) {
		case "일괄공개":
			List<String> oids = Arrays.asList(openIds);
			List<String>cids = new ArrayList(Arrays.asList(ids));
			cids.removeAll(oids);
			service.pubNoticeAll(oids,cids);
			break;
			
		//transaction 처리 : 업무 단위를 하나의 명령처럼 실행되게 함
			
			
		case "일괄삭제":
			int[] ids1 = new int[delIds.length];
			for (int i=0; i<delIds.length; i++) ids1[i]= Integer.parseInt(delIds[i]);
			int result = service.deleteNoticeAll(ids1);
			break;
		}
		resp.sendRedirect("list");
	}
}