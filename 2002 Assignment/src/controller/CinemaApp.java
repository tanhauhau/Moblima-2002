package controller;
import java.util.ArrayList;

import model.Ticket;
import model.cinema.Cinema;
import model.cinema.Cineplex;
import model.cinema.seat.Seat;
import model.cinema.seat.Seat.SeatOccupiedException;
import model.cinema.seat.SeatAllocation;
import model.cinema.showtime.ShowTime;
import model.customer.Customer;
import model.customer.User.UserNotLoggedInException;
import model.movie.Movie;
import model.movie.Status;

public class CinemaApp extends AbstractCinemaApp{
	protected ArrayList<Movie> movieList;
	protected ArrayList<Cineplex> cineplexList;
	protected Customer customer;
	
	/**
	 */
	public CinemaApp(){
		movieList = new ArrayList<Movie>();
		cineplexList = new ArrayList<Cineplex>();	
	}
	public void setCustomer(Customer customer){
		this.customer = customer;
		if(customer == null)
			 throw new IllegalArgumentException("Customer is null");
	}
	/**
	 * Get all movie listing
	 * @return array list of movie listing
	 */
	public ArrayList<Movie> getMovieListing(){
		return movieList;
	}
	/**
	 * Get all movie listing
	 * @param status filter status of movie
	 * @return array list of movie listing
	 */
	public ArrayList<Movie> getMovieListing(Status status){
		ArrayList<Movie> result = new ArrayList<Movie>();
		for (Movie movie : movieList) {
			if(movie.getStatus().equals(status)){
				result.add(movie);
			}
		}
		return result;
	}	
	/**
	 * @return total number of movie in the movie listing array
	 */
	public int getNumberOfMovies(){
		return movieList.size();
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
		return cineplexList;
	}
	/**
	 * @return Number of cineplex
	 */
	public int getNumberOfCineplex(){
		return cineplexList.size();
	}
	/**
	 * @param index index of cineplex in the cineplex array list
	 * @return total number of cinema in the cineplex
	 */
	public int numCinemaInCineplex(int index){
		return cineplexList.get(index).getNumberOfCinema();
	}

	/**
	 * get the cinema
	 * @param cineplexIndex index of the cineplex in the cineplex array list
	 * @param cinemaIndex	index of the cinema in the cinema array list
	 * @return the cinema
	 */
	public Cinema getCinema(int cineplexIndex, int cinemaIndex){
		return cineplexList.get(cineplexIndex).getCinemas().get(cinemaIndex);
	}
	/**
	 * get all cinema in the cineplex
	 * @param cineplexIndex index of the cineplex in the cineplex array list
	 * @return array list of cinemas
	 */
	public ArrayList<Cinema> getAllCinema(int cineplexIndex){
		return cineplexList.get(cineplexIndex).getCinemas();
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
		Cinema cinema = getCinema(cineplexIndex, cinemaIndex);
		return cinema.getShowTime(year, month, date, hour)[0];
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
		Cinema cinema = getCinema(cineplexIndex, cinemaIndex);
		ShowTime[] showTime = cinema.getShowTime(year, month, date);
		ArrayList<ShowTime> s = new ArrayList<ShowTime>();
		for (ShowTime ss : showTime) {
			if(ss.hasMovie())
				s.add(ss);
		}
		ShowTime[] r = new ShowTime[s.size()];
		return s.toArray(r);
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
		Cineplex cineplex = cineplexList.get(cineplexIndex);
		ArrayList<ShowTime> shows = new ArrayList<ShowTime>();
		for (Cinema c : cineplex.getCinemas()) {
			for (ShowTime showTime : c.getShowTime(year, month, date)) {
				if(showTime.hasMovie())
					shows.add(showTime);
			}
//			shows.addAll(Arrays.asList());
		}
		ShowTime[] s = new ShowTime[shows.size()];
		return shows.toArray(s);
	}
	/**
	 * Get all movie show time in all cineplexes
	 * @return	array of show time
	 */
	public ShowTime[] getShowTime(){
		ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
		
		for (Cineplex cineplex : cineplexList) {
			for (Cinema cinema : cineplex.getCinemas()) {
				for (ShowTime showTime : cinema.getShowTime()) {
					if(showTime.hasMovie())
						showTimes.add(showTime);
				}
//				showTimes.addAll(Arrays.asList(cinema.getShowTime()));
			}
		}
		ShowTime[] result = new ShowTime[showTimes.size()];
		return showTimes.toArray(result);
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
		Cinema cinema = getCinema(cineplexIndex, cinemaIndex);
		Movie movie = movieList.get(movieIndex);
		ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
		
		ShowTime[] all = cinema.getShowTime(year, month, day);
		
		for (ShowTime showTime : all) {
			if(showTime.hasMovie() && showTime.getMovie().equals(movie)){
				showTimes.add(showTime);
			}
		}
		ShowTime[] result = new ShowTime[showTimes.size()];
		return showTimes.toArray(result);
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
		Cineplex cineplex = cineplexList.get(cineplexIndex);
		Movie movie = movieList.get(movieIndex);
		ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
		
		for (Cinema cinema : cineplex.getCinemas()) {
			ShowTime[] all = cinema.getShowTime(year, month, day);
			for (ShowTime showTime : all) {
				if(showTime.hasMovie() && showTime.getMovie().equals(movie)){
					showTimes.add(showTime);
				}
			}
		}
		ShowTime[] result = new ShowTime[showTimes.size()];
		return showTimes.toArray(result);
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
		Movie movie = movieList.get(movieIndex);
		ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
		
		for (Cineplex cineplex : cineplexList) {
			for (Cinema cinema : cineplex.getCinemas()) {
				ShowTime[] all = cinema.getShowTime(year, month, day);
				for (ShowTime showTime : all) {
					if(showTime.hasMovie() && showTime.getMovie().equals(movie)){
						showTimes.add(showTime);
					}
				}
			}
		}
		ShowTime[] result = new ShowTime[showTimes.size()];
		return showTimes.toArray(result);
	}
	
