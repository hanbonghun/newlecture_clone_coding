package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8"); //utf-8�� ���ڵ� �Ͽ� ���� 
		resp.setContentType("text/html; charset=utf-8"); // ���������� utf-8�� �ؼ�
		
		String v_= req.getParameter("v");
		String op = req.getParameter("operator");
		
		ServletContext application = req.getServletContext();
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
	

		
		int v=0;
		if(!v_.equals("")) v=Integer.parseInt(v_);
		
		if(op.equals("=")) {
			//int x = (Integer)application.getAttribute("value");
			//int x = (Integer)session.getAttribute("value");
			
			int x=0;
			String operator="";
			for(Cookie cookie:cookies) {
				if (cookie.getName().equals("value")){
					 x = Integer.parseInt(cookie.getValue());
				}
				if (cookie.getName().equals("op")){
					 operator = (String)cookie.getValue();
				}
			}
			
			int y = v;
			//String operator = (String)application.getAttribute("op");
			//String operator = (String)session.getAttribute("op");

			int result =0;
			
			if(operator.equals("+")) result=x+y;
			else result = x-y;
			
			
			resp.getWriter().printf("result : %d",result);
			
		}
		else {
//			application.setAttribute("value", v);
//			application.setAttribute("op", op);
			
//			session.setAttribute("value", v);
//			session.setAttribute("op", op);
			
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/"); //Ư�� url���� cookie ������ 
			resp.addCookie(opCookie);
			resp.addCookie(valueCookie);
			
			resp.sendRedirect("calc2.html");
		}
		
	}
}