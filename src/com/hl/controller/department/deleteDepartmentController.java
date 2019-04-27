package com.hl.controller.department;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.DepartmentDao;

/**
 * Servlet implementation class deleteDepartmentController
 */
public class deleteDepartmentController extends HttpServlet {
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
		deleteDepartment(request, response);
	}
	
	private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DepartmentDao departmentDao = new DepartmentDao();
		AppResult aResult = null;
		request.setCharacterEncoding("utf-8");
		int result = -1;
		int department_id = Integer.parseInt(request.getParameter("department_id"));
		
		try {
			result = departmentDao.deleteDepartmentById(department_id);
			if(result == -1 || result ==0) {
				throw new RuntimeException();
			}
			aResult = new AppResult(200, "删除成功", null);
		} catch (Exception e) {
			aResult = new AppResult(201, "数据异常，删除失败", null);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().print(JSON.toJSONString(aResult));
		response.getWriter().flush();
		
		
		
		
	}

}
