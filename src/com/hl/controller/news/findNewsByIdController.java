package com.hl.controller.news;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.NewsDao;
import com.hl.entity.News;

/**
 * Servlet implementation class findNewsByIdController
 */
public class findNewsByIdController extends HttpServlet {
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
		findNewsById(request, response);
	}
	private void findNewsById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int news_id = Integer.parseInt(request.getParameter("news_id"));
		AppResult aResult = null;
		NewsDao newsDao = new NewsDao();
		News news = new News();
		
		try {
			news = newsDao.findNewsById(news_id);
			if(news == null) {
				throw new RuntimeException();
			}
			aResult = new AppResult(200, "查询成功", news);
		} catch (Exception e) {
			aResult = new AppResult(201, "查询失败", null);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
		
	}

}
