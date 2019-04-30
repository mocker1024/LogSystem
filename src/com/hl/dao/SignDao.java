package com.hl.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			String sql = "SELECT * FROM calendar_info WHERE uname=? ORDER BY DATE DESC";
			slist = runner.query(con, sql, new BeanListHandler<>(Sign.class), uname);
		} catch (Exception e) {
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
	//判断今日是否签到
	public int judgeSignToday(String uname) {
		int result = -1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String sql = "select * from calendar_info where uname='"+uname+"' and date = '"+df.format(new Date())+"'";
		
		Sign re = null;
		
		try {
			con = JDBCUtils.getConnection();
			re=runner.query(con, sql, new BeanHandler<>(Sign.class));
			if(re==null) {
				result = 0;//为0则可以签到
			}else {
				result =1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return result;
	}
}
