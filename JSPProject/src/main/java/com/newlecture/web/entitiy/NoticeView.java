package com.newlecture.web.entitiy;

import java.util.Date;

public class NoticeView extends Notice {
	private int cmtCount; 
	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}

	public NoticeView() {
		// TODO Auto-generated constructor stub
	}

	public NoticeView(int id, String title, String writer_id, Date regdate, String hit, String files, int cmtCount) {
		// TODO Auto-generated constructor stub
		super(id,title,writer_id,regdate,hit,files,"");
		this.cmtCount = cmtCount; 
	}

}
