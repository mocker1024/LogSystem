package com.hl.entity;

public class Log {
	private int log_id;
	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	private String log_context;
	private String log_date;
	private String uname;
	private int comment_id;
	private int status;
	public String getLog_context() {
		return log_context;
	}
	public void setLog_context(String log_context) {
		this.log_context = log_context;
	}
	public String getLog_date() {
		return log_date;
	}
	public void setLog_date(String log_date) {
		this.log_date = log_date;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public  Log() {
		super();
	}
	public Log(String log_context, String log_date, String uname, int comment_id,int status) {
		super();
		this.log_context = log_context;
		this.log_date = log_date;
		this.uname = uname;
		this.comment_id = comment_id;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Log [log_context=" + log_context + ", log_date=" + log_date + ", uname=" + uname + ", comment_id="
				+ comment_id +  status+"]";
	}
	
	
	
}
