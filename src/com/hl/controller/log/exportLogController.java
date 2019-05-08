package com.hl.controller.log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
 * Servlet implementation class exportLogController
 */
public class exportLogController extends HttpServlet {
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
		exportLog(request,response);
	}

	public void exportLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LogDao logDao = new LogDao();
		request.setCharacterEncoding("utf-8");
		AppResult aResult = null;
		List<Log> list = null;
		String uname = request.getParameter("uname");
		String date = request.getParameter("date");
		try {
			list = logDao.findlogbydate(uname, date);
			if(list == null || list.size()==0) {
				throw new RuntimeException();
			}
			aResult = new AppResult(200, "日志导出成功！", list);
		} catch (Exception e) {
			aResult = new AppResult(201, "日志导出异常！", null);
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	      
		
	}
}
