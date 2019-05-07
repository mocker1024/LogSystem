package com.hl.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.common.CombUserInfo;
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
		AppResult aResult=null;
		List<CombUserInfo> ulist = null;
		
		System.out.println("");
		
		String uname = request.getParameter("uname");
		User user = new User();
		user.setUname(uname);
		
		try {
			ulist = userDao.findAllUser(user);
			if(ulist == null) {
			    throw new RuntimeException();
			}
			aResult = new AppResult(200, "查询成功", ulist);
		} catch (Exception e) {
			 aResult = new AppResult(201, "查询失败", null);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
