package com.hl.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.common.ListObject;
import com.hl.dao.UserDao;
import com.hl.entity.User;

/**
 * Servlet implementation class findAllUserController
 */
public class findAllUserController extends HttpServlet {
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
		findAllUser(request, response);
		
	}
	private void findAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDao userDao = new UserDao();
		//AppResult aResult=null;
		ListObject listObject = null;
		List<User> ulist = null;
		
		String uname = request.getParameter("uname");
		User user = new User();
		user.setUname(uname);
		
		try {
			ulist = userDao.findAllUser(user);
			if(ulist != null) {
				listObject = new ListObject(ulist);
				//listObject.setItems(ulist);
				//aResult = new AppResult(200,"注册成功,等待审核",null);
				//System.out.println("listobject");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(listObject));
		response.getWriter().flush();
	}

}
