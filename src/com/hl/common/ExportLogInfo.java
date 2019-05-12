package com.hl.common;

public class ExportLogInfo {
	private String log_context;
	private String log_date;
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
	public  ExportLogInfo() {
		super();
	}
	public ExportLogInfo(String log_context, String log_date) {
		super();
		this.log_context = log_context;
		this.log_date = log_date;
		
	}
	@Override
	public String toString() {
		return "日期：" + log_date+"\n日志：\n" + log_context+"\n\n";
	}
	
}
