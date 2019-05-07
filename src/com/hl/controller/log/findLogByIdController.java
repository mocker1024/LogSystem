package com.hl.controller.log;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.LogDao;
import com.hl.entity.Log;

/**
 * Servlet implementation class findLogByIdController
 */
public class findLogByIdController extends HttpServlet {
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
		findLogById(response, request);
		//findCommentByID(response, request);
		
	}
	private void findLogById(HttpServletResponse response,HttpServletRequest request) throws IOException {
		LogDao logDao = new LogDao();
		Log log = null;
		AppResult aResult = null;
		int log_id = Integer.parseInt(request.getParameter("log_id"));
		
		try {
			log=logDao.findLogById(log_id);
			if(log == null) {
				throw new RuntimeException();
			}
			aResult = new AppResult(200, "查询成功", log);
		} catch (Exception e) {
			aResult = new AppResult(201, "数据异常，查询失败", null);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
