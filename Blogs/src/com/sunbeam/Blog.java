package com.sunbeam;

import java.sql.Date;

public class Blog {
	private int bId;
	private String title;
	private String contents;
	private Date createTime;
	private int userId;
	private int categoryId;

	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Blog(int bId, String title, String contents, Date createTime, int userId, int categoryId) {
		super();
		this.bId = bId;
		this.title = title;
		this.contents = contents;
		this.createTime = createTime;
		this.userId = userId;
		this.categoryId = categoryId;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Blog [bId=" + bId + ", title=" + title + ", contents=" + contents + ", createTime=" + createTime
				+ ", userId=" + userId + ", categoryId=" + categoryId + "]";
	}

}
