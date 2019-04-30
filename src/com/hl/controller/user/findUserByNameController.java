package com.hl.controller.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.UserDao;
import com.hl.entity.User;

/**
 * Servlet implementation class findUserByNameController
 */
public class findUserByNameController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		findUserByName(request, response);
	}
	private void findUserByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDao userDao = new UserDao();
		User user = new User();
		AppResult aResult = null;
		request.setCharacterEncoding("utf-8");
		String uname = request.getParameter("uname");
		
		try {
			user = userDao.findUserByName(uname);
			if(user == null) {
				throw new RuntimeException();
			}
			aResult = new AppResult(200, "查询成功", user);
		} catch (Exception e) {
			aResult = new AppResult(201, "查询失败", user);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}
	
	

}
