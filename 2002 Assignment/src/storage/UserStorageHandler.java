package storage;

import java.io.File;
import java.util.ArrayList;

import model.customer.Customer;
import model.customer.SeniorCustomer;
import model.customer.StudentCustomer;
import model.customer.User;

public class UserStorageHandler extends StorageHandler{
	
	private ArrayList<User> userList;
	private String pathname = "..."; //TODO to be implemented
	
	public UserStorageHandler(ArrayList<User> userList) {
		this.userList = userList;
		this.file = new File(pathname);
	}
	
	public void loadData(){
		openFile();
		readFile();
		closeFile();
	}
	public void saveData(){
		openFile();
		writeFile();
		closeFile();
	}
	
	@Override
	protected void interpretLine(String line) {
		//TODO interpret a line and convert into user object and store it into user list
		/*
		 
		User user;
		Customer c = user.getCustomer();
		
		- check whether the customer is a senior or a student or normal
		c instanceof SeniorCustomer
		c instanceof StudentCustomer
		
		- check whether the user is admin
		user.isAdmin()
		
		- to create admin
		User.createAdmin(name, password, mobileNumber, emailAddress)
		
		- to create user
		User.createUser(name, password, mobileNumber, age, emailAddress)
		
		- add into userlist
		userList.add(user);
		*/
	}

	@Override
	protected String getDataToWrite() {
		// TODO translate user list into string which can be interpreted by interpretLine
		/*
		
		- iterate the user list
		for (User user : userList) {
			
		}
		- get customer from user
		User user;
		Customer customer = user.getCustomer();
		
		- customer information
		customer.getAge();
		customer.getEmailAddress();
		customer.getMobileNumber();
		customer.getName();
		*/
		return null;
	}

}
