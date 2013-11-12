package controller;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import model.Ticket;
import model.cinema.Cinema;
import model.cinema.Cineplex;
import model.cinema.PlatinumSuiteCinema;
import model.cinema.seat.Seat;
import model.cinema.showtime.ShowTime;
import model.movie.Genre;
import model.movie.Language;
import model.movie.Movie;
import model.movie.Rating;
import model.movie.Status;

public class AdminCinemaApp extends CinemaApp{

	/*
	 * Movies
	 */
	/**
	 * 
	 * @return true if successfully added, false otherwise
	 */
	public boolean addMovie(String code, String title, String description, Genre genre, Language language, Rating rating){
		return movieList.add(new Movie(code, title, description, genre, language, rating, Status.COMING_SOON));
	}
	
	/**
	 * 
	 * @param index index of the movie in the arraylist
	 */
	public void removeMovie(int index){
		Movie movie = movieList.get(index);
		ArrayList<ShowTime> s = movie.getShowTimes();
		ShowTime[] st = new ShowTime[s.size()];
		st = s.toArray(st);
		for (int i = 0; i < st.length; i++) {
			movie.removeShowTime(st[i]);
			st[i].setMovie(null);
		}
		movieList.remove(index);
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
		Movie movie = movieList.get(index);
		if(code != null){ 
			movie.setCode(code);
		} 
		if(title != null){ 
			movie.setTitle(title);
		} 
		if(description != null){ 
			movie.setDescription(description);
		} 
		if(genre != null){ 
			movie.setGenre(genre);
		} 
		if(language != null){ 
			movie.setLanguage(language);
		} 
		if(rating != null){ 
			movie.setRating(rating);
		} 
		if(status != null){ 
			movie.setStatus(status);
		} 
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
		Cineplex c = new Cineplex(name);
		cineplexList.add(c);
		return cineplexList.indexOf(c);
	}
	/**
	 * @param index index to be remove from the cineplex array list
	 */
	public void removeCineplex(int index){
		cineplexList.remove(index);
	}
	/**
	 * @param index index of the cineplex in the cineplex array list
	 * @param name
	 */
	public void updateCineplex(int index, String name){
		cineplexList.get(index).changeName(name);
	}
	/**
	 * 
	 * @param index index of cineplex in cineplex array list
	 * @param id id of the cinema to be added
	 * @param type NORMAL or PLATINUM_SUITE
	 * @return
	 */
	public boolean addCinema(int index, String id, Cinema.CinemaType type){
		Cineplex cineplex = cineplexList.get(index);
		switch (type) {
			case NORMAL:
				return cineplex.addCinema(new Cinema(cineplex, id));
			case PLATINUM_SUITE:
				return cineplex.addCinema(new PlatinumSuiteCinema(cineplex, id));
			default:
				return false;			
		}
	}
	/**
	 * remove the cinema from the cineplex
	 * @param cineplexIndex index of the cineplex in the cineplex array list
	 * @param cinemaIndex	index of the cinema in the cinema array list
	 */
	public void removeCinema(int cineplexIndex, int cinemaIndex){
		Cineplex cineplex = cineplexList.get(cineplexIndex);
		Cinema cinema = cineplex.getCinemas().get(cinemaIndex);
		cineplex.removeCinema(cinema);
	}
	/**
	 * change the cinema id
	 * @param cineplexIndex index of the cineplex in the cineplex array list
	 * @param cinemaIndex	index of the cinema in the cinema array list
	 * @param id			new id of the cinema
	 */
	public void updateCinema(int cineplexIndex, int cinemaIndex, String id){
		getCinema(cineplexIndex, cinemaIndex).setId(id);
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
		Cinema cinema = getCinema(cineplexIndex, cinemaIndex);
		ShowTime showTime = cinema.getShowTime(year, month, date, hour)[0];
		showTime.setMovie(movieList.get(movieIndex));
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
		Cinema cinema = getCinema(cineplexIndex, cinemaIndex);
		for (ShowTime showTime : cinema.getShowTime(year, month, date)) {
			showTime.setMovie(movieList.get(movieIndex));
		}
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
		Cinema cinema = getCinema(cineplexIndex, cinemaIndex);
		ShowTime showTime = cinema.getShowTime(year, month, date, hour)[0];
		Movie movie = showTime.getMovie();
		showTime.setMovie(null);
		movie.removeShowTime(showTime);
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
		Cinema cinema = getCinema(cineplexIndex, cinemaIndex);
		for (ShowTime showTime : cinema.getShowTime(year, month, date)) {
			Movie movie = showTime.getMovie();
			showTime.setMovie(null);
			movie.removeShowTime(showTime);
		}
	}
	
	
	/***************************************************************************/
	/***************************************************************************/
	/*
	 * Report
	 */

	public String getSalesReportByMovie(){
		double[] profit = new double[movieList.size()];
		int[] ticketSold = new int[movieList.size()];
		
		for (int i = 0; i < movieList.size(); i++) {
			profit[i] = 0.0;
			ticketSold[i] = 0;
		}
				
		ShowTime[] showtimes = getShowTime();
		for (ShowTime showtime : showtimes) {
			for (Seat seat : showtime.getSeatAllocations().getAllSeats()) {
				if(seat.isOccupied()){
					Ticket ticket = seat.getTicket();
					Movie movie = ticket.getShowTime().getMovie();
					int movieIndex = movieList.indexOf(movie);
					profit[movieIndex] += ticket.getPrice();
					ticketSold[movieIndex] ++;
				}
			}
			
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < movieList.size(); i++) {
			sb.append("Movie: ").append(movieList.get(i).getTitle()).append(System.lineSeparator());
			sb.append("Ticket Sold: ").append(ticketSold[i]).append(System.lineSeparator());
			sb.append("Profit: ").append(String.format("%.2f", profit[i])).append(System.lineSeparator());
		}
		return sb.toString();
	}
	public String getSalesReportByCineplex(){
		Hashtable<Cinema, Double> profit = new Hashtable<Cinema, Double>();
		Hashtable<Cinema, Integer> ticketSold = new Hashtable<Cinema, Integer>();
		
		ShowTime[] showtimes = getShowTime();
		for (ShowTime showtime : showtimes) {
			for (Seat seat : showtime.getSeatAllocations().getAllSeats()) {
				if(seat.isOccupied()){
					Ticket ticket = seat.getTicket();
					Cinema cinema = ticket.getShowTime().getCinema();
					if(profit.containsKey(cinema)){
						profit.put(cinema, profit.get(cinema) + ticket.getPrice());
						ticketSold.put(cinema, ticketSold.get(cinema) + 1);
					}else{
						profit.put(cinema, ticket.getPrice());
						ticketSold.put(cinema, 1);
					}
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		double p;
		int count;
		for (Cineplex cineplex : cineplexList) {
			sb.append("Cineplex: ").append(cineplex.getName()).append(System.lineSeparator());
			count = 0;
			p = 0;
			for (Cinema cinema : cineplex.getCinemas()) {
				if(profit.containsKey(cinema)){
					sb.append("Cinema: ").append(cinema.getId()).append(System.lineSeparator())
					.append("Ticket Sold: ").append(ticketSold.get(cinema)).append(System.lineSeparator())
					.append("Profit: ").append(String.format("%.2f", profit.get(cinema))).append(System.lineSeparator());
					count += ticketSold.get(cinema);
					p += profit.get(cinema);
				}
			}
			sb.append("Total Ticket Sold: ").append(count).append(System.lineSeparator())
			.append("Total Profit: ").append(String.format("%.2f", p)).append(System.lineSeparator()).append(System.lineSeparator());
		}
		return sb.toString();
	}
	public String getSalesReportByMonth(){
		StringBuffer sb = new StringBuffer();
		int year = 2013;
		for (int month = 0; month < 12; month++) {
			double profit = 0.0;
			int ticketSold = 0;
			for (ShowTime showtime : getShowTime()) {
				Calendar c = Calendar.getInstance();
				c.setTime(showtime.getStartTime());
				if(c.get(Calendar.YEAR) == year && c.get(Calendar.MONTH) == month){
					for (Seat seat : showtime.getSeatAllocations().getAllSeats()) {
						if(seat.isOccupied()){
							Ticket ticket = seat.getTicket();
							profit += ticket.getPrice();
							ticketSold ++;
						}
					}
				}
			}
			if(ticketSold != 0){
				sb.append("Year: ").append(year).append(System.lineSeparator());
				sb.append("Month: ").append(new DateFormatSymbols().getMonths()[month-1]).append(System.lineSeparator());
				sb.append("Profit: ").append(String.format("%.2f", profit)).append(System.lineSeparator());
				sb.append("Ticket Sold: ").append(ticketSold).append(System.lineSeparator());
			}
		}
		return sb.toString();
	}
	public String getSalesReportByDay(){
		StringBuffer sb = new StringBuffer();
		int year = 2013;
		for (int month = 10; month < 11; month++) {
			for (int day = 1; day <= 31; day++) {
				double profit = 0.0;
				int ticketSold = 0;
				for (ShowTime showtime : getShowTime()) {
					Calendar c = Calendar.getInstance();
					c.setTime(showtime.getStartTime());
					if(c.get(Calendar.YEAR) == year && c.get(Calendar.MONTH) == month && c.get(Calendar.DATE) == day){
						for (Seat seat : showtime.getSeatAllocations().getAllSeats()) {
							if(seat.isOccupied()){
								Ticket ticket = seat.getTicket();
								profit += ticket.getPrice();
								ticketSold ++;
							}
						}
					}
				}
				if(ticketSold != 0){
					sb.append("Year: ").append(year).append(System.lineSeparator());
					sb.append(day).append(" ").append(new DateFormatSymbols().getMonths()[month-1]).append(System.lineSeparator());
					sb.append("Profit: ").append(String.format("%.2f", profit)).append(System.lineSeparator());
					sb.append("Ticket Sold: ").append(ticketSold).append(System.lineSeparator());
				}
			}
		}
		return sb.toString();
	}
}
