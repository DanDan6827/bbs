package com.ict.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

//DB처리를위한 클래스
public class DAO {
	//MYbatis에서는 SqlSession 크래스를 이용해서 
	//mapper의 태그들을 실행 시킨다.
	private static SqlSession ss;
	
	//싱글턴 패턴
	private synchronized static SqlSession getSession() {
		if (ss==null) {
			//DBService를 사용
			ss=DBService.getFactory().openSession(false);

			//DBService.getFactory().openSession(); - 자동커밋
			//DBService.getFactory().openSession(true);-자동커밋
			
			//DBService.getFactory().openSession(false);-수동커밋 (커밋명령어를 별도로내려야함)
			//수동커밋은 반드시 insert,update, delete 하면 커밋을 해야 DB에도 적용된다.
		
		}
		return ss;
	}
	//list
	public static List<BVO> getlist() {
		List<BVO> Blist= new ArrayList<BVO>();
		Blist = getSession().selectList("list");
		return Blist;
	}

	public static int getInsert(BVO bvo) {
		int result = 0;
		result = getSession().insert("write", bvo);
		ss.commit();
		return result;
	}
	//조회수 업데이트
	public static int gethitup(String b_idx) {
		int result = 0 ;
		result = getSession().update("hitup", b_idx);
		ss.commit();
		return result;
	}
	//원글 가져오기
	public static BVO getonelist(String b_idx) {
		BVO bvo = new BVO();
		bvo = getSession().selectOne("onelist", b_idx);
		return bvo;
	}
	//댓글 가져오기
	public static List<CVO> getclist(String b_idx) {
		List<CVO> clist = new ArrayList<CVO>();
		clist = getSession().selectList("clist", b_idx);
		return clist;
	}
	//댓글쓰기
	public static int getC_insert(CVO cvo) {
		int result = 0 ;
		result = getSession().insert("c_insert", cvo);
		ss.commit();
		return result;
	}
	public static int getC_delete(CVO cvo) {
		int result = 0 ;
		result = getSession().delete("c_delete", cvo);
		return result;
	}
	//총게시물 수 구하기 
	public static int getCount() {
		int result = 0 ;
		result = getSession().selectOne("count");
		return result;
	}
	//페이지기법 리스트(시작번호,끝번호)
	public static List<BVO> getlist(int begin, int end) {
		List<BVO>list= new ArrayList<BVO>();
		Map<String, Integer> map =new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		list = getSession().selectList("list", map);
		return list;
	}
	//수정
	public static int update(BVO bvo) {
		int result = 0;
		result = getSession().update("update", bvo);
		ss.commit();
		return result;
	}
	//원글과 관련된 댓글 먼저 삭제
	public static int getC_AllDelete(String b_idx)throws Exception {
		int result = 0;
		result = getSession().delete("c_delete_all",b_idx);
		ss.commit();
		return result;
	}
	//원글 삭제
	public static int getdelete(String b_idx) throws Exception {
		int result = 0;
		result = getSession().delete("delete",b_idx);
		ss.commit();
		return result;
	}
	
	
	}
	
	

