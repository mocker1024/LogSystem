package com.hl.controller.sign;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.SignDao;
import com.hl.entity.Sign;

/**
 * Servlet implementation class addSignController
 */
public class addSignController extends HttpServlet {
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
		addSign(request, response);
	}
	
	private void addSign(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SignDao signDao = new SignDao();
		AppResult aResult = null;
		int result=-1;
		Sign sign = new Sign();
		
		request.setCharacterEncoding("utf-8");
		
		String uname = request.getParameter("uname");
		sign.setUname(uname);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		sign.setDate(df.format(new Date()));// new Date()为获取当前系统时间
		System.out.println(df.format(new Date()));
		try {
			result = signDao.addSign(sign);
			if(result == -1) {
				throw new RuntimeException();
			}else {
				aResult = new AppResult(200,"签到成功",null);
			}
		} catch (Exception e) {
			aResult = new AppResult(201,"数据异常，签到失败",null);
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
