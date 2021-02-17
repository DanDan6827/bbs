package com.ict.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.model.Commend;
import com.ict.model.comm_deletecommend;
import com.ict.model.comm_writecommend;
import com.ict.model.delete_commend;
import com.ict.model.delete_okcommend;
import com.ict.model.list_commend;
import com.ict.model.onelist_commend;
import com.ict.model.update_commend;
import com.ict.model.update_okcommend;
import com.ict.model.write_commend;
import com.ict.model.write_okcommend;


@WebServlet("/mycontroller")
public class mycontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String cmd = request.getParameter("cmd");
		Commend comm = null;
		
		switch (cmd) {
		case "list":comm= new list_commend();break;
		case "write":comm= new write_commend();break;
		case "write_ok":comm= new write_okcommend();break;
		case "onelist":comm= new onelist_commend();break;
		case "update":comm= new update_commend();break;
		case "update_ok":comm= new update_okcommend();break;
		case "delete":comm= new delete_commend();break;
		case "delete_ok":comm= new delete_okcommend();break;
		case "comm_write":comm= new comm_writecommend();break;
		case "comm_delete":comm= new comm_deletecommend();break;
		}
		String path = comm.exec(request, response);
		request.getRequestDispatcher(path).forward(request, response);
	}

}
