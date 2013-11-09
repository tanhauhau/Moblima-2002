package controller;

import java.util.ArrayList;

import model.cinema.Cineplex;
import model.customer.Customer;
import model.customer.User;
import model.movie.Movie;

public class MainApp {
	public MainApp() {
		//read from storage
		StorageApp storage = new StorageApp();
		
		boolean hasStorage = storage.hasStorage();
		
		ArrayList<Movie> movie = null;
		ArrayList<Cineplex> cineplex = null;
		
		if(hasStorage){
			ArrayList<User> users = storage.loadUsers();
			User.initialize(users);
			
			movie = storage.loadMovies();
			cineplex = storage.loadCineplexes();
			storage.loadShowTime(cineplex, movie);
		}
		
		//user login
		LoginApp login = new LoginApp();
		//either one
		User user = login.login(name, password);
//		User user = login.createUser(name, password, mobileNumber, age, emailAddress);
//		User user = login.createAdmin(name, password, mobileNumber, age, emailAddress);
//		storage.saveUsers(User.getUserList());
		
		Customer customer = user.getCustomer();
		
		AbstractCinemaApp cinemaApp;
		if(user.isAdmin()){
			cinemaApp = (hasStorage) ? new AdminCinemaApp(customer, movie, cineplex) : new AdminCinemaApp(customer);
		}else{
			cinemaApp = (hasStorage) ? new CinemaApp(customer, movie, cineplex) : new CinemaApp(customer);
		}
		
		
	}
	public static void main(String[] args) {
		new MainApp();
	}
}
