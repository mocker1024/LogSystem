package com.hl.entity;

import java.util.Date;

/**
 * 
 */
public class Comment {
	private String uname;
	private String comment_context;
	private Date comment_date;
	private int log_id;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getComment_context() {
		return comment_context;
	}
	public void setComment_context(String comment_context) {
		this.comment_context = comment_context;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	public Comment() {
		super();
	}
	public Comment(String uname, String comment_context, Date comment_date, int log_id) {
		super();
		this.uname = uname;
		this.comment_context = comment_context;
		this.comment_date = comment_date;
		this.log_id = log_id;
	}
	@Override
	public String toString() {
		return "Comment [uname=" + uname + ", comment_context=" + comment_context + ", comment_date=" + comment_date
				+ ", log_id=" + log_id + "]";
	}
	
	
}
