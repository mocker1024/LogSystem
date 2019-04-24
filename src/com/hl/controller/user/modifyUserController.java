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
 * Servlet implementation class modifyUserController
 */
public class modifyUserController extends HttpServlet {
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
		AppResult aResult=null;
		request.setCharacterEncoding("utf-8");
		
		String uname=request.getParameter("uname");
		String password = request.getParameter("password");
		int department_id = Integer.parseInt(request.getParameter("department_id"));
		int position= Integer.parseInt(request.getParameter("position"));
		String realname = request.getParameter("realname");
		int status =1;
		int sex = Integer.parseInt(request.getParameter("sex"));
		String tel=request.getParameter("tel");
		int age = Integer.parseInt(request.getParameter("age"));
		UserDao userDao = new UserDao();
		
		User user = new User(uname, password, department_id, position, realname, status,sex,tel,age);
		try {
			int result = userDao.modifyUser(user);
			if(result!=1) {
				throw new RuntimeException();
			}
			aResult = new AppResult(200,"信息修改成功",null);
		} catch (Exception e) {
			aResult = new AppResult(201,"数据异常,信息修改失败",null);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSON(aResult));
		response.getWriter().flush();
	}

}
