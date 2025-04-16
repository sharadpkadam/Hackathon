package com.sunbeam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements AutoCloseable,DAOUser {
	private Connection con;

	public UserDAO() throws Exception {
		con = Dbutil.getConnection();
	}

	@Override
	public void close() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.err.println("Error closing connection: " + e.getMessage());
		}
	}
	@Override
	public int addBlog(Blog blog) throws Exception {
		String query = "INSERT INTO blogs (b_id,title, contents,u_id, c_id) VALUES (?, ?, ?, ?,?)";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setInt(1, blog.getbId());
			stmt.setString(2, blog.getTitle());
			stmt.setString(3, blog.getContents());
//			stmt.setDate(4, blog.getCreateTime());
			stmt.setInt(4, blog.getUserId());
			stmt.setInt(5, blog.getCategoryId());
			return stmt.executeUpdate();
		}
	}

	@Override
	public int register(User user) throws Exception {
		int count = 0;
		String sql = "INSERT INTO users (u_id,full_name, email, password,phone_no) VALUES (?,?, ?, ?,?)";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, user.getuId());
			stmt.setString(2, user.getFullName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.setString(5, user.getPhoneNo());
//			stmt.setDate(6, user.getCreatedTime());
			count = stmt.executeUpdate();
			System.out.println("User registered successfully.");
		} catch (Exception e) {
			System.out.println("Email already exists!");
		}
		return count;
	}

	@Override
	public User login(User user) throws Exception {
		String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					User loggedIn = new User();
					loggedIn.setuId(rs.getInt("u_id"));
					loggedIn.setFullName(rs.getString("full_name"));
					loggedIn.setEmail(rs.getString("email"));
					loggedIn.setPassword(rs.getString("password"));
					System.out.println("Login successful.");
					return loggedIn;
				}
			}
		}
		return null;
	}

	@Override
	public List<Blog> getBlogByUser(int b_Id) throws Exception { // enter uid to get blog
		String query = "SELECT * FROM blogs WHERE u_id = ?";
		List<Blog> tasks = new ArrayList<>();
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setInt(1, b_Id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Blog blog = new Blog();
				blog.setbId(rs.getInt("b_id"));
				blog.setTitle( rs.getString("title"));
				blog.setContents(rs.getString("contents"));
				blog.setCreateTime(rs.getDate("create_time"));
				blog.setUserId(rs.getInt("u_Id"));
				blog.setCategoryId(rs.getInt("c_Id"));
				tasks.add(blog);
			}
		}
		return tasks;
	}

	@Override
	public void deleteBlog(int b_Id) throws Exception {
		String query = "DELETE FROM blogs WHERE b_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setInt(1, b_Id);
			stmt.executeUpdate();
		}
		
	}

	@Override
	public void updateBlog(int bId , String contents) throws Exception {
		String query = "UPDATE blogs SET contents=? WHERE b_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setString(1, contents);
			stmt.setInt(2, bId);
			
			stmt.executeUpdate();
		}
	}

	@Override
	public int addCategory(Category category) throws Exception {
		String query = "INSERT INTO categories (c_id,title, description) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setInt(1, category.getcId());
			stmt.setString(2, category.getTitle());
			stmt.setString(3, category.getDescription());
			return stmt.executeUpdate();
		}
	}

	@Override
	public List<Category> findAll() throws Exception {
		List<Category> list = new ArrayList<Category>();
		String sql = "Select * from categories";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			try(ResultSet rs=stmt.executeQuery()){
				while (rs.next()) {
					int cId = rs.getInt("c_id");
					String title = rs.getString("title");
					String description = rs.getString("description");
					Category c = new Category(cId,title,description);
					list.add(c);
				}
			}
		}
		return list;
	}

	@Override
	public List<Blog> viewAllBlogs() throws Exception {
		List<Blog> list = new ArrayList<Blog>();
		String sql = "Select * from blogs";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			try(ResultSet rs=stmt.executeQuery()){
				while (rs.next()) {
					int bId = rs.getInt("b_id");
					String title = rs.getString("title");
					String contents = rs.getString("contents");
					int cId = rs.getInt("c_id");
					int uId = rs.getInt("u_id");
					Blog c = new Blog(bId,title,contents, null, cId, uId);
					list.add(c);
				}
			}
		}
		return list;
	}

	@Override
	public List<Blog> findByUId(int uId) throws Exception {
		String query = "SELECT * FROM blogs WHERE u_id = ?";
		List<Blog> blogs = new ArrayList<>();
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setInt(1, uId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int bId = rs.getInt("b_id");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				Blog b = new Blog(bId, title, contents, null, uId, bId);
				blogs.add(b);
			}
		}
		return blogs;
	}

	@Override
	public List<String> findBlogs(String word) throws Exception {
		String sql = "SELECT * FROM blogs WHERE contents LIKE ?";
		List<String> results = new ArrayList<>();
		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, "%" + word + "%");

			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				results.add(resultSet.getString("contents").toLowerCase());
			}
		}
		for (String result : results) {
			System.out.println(result);
		}
		return results;
	}

	




}
