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
 * Servlet implementation class modifyDepartmentController
 */
public class modifyDepartmentController extends HttpServlet {
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
		modifyDepartment(request, response);
	}
	
	private void modifyDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DepartmentDao departmentDao = new DepartmentDao();
		AppResult aResult = null;
		Department department = new Department();
		request.setCharacterEncoding("utf-8");
		String uname = request.getParameter("uname");
		String dname = request.getParameter("dname");
		//获得修改后的信息（ID不可改)
		department.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
		department.setDname(dname);
		department.setUname(uname);
	    int result = -1;
	    try {
	    	result = departmentDao.modifyDepartmentName(department);
	    	System.out.println(department.getDepartment_id()+":"+department.getDname()+":"+department.getUname());
	    	System.out.println(result);
	    	if(result == -1 || result == 0) {
	    		throw new RuntimeException();
	    	}
	    	aResult = new AppResult(200, "修改成功", null);
	    } catch (Exception e) {
	    	aResult = new AppResult(201, "数据异常，修改失败", null);
			e.printStackTrace();
		}
	    response.setCharacterEncoding("utf-8");
	    response.setContentType("text/json");
	    response.getWriter().println(JSON.toJSONString(aResult));
	    response.getWriter().flush();
	}

}
