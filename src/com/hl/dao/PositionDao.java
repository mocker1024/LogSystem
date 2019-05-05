package com.hl.dao;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.hl.entity.Position;
import com.hl.utils.JDBCUtils;

public class PositionDao {
	public String findpnamebyposition(int position) throws Exception {
		Position p = new Position();
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		
		String sql = "select * from position_info where position = ?";
		try {
			con = JDBCUtils.getConnection();
			p = runner.query(con, sql, new BeanHandler<>(Position.class),position);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return p.getPname();
	}
}
