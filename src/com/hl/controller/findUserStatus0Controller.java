package com.hl.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.ListLog;
import com.hl.common.ListObject;
import com.hl.dao.LogDao;
import com.hl.dao.UserDao;
import com.hl.entity.Log;
import com.hl.entity.User;

/**
 * Servlet implementation class findUserStatus0Controller
 */
public class findUserStatus0Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		findUserStatus0(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		findUserStatus0(request,response);
	}
	private void findUserStatus0(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDao userDao = new UserDao();
		ListObject listObject = null;
		List<User> ulist = null;
		
		String uname = request.getParameter("uname");
		User user = new User();
		user.setUname(uname);
		try {
			ulist = userDao.findUserStatus0(user);
			if(ulist != null) {
				listObject = new ListObject(ulist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(listObject));
		response.getWriter().flush();
	}

}
