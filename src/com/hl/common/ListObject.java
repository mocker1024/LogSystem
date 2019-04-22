package com.hl.common;

import java.util.List;

import com.hl.entity.User;

import jdk.nashorn.api.scripting.AbstractJSObject;

public class ListObject extends AbstractJSObject{

	private List<User> items;
	public List<User> getItems() {
		return items;
	}
 
	public void setItems(List<User> items) {
		this.items = items;
	}
	
	public ListObject(List<User> items) {
		super();
		this.items = items;
	}

	
}
