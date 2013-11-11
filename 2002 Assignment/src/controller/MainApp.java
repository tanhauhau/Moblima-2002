package controller;

import model.customer.Customer;
import model.customer.User;
import model.customer.User.UserNotLoggedInException;
import view.ConsoleViewApp;
import view.IViewApp;

public class MainApp {
	public MainApp() throws UserNotLoggedInException {
		//View
		IViewApp view = new ConsoleViewApp(); 
		
		//read from storage
		StorageApp storage = new StorageApp();
		
		//load users
		LoginApp auth = new LoginApp();
		storage.loadUsers(auth);
		
		while(true){
			//login
			boolean loginSuccess = view.auth(auth);
			if(!loginSuccess) break;
			//save changes
			storage.saveUsers(auth);
			
			//get user
			User user = auth.getCurrentUser();
			Customer customer = user.getCustomer();
			
			//get cinema app
			AbstractCinemaApp cinemaApp;
			cinemaApp = new AdminCinemaApp();//user.isAdmin()? new AdminCinemaApp() : new CinemaApp();   
			cinemaApp.setCustomer(customer);
			
			//load all saved movies
			storage.loadMovies(cinemaApp);
			storage.loadCineplexes(cinemaApp);
			storage.loadShowTime(cinemaApp, auth);
	
			//start view
			view.start(cinemaApp, user.isAdmin());
			
			//save
			storage.saveMovies(cinemaApp);
			storage.saveCineplexes(cinemaApp);
			storage.saveShowTime(cinemaApp, auth);
			
			auth.logout();
		}		
	}
	public static void main(String[] args) throws UserNotLoggedInException {
		new MainApp();
	}
}
