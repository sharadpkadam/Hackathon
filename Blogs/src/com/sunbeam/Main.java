package com.sunbeam;

import java.util.List;
import java.util.Scanner;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static User currentUser = null;
	
	

	public static int loginMenu() {
		System.out.println("\n---LogIn Menu---");
		System.out.println("0.Exit");
		System.out.println("1.Register");
		System.out.println("2.Login");

		System.out.println("Enter your choice : ");
		int loginChoice = scanner.nextInt();
		scanner.nextLine();
		return loginChoice;
	}

	public static int mainMenu() {
		System.out.println("\n---Main Menu---");
		System.out.println("0.LogOut");
		System.out.println("1.Add Category");
		System.out.println("2.Show Categories");
		System.out.println("3.All Blogs");
		System.out.println("4.My Blogs");
		System.out.println("5.Add Blogs");
		System.out.println("6.Edit Blogs");
		System.out.println("7.Search Blogs");
		System.out.println("8.Delete Blog");

		System.out.println("Enter Your Choice");
		int choice = scanner.nextInt();
		scanner.nextLine();
		return choice;
	}

	public static void main(String[] args) throws Exception {
		UserDAO userDAO =new UserDAO();
		int loginChoice;
		while ((loginChoice = loginMenu())!=0) {
			switch (loginChoice) {
			case 1:
				System.out.println("Enter ID");
				int uId=scanner.nextInt();
				System.out.print("Enter Full Name: ");
				String fullName = scanner.next();
				System.out.print("Enter Email: ");
				String email = scanner.next();
				System.out.print("Enter Password: ");
				String password = scanner.next();
				System.out.println("Enter Phone Number : ");
				String phoneNo = scanner.next();

				User newUser = new User(uId, fullName, email, password, phoneNo);
				userDAO.register(newUser);
				break;
				
			case 2:
				System.out.print("Enter Email: ");
				String loginEmail = scanner.nextLine();
				System.out.print("Enter Password: ");
				String loginPassword = scanner.nextLine();

				User loginUser = new User();
				loginUser.setEmail(loginEmail);
				loginUser.setPassword(loginPassword);
				User loggedIn = userDAO.login(loginUser);

				if (loggedIn != null) {
					currentUser = loggedIn;
					System.out.println("Welcome, " + currentUser.getFullName());
					
					int choice;
					while ((choice = mainMenu())!=0) {
						switch (choice) {
						case 1:
							Category category=new Category();
							
							System.out.println("cId : ");
							category.setcId(scanner.nextInt());
							System.out.println("Title: ");
							category.setTitle(scanner.next());
							System.out.println("description : ");
							category.setDescription(scanner.next());
							

							userDAO.addCategory(category);
							System.out.println("Category Added!");
							break;
						case 2:
							List<Category> list= userDAO.findAll();
							for (Category c : list) {
								System.out.println(c.toString());
							}
							break;
						case 3:
							List<Blog> list1= userDAO.viewAllBlogs();
							for (Blog c : list1) {
								System.out.println(c.toString());
							}
							break;
						case 4:
							List<Blog> list11= userDAO.findByUId(currentUser.getuId());
							for (Blog c : list11) {
								System.out.println(c.toString());
							}
							break;
						case 5:
							Blog blog=new Blog();
							
							System.out.println("bId : ");
							blog.setbId(scanner.nextInt());
							System.out.println("Title: ");
							blog.setTitle(scanner.next());
							System.out.println("contents : ");
							blog.setContents(scanner.next());
							blog.setUserId(currentUser.getuId());							
							System.out.println("categoryId : ");
							blog.setCategoryId(scanner.nextInt());
							

							userDAO.addBlog(blog);
							System.out.println("blog Added!");
							break;
						case 6:
							System.out.print("Enter blog ID to update: ");
							int blogIdToUpdate = scanner.nextInt();
							scanner.nextLine();
							System.out.println("Enter new contents");
							String newContent=scanner.nextLine();
							userDAO.updateBlog(blogIdToUpdate, newContent);
							System.out.println("Blog updated.");
							break;
						case 7:
							System.out.println("ENter the word : ");
							String word = scanner.next();
							userDAO.findBlogs(word);
							break;
						case 8:
							System.out.print("Enter blog ID to delete: ");
							int blogIdToDelete = scanner.nextInt();
							userDAO.deleteBlog(blogIdToDelete);
							System.out.println("Blog deleted.");
								break;
							case 0:
								currentUser = null;
								System.out.println("Logged out.");
								break;
							default:
							break;
						}
					}
				} else {
					System.out.println("Invalid email or password.");
				}
				break;
			default:
				break;
			}
		}
	}

	private static void showCategories() {
		// TODO Auto-generated method stub
		
	}

	private static void addCategory() {
		// TODO Auto-generated method stub
		
	}
}
