package com.hl.controller.sign;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
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
		ListSign listSign = null;
		List<Sign> slist = null;
		
		String uname = request.getParameter("uname");
		
		try {
			slist = signDao.findSignByName(uname);
			System.out.println("slist = signDao.findSignByName(uname)");
			
			if(slist!= null) {
				listSign = new ListSign(slist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(listSign));
		response.getWriter().flush();
	}

}
