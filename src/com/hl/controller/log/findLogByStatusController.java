package com.hl.controller.log;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.ListLog;
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
		ListLog listLog = null;
		List<Log> list = null;
		
		String uname = request.getParameter("uname");
		//System.out.println(uname);
		
		try {
			list = logDao.findAllLogByStatus(uname);
			//System.out.println("Dao");
			if(list != null) {
				//System.out.println("ulist!=null");
				listLog = new ListLog(list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("ulist==null");
		}
//		int i = 0;
//		for(i = 0;i<3;i++) {
//			list.toString();
//		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(listLog));
		response.getWriter().flush();
	}

}
