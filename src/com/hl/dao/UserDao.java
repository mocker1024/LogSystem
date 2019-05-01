package com.hl.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hl.common.AppResult;
import com.hl.entity.User;
import com.hl.utils.JDBCUtils;

public class UserDao {
	//登录
	public User findUserByNameAndPassword(String uname,String password)throws Exception{
		User user =null;
		Connection con = null;
		QueryRunner runner= new QueryRunner();
		try {
		// 
			con = JDBCUtils.getConnection();
			String sql = "SELECT department_id,position,realname,status,sex,tel,age FROM user_info "
			              +"WHERE uname=? AND password=?";
			
			user = runner.query(con, sql,new BeanHandler<>(User.class),uname,password);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return user;
	}
	//查询所有人信息(0,基层,1,中层,2高层..)
	public List<User> findAllUser(User user) throws Exception{
		List<User> list= null;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		User user1=null;
		try {
			con = JDBCUtils.getConnection();
			String sql = null;
			String sql1="select * from user_info where uname='"+user.getUname()+"'";
			user1 = runner.query(con, sql1, new BeanHandler<>(User.class));
//			System.out.println(user1.getUname());
//			System.out.println(user1.getDepartment_id());
//			System.out.println(user1.getPosition());
			if(user1.getPosition() == 2) {
				 sql = "select * from user_info where status = 1";
			}else if(user1.getPosition() == 1) {
				int department_id= user1.getDepartment_id();
				 sql = "select uname,department_id,position,realname,STATUS,sex,tel,age from user_info where status=1 and department_id ="+department_id;
			}else {
				 sql="select uname,department_id,position,realname,STATUS,sex,tel,age from user_info where status = 1 and uname="+user1.getUname();
			}
			list = runner.query(con, sql, new BeanListHandler<>(User.class));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return list;
	}
	//查找未被审核的用户信息
	public List<User> findUserStatus0(User user) throws Exception{
		List<User> ulist = null;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		String uname = user.getUname();
		try {
			con = JDBCUtils.getConnection();
			String sql = null;
			String sql1 = "select * from user_info where uname = '"+uname+"'";
			User user1 = new User(runner.query(con, sql1, new BeanHandler<>(User.class)));
			int position = user1.getPosition();
			if(position == 2) {
				sql = "select uname,department_id,position,realname,STATUS,sex,tel,age from user_info where status=0 and position=1";
			}
			if(position == 1) {
				sql = "select uname,department_id,position,realname,STATUS,sex,tel,age from user_info "
			          +"where status=0 and position=0 and department_id ="+user1.getDepartment_id();
			}
//			System.out.println(user1.getUname());
//			System.out.println(user1.getDepartment_id());
//			System.out.println(user1.getPosition());
//			System.out.println(sql);
			ulist = runner.query(con, sql, new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		
		return ulist;
	}
	//根据uname查询用户信息
	public User findUserByName(String uname) throws Exception {
		User user = new User();
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		String sql = "SELECT uname,department_id,POSITION,realname,STATUS,sex,tel,age FROM user_info WHERE uname =?";
		
		try {
			con = JDBCUtils.getConnection();
			user = runner.query(con, sql, new BeanHandler<>(User.class),uname);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return user;
	}
	//ע注册
	public int addUser(User user) throws Exception {
		int result=-1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		try {
			con = JDBCUtils.getConnection();
			String sql="insert into user_info(uname,password,department_id,position,realname) value(?,?,?,?,?);";
			result = runner.update(con, sql,user.getUname(),
											user.getPassword(),
											user.getDepartment_id(),
											user.getPosition(),
											user.getRealname());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				JDBCUtils.closeAll(con, null, null);
		}
		return result;
	}
	//判断用户名是否已存在
	public int unameExist(String uname) throws Exception {
		//int result = -1;
		int result = 0;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		User user = new User();
		
		String sql = "select * from user_info where uname =?";
		
		String sql1 = "SELECT COUNT(uname) FROM user_info WHERE uname=?;";
		try {
			con = JDBCUtils.getConnection();
//			user = runner.query(con, sql, new BeanHandler<>(User.class),uname);
//			System.out.println("user.dao:"+uname);
//			System.out.println(user.getUname());
			System.out.println("dao");
			Long res =(runner.query(con, sql1,new ScalarHandler<Long>(),uname));
			System.out.println("runner query");
			System.out.println(res);
			result = res.intValue();
		
			System.out.println(result);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return ((Integer)result);
	}
	//个人信息修改
	public int modifyUser(User user) throws Exception {
		int result = 1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		User users = null;
		//int result_age=-1,result_pwd=-1,result_depart=-1,result_position=-1,result_realname=-1,result_tel=-1,result_sex=-1;
		
		try {
			con = JDBCUtils.getConnection();
			String sql = "select * from user_info where uname = ?";
			users = runner.query(con, sql, new BeanHandler<>(User.class),user.getUname());
			if(users == null) {
				result = 0;
				return result ;
			}
			System.out.println(user.getAge());
			if (user.getAge() != -1) {
				sql = "UPDATE user_info SET age =? WHERE uname = ?";
//			    result_age =
			    		runner.update(con,sql,user.getAge(),user.getUname());
			}
			System.out.println(user.getPassword());
			if(user.getPassword() != null) {
				sql = "UPDATE user_info SET password =? WHERE uname = ?";
//				result_pwd =
						runner.update(con,sql,user.getPassword(),user.getUname());
			}
			System.out.println(user.getDepartment_id());
			if(user.getDepartment_id() != -1) {
				sql = "UPDATE user_info SET department_id =? WHERE uname = ?";
//				result_depart =
				runner.update(con,sql,user.getDepartment_id(),user.getUname());
			}
			if(user.getPosition()!= -1) {
				sql = "UPDATE user_info SET position =? WHERE uname = ?";
//				result_position =
				runner.update(con,sql,user.getPosition(),user.getUname());
			}
			if(user.getRealname() != null) {
				sql = "UPDATE user_info SET realname =? WHERE uname = ?";
//				result_realname = 
				runner.update(con,sql,user.getRealname(),user.getUname());
			}
			if(user.getTel() != null) {
				sql = "UPDATE user_info SET tel =? WHERE uname = ?";
//				result_tel =
				runner.update(con,sql,user.getTel(),user.getUname());
			}
			if(user.getSex() != -1) {
				sql = "UPDATE user_info SET sex =? WHERE uname = ?";
//				result_sex = 
				runner.update(con,sql,user.getSex(),user.getUname());
			}
			
			
//			String sql ="UPDATE user_info SET PASSWORD = ?,department_id=?,POSITION =?,realname=?,STATUS = ?,sex=?,tel=?,age =? WHERE uname = ?";
//			result = runner.update(con,sql,user.getPassword(),user.getDepartment_id(),user.getPosition(),user.getRealname(),user.getStatus(),user.getSex(),user.getTel(),user.getAge(),user.getUname());
//			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		
		return result;
	}
	//审核通过
	public int modifyStatus(User user) throws Exception {
		int result = -1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		try {
			con = JDBCUtils.getConnection();
			System.out.println("dao try");
			//
			String sql = "UPDATE user_info SET STATUS = 1 WHERE uname=?";
			result = runner.update(con,sql,user.getUname());
			System.out.println("sqlrunner");
//			String sql ="UPDATE user_info SET STATUS = ? WHERE uname=?";
//			result = runner.update(con,sql,user.getStatus(),user.getUname());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return result;
	}
	
	//删除
	public int deleteUser(String uname) throws Exception {
		int result =-1;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		User user = new User();
		try {
			con = JDBCUtils.getConnection();
			String sql1 = "select * from user_info where uname = ?";
			user = runner.query(con, sql1, new BeanHandler<>(User.class),uname);
			if(user.getPosition() == 1) {
				sql1 =  "UPDATE department_info SET uname = NULL WHERE uname=?" ;
				runner.update(con, sql1,user.getUname());
			}	
			String sql = "DELETE FROM user_info WHERE uname = ?";
			result = runner.update(con, sql,uname);
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return result;
	}
	
}
