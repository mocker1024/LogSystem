package com.hl.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hl.entity.Department;
import com.hl.entity.User;
import com.hl.utils.JDBCUtils;

public class DepartmentDao {
    //部门管理（管理员权限才可以）
	
	//增加部门
	public int addDepartment(Department department) throws Exception {
		int result = -1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		User user = new User();
		String sql = "insert into department_info (dname) value (?)";
		//如果主管也有，则sql语句变化：
		if(department.getUname() != null) {
		sql = "INSERT INTO department_info (dname,uname) VALUE (?,?)";
		}
		
		try {
			con = JDBCUtils.getConnection();
			if(department.getUname() != null) {
				//department_info 表增加部门信息
				result = runner.update(con, sql,department.getDname(),department.getUname());
				//user 获得该部门主管的信息
				String sql2 = "select * from user_info where uname = ?";
				user = runner.query(con, sql2, new BeanHandler<>(User.class),department.getUname());
				//获得已增加的部门的部门信息
				sql2 = "select * from department_info where dname = ?";
				Department department1 = new Department();
				department1= runner.query(con, sql2, new BeanHandler<>(Department.class),department.getDname());
				//更改部门主管的用户信息
				if(user.getPosition()==0 || user.getDepartment_id() != department1.getDepartment_id()) {
					String sql3 = "update user_info set position = 1,department_id = ? where uname = ?";
					runner.update(con, sql3,department1.getDepartment_id(),user.getUname());
					
				}
				
			}else {
				result = runner.update(con, sql,department.getDname());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return result ;
	}
	//修改部门信息（部门经理的position也得改）
	public int modifyDepartmentName(Department department) {
		int result = -1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		User user = new User();
		System.out.println("dao 进入");
		String sql = "update department_info set uname = ?,dname = ? where department_id = ?";
		String sql1 = "SELECT * FROM user_info WHERE department_id = ? AND POSITION = 1";
		try {
			con = JDBCUtils.getConnection();
			//获得修改前部门主管的用户信息
			user = runner.query(con, sql1, new BeanHandler<>(User.class),department.getDepartment_id());
			//修改当前部门主管信息
			result = runner.update(con, sql,department.getUname(),department.getDname(),department.getDepartment_id());
		    System.out.println("dao"+result);
			if(result == 1) {
		    	//修改前主管的用户信息
				System.out.println("if result ==1");
		    	sql = "update user_info set position = 0 where uname = ?";
		    	System.out.println(sql);
		    	runner.update(con, sql, user.getUname());
		    	System.out.println(sql);
		    	//修改现主管的用户信息
		    	sql = "update user_info set position = 1 where uname = ?";
		    	
		    	runner.update(con, sql, department.getUname());
		    	System.out.println(sql);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//删除部门信息（相关员工的department_id也得更改)
	public int deleteDepartmentById(int department_id) throws Exception {
		int result = -1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		String sql = "DELETE FROM department_info WHERE department_id = ?";
		try {
			con = JDBCUtils.getConnection();
			result = runner.update(con, sql,department_id);
			if(result == 1) {
				sql = "UPDATE user_info SET department_id = 1,STATUS =0,POSITION = 0 WHERE department_id = ?";
				runner.update(con, sql,department_id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return result;
	}
	//查看所有部门
	public List<Department> findAllDepartment() throws Exception{
		List<Department> dlist = null;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		
		String sql = "SELECT * FROM department_info WHERE department_id  NOT LIKE ('1') AND department_id  NOT LIKE ('1024')";
		
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
	//根据id查询部门
	public Department findDepartmentInfo(int department_id) throws Exception {
		Department department = new Department();
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		String sql = "select * from department_info where department_id = ?";
		try {
			con = JDBCUtils.getConnection();
			department = runner.query(con, sql, new BeanHandler<>(Department.class),department_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return department;
	}
}
