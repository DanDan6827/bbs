package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;

public class delete_okcommend implements Commend {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String b_idx=request.getParameter("b_idx");
		String cPage = request.getParameter("cPage");
		
		//댓글이 없으면 삭제가능 ,댓글이있으면 오류!
		//방법 1
	/*	try {
			int result = DAO.getdelete(b_idx);
			
		} catch (Exception e) {
			return"view/error.jsp";
		}
	*/	//방법2
		
		//원글에 관련된 댓글 먼저 삭제하고 원글 삭제 하자
		try {
			int result=DAO.getC_AllDelete(b_idx);
		} catch (Exception e) {
		}
		
		return "mycontroller?cmd=list&cPage="+cPage;
	}

}
