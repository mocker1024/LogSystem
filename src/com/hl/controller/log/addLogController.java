package com.hl.controller.log;

import java.io.IOException;
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
		addLog(request,response);
	}
	private void addLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AppResult aResult = null;
		request.setCharacterEncoding("utf-8");
		
		Log log= new Log();
		log.setLog_context(request.getParameter("log_context"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		log.setLog_date(df.format(new Date()));
		log.setUname(request.getParameter("uname"));
		//log.setStatus(0);
		LogDao logDao = new LogDao();
		
		SimpleDateFormat dftest = new SimpleDateFormat("yyyy-MM-dd");//设置测试日期格式
		try {
			//日志不能为空
			if(log.getLog_context()==null || log.getLog_context().length()==0) {
				aResult = new AppResult(202,"日志内容不能为空",null);
				throw new RuntimeException();
			}
			//判断此日是否已写过日志
			Log logtest = new Log();
			logtest = logDao.findLogBydate(request.getParameter("uname"), dftest.format(new Date()));
			if(logtest!=null) {
				aResult = new AppResult(203,"今日日志已提交",null);
				throw new RuntimeException();
			}
			
			int result = logDao.addLog(log);
			if(result == -1) {
				aResult = new AppResult(201,"数据异常，提交失败",null);
				throw new RuntimeException();
			}else {
				aResult = new AppResult(200,"日志填写完成",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
