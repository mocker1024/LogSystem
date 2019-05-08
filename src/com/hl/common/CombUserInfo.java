package com.hl.common;

/**
 * @author admin
 *
 */
public class CombUserInfo extends CombRegister{

	private String tel;
	private String sex;
	
	private int department_id;
	private int position;
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public CombUserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CombUserInfo(String uname, String realname, String dname, String pname) {
		super(uname, realname, dname, pname);
		// TODO Auto-generated constructor stub
	}
	public CombUserInfo(String uname, String realname, String dname, String pname,String tel,int sex,int did,int position) {
		super(uname, realname, dname, pname);
		if(sex == 0) {
			this.sex = "男";
		}else {
			this.sex = "女";
		}
		this.tel = tel;
		this.department_id = did;
		this.position = position;
	}
}
