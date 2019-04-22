package com.hl.entity;


public class Sign {
	private String uname;
	private String date;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Sign() {
		super();
	}
	public Sign(String uname, String date) {
		super();
		this.uname = uname;
		this.date = date;
	}
	@Override
	public String toString() {
		return "Sign [uname=" + uname + ", date=" + date + "]";
	}
	
	
}
