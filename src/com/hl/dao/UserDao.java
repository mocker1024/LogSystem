package com.hl.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hl.common.CombRegister;
import com.hl.common.CombUserInfo;
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
	public List<CombUserInfo> findAllUser(User user) throws Exception{
		List<CombUserInfo> list= null;
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		User user1=null;
		try {
			con = JDBCUtils.getConnection();
			String sql = null;
			String sql1="select * from user_info where uname='"+user.getUname()+"'";
			user1 = runner.query(con, sql1, new BeanHandler<>(User.class));
			if(user1.getPosition() == 2) {
				// 
				sql = " SELECT user_info.`department_id`,user_info.`status`,realname,user_info.uname,pname,dname,tel,CASE sex WHEN '0' THEN '男'" + 
						" WHEN '1' THEN '女' END AS sex FROM user_info,position_info,department_info" + 
						" WHERE user_info.`department_id` = department_info.`department_id` AND STATUS =1 AND position_info.`position`=user_info.`position` " + 
						" ORDER BY user_info.`position` DESC,user_info.`department_id` ";
			}else if(user1.getPosition() == 1) {
				int department_id= user1.getDepartment_id();
				sql = "SELECT realname,user_info.uname,pname,dname,tel,CASE sex WHEN '0' THEN '男'" + 
					  " WHEN '1' THEN '女' END AS sex "+
					  "FROM user_info,position_info,department_info " + 
					  " WHERE user_info.position=0 AND position_info.`position`=0 AND user_info.`department_id` = department_info.`department_id` AND STATUS =1 AND user_info.`department_id`=" +department_id ; 
				//sql = "select uname,department_id,position,realname,STATUS,sex,tel,age from user_info where status=1 and department_id ="+department_id;
			}else {
				 sql="SELECT realname,user_info.uname,pname,dname,tel,CASE sex WHEN '0' THEN '男' " + 
				 	 " WHEN '1' THEN '女' END AS sex FROM user_info,position_info,department_info " + 
				 	 " WHERE  user_info.`department_id` = department_info.`department_id` AND STATUS =1  AND position_info.`position`=user_info.`position` AND user_info.`uname`=?" +user1.getUname();
			}
			list = runner.query(con, sql, new BeanListHandler<>(CombUserInfo.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return list;
	}
	//查找未被审核的用户信息
	public List<CombRegister> findUserStatus0(User user) throws Exception{
		List<CombRegister> clist = null;
		User user1 =new User();
		Connection con = null;
		QueryRunner runner = new QueryRunner();
		String uname = user.getUname();
		try {
			con = JDBCUtils.getConnection();
			String sql = null;
			String sql1 = "select uname,department_id,POSITION,realname,STATUS,sex,tel,age from user_info where uname = '"+uname+"'";
			
			user1 = runner.query(con, sql1, new BeanHandler<>(User.class));
			
			int position = user1.getPosition();
			if(position == 2) {
				sql = "SELECT realname,user_info.uname,pname,dname,user_info.`department_id`,STATUS FROM user_info,position_info,department_info " + 
					" WHERE STATUS=0 AND user_info.position=1 AND position_info.`position`=1 AND user_info.`department_id` = department_info.`department_id`";
			}
			if(position == 1) {
				sql = "SELECT realname,user_info.uname,pname,dname FROM user_info,position_info,department_info "
					 +"WHERE user_info.`position` = position_info.`position` AND STATUS=0 AND user_info.position=0 AND user_info.department_id = "+user1.getDepartment_id()+" AND department_info.department_id = "+user1.getDepartment_id();
				System.out.println(sql);
			}
			clist = runner.query(con, sql, new BeanListHandler<>(CombRegister.class));
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		
		return clist;
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
		String sql1 = "SELECT COUNT(uname) FROM user_info WHERE uname=?;";
		try {
			con = JDBCUtils.getConnection();
			System.out.println("dao");
			Long res =(runner.query(con, sql1,new ScalarHandler<Long>(),uname));
			result = res.intValue();
		
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
			    runner.update(con,sql,user.getAge(),user.getUname());
			}
			System.out.println(user.getPassword());
			if(user.getPassword() != null) {
				sql = "UPDATE user_info SET password =? WHERE uname = ?";
				runner.update(con,sql,user.getPassword(),user.getUname());
			}
			System.out.println(user.getDepartment_id());
			if(user.getDepartment_id() != -1) {
				sql = "UPDATE user_info SET department_id =? WHERE uname = ?";
				runner.update(con,sql,user.getDepartment_id(),user.getUname());
			}
			if(user.getPosition()!= -1) {
				sql = "UPDATE user_info SET position =? WHERE uname = ?";
				runner.update(con,sql,user.getPosition(),user.getUname());
			}
			if(user.getRealname() != null) {
				sql = "UPDATE user_info SET realname =? WHERE uname = ?";
				runner.update(con,sql,user.getRealname(),user.getUname());
			}
			if(user.getTel() != null) {
				sql = "UPDATE user_info SET tel =? WHERE uname = ?";
				runner.update(con,sql,user.getTel(),user.getUname());
			}
			if(user.getSex() != -1) {
				sql = "UPDATE user_info SET sex =? WHERE uname = ?";
				runner.update(con,sql,user.getSex(),user.getUname());
			}
		} catch (Exception e) {
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
		System.out.println("deleteUser dao in");
		try {
			con = JDBCUtils.getConnection();
			String sql1 = "select * from user_info where uname = ?";
			user = runner.query(con, sql1, new BeanHandler<>(User.class),uname);
			System.out.println(user.getUname()+":"+user.getAge()+":"+user.getPosition()+":"+user.getStatus());
			if(user.getPosition() == 1) {
				sql1 =  "UPDATE department_info SET uname = NULL WHERE uname=?" ;
				result = runner.update(con, sql1,user.getUname());
				System.out.println(result);
			}	
			sql1 = "DELETE FROM calendar_info WHERE uname = '"+user.getUname()+"'";
			result = runner.update(con, sql1);
			String sql = "DELETE FROM user_info WHERE uname = '"+user.getUname()+"'";
			result = runner.update(con, sql);
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, null, null);
		}
		return result;
	}
	
}
