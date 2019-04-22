package com.hl.entity;

public class User {
	private String uname;      	
	private String password;	
	private int department_id;	
	private int position;		
	private String realname;	
	private int status;
	private int sex;			
	private String tel;			
	private int age;		
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public User() {
		super();
	}
	
	public User(String uname, String password, int department_id, int position, String realname,int status) {
		super();
		this.uname = uname;
		this.password = password;
		this.department_id = department_id;
		this.position = position;
		this.realname = realname;
		this.status = status;
	}
	public User(String uname, String password, int department_id, int position, String realname, int status, int sex,
			String tel, int age) {
		super();
		this.uname = uname;
		this.password = password;
		this.department_id = department_id;
		this.position = position;
		this.realname = realname;
		this.status = status;
		this.sex = sex;
		this.tel = tel;
		this.age = age;
	}
	public User(User user) {
		super();
		this.uname= user.uname;
		this.password = user.password;
		this.department_id = user.department_id;
		this.position = user.position;
		this.realname = user.realname;
		this.status = user.status;
		this.sex = user.sex;
		this.tel = user.tel;
		this.age = user.age;
	}
	@Override
	public String toString() {
		return "User [uname=" + uname + ", password=" + password + ", department_id=" + department_id + ", position="
				+ position + ", realname=" + realname + ", status=" + status + ", sex=" + sex + ", tel=" + tel
				+ ", age=" + age + "]";
	}
	
	
	
}
