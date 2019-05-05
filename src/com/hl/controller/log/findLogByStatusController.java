package com.hl.controller.log;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.LogDao;
import com.hl.entity.Log;

/**
 * Servlet implementation class findLogByStatusController
 */
public class findLogByStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		findLogBystatus(request, response);
	}
	
	private void findLogBystatus(HttpServletRequest request,HttpServletResponse response) throws IOException {
		LogDao logDao = new LogDao();
		AppResult aResult = null;
		List<Log> list = null;
		
		String uname = request.getParameter("uname");
		
		try {
			list = logDao.findAllLogByStatus(uname);
			if(list == null || list.size()==0) {
				throw new RuntimeException();
			}
			aResult = new AppResult(200, "查询到未被阅读的日志", list);
		} catch (Exception e) {
			aResult = new AppResult(201, "无未被阅读的日志", null);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
