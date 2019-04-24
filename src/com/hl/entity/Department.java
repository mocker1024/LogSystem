package com.hl.entity;
/**
 * 
 */
public class Department {
	private int department_id;
	private String dname;
	private String uname;
	
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Department(){
		super();
	}
	public Department(String dname, String uname) {
		super();
		this.dname = dname;
		this.uname = uname;
	}
	@Override
	public String toString() {
		return "Department [dname=" + dname + ", uname=" + uname + "]";
	}
	
	
}
