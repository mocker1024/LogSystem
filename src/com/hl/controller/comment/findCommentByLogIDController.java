package com.hl.controller.comment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.common.AppResult;
import com.hl.dao.CommentDao;
import com.hl.entity.Comment;

/**
 * Servlet implementation class findCommetByLogIDController
 */
public class findCommentByLogIDController extends HttpServlet {
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
		findCommentByID(response, request);
	}

	private void findCommentByID(HttpServletResponse response,HttpServletRequest request) throws IOException {
		CommentDao commentDao= new CommentDao();
		Comment comment = null;
		AppResult aResult = null;
		
		int log_id = Integer.parseInt(request.getParameter("log_id"));
		
		try {
			comment = commentDao.findCommentByLogId(log_id);
			
			if(comment ==null) {
				throw new RuntimeException();
			}
			aResult = new AppResult(200, "查询成功", comment);
		} catch (Exception e) {
			aResult = new AppResult(201, "数据异常，查询失败", null);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
