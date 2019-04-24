package com.hl.controller.log;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hl.dao.CommentDao;
import com.hl.dao.LogDao;
import com.hl.entity.Comment;
import com.hl.entity.Log;

/**
 * Servlet implementation class findLogByIdController
 */
public class findLogByIdController extends HttpServlet {
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
		findLogById(response, request);
		//findCommentByID(response, request);
		
	}
	private void findLogById(HttpServletResponse response,HttpServletRequest request) throws IOException {
		LogDao logDao = new LogDao();
		Log log = null;
//		CommentDao commentDao = new CommentDao();
//		Comment comment = null;
		
		int log_id = Integer.parseInt(request.getParameter("log_id"));
		//System.out.println(log_id);
		
		try {
			log=logDao.findLogById(log_id);
			if(log != null) {
				logDao.modifyLogStatus(log);
				//System.out.println("log!=null");
//				if(log.getComment_id() != 0) {
//					comment = commentDao.findCommentByLogId(log_id);
//					System.out.println(comment.getComment_context());
//				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.getWriter().println(JSON.toJSONString(log));
		response.getWriter().flush();
	}

}
