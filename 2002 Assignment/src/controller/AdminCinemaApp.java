package controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import model.cinema.Cinema;
import model.cinema.Cineplex;
import model.cinema.PlatinumSuiteCinema;
import model.movie.Genre;
import model.movie.Language;
import model.movie.Movie;
import model.movie.Status;
import model.showtime.DayShowTime;
import model.showtime.ShowTime;
import model.showtime.ShowTimetable;

public class AdminCinemaApp extends CinemaApp{
	/*
	 * Movies
	 */
	/**
	 * 
	 * @return true if successfully added, false otherwise
	 */
	public boolean addMovie(String code, String title, String description, Genre genre, Language language, int rating, Status status){
		return movieList.add(new Movie(code, title, description, genre, language, rating, status));
	}
	
	/**
	 * 
	 * @param index index of the movie in the arraylist
	 */
	public void removeMovie(int index){
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
	public void updateMovie(int index, String code, String title, String description, Genre genre, Language language, Integer rating, Status status){
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
	 * @return true if succefully added a cineplex to cineplex listing
	 */
	public boolean addCineplex(String name){
		return cineplexList.add(new Cineplex(name));
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
		cinema.remove();
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
		Calendar c = GregorianCalendar.getInstance();
		c.set(year, month, date);
		Date day = c.getTime();
		ShowTimetable timetable = cinema.getShowTimetable();
		DayShowTime dayShowTime = timetable.addDayShowTime(day);
		ShowTime showTime = dayShowTime.getShowTime(hour);
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
		Calendar c = GregorianCalendar.getInstance();
		c.set(year, month, date);
		Date day = c.getTime();
		ShowTimetable timetable = cinema.getShowTimetable();
		DayShowTime dayShowTime = timetable.addDayShowTime(day);
		for (ShowTime showTime : dayShowTime.getShowTimes()) {
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
		Calendar c = GregorianCalendar.getInstance();
		c.set(year, month, date);
		Date day = c.getTime();
		ShowTimetable timetable = cinema.getShowTimetable();
		DayShowTime dayShowTime = timetable.addDayShowTime(day);
		ShowTime showTime = dayShowTime.getShowTime(hour);
		showTime.setMovie(null);
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
		Calendar c = GregorianCalendar.getInstance();
		c.set(year, month, date);
		Date day = c.getTime();
		ShowTimetable timetable = cinema.getShowTimetable();
		DayShowTime dayShowTime = timetable.addDayShowTime(day);
		for (ShowTime showTime : dayShowTime.getShowTimes()) {
			showTime.setMovie(null);
		}
	}
	
	
	/***************************************************************************/
	/***************************************************************************/
	/*
	 * Report
	 */

	public String getSalesReportByMovie(){
		//TODO yet to be implemented
		return null;
	}
	public String getSalesReportByCineplex(){
		//TODO yet to be implemented
		return null;
	}
	public String getSalesReportByMonth(){
		//TODO yet to be implemented
		return null;
	}
	public String getSalesReportByDay(){
		//TODO yet to be implemented
		return null;
	} 
}
