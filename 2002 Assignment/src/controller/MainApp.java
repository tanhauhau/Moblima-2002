package controller;

import java.util.ArrayList;

import view.GUIViewApp;
import view.IViewApp;

import model.cinema.Cineplex;
import model.customer.Customer;
import model.customer.User;
import model.customer.User.UserNotLoggedInException;
import model.movie.Movie;

public class MainApp {
	public MainApp() throws UserNotLoggedInException {
		//View
		IViewApp view = new GUIViewApp(); 
		
		//read from storage
		StorageApp storage = new StorageApp();
		
		boolean hasStorage = storage.hasStorage();
		
		ArrayList<Movie> movie = null;
		ArrayList<Cineplex> cineplex = null;
		
		if(hasStorage){
//			ArrayList<User> users ;//= storage.loadUsers();
//			User.initialize(users);
			
			storage.loadMovies();
			storage.loadCineplexes();
			storage.loadShowTime();
		}
		
		//user login
		LoginApp login = new LoginApp();
		//either one
		view.auth(login);
		//		storage.saveUsers(User.getUserList());
		User user = login.getCurrentUser();
		Customer customer = user.getCustomer();
		
		AbstractCinemaApp cinemaApp;
		if(user.isAdmin()){
			cinemaApp = (hasStorage) ? new AdminCinemaApp(customer, movie, cineplex) : new AdminCinemaApp(customer);
		}else{
			cinemaApp = (hasStorage) ? new CinemaApp(customer, movie, cineplex) : new CinemaApp(customer);
		}
		
		
	}
	public static void main(String[] args) throws UserNotLoggedInException {
		new MainApp();
	}
}
