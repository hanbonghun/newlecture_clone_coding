package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8"); //utf-8�� ���ڵ� �Ͽ� ���� 
		resp.setContentType("text/html; charset=utf-8"); // ���������� utf-8�� �ؼ�
		PrintWriter out = resp.getWriter();
		
		int result =0;
		String[] x = req.getParameterValues("num");
		
		for(String str :x ) {
			result+= Integer.parseInt(str);
		}
		
		out.println(result);
	}
}