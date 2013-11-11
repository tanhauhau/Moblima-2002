package controller;

import java.util.ArrayList;

import model.customer.User;
import model.customer.User.LoginFailedException;
import model.customer.User.UserNameExistsException;
import model.customer.User.UserNotLoggedInException;

public class LoginApp {
	public LoginApp() {
		User.initialize(new ArrayList<User>());
	}
	public ArrayList<User> getUserList(){
		return User.getUserList();
	}
	
	public User getCurrentUser() throws UserNotLoggedInException{
		return User.getCurrentUser();
	}
	public User login(String name, String password) throws LoginFailedException, UserNotLoggedInException{
		User user = User.login(name, password);
		return user;
	}
	public void logout(){
		User.logout();
	}
	public void createUser(String name, String password, String mobileNumber, int age, String emailAddress) throws UserNameExistsException{
		User.createUser(name, password, mobileNumber, age, emailAddress);
	}
	public void createAdmin(String name, String password, String mobileNumber, String emailAddress) throws UserNameExistsException{
		User.createAdmin(name, password, mobileNumber, emailAddress);
	}
}
