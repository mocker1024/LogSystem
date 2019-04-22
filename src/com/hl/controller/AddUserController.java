package com.hl.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.UserDao;
import com.hl.entity.User;

/**
 * Servlet implementation class AddUserController
 */
public class AddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			addUser(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		AppResult aResult = null;
		request.setCharacterEncoding("utf-8");
		//uname,password,department_id,position,realname
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		
		int department_id = Integer.parseInt(request.getParameter("department_id"));
		int position= Integer.parseInt(request.getParameter("position"));
		String realname = request.getParameter("realname");
		
		UserDao userDao= new UserDao();
		try {
			if(uname !=null || password != null || department_id != -1|| position!= -1||realname != null) {
				User user = new User(uname,password,department_id,position,realname,0);
				int result = userDao.addUser(user);
				if(result!=1) {
					throw new RuntimeException();
				}
				aResult = new AppResult(200,"注册成功,等待审核",null);
				}
		} catch (Exception e) {
			aResult = new AppResult(201, "注册失败", null);
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
