package com.hl.controller.log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.LogDao;
import com.hl.entity.Log;

/**
 * Servlet implementation class addLogController
 */
public class addLogController extends HttpServlet {
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
			addLog(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void addLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AppResult aResult = null;
		request.setCharacterEncoding("utf-8");
		
		Log log= new Log();
		log.setLog_context(request.getParameter("log_context"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		log.setLog_date(df.format(new Date()));
		log.setUname(request.getParameter("uname"));
		//log.setStatus(0);
		LogDao logDao = new LogDao();
		try {
			int result = logDao.addLog(log);
			if(result == -1) {
				throw new RuntimeException();
			}else {
				aResult = new AppResult(200,"日志填写完成",null);
			}
		} catch (Exception e) {
			aResult = new AppResult(201,"数据异常，提交失败",null);
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
		
		
	}

}
