package com.hl.common;

public class AppResult {
	private int keycode;
	private String message;
	private Object data;
	public int getKeycode() {
		return keycode;
	}
	public void setKeycode(int keycode) {
		this.keycode = keycode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public AppResult() {
		super();
	}
	public AppResult(int keycode,String message,Object data) {
		super();
		this.keycode = keycode;
		this.message = message;
		this.data = data;
	}
}
