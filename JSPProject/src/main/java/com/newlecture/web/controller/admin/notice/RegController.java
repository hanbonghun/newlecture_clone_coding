package com.newlecture.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlecture.web.entitiy.Notice;
import com.newlecture.web.service.NoticeService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50 * 5)

@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String isOpen = req.getParameter("open");

		// 파일은 바이너리 데이터 형식
		Collection<Part> parts = req.getParts(); // multi part 중에 name이 file에 해당하는 part의 값
		StringBuilder sb = new StringBuilder();

		for (Part p : parts) {
			if (!p.getName().equals("file") || p.getSize()==0)
				continue;
			Part filePart = p;
			InputStream fis = filePart.getInputStream(); // 바이너리 형태로 저장

			String realPath = req.getServletContext().getRealPath("/upload"); // 절대경로
			File path = new File(realPath);
			if(!path.exists())
				path.mkdirs();
			
			
			
			String fileName = filePart.getSubmittedFileName();
		
			String fileNameWithOutExt  = fileName.split("\\.")[0];
			System.out.println(fileNameWithOutExt);
			String ext = fileName.split("\\.")[1];
			System.out.println(ext);
	
			
			String filePath = realPath + File.separator + fileName;
			File tempFile = new File(filePath);
			
			int count= 1;
			if(tempFile.exists()) {
				if(count==1) fileNameWithOutExt+="_";
				fileNameWithOutExt+=String.valueOf(count);
				filePath = realPath + File.separator + fileNameWithOutExt+"."+ext;
			}
			
			sb.append(fileNameWithOutExt+"."+ext);
			sb.append(",");
			
			System.out.println(filePath);
			FileOutputStream fos = new FileOutputStream(filePath,false);
			

			byte[] buf = new byte[1024]; // 파일 스트림을 1024 바이트씩 읽음
			int size = 0;
			while ((size = fis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}

			fos.close();
			fis.close();
		}
		
		if(sb.length()!=0)
			sb.delete(sb.length()-1,sb.length());
		boolean pub = (isOpen == null) ? false : true;
		Notice notice = new Notice();
		notice.setContent(content);
		notice.setTitle(title);
		notice.setPub(pub);
		notice.setWriter("newlec"); // 현재 사용자
		System.out.println(sb.length());
		notice.setFiles(sb.toString());

		NoticeService service = new NoticeService();
		int result = service.insertNotice(notice);
		resp.sendRedirect("list");

	}
}
