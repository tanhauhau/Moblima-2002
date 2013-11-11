package storage;

import java.util.StringTokenizer;

import model.customer.Customer;
import model.customer.User;
import model.customer.User.UserNameExistsException;
import controller.LoginApp;

public class UserStorageHandler extends StorageHandler{
	
	private static final String PATH_NAME = "Storage/user.csv";
	
	private LoginApp loginApp;
	
	public UserStorageHandler(LoginApp loginApp) {
		this.loginApp = loginApp;
		setFilePath(PATH_NAME);
	}
	
	public void loadData(){
		readFile();
	}
	public void saveData(){
		writeFile();
	}
	
	@Override
	protected void interpretLine(String line) {
		/*
		 * name, password, mobileNumber, age, emailAddress, staff
		 */
		//TODO interpret a line and convert into user object and store it into user list
		StringTokenizer st = new StringTokenizer(line, ",");
		String name = st.nextToken();
		String password = st.nextToken();
		String mobileNumber = st.nextToken();
		Integer age = Integer.parseInt(st.nextToken());
		String emailAddress = st.nextToken();
		boolean admin = Integer.parseInt(st.nextToken()) == 1; 
		
		try{
			if(admin){
				loginApp.createAdmin(name, password, mobileNumber, emailAddress);
			}else{
				loginApp.createUser(name, password, mobileNumber, age, emailAddress);
			}
		}catch(UserNameExistsException e){}
	}

	@Override
	protected String getDataToWrite() {
		/*
		 * name, password, mobileNumber, age, emailAddress, staff
		 */
		StringBuffer sb = new StringBuffer();
		for(User user : loginApp.getUserList()){
			Customer customer = user.getCustomer();
			sb.append(String.format("%s,%s,%s,%d,%s,%d", 
					customer.getName(), user.getPassword(), customer.getMobileNumber(),
					customer.getAge(), customer.getEmailAddress(), user.isAdmin()?1:0));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
}
