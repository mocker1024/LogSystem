package com.hl.common;

import java.util.List;

import com.hl.entity.News;

public class ListNews {
	private List<News> news;
	public List<News> getNews() {
		return news;
	}
 
	public void setNews(List<News> news) {
		this.news = news;
	}
	
	public ListNews(List<News> news) {
		super();
		this.news = news;
	}
}
