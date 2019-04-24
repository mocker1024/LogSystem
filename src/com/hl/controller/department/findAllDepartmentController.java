package com.hl.controller.department;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.DepartmentDao;
import com.hl.entity.Department;

/**
 * Servlet implementation class findAllDepartmentController
 */
public class findAllDepartmentController extends HttpServlet {
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
		findAllDepartment(request, response);
	}

	private void findAllDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DepartmentDao departmentDao = new DepartmentDao();
		AppResult aResult = null;
		
		List<Department> dlist = null;
		try {
			dlist = departmentDao.findAllDepartment();
			if(dlist == null || dlist.size()==0) {
				throw new RuntimeException();
			}
			aResult = new AppResult(200, "查询ok", dlist);
		} catch (Exception e) {
			e.printStackTrace();
			aResult = new AppResult(201, "未查到", dlist);
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
		
	}
}
