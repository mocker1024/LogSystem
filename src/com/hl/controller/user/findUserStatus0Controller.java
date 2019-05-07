package com.hl.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.common.CombRegister;
import com.hl.dao.DepartmentDao;
import com.hl.dao.PositionDao;
import com.hl.dao.UserDao;
import com.hl.entity.Department;
import com.hl.entity.User;

/**
 * Servlet implementation class findUserStatus0Controller
 */
public class findUserStatus0Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		findUserStatus0(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		findUserStatus0(request,response);
	}
	private void findUserStatus0(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDao userDao = new UserDao();
		AppResult aResult = null;
		List<CombRegister> clist = null;
		PositionDao positionDao = new PositionDao();
	    DepartmentDao departDao = new DepartmentDao();
		
	    Department depart = new Department();
	    System.out.println(depart.getUname());
		String uname = request.getParameter("uname");
		User user = new User();
		user.setUname(uname);
		try {
			clist = userDao.findUserStatus0(user);
			if(clist == null) {
				throw new RuntimeException();
			}
			int position = user.getPosition();
			String pname= positionDao.findpnamebyposition(position);
			
			System.out.println(pname);
			
			int department_id = user.getDepartment_id();
			depart = departDao.findDepartmentInfo(department_id);
			
			aResult = new AppResult(200, "已查到数据", clist);
		} catch (Exception e) {
			aResult = new AppResult(201, "无数据", null);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
