package model.customer;

import java.util.ArrayList;

public class User{
	private String password;
	private Customer customer;
	private boolean isAdmin;
	
	private static User currentUser;
	private static ArrayList<User> userList;
//	private static UserStorageHandler storage;
	/*
	 * Create user
	 */
	public static User createUser(String name, String password, String mobileNumber, int age, String emailAddress) throws UserNameExistsException{
		return createUser(name, password, mobileNumber, emailAddress, age, false);
	}
	public static User createAdmin(String name, String password, String mobileNumber, String emailAddress) throws UserNameExistsException{
		return createUser(name, password, mobileNumber, emailAddress, 0, true);
	}
	private static User createUser(String name, String password, String mobileNumber, String emailAddress, int age, boolean isAdmin) throws UserNameExistsException{
		if(isRepeatName(name)) throw new UserNameExistsException();
		User user;
		Customer customer = Customer.createCustomer(name, mobileNumber, emailAddress, age);
		if(isAdmin)
			user = new User(customer, password, true);
		else
			user = new User(customer, password, false);
		userList.add(user);
//		save();
		return user;
	}
	private static boolean isRepeatName(String name){
		for (User user : userList) {
			if(user.customer.getName().equals(name))
				return true;
		}
		return false;
	}
	
	/*
	 * store and load
	 */
	public static void initialize(ArrayList<User> users){
		userList = users;
		currentUser = null;
	}

//	public static void initialize(){
//		userList = new ArrayList<User>();
//		storage = new UserStorageHandler(userList);
//		storage.loadData();
//		currentUser = null;
//	}
//	public static void save(){
//		storage.saveData();
//	}
	public static ArrayList<User> getUserList() {
		return userList;
	}
	
	/*
	 * Log in, log out
	 */
	public static boolean hasUserLoggedIn(){
		return currentUser != null;
	}
	public static User getCurrentUser() throws UserNotLoggedInException{
		if(!hasUserLoggedIn()) throw new UserNotLoggedInException();
		return currentUser;
	}
	public static Customer getCurrentCustomer() throws UserNotLoggedInException{
		return getCurrentUser().getCustomer();
	}
	public static User login(String name, String password) throws LoginFailedException{
		for (User user : userList) {
			if(user.customer.getName().equals(name)){
				if(user.authenticate(password)){
					currentUser = user;
					return user;
				}
				throw new LoginFailedException("Password incorrect");
			}
		}
		throw new LoginFailedException("No such user");
	}
	public static void logout(){
		currentUser = null;
	}
	
	/*
	 * User Class
	 * -cannot be instantiated outside this class
	 * -has to call User.createUser factory method
	 */
	private User(Customer customer, String password, boolean isAdmin) {
		this.customer = customer;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	private boolean authenticate(String password){
		return this.password.equals(password);
	}
	public Customer getCustomer(){
		return customer;
	}
	public boolean isAdmin(){
		return isAdmin;
	}
	
	public Customer getCustomer(String name){
		for (User user : userList) {
			if(user.getCustomer().getName().equals(name)){
				return user.getCustomer();
			}
		}
		return null;
	}
	
//	/*
//	 * Admin Class
//	 */
//	public static class Admin extends User{
//		private Admin(Customer customer, String password){
//			super(customer, password);
//		}
//	}
	/*
	 * User Class Exceptions
	 */
	public static class UserNameExistsException extends Exception{
		private static final long serialVersionUID = 1L;
		public UserNameExistsException() {
		}
		@Override
		public String getMessage() {
			return "User name exists!";
		}
	}
	public static class LoginFailedException extends Exception{
		private static final long serialVersionUID = 1L;
		public LoginFailedException(String message) {
			super(message);
		}
		@Override
		public String getMessage() {
			return "Login Failed - " + super.getMessage() + "!";
		}
	}
	public static class UserNotLoggedInException extends Exception{
		private static final long serialVersionUID = 1L;
		public UserNotLoggedInException() {
		}
		@Override
		public String getMessage() {
			return "User not logged in!";
		}
	}
}
