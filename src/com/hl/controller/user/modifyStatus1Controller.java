package com.hl.controller.user;

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
 * Servlet implementation class modifyStatus1Controller
 */
public class modifyStatus1Controller extends HttpServlet {
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
			modifyStatus(request,response);
		
	}
	private void modifyStatus(HttpServletRequest request, HttpServletResponse response) throws IOException{
		AppResult aResult = null;
		request.setCharacterEncoding("utf-8");
		String uname=request.getParameter("uname");
		//int status= Integer.parseInt(request.getParameter("status"));
		int status = 1;
		UserDao userDao =new UserDao();
		User user=new User();
		user.setUname(uname);
		user.setStatus(status);
		try {
			int result = userDao.modifyStatus(user);
			if(result != 1) {
				throw new RuntimeException();
			}
			aResult = new AppResult(200,"修改成功",null);
		} catch (Exception e) {
			aResult = new AppResult(201, "数据修改失败", null);
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
