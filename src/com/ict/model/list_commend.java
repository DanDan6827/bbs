package com.ict.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.BVO;
import com.ict.db.DAO;

public class list_commend implements Commend {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		/* List<BVO>Blist = DAO.getlist(); */
		Paging paging = new Paging();

		
		//1.전체 게시물 수 구하기 
		int count = DAO.getCount();
		paging.setTotalRecord(count);
		
		//2.전체 게시물의 수를 활용해서 전체페이지 수 구하기
	paging.setTotalPage(paging.getTotalRecord()/paging.getNumPerpage());
	
		if (paging.getTotalRecord()%paging.getNumPerpage()!=0) {
		paging.setTotalPage(paging.getTotalPage()+1);	
		}
		
		//3.현재 페이지를 구하기
		//list.jsp 로부터 cPage라는 파라미터에 현재 페이지값이 들어있다.
		String cPage = request.getParameter("cPage");
		
		//맨처음 Index를 통해서 들어오면 cPage 라는 파라미터가 없다.
		if (cPage==null) {
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		// **4 현재 페이지의 시작번호와 끝번호를 구해서 DB에서 가져오자
		paging.setBegin((paging.getNowPage()-1)+paging.getNumPerpage()+1);
		paging.setEnd(paging.getBegin()-1+paging.getNumPerpage());
		List<BVO>list= DAO.getlist(paging.getBegin(),paging.getEnd());
		
		// **5 시작 블록과 끝블록을 구하자 
		paging.setBeginBlock((int)((paging.getNowPage()-1)/paging.getPagePerBlock())*paging.getPagePerBlock()+1);
		paging.setEndBlock(paging.getBeginBlock()+paging.getPagePerBlock()-1);
		//주의사항 endBlock이 totalPage보다 클수 있다.
		//		   이때는 쓸데 없는 페이지 번호가 생성된다.
		//		   해결 방법은 endBlock 을 totalPage로 변경.
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("cPage", cPage);
		return "view/list.jsp";
	}

}
