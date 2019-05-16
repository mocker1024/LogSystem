package com.hl.controller.log;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.common.ExportLogInfo;
import com.hl.dao.LogDao;

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
		List<ExportLogInfo> list = null;
		String uname = request.getParameter("uname");
		String beginDate = request.getParameter("beginDate");
		String endDate= request.getParameter("endDate");
		try {
			
			list = logDao.findLogsBySomeDay(uname,beginDate,endDate);
			if(list == null || list.size() ==0) {
				aResult = new AppResult(201, "不存在日志，日志导出失败", null);
				throw new RuntimeException();
			}
			
			String address = "D:/test/log"+beginDate+"~"+endDate+".doc";
			FileWriter fw = new FileWriter(address);
			//存入手机地址
			/*
			String mobileAddress = "/storage/emulated/0/logSystem/log"+beginDate+"~"+endDate+".doc";
			FileWriter fw = new FileWriter(mobileAddress);
			*/
			for(ExportLogInfo exlog:list) {
				fw.write(exlog.toString());
			}
			//fw.write(list.toString());
			fw.close();
			aResult = new AppResult(200, "文件位置："+address, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	      
		
	}
}
