package com.hl.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hl.entity.Sign;
import com.hl.utils.JDBCUtils;

public class SignDao {
	//签到
	//@SuppressWarnings("unused")
	public int addSign(Sign sign) throws Exception {
		int result = -1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		
		try {
			con = JDBCUtils.getConnection();
			String sql1 = "select * from calendar_info where uname='"+sign.getUname()+"' and date = '"+sign.getDate()+"'";
			
			Sign re = null;
			//re = runner.query(con, sql1, new BeanHandler<>(Sign.class),sign.getUname(),sign.getDate());
			re = runner.query(con, sql1, new BeanHandler<>(Sign.class));
			//System.out.println(sql1);
			//System.out.println(re.getDate()+":"+re.getUname());
			if(re == null) {
			//	System.out.println("re == null");
				String sql = "INSERT INTO calendar_info (uname,DATE) VALUE(?,?)";
				result = runner.update(con, sql,sign.getUname(),sign.getDate());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return result;
	}
	//查看签到
	public List<Sign> findSignByName(String uname) throws Exception{
		List<Sign> slist = null;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		
		try {
			con = JDBCUtils.getConnection();
			String sql = "SELECT uname,DATE FROM calendar_info WHERE uname=? ORDER BY DATE DESC";
			slist = runner.query(con, sql, new BeanListHandler<>(Sign.class), uname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return slist;
	}
	public int deleteSignByName(String uname) throws Exception {
		int result = -1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		
		try {
			con = JDBCUtils.getConnection();
			String sql = "DELETE FROM calendar_info WHERE uname = ?";
			
			result = runner.update(con, sql, uname);
//			String sql = "DELETE FROM calendar_info WHERE uname = '"+uname+"'";
//			System.out.println(sql);
//			result = runner.update(con, sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		
		
		return result;
	}
}
