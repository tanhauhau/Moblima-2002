package controller;

import model.customer.Customer;
import model.customer.User;
import model.customer.User.UserNotLoggedInException;
import storage.Saveable;
import view.ConsoleView;
import view.IView;

public class MainApp implements Saveable{

	private LoginApp auth;
	private AbstractCinemaApp cinemaApp;
	private StorageApp storage;
	
	public MainApp() throws UserNotLoggedInException {
		//View
		IView view;
		view = new ConsoleView();
//		view = new GraphicView();
		
		//read from storage
		storage = new StorageApp();
		
		//load users
		auth = new LoginApp();
		storage.loadUsers(auth);
		
		//login
		boolean loginSuccess = view.auth(auth);
		if(!loginSuccess) return;
		//save changes
		storage.saveUsers(auth);
		
		//get user
		User user = auth.getCurrentUser();
		Customer customer = user.getCustomer();
		
		//get cinema app
		cinemaApp = new AdminCinemaApp();//user.isAdmin()? new AdminCinemaApp() : new CinemaApp();   
		cinemaApp.setCustomer(customer);
		
		//load all saved movies
		storage.loadMovies(cinemaApp);
		storage.loadCineplexes(cinemaApp);
		storage.loadShowTime(cinemaApp, auth);

		//start view
		view.start(cinemaApp, user.isAdmin());
	}
	public static void main(String[] args) throws UserNotLoggedInException {
		new MainApp();
	}

	@Override
	public void save() {
		//save
		storage.saveMovies(cinemaApp);
		storage.saveCineplexes(cinemaApp);
		storage.saveShowTime(cinemaApp, auth);
		storage.saveUsers(auth);
	}
}
