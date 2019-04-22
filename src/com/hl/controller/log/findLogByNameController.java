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
 * Servlet implementation class findLogByNameController
 */
public class findLogByNameController extends HttpServlet {
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
		findLogByName(request,response);
	}

	private void findLogByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LogDao logDao = new LogDao();
		ListLog listLog = null;
		List<Log> ulist = null;
		
		String uname = request.getParameter("uname");
		System.out.println(uname);
		
		try {
			ulist = logDao.findLogByUname(uname);
			System.out.println("Dao");
			if(ulist != null) {
				//System.out.println("ulist!=null");
				listLog = new ListLog(ulist);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("ulist==null");
		}
//		int i = 0;
//		for(i = 0;i<3;i++) {
//			ulist.toString();
//		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(listLog));
		response.getWriter().flush();
	}

	private void where(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
