package com.hl.controller.comment;

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
import com.hl.dao.CommentDao;
import com.hl.entity.Comment;

/**
 * Servlet implementation class addCommentController
 */
public class addCommentController extends HttpServlet {
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
			addComment(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AppResult aResult = null;
		request.setCharacterEncoding("utf-8");
		//设置接收的信息格式
		Comment comment = new Comment();
		comment.setUname(request.getParameter("uname"));
		comment.setComment_context(request.getParameter("comment_context"));
		comment.setLog_id(Integer.parseInt(request.getParameter("log_id")));
		SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		comment.setComment_date(df.format(new Date()));
		
		int result=-1;
		CommentDao commentDao = new CommentDao();
		try {
			result = commentDao.addComment(comment);
			if(result == -1) {
				throw new RuntimeException();
			}else {
				aResult = new AppResult(200,"评论完成",null);
			}
		} catch (Exception e) {
			aResult = new AppResult(201,"数据异常，评论失败",null);
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(aResult));
		response.getWriter().flush();
	}

}
