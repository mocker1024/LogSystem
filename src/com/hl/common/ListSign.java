package com.hl.common;

import java.util.List;

import com.hl.entity.Sign;

import jdk.nashorn.api.scripting.AbstractJSObject;

public class ListSign extends AbstractJSObject{
	private List<Sign> signs;
	public List<Sign> getSigns() {
		return signs;
	}
 
	public void setSigns(List<Sign> signs) {
		this.signs = signs;
	}
	
	public ListSign(List<Sign> signs) {
		super();
		this.signs = signs;
	}
}
