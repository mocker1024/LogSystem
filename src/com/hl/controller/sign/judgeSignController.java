package com.hl.controller.sign;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.SignDao;

/**
 * Servlet implementation class judgeSignController
 */
public class judgeSignController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		judgeSignToday(request, response);
	}
	
	private void judgeSignToday(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		AppResult aResult = null;
		SignDao signDao = new SignDao();
		
		String uname=request.getParameter("uname");
		try {
			int result = signDao.judgeSignToday(uname);
			if(result == 1) {
				throw new RuntimeException();
			}
			aResult = new AppResult(200, "可签到", null);
		} catch (Exception e) {
			aResult = new AppResult(201, "今日已签到，无需再签到", null);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
		
		
	}

}
