package com.sunbeam;

import java.util.List;

public interface DAOUser extends AutoCloseable {
	int register(User user) throws Exception;

	User login(User user) throws Exception;

	int addBlog(Blog blog) throws Exception;

	List<Blog> getBlogByUser(int b_Id) throws Exception;

	void updateBlogStatus(int bId) throws Exception;

	void deleteBlog(int b_Id) throws Exception;
}