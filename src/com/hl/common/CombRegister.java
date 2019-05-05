package com.hl.common;

public class CombRegister {
	private String uname;	 //用户名
	private String realname; //真实名字
	private String pname;    //职位名
	private String dname;	 //部门名
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public CombRegister(String uname, String realname, String dname, String pname) {
		super();
		this.dname = dname;
		this.realname = realname;
		this.uname = uname;
		this.pname = pname;
	}
	public CombRegister() {
		super();
	}
	@Override
	public String toString() {
		return "CombRegister [uname=" + uname + ", realname=" + realname + ", pname=" + pname + ", dname=" + dname
				+ "]";
	}
	
}
