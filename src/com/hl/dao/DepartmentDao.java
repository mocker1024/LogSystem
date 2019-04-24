package com.hl.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hl.entity.Department;
import com.hl.utils.JDBCUtils;

public class DepartmentDao {
//部门管理（管理员权限才可以）
	
	//增加部门
	
	//修改部门信息（部门经理的position也得改）
	
	//删除部门信息（相关员工的department_id也得更改)
	
	//查看所有部门
	public List<Department> findAllDepartment() throws Exception{
		List<Department> dlist = null;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		
		String sql = "select * from department_info";
		
		try {
			con = JDBCUtils.getConnection();
			dlist = runner.query(con, sql, new BeanListHandler<>(Department.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return dlist;
	}
}
