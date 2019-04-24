package com.hl.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hl.entity.Log;
import com.hl.entity.User;
import com.hl.utils.JDBCUtils;

public class LogDao {
	//写日志（添加日志）
	public int addLog(Log log) throws Exception {
		int result = -1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		
		try {
			con = JDBCUtils.getConnection();
			String sql = "INSERT INTO log_info(log_context,log_date,uname) VALUE(?,?,?);";
			result = runner.update(con, sql,log.getLog_context(),log.getLog_date(),log.getUname());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return result;
	}
	//日志状态修改（改为已读）
	public int modifyLogStatus(Log log) throws Exception {
		int result = -1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		
		try {
			con = JDBCUtils.getConnection();
			String sql = "UPDATE log_info SET STATUS = 1 WHERE log_id = ?";
			result = runner.update(con, sql,log.getLog_id());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return result;
	}
	//根据ID查询日志（阅读日志->status=1 && 查评论）
	public Log findLogById(int log_id) throws Exception {
		Log log=null;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		try {
			con = JDBCUtils.getConnection();
			String sql = "select * from log_info where log_id=? order by log_date desc";
			log = runner.query(con, sql, new BeanHandler<>(Log.class),log_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		
		return log;
	}
	//根据用户名查询日志(查询某个人的日志）
	public List<Log> findLogByUname(String uname) throws Exception{
		List<Log> ulist = null;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		System.out.println(uname);
		try {
			con = JDBCUtils.getConnection();
			String sql = "SELECT * FROM log_info WHERE uname = '"+uname+"' order by log_date desc";
			System.out.println(sql);
			ulist = runner.query(con,sql,new BeanListHandler<>(Log.class));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return ulist;
	}
	//查询未被阅读的日志（status=0）->status=1（根据权限查询未被阅读的一群日志）
	public List<Log> findAllLogByStatus(String uname) throws Exception{
		List<Log> list = null;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		User user = null;
		try {
			con=JDBCUtils.getConnection();
			String sql = "select * from user_info where uname='"+uname+"'";
			user = runner.query(con, sql, new BeanHandler<>(User.class));
			if(user.getPosition() == 2) {
				//sql = "select * from log_info where status=0 and position=1";
				sql = "SELECT * FROM log_info WHERE STATUS=0 AND uname IN (SELECT uname FROM user_info WHERE POSITION=1) order by log_date desc";
			}else if(user.getPosition()==1) {
				sql = "SELECT * FROM log_info WHERE STATUS =0 AND uname IN"
			          +"(SELECT uname FROM user_info WHERE POSITION = 0 AND department_id="+user.getDepartment_id()+") order by log_date desc";
				
			}
			//System.out.println(sql);
			list = runner.query(con, sql, new BeanListHandler<>(Log.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
			//System.out.println("数据库关闭 ");
		}
		return list;
	}
}
