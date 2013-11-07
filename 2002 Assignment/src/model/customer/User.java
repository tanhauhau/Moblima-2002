package model.customer;

import java.util.ArrayList;

import storage.UserStorageHandler;

public class User extends Customer{
	private String password;
	
	private static User currentUser;
	private static ArrayList<User> userList;
	private static UserStorageHandler storage;
	/*
	 * Create user
	 */
	public static User createUser(String name, String password, String mobileNumber, String emailAddress) throws UserNameExistsException{
		return createUser(name, password, mobileNumber, emailAddress, false);
	}
	public static User createAdmin(String name, String password, String mobileNumber, String emailAddress) throws UserNameExistsException{
		return createUser(name, password, mobileNumber, emailAddress, true);
	}
	private static User createUser(String name, String password, String mobileNumber, String emailAddress, boolean isAdmin) throws UserNameExistsException{
		if(isRepeatName(name)) throw new UserNameExistsException();
		User user;
		if(isAdmin)
			user = new Admin(name, password, mobileNumber, emailAddress);
		else
			user = new User(name, password, mobileNumber, emailAddress);
		userList.add(user);
		save();
		return user;
	}
	private static boolean isRepeatName(String name){
		for (User user : userList) {
			if(user.getName().equals(name))
				return true;
		}
		return false;
	}
	
	/*
	 * store and load
	 */
	public static void initialize(){
		userList = new ArrayList<User>();
		storage = new UserStorageHandler(userList);
		storage.loadData();
		currentUser = null;
	}
	public static void save(){
		storage.saveData();
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
	public static User login(String name, String password) throws LoginFailedException{
		for (User user : userList) {
			if(user.getName().equals(name)){
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
	private User(String name, String password, String mobileNumber, String emailAddress) {
		super(name, mobileNumber, emailAddress);
		this.password = password;
	}
	private boolean authenticate(String password){
		return this.password.equals(password);
	}
	
	/*
	 * Admin Class
	 */
	public static class Admin extends User{
		private Admin(String name, String password, String mobileNumber, String emailAddress){
			super(name, password, mobileNumber, emailAddress);
		}
	}
	
	public static boolean isAdmin() throws UserNotLoggedInException{
		return (getCurrentUser() instanceof Admin);
	}
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
