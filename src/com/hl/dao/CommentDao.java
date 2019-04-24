package com.hl.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.hl.entity.Comment;
import com.hl.utils.JDBCUtils;

public class CommentDao {

	//写评论
	public int addComment(Comment comment) throws Exception {
		int result = -1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		String sql = "INSERT INTO comment_info (uname,comment_context,comment_date,log_id) VALUE (?,?,?,?)";
		//String sql2 = "UPDATE log_info SET comment_id = ? WHERE log_id=?";
		try {
			con = JDBCUtils.getConnection();
			
			result = runner.update(con, sql,comment.getUname(),comment.getComment_context(),comment.getComment_date(),comment.getLog_id());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return result;
	}
	//查评论（根据日志ID查询）
	public Comment findCommentByLogId(int log_id) throws Exception {
		Comment comment = new Comment();
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		
		//String sql = "SELECT * FROM comment_info WHERE comment_id = ?";
		String sql = "SELECT * FROM comment_info WHERE log_id = '"+log_id+"'";
		try {
			con = JDBCUtils.getConnection();
			//comment = runner.query(con, sql, new BeanHandler<>(Comment.class),log_id);
			comment = runner.query(con, sql, new BeanHandler<>(Comment.class));
			} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		
		return comment;
	}
}
