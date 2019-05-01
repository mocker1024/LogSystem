package com.hl.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.UserDao;

/**
 * Servlet implementation class unameExistController
 */
public class unameExistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		unameExist(request, response);
	}
	
	private void unameExist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int result= -1;
	    UserDao userDao = new UserDao();
	    AppResult aResult = null;
	    
	    request.setCharacterEncoding("utf-8");
	    
	    String uname = request.getParameter("uname");
	    System.out.println("controller getparameter:"+uname);
	    try {
	    	System.out.println( "controller try");
			result = userDao.unameExist(uname);
			System.out.print(" controller result:");
			System.out.println(result);//result ==0 用户不存在
			if(result == 1) {
				throw new RuntimeException();
			}else {
				aResult = new AppResult(200, "用户名可用", null);
			}
			
		} catch (Exception e) {
			aResult = new AppResult(201,"用户名已存在",null);
			e.printStackTrace();
		}
	    response.setCharacterEncoding("utf-8");
	    response.setContentType("text/json");
	    response.getWriter().println(JSON.toJSONString(aResult));
	    response.getWriter().flush();
	    
	}

}
