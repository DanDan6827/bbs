package com.ict.db;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBService {
	//config 파일을 읽어서 DAO에서 필요한 SqlSession 클래스를 
	//만들기 위한 SqlSessionFactiory클래스를 생성하는 역할
	static private SqlSessionFactory factory;
	
	//스태틱 초기화
	static {
		try {
			factory = new SqlSessionFactoryBuilder().build(
					Resources.getResourceAsReader("com/ict/db/config.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
