package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class Calc extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8"); //utf-8�� ���ڵ� �Ͽ� ���� 
		resp.setContentType("text/html; charset=utf-8"); // ���������� utf-8�� �ؼ�
		PrintWriter out = resp.getWriter();
		
		int x = Integer.parseInt(req.getParameter("x"));
		int y = Integer.parseInt(req.getParameter("y"));
		String op = req.getParameter("operator");
		
		if(op.equals("����")) out.println(x+y);
		else out.println(x-y);
		
	}
}