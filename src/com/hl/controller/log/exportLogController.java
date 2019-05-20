package com.hl.controller.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.common.ExportLogInfo;
import com.hl.dao.LogDao;
import com.hl.dao.UserDao;
import com.hl.entity.User;

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
		User user = new User();
		UserDao userDao = new UserDao();
		String beginDate = request.getParameter("beginDate");
		
		String endDate= request.getParameter("endDate");
		//分割结束日期，使日期+1
		String [] temp;
		String delimeter = "-";
		temp = endDate.split(delimeter);
		int day = Integer.parseInt(temp[2]);
		day = day+1;
		temp[2] = Integer.toString(day);
		String endDate1=temp[0]+"-"+temp[1]+"-"+temp[2];
		
		
		////
		
		
		//日志导出
		try {
			user = userDao.findUserByName(uname);
			list = logDao.findLogsBySomeDay(uname,beginDate,endDate1);
			if(list == null || list.size() ==0) {
				aResult = new AppResult(201, "不存在日志，日志导出失败", null);
				throw new RuntimeException();
			}
			//存入电脑地址：
		//	String path = "D:/logFile/";
			
			//存入手机地址
//			String path="/storage/emulated/0/logFile/";
			///////
//			
			//存入文件名及其地址
			//String address = path+"log"+beginDate+"~"+endDate+".doc";
//			File file = new File(address);
//			if(!file.exists()) {
//				file.getParentFile().mkdir();
//				file.createNewFile();
//			}
			//////
			OutputStream ooo = response.getOutputStream();
			response.setHeader("Content-Disposition", "attachment;filename=" + "log"+beginDate+"~"+endDate+".doc");
			response.setCharacterEncoding("utf-8");
			
			//给文件添加标题
			String title = "\r\t\t\t"+user.getRealname()+"："+beginDate+"~"+endDate+"的日志 \r\n";
			byte[] arr = title.getBytes();
			ooo.write(arr);
			//文件里的日志内容
			for(ExportLogInfo explog:list) {
				byte[] byteArr = explog.toString().getBytes();
				ooo.write(byteArr);
			}
			ooo.flush();
			
		//	aResult = new AppResult(200, "文件位置："+address, null);
		} catch (Exception e) {
			System.out.println("抛异常");
			e.printStackTrace();
		}
	      
		
	}
}