	/**
	 * get all the show time for the specific movie in the specific cinema
	 * @param cineplexIndex 	index of the cineplex in cineplex array list
	 * @param cinemaIndex		index of the cinema in cinema array list
	 * @param movieIndex		index of the movie in movie array list
	 * @return					array of show time
	 */
	public ShowTime[] getMovieShowTime(int cineplexIndex, int cinemaIndex, int movieIndex){
		Cinema cinema = getCinema(cineplexIndex, cinemaIndex);
		Movie movie = movieList.get(movieIndex);
		ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
		
		ShowTime[] all = cinema.getShowTime();
		for (ShowTime showTime : all) {
			if(showTime.hasMovie() && showTime.getMovie().equals(movie)){
				showTimes.add(showTime);
			}
		}
		ShowTime[] result = new ShowTime[showTimes.size()];
		return showTimes.toArray(result);
	}
	/**
	 * get all the show time for the specific movie in the specific cineplex
	 * @param cineplexIndex 	index of the cineplex in cineplex array list
	 * @param movieIndex		index of the movie in movie array list
	 * @return					array of show time
	 */
	public ShowTime[] getMovieShowTime(int cineplexIndex, int movieIndex){
		Cineplex cineplex = cineplexList.get(cineplexIndex);
		Movie movie = movieList.get(movieIndex);
		ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
		
		for (Cinema cinema : cineplex.getCinemas()) {
			ShowTime[] all = cinema.getShowTime();
			for (ShowTime showTime : all) {
				if(showTime.hasMovie() && showTime.getMovie().equals(movie)){
					showTimes.add(showTime);
				}
			}
		}
		ShowTime[] result = new ShowTime[showTimes.size()];
		return showTimes.toArray(result);
	}
	/**
	 * get all the show time for the specific movie in all cineplexes
	 * @param movieIndex		index of the movie in movie array list
	 * @return					array of show time
	 */
	public ShowTime[] getMovieShowTime(int movieIndex){
		Movie movie = movieList.get(movieIndex);
		ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
		
		for (Cineplex cineplex : cineplexList) {
			for (Cinema cinema : cineplex.getCinemas()) {
				ShowTime[] all = cinema.getShowTime();
				for (ShowTime showTime : all) {
					if(showTime.hasMovie() && showTime.getMovie().equals(movie)){
						showTimes.add(showTime);
					}
				}
			}
		}
		ShowTime[] result = new ShowTime[showTimes.size()];
		return showTimes.toArray(result);
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
		return showtime.getSeatAllocations().getAllSeats();
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
		return showtime.getSeatAllocations().getNumberOfAvailableSeats();
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
		return showTime.getSeatAllocations().isFull();
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
	public Ticket purchaseSeat(ShowTime showtime, int seatIndex) throws SeatOccupiedException, IndexOutOfBoundsException{
		SeatAllocation seatAlloc = showtime.getSeatAllocations(); 
		Seat seat = seatAlloc.getAllSeats()[seatIndex];
		Ticket ticket = Ticket.generateTicket(showtime, customer, seat);
		seatAlloc.purchaseSeat(seat, ticket);
		return ticket;
	}
	/**
	 * @return array of ticket the user has bought
	 * @throws UserNotLoggedInException user has not logged in
	 */
	public Ticket[] getPurchaseHistory(){
		ShowTime[] showtimes = getShowTime();
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		for (ShowTime showtime : showtimes) {
			for (Seat seat : showtime.getSeatAllocations().getAllSeats()) {
				if(seat.isOccupied()){
					Ticket ticket = seat.getTicket();
					if(ticket.getCustomer().equals(customer)){
						tickets.add(ticket);
					}
				}
			}
			
		}
		Ticket[] t = new Ticket[tickets.size()];
		return tickets.toArray(t);
	}
}
