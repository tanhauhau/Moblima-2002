package controller;

import java.util.ArrayList;

import model.cinema.Cineplex;
import model.cinema.showtime.ShowTime;
import model.customer.User;
import model.movie.Movie;

public class StorageApp {
	public ArrayList<Cineplex> loadCineplexes(){
		return null;
	}
	public ArrayList<Movie> loadMovies(){
		return null;
	}
	public void loadShowTime(ArrayList<Cineplex> cineplex, ArrayList<Movie> movie){
		
	}
	public ArrayList<User> loadUsers(){
		return null;
	}
	
	public void saveCineplexes(ArrayList<Cineplex> cineplex){
	}
	public void saveMovies(ArrayList<Movie> movie){
	}
	public void saveShowTime(ShowTime[] showtime){
	}
	public void saveUsers(ArrayList<User> user){
	}
	
	public boolean hasStorage(){
		return false;
	}
}
