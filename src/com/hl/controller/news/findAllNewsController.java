package com.hl.controller.news;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.ListLog;
import com.hl.common.ListNews;
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
		ListNews listNews = null;
		List<News> nlist = null;
		
		try {
			nlist = newsDao.findAllNews();
			if(nlist != null) {
				//System.out.println("ulist!=null");
				listNews = new ListNews(nlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(listNews));
		response.getWriter().flush();
		
	}

}
