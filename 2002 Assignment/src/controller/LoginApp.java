package controller;

import model.customer.User;
import model.customer.User.LoginFailedException;
import model.customer.User.UserNotLoggedInException;

public class LoginApp {
	public LoginApp() {
		User.initialize();
	}
	public CinemaApp login(String name, String password) throws LoginFailedException, UserNotLoggedInException{
		User.login(name, password);
		if(User.isAdmin())
			return new AdminCinemaApp();
		else
			return new CinemaApp();
	}
	public void logout(){
		User.logout();
	}
}
