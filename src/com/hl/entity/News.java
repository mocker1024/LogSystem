package com.hl.entity;

import java.util.Date;


public class News {
	private String title;
	private String news_context;
	private Date news_date;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNews_context() {
		return news_context;
	}
	public void setNews_context(String news_context) {
		this.news_context = news_context;
	}
	public Date getNews_date() {
		return news_date;
	}
	public void setNews_date(Date news_date) {
		this.news_date = news_date;
	}
	public News(){
		super();
	}
	public News(String title, String news_context, Date news_date) {
		super();
		this.title = title;
		this.news_context = news_context;
		this.news_date = news_date;
	}
	@Override
	public String toString() {
		return "News [title=" + title + ", news_context=" + news_context + ", news_date=" + news_date + "]";
	}
	
	
}
