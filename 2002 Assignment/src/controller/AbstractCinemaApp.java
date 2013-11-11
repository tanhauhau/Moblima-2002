package controller;

import java.util.ArrayList;

import model.Ticket;
import model.cinema.Cinema;
import model.cinema.Cineplex;
import model.cinema.seat.Seat;
import model.cinema.seat.Seat.SeatOccupiedException;
import model.cinema.showtime.ShowTime;
import model.customer.Customer;
import model.customer.User.UserNotLoggedInException;
import model.movie.Genre;
import model.movie.Language;
import model.movie.Movie;
import model.movie.Rating;
import model.movie.Status;

public abstract class AbstractCinemaApp {
	/**
	 * Get all movie listing
	 * @return array list of movie listing
	 */
	public void setCustomer(Customer customer){
		throw new UnsupportedOperationException();
	}
	/**
	 * Get all movie listing
	 * @return array list of movie listing
	 */
	public ArrayList<Movie> getMovieListing(){
		throw new UnsupportedOperationException();
	}
	/**
	 * Get all movie listing
	 * @param status filter status of movie
	 * @return array list of movie listing
	 */
	public ArrayList<Movie> getMovieListing(Status status){
		throw new UnsupportedOperationException();
	}	
	
	/**
	 * @return total number of movie in the movie listing array
	 */
	public int getNumberOfMovies(){
		throw new UnsupportedOperationException();
	}
	
	/***************************************************************************/
	/***************************************************************************/
	
	/*
	 * Cineplex
	 */
	
	
	/**
	 * @return array list of cineplex
	 */
	public ArrayList<Cineplex> getAllCineplex(){
		throw new UnsupportedOperationException();
	}
	/**
	 * @return Number of cineplex
	 */
	public int getNumberOfCineplex(){
		throw new UnsupportedOperationException();
	}
	/**
	 * @param index index of cineplex in the cineplex array list
	 * @return total number of cinema in the cineplex
	 */
	public int numCinemaInCineplex(int index){
		throw new UnsupportedOperationException();
	}

	/**
	 * get the cinema
	 * @param cineplexIndex index of the cineplex in the cineplex array list
	 * @param cinemaIndex	index of the cinema in the cinema array list
	 * @return the cinema
	 */
	public Cinema getCinema(int cineplexIndex, int cinemaIndex){
		throw new UnsupportedOperationException();
	}
	/**
	 * get all cinema in the cineplex
	 * @param cineplexIndex index of the cineplex in the cineplex array list
	 * @return array list of cinemas
	 */
	public ArrayList<Cinema> getAllCinema(int cineplexIndex){
		throw new UnsupportedOperationException();
	}
	
	
	/***************************************************************************/
	/***************************************************************************/
	/*
	 * ShowTime
	 */

