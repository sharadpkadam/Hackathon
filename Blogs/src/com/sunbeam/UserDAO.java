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
		String query = "INSERT INTO blogs (b_id, contents, u_id, c_id) VALUES (?, ?, ?, ?)";
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
			stmt.setString(1, user.getFullName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString(3, user.getPhoneNo());
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
					loggedIn.setuId(rs.getInt("user_id"));
					loggedIn.setFullName(rs.getString("name"));
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
	public void updateBlogStatus(int bId) throws Exception {
		String query = "UPDATE tasks SET * WHERE b_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setInt(1, bId);
			stmt.executeUpdate();
		}
	}

}
