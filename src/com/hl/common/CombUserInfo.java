package com.hl.common;

/**
 * @author admin
 *
 */
public class CombUserInfo extends CombRegister{

	private String tel;
	private String sex;
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
	public CombUserInfo(String uname, String realname, String dname, String pname,String tel,int sex) {
		super(uname, realname, dname, pname);
		if(sex == 0) {
			this.sex = "男";
		}else {
			this.sex = "女";
		}
		this.tel = tel;
	}
}
