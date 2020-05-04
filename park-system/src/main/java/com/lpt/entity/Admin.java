package com.lpt.entity;

import java.util.Date;

public class Admin {
	/*主键*/
	private int id;
	/*管理员姓名*/
	private String name;
	/*管理员密码*/
	private String password;
	/*创建日期*/
	private Date createDate;
	/*管理员类型 0超级管理员 1公共管理员*/
	private String adminType;
	/*用户id*/
	private String userId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAdminType() {
		return adminType;
	}
	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
