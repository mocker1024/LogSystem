package com.hl.controller.news;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.NewsDao;

/**
 * Servlet implementation class deleteNewsByIdController
 */
public class deleteNewsByIdController extends HttpServlet {
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
		deleteNewsById(request, response);
	}
	
	private void deleteNewsById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int news_id = Integer.parseInt(request.getParameter("news_id"));
		NewsDao newsDao = new NewsDao();
		AppResult aResult = null;
		int result = -1;
		try {
			result = newsDao.deleteNewsById(news_id);
			if(result == 0 || result ==-1) {
				throw new RuntimeException();
			}
			//System.out.println(result);
			aResult = new AppResult(200, "公告删除成功",null );
		} catch (Exception e) {
			aResult = new AppResult(201, "公告删除失败",null );
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}
}
