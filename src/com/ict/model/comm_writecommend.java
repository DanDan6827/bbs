package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.CVO;
import com.ict.db.DAO;

public class comm_writecommend implements Commend {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		CVO cvo = new CVO();
		cvo.setContent(request.getParameter("content"));
		cvo.setB_idx(request.getParameter("b_idx"));
		int result = DAO.getC_insert(cvo);
		return "mycontroller?cmd=onelist&b_idx="+cvo.getB_idx();
	}

}
