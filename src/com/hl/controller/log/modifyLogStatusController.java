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
 * Servlet implementation class modifyLogStatusController
 */
public class modifyLogStatusController extends HttpServlet {
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
		try {
			modifyLogStatus(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void modifyLogStatus(HttpServletRequest request, HttpServletResponse response) throws Exception{
		AppResult aResult = null;
		request.setCharacterEncoding("utf-8");
		
		Log log = new Log();
		int id = Integer.parseInt(request.getParameter("log_id"));
		log.setLog_id(id);
		
		LogDao logDao = new LogDao();
		try {
			int result = logDao.modifyLogStatus(log);
			if(result == -1) {
				throw new RuntimeException();
			}else {
				aResult = new AppResult(200,"日志已读",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			aResult = new AppResult(201,"数据异常",null);
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
		
	}

}
