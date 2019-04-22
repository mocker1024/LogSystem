package com.hl.dao;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;

import com.hl.entity.Sign;
import com.hl.utils.JDBCUtils;

public class SignDao {
	public int addSign(Sign sign) throws Exception {
		int result = -1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		try {
			con = JDBCUtils.getConnection();
			String sql = "INSERT INTO calendar_info (uname,DATE) VALUE(?,?)";
			result = runner.update(con, sql,sign.getUname(),sign.getDate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return result;
	}
}
