package com.hl.controller.sign;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.SignDao;

/**
 * Servlet implementation class deleteSignByNameController
 */
public class deleteSignByNameController extends HttpServlet {
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
		deleteSignByName(request, response);
	}
	private void deleteSignByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SignDao signDao = new SignDao();
		AppResult aResult = null;
		
		String uname = request.getParameter("uname");
		System.out.println(uname);
		request.setCharacterEncoding("utf-8");
		int result = -1;
		
		try {
			result = signDao.deleteSignByName(uname);
			System.out.println(result);
			if(result == -1 || result == 0) {
				System.out.println("result!=1");
				throw new RuntimeException();	
			}
			aResult = new AppResult(200, "删除成功", null);
		} catch (Exception e) {
			aResult = new AppResult(201,"数据异常,删除失败",null);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
