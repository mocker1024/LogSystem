package com.hl.controller.department;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.DepartmentDao;
import com.hl.entity.Department;

/**
 * Servlet implementation class addDeapartmentController
 */
public class addDeapartmentController extends HttpServlet {
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
		
		addDepartment(request, response);
	}
	private void addDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DepartmentDao departmentDao = new DepartmentDao();
		Department department = new Department();
		int result = -1;
		AppResult aResult = null;
		request.setCharacterEncoding("utf-8");
		//System.out.println("controller"+request.getParameter("uname"));
		
		if(request.getParameter("uname") != null) {
			
		    String uname= request.getParameter("uname");	
		    department.setUname(uname);
		   // System.out.println("if uname !+ null"+uname);
		}
		department.setDname(request.getParameter("dname"));
		
		try {
			result = departmentDao.addDepartment(department);
			if(result == -1 || result == 0) {
				throw new RuntimeException();
			}
			aResult = new AppResult(200, "添加成功", null);
		} catch (Exception e) {
			aResult = new AppResult(201, "数据异常，添加失败", null);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
