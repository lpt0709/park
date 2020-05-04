package com.lpt.entity;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
	/*主键id*/
	private int id;
	/*公告标题*/
	private String title;
	/*公告内容*/
	private String content;
	/*创建人姓名*/
	private String admin_name;
	/*创建时间*/
	private Date createDate;
	/*创建人id*/
	private int admin_id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
}
