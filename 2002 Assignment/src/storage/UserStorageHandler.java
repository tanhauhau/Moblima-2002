package storage;

import java.io.File;
import java.util.ArrayList;

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
	}

	@Override
	protected String getDataToWrite() {
		// TODO translate user list into string which can be interpreted by interpretLine
		return null;
	}

}
