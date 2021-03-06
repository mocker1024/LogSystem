package com.hl.controller.news;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.NewsDao;
import com.hl.entity.News;

/**
 * Servlet implementation class findAllNewsController
 */
public class findAllNewsController extends HttpServlet {
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
		findAllNews(request, response);
	}
	private void findAllNews(HttpServletRequest request, HttpServletResponse response) throws IOException {
		NewsDao newsDao = new NewsDao();
		AppResult aResult = null;
		List<News> nlist = null;
		
		try {
			nlist = newsDao.findAllNews();
			if(nlist == null) {
				throw new RuntimeException();
			}
			aResult = new AppResult(200, "查询成功", nlist);
			
		} catch (Exception e) {
			aResult = new AppResult(201, "无公告数据", null);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
		
	}

}
