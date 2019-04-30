package com.hl.controller.news;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.common.ListSign;
import com.hl.dao.NewsDao;
import com.hl.entity.News;

/**
 * Servlet implementation class addNewsController
 */
public class addNewsController extends HttpServlet {
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
		try {
			addNews(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addNews(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//ListSign listsign = null;
		AppResult aResult = null;
		request.setCharacterEncoding("utf-8");
		NewsDao newsDao = new NewsDao();
		News news = new News(); 
		
		String title = request.getParameter("title");
		String news_context = request.getParameter("news_context");
		SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		
		news.setTitle(title);
		news.setNews_context(news_context);
		news.setNews_date(df.format(new Date()));
		try {
			if(title.length()==0 || news_context.length()==0) {
				//aResult = new AppResult(201, "标题与内容不能为空",null);
				throw new RuntimeException();
			}
			int result = newsDao.addNews(news);
			if(result != 1) {
				throw new RuntimeException();
			}else {
				aResult = new AppResult(200,"公告发布成功",null);
			}
			
		} catch (Exception e) {
			aResult = new AppResult(201,"公告发布失败",null);
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
