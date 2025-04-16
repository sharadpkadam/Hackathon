package com.sunbeam;

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
//							addCategory();
							break;
						case 2:
//							showCategories();
							break;
						case 3:
//							displayAllBlogs();
							break;
						case 4:
//							displayMyBlogs();
							break;
						case 5:
							if (currentUser==null) {
								System.out.println("Please loggin");
								break;
							}
							Blog blog=new Blog();
							
							System.out.println("bId : ");
							blog.setCategoryId(scanner.nextInt());
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
//							editBlog();
							break;
						case 7:
//							searchblog();
							break;
						case 8:
//							deleteBlog();
							break;
						case 9:
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
}
