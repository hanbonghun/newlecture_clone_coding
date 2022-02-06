package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num=0;
		 String num_ = req.getParameter("n");
		 if(num_ != null && !num_.equals(""))
			 num= Integer.parseInt(num_);
		 
		 String result;
		 if(num%2!=0)result="Ȧ��";
		 else result="¦��";
		 
		 String[] names = {"han","park"};
		 req.setAttribute("name",names);
		 
		 req.setAttribute("result", result);
		 Map<String,Object> notice = new HashMap<>();
		 notice.put("id",1);
		 notice.put("title", "���ƿ�");
		 
		 req.setAttribute("notice", notice);
		 //forward  : �۾��� �̾����
		 //redirect : ���ο� ��û
		 RequestDispatcher dispatcher =req.getRequestDispatcher("spag.jsp");
		 dispatcher.forward(req, resp);
		 
	}
}