	/**
	 * get the specific show time for the cinema at specific hour
	 * @param cineplexIndex index of the cineplex in cineplex array list
	 * @param cinemaIndex   index of the cinema in cinema array list
	 * @param year          year
	 * @param month  		month
	 * @param date			date
	 * @param hour			hour
	 * @return 				the specific show time
	 */
	public ShowTime getShowTime(int cineplexIndex, int cinemaIndex, int year, int month, int date, int hour){
		throw new UnsupportedOperationException();
	}
	/**
	 * get the show time for the whole day for the cinema at specific hour
	 * @param cineplexIndex index of the cineplex in cineplex array list
	 * @param cinemaIndex   index of the cinema in cinema array list
	 * @param year          year
	 * @param month  		month
	 * @param date			date in the month
	 * @return 				array of show time 
	 * 
	 */
	public ShowTime[] getShowTime(int cineplexIndex, int cinemaIndex, int year, int month, int date){
		throw new UnsupportedOperationException();
	}
	/**
	 * get the show time for the whole day for the cinema at specific hour
	 * @param cineplexIndex index of the cineplex in cineplex array list
	 * @param cinemaIndex   index of the cinema in cinema array list
	 * @param year          year
	 * @param month  		month
	 * @param date			date in the month
	 * @return 				array of show time 
	 * 
	 */
	public ShowTime[] getShowTime(int cineplexIndex, int year, int month, int date){
		throw new UnsupportedOperationException();
	}
	/**
	 * Get all movie show time in all cineplexes
	 * @return	array of show time
	 */
	public ShowTime[] getShowTime(){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * get all the show time for the specific movie in the specific cinema
	 * @param cineplexIndex 	index of the cineplex in cineplex array list
	 * @param cinemaIndex		index of the cinema in cinema array list
	 * @param movieIndex		index of the movie in movie array list
	 * @param year          	year
	 * @param month  			month
	 * @param day				date in the month	 
	 * @return					array of show time
	 */
	public ShowTime[] getMovieShowTime(int cineplexIndex, int cinemaIndex, int movieIndex, int year, int month, int day){
		throw new UnsupportedOperationException();
	}
	/**
	 * get all the show time for the specific movie in the specific cineplex
	 * @param cineplexIndex 	index of the cineplex in cineplex array list
	 * @param movieIndex		index of the movie in movie array list
	 * @param year          	year
	 * @param month  			month
	 * @param day				date in the month
	 * @return					array of show time
	 */
	public ShowTime[] getMovieShowTime(int cineplexIndex, int movieIndex, int year, int month, int day){
		throw new UnsupportedOperationException();
	}
	/**
	 * get all the show time for the specific movie in all cineplexes
	 * @param movieIndex		index of the movie in movie array list
	 * @param year          	year
	 * @param month  			month
	 * @param day				date in the month
	 * @return					array of show time
	 */
	public ShowTime[] getMovieShowTime(int movieIndex, int year, int month, int day){
		throw new UnsupportedOperationException();
	}
	/**
	 * get all the show time for the specific movie in the specific cinema
	 * @param cineplexIndex 	index of the cineplex in cineplex array list
	 * @param cinemaIndex		index of the cinema in cinema array list
	 * @param movieIndex		index of the movie in movie array list
	 * @return					array of show time
	 */
	public ShowTime[] getMovieShowTime(int cineplexIndex, int cinemaIndex, int movieIndex){
		throw new UnsupportedOperationException();
	}
	/**
	 * get all the show time for the specific movie in the specific cineplex
	 * @param cineplexIndex 	index of the cineplex in cineplex array list
	 * @param movieIndex		index of the movie in movie array list
	 * @return					array of show time
	 */
	public ShowTime[] getMovieShowTime(int cineplexIndex, int movieIndex){
		throw new UnsupportedOperationException();
	}
	/**
	 * get all the show time for the specific movie in all cineplexes
	 * @param movieIndex		index of the movie in movie array list
	 * @return					array of show time
	 */
	public ShowTime[] getMovieShowTime(int movieIndex){
		throw new UnsupportedOperationException();
	}
	/**
	 * get the array of seats in the showtime
	 * @param showtime
	 * @return array of seats in the showtime
	 * @see CinemaApp#getMovieShowTime()
	 * @see CinemaApp#getMovieShowTime(int movieIndex)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int movieIndex)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int cinemaIndex, int movieIndex)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int cinemaIndex, int year, int month, int date)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int cinemaIndex, int year, int month, int date, int hour)
	 */
	public Seat[] getShowTimeSeatAllocation(ShowTime showtime){
		throw new UnsupportedOperationException();
	}
	/**
	 * @param showtime
	 * @return number of seats available in the showtime
	 * @see CinemaApp#getMovieShowTime()
	 * @see CinemaApp#getMovieShowTime(int movieIndex)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int movieIndex)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int cinemaIndex, int movieIndex)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int cinemaIndex, int year, int month, int date)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int cinemaIndex, int year, int month, int date, int hour)
	 */
	public int getShowTimeSeatsAvailable(ShowTime showtime){
		throw new UnsupportedOperationException();
	}
	/**
	 * @param showTime
	 * @return true if the show time has no more seats available, false otherwise
	 * @see CinemaApp#getMovieShowTime()
	 * @see CinemaApp#getMovieShowTime(int movieIndex)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int movieIndex)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int cinemaIndex, int movieIndex)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int cinemaIndex, int year, int month, int date)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int cinemaIndex, int year, int month, int date, int hour)
	 */
	public boolean isShowTimeFull(ShowTime showTime){
		throw new UnsupportedOperationException();
	}
	/**
	 * purchase ticket
	 * @param showtime						showtime to buy								
	 * @param seatIndex						index of the seat selected in the seat array
	 * @return								ticket for the seat
	 * @throws SeatOccupiedException		the seat selected is occupied
	 * @throws UserNotLoggedInException		user has not logged in
	 * @throws IndexOutOfBoundsException 	seat index is out of bounds
	 * 
	 * @see CinemaApp#getMovieShowTime()
	 * @see CinemaApp#getMovieShowTime(int movieIndex)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int movieIndex)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int cinemaIndex, int movieIndex)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int cinemaIndex, int year, int month, int date)
	 * @see CinemaApp#getMovieShowTime(int cineplexIndex, int cinemaIndex, int year, int month, int date, int hour)
	 */
	public Ticket purchaseSeat(ShowTime showtime, int seatIndex) throws SeatOccupiedException, UserNotLoggedInException, IndexOutOfBoundsException{
		throw new UnsupportedOperationException(); 
	}
	/**
	 * @return array of ticket the user has bought
	 * @throws UserNotLoggedInException user has not logged in
	 */
	public Ticket[] getPurchaseHistory() throws UserNotLoggedInException{
		throw new UnsupportedOperationException();
	}
	/*
	 * Movies
	 */
	/**
	 * 
	 * @return true if successfully added, false otherwise
	 */
	public boolean addMovie(String code, String title, String description, Genre genre, Language language, Rating rating){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @param index index of the movie in the arraylist
	 */
	public void removeMovie(int index){
		throw new UnsupportedOperationException();
	}
	/**
	 * 
	 * @param index index of the movie to be updated
	 * @param code	movie code, null if not to change
	 * @param title movie title, null if not to change 
	 * @param description movie description, null if not to change
	 * @param genre genre of the movie, null if not to change
	 * @param language language of the movie, null if not to change
	 * @param rating rating of the movie, null if not to change
	 * @param status status of the movie, null if not to change
	 */
	public void updateMovie(int index, String code, String title, String description, Genre genre, Language language, Rating rating, Status status){
		throw new UnsupportedOperationException();
	}
	
	
	/***************************************************************************/
	/***************************************************************************/
	
	/*
	 * Cineplex
	 */
	/**
	 * @param name name of the cineplex
	 * @return index of the newly added Cineplex
	 * Lihau: changed - previous : true if succefully added a cineplex to cineplex listing
	 */
	public int addCineplex(String name){
		throw new UnsupportedOperationException();
	}
	/**
	 * @param index index to be remove from the cineplex array list
	 */
	public void removeCineplex(int index){
		throw new UnsupportedOperationException();
	}
	/**
	 * @param index index of the cineplex in the cineplex array list
	 * @param name
	 */
	public void updateCineplex(int index, String name){
		throw new UnsupportedOperationException();
	}
	/**
	 * 
	 * @param index index of cineplex in cineplex array list
	 * @param id id of the cinema to be added
	 * @param type NORMAL or PLATINUM_SUITE
	 * @return
	 */
	public boolean addCinema(int index, String id, Cinema.CinemaType type){
		throw new UnsupportedOperationException();
	}
	/**
	 * remove the cinema from the cineplex
	 * @param cineplexIndex index of the cineplex in the cineplex array list
	 * @param cinemaIndex	index of the cinema in the cinema array list
	 */
	public void removeCinema(int cineplexIndex, int cinemaIndex){
		throw new UnsupportedOperationException();
	}
	/**
	 * change the cinema id
	 * @param cineplexIndex index of the cineplex in the cineplex array list
	 * @param cinemaIndex	index of the cinema in the cinema array list
	 * @param id			new id of the cinema
	 */
	public void updateCinema(int cineplexIndex, int cinemaIndex, String id){
		throw new UnsupportedOperationException();
	}
	
	
	
	/***************************************************************************/
	/***************************************************************************/
	/*
	 * Show Time
	 */
	
	
	/**
	 * set the movie to show on the specific cinema for the specific hour
	 * @param cineplexIndex index of the cineplex in cineplex array list
	 * @param cinemaIndex   index of the cinema in cinema array list
	 * @param movieIndex    index of the movie in movie array list
	 * @param year          year
	 * @param month  		month
	 * @param date			date
	 * @param hour			hour
	 */
	public void showMovie(int cineplexIndex, int cinemaIndex, int movieIndex, int year, int month, int date, int hour){
		throw new UnsupportedOperationException();
	}
	/**
	 * set the movie to show on the specific cinema for the whole day
	 * @param cineplexIndex index of the cineplex in cineplex array list
	 * @param cinemaIndex   index of the cinema in cinema array list
	 * @param movieIndex    index of the movie in movie array list
	 * @param year          year
	 * @param month  		month
	 * @param date			date
	 */
	public void showMovie(int cineplexIndex, int cinemaIndex, int movieIndex, int year, int month, int date){
		throw new UnsupportedOperationException();
	}
	/**
	 * set the cinema to not showing any movie for the specific hour
	 * @param cineplexIndex index of the cineplex in cineplex array list
	 * @param cinemaIndex   index of the cinema in cinema array list
	 * @param year          year
	 * @param month  		month
	 * @param date			date
	 * @param hour			hour
	 */
	public void removeMovie(int cineplexIndex, int cinemaIndex, int year, int month, int date, int hour){
		throw new UnsupportedOperationException();
	}
	/**
	 * set the cinema to not showing any movie for the specific date
	 * @param cineplexIndex index of the cineplex in cineplex array list
	 * @param cinemaIndex   index of the cinema in cinema array list
	 * @param year          year
	 * @param month  		month
	 * @param date			date in the month
	 */
	public void removeMovie(int cineplexIndex, int cinemaIndex, int year, int month, int date){
		throw new UnsupportedOperationException();
	}
	
	
	/***************************************************************************/
	/***************************************************************************/
	/*
	 * Report
	 */

	public String getSalesReportByMovie(){
		throw new UnsupportedOperationException();
	}
	public String getSalesReportByCineplex(){
		throw new UnsupportedOperationException();
	}
	public String getSalesReportByMonth(){
		throw new UnsupportedOperationException();
	}
	public String getSalesReportByDay(){
		throw new UnsupportedOperationException();
	} 

}
