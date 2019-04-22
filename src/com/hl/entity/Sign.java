package com.hl.entity;


public class Sign {
	private int sign_id;
	private String uname;
	private String date;
	
	public int getSign_id() {
		return sign_id;
	}
	public void setSign_id(int sign_id) {
		this.sign_id = sign_id;
	}
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
	public Sign(int id,String uname, String date) {
		super();
		this.sign_id = id;
		this.uname = uname;
		this.date = date;
	}
	@Override
	public String toString() {
		return "Sign [sign_id="+sign_id+"uname=" + uname + ", date=" + date + "]";
	}
	
	
}
