package com.hl.entity;

public class Position {
	private int position;
	private String pname;
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	@Override
	public String toString() {
		return "Position [position=" + position + ", pname=" + pname + "]";
	}
	
}
