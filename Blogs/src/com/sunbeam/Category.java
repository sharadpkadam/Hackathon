package com.sunbeam;

public class Category {
	int cId;
	String title;
	String description;

	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(int cId, String title, String description) {
		this.cId = cId;
		this.title = title;
		this.description = description;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Category [cId=" + cId + ", title=" + title + ", description=" + description + "]";
	}

}
