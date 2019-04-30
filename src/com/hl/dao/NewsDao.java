package com.hl.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hl.entity.News;
import com.hl.utils.JDBCUtils;

public class NewsDao {

	//发布新公告
	public int addNews(News news) throws Exception {
		int result = -1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		
		String sql = "INSERT INTO news_info (title,news_context ,news_date) VALUE (?,?,?)";
		
		try {
			con = JDBCUtils.getConnection();
			result = runner.update(con, sql,news.getTitle(),news.getNews_context(),news.getNews_date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return result;
	}
	//查询所有公告
	public List<News> findAllNews() throws Exception{
		List<News> nlist = null;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		
		String sql = "select * from news_info ORDER BY news_date DESC";
		
		try {
			con = JDBCUtils.getConnection();
			nlist = runner.query(con, sql, new BeanListHandler<>(News.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return nlist;
	}
	//按ID查询公告
	public News findNewsById(int news_id) throws Exception {
		News news = new News();
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		
		String sql = "select * from news_info where news_id = ?";
		
		try {
			con = JDBCUtils.getConnection();
			news = runner.query(con, sql, new BeanHandler<>(News.class),news_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return news;
	}
	//删除公告（管理员权限）
	public int deleteNewsById(int news_id) throws Exception  {
		int result= -1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		String sql = "DELETE FROM news_info WHERE news_id = ?";
		try {
			con = JDBCUtils.getConnection();
			result = runner.update(con, sql,news_id);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		
		
		return result;
	}
}
