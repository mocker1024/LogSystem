package com.hl.controller.sign;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.common.ListSign;
import com.hl.dao.SignDao;
import com.hl.entity.Sign;

/**
 * Servlet implementation class findSignByNameController
 */
public class findSignByNameController extends HttpServlet {
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
		// TODO Auto-generated method stub
		findSignByName(request, response);
	}
	private void findSignByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SignDao signDao = new SignDao();
		AppResult aResult = null;
		List<Sign> slist = null;
		
		String uname = request.getParameter("uname");
		
		try {
			slist = signDao.findSignByName(uname);
			if(slist.size() == 0) {
				throw new RuntimeException();
			}
			int i = slist.size();
			//System.out.println(i);
			//System.out.println(slist.get(i));
			//System.out.println(uname);
			aResult = new AppResult(200, "签到查询成功", slist);
		} catch (Exception e) {
			aResult = new AppResult(201, "无签到数据", null);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
