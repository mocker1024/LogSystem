package com.hl.common;

import java.util.List;

import com.hl.entity.Log;

import jdk.nashorn.api.scripting.AbstractJSObject;

public class ListLog extends AbstractJSObject{
	private List<Log> logs;
	public List<Log> getLogs() {
		return logs;
	}
 
	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}
	
	public ListLog(List<Log> logs) {
		super();
		this.logs = logs;
	}
	
	
}
