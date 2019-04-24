package com.hl.entity;

public class News {
	private int news_id;
	private String title;
	private String news_context;
	private String news_date;
	
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
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
	public String getNews_date() {
		return news_date;
	}
	public void setNews_date(String news_date) {
		this.news_date = news_date;
	}
	public News(){
		super();
	}
	public News(String title, String news_context, String news_date) {
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
