package com.sunbeam;

import java.util.List;

public interface DAOUser extends AutoCloseable {
	int register(User user) throws Exception;

	User login(User user) throws Exception;

	int addBlog(Blog blog) throws Exception;
	
	int addCategory(Category category) throws Exception;

	List<Blog> getBlogByUser(int b_Id) throws Exception;

	void updateBlog(int bId, String contents) throws Exception;

	void deleteBlog(int b_Id) throws Exception;
	
	List<Category> findAll() throws Exception;
	
	List<Blog> viewAllBlogs() throws Exception;
	
	List<Blog> findByUId(int uId) throws Exception;
	
	List<String> findBlogs(String word) throws Exception;
}