package controller;

import model.customer.User;
import model.customer.User.LoginFailedException;
import model.customer.User.UserNameExistsException;
import model.customer.User.UserNotLoggedInException;

public class LoginApp {
	public LoginApp() {

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
	public User createUser(String name, String password, String mobileNumber, int age, String emailAddress) throws UserNameExistsException, LoginFailedException, UserNotLoggedInException{
		User user = User.createUser(name, password, mobileNumber, age, emailAddress);
		login(name, password);
		return user;
	}
	public User createAdmin(String name, String password, String mobileNumber, String emailAddress) throws UserNameExistsException, LoginFailedException, UserNotLoggedInException{
		User user = User.createAdmin(name, password, mobileNumber, emailAddress);
		login(name, password);
		return user;
	}
}
