package com.ict.model;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.ict.db.BVO;
import com.ict.db.DAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class update_okcommend implements Commend {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		BVO bvo = new BVO();
		
		try {
			String path = request.getServletContext().getRealPath("/upload");
			MultipartRequest mr = new MultipartRequest(request, path, 100*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
			String cPage = mr.getParameter("cPage");
			String f_name = mr.getParameter("f_name");
			bvo.setB_idx(mr.getParameter("b_idx"));
			bvo.setSubject(mr.getParameter("subject"));
			bvo.setWriter(mr.getParameter("writer"));
			bvo.setContent(mr.getParameter("content"));
			
			if (mr.getFile("file_name")==null) {
				if (f_name==null) {
					bvo.setFile_name("");
				}else {
					bvo.setFile_name(f_name);
				}
			}else {
				bvo.setFile_name(mr.getFilesystemName("file_name"));
			}
			
			int result = DAO.update(bvo);
			
			if (result>0) {
				return"mycontroller?cmd=onelist&cPage="+cPage+"&b_idx="+bvo.getB_idx();
			}
			
		} catch (Exception e) {
		}
		return null;
	}
	

}
