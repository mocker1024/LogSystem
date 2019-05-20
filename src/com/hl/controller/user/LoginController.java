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
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		login(request,response);
	}	
	private void login(HttpServletRequest request, HttpServletResponse response)throws  IOException{
		
		System.out.println("登录接口：");
		AppResult aResult=null;
		String uname=request.getParameter("uname");
		System.out.println(uname);
		String password=request.getParameter("password");
		UserDao userDao = new UserDao();
		User user = null;
		try {
			user = userDao.findUserByNameAndPassword(uname, password);
			//user.toString();
			if(user == null) {
				throw new RuntimeException();
			}else {
				if(user.getStatus()==1) {
					user = userDao.findUserByName(uname);
					user.setPassword(null);
					aResult = new AppResult(200,"登录成功！",user);
					request.getSession().setAttribute("user", user);
				}else if(user.getStatus()==0) {
					aResult = new AppResult(203,"登录失败,账号等待审核...",null);
				}		
			}
		} catch (Exception e) {
			aResult = new AppResult(201,"用户名或密码错误！",null);
			e.printStackTrace();
		}
		// json
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}
}
