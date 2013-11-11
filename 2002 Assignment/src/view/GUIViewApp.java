package view;

import java.util.Scanner;

import model.Ticket;
import model.cinema.Cinema;
import model.cinema.Cineplex;
import model.cinema.seat.Seat;
import model.cinema.seat.Seat.SeatOccupiedException;
import model.cinema.showtime.ShowTime;
import model.customer.User.LoginFailedException;
import model.customer.User.UserNameExistsException;
import model.customer.User.UserNotLoggedInException;
import model.movie.Genre;
import model.movie.Language;
import model.movie.Movie;
import model.movie.Rating;
import model.movie.Status;
import controller.AbstractCinemaApp;
import controller.LoginApp;

public class GUIViewApp implements IViewApp {
	
	private final static int CHOICE_LOGIN = 1;
	private final static int CHOICE_REGISTER = 2;

	private final static int CHOICE_CUSTOMER_NOW_SHOWING = 1;
	private final static int CHOICE_CUSTOMER_COMING_SOON = 2;
	private final static int CHOICE_CUSTOMER_BY_CINEPLEX = 3;
	private final static int CHOICE_CUSTOMER_BY_MOVIE = 4;
	private final static int CHOICE_CUSTOMER_BOOK_TICKET = 5;
	private final static int CHOICE_CUSTOMER_CHECK_BOOKING = 6;

	private final static int CHOICE_STAFF_ADD_MOVIE = 1;
	private final static int CHOICE_STAFF_UPDATE_MOVIE = 2;
	private final static int CHOICE_STAFF_REMOVE_MOVIE = 3;
	private final static int CHOICE_STAFF_ADD_SHOWTIME = 4;
	private final static int CHOICE_STAFF_REMOVE_SHOWTIME = 5;
	private final static int CHOICE_STAFF_REVENUE_CINEPLEX = 6;
	private final static int CHOICE_STAFF_REVENUE_PERIOD = 7;
	private final static int CHOICE_STAFF_REVENUE_MOVIE = 8;
	
	private Scanner in;
	private AbstractCinemaApp cinemaApp;
	
	public GUIViewApp() {
		in = new Scanner(System.in);
	}
	
	
	
	@Override
	public void start(AbstractCinemaApp cinemaApp, boolean admin) {
		int choice;
		this.cinemaApp = cinemaApp;
		while(true){
			printMenu(admin);
			choice = getInputInteger("Option: ");
			executes(choice, admin);
		}
	}

	@Override
	public void auth(LoginApp loginApp) {
		while(true){
			printLoginMenu();
			int choice = getInputInteger();
			switch(choice){
				case CHOICE_LOGIN:
					if(loginUser(loginApp)){
						return;
					}
					break;
				case CHOICE_REGISTER:
					registerUser(loginApp);
					return;
			}
		}
	}

	private void printMenu(boolean admin){
		if(admin){
			printStaffMenu();
		}else{
			printCustomerMenu();
		}
	}
	
	private void printCustomerMenu(){
		print("");
		print("Please select your option");
		print("MOVIE HIGHLIGHTS");
		print("1.Now Showing");
		print("2.Coming Soon");
		print("");
		print("SHOWTIME");
		print("3. By Cineplex");
		print("4. By Movie");
		print("");
		print("TICKETING");
		print("5.Book Ticket");
		print("6.Check Booking Status");
		print("");	
	}
	private void printStaffMenu(){
		print("Please select your option");
		print("");
		print("MOVIE SETTINGS");
		print("1.Add Movie");
		print("2.Update Movie Status");
		print("3.Remove Movie");
		print("");
		print("SHOWTIME SETTINGS");
		print("4.Add Showtime");
		print("5.Remove Showtime");
		print("");
		print("REVENUE REPORT");
		print("6.by cineplex");
		print("7.by period");
		print("8.by movie");
		print("");

	}
	
	private void executes(int choice, boolean admin){
		if(admin)
			staffExecutes(choice);
		else
			customerExecutes(choice);
	}
	private void customerExecutes(int choice){
		switch(choice){
		case CHOICE_CUSTOMER_NOW_SHOWING:
			showShowingMovie();
			break;
		case CHOICE_CUSTOMER_COMING_SOON:
			showComingSoon();
			break;
		case CHOICE_CUSTOMER_BY_MOVIE:
			showShowTimeByMovie();
			break;
		case CHOICE_CUSTOMER_BY_CINEPLEX:
			showShowTimeByCineplex();
			break;
		case CHOICE_CUSTOMER_BOOK_TICKET:
			bookTicket();
			break;
		case CHOICE_CUSTOMER_CHECK_BOOKING:
			checkBooking();
			break;
		}
	}
	private void staffExecutes(int choice){
		switch(choice){
		case CHOICE_STAFF_ADD_MOVIE:
			addMovie();
			break;
		case CHOICE_STAFF_UPDATE_MOVIE:
			updateMovie();
		case CHOICE_STAFF_REMOVE_MOVIE:
			removeMovie();
			break;
		case CHOICE_STAFF_ADD_SHOWTIME:
			addShowTime();
			break;
		case CHOICE_STAFF_REMOVE_SHOWTIME:
			removeShowTime();
			break;
		case CHOICE_STAFF_REVENUE_CINEPLEX:
		case CHOICE_STAFF_REVENUE_PERIOD:
		case CHOICE_STAFF_REVENUE_MOVIE:
		}
	}
	private void showShowingMovie(){
		int i = 0;
		for(Movie movie : cinemaApp.getMovieListing(Status.NOW_SHOWING)){
			printNumeric( i++, movie.toString());
			print("");
		}
	}
	private void showComingSoon(){
		int i = 0;
		for(Movie movie : cinemaApp.getMovieListing(Status.COMING_SOON)){
			printNumeric( i++, movie.toString());
			print("");
		}
	}
	
	private ShowTime[] showShowTimeByMovie(){
		int movieIndex;
		int cineplexIndex = 0, cinemaIndex = 0;
		int year = 0, month = 0, date = 0;
		
		boolean filterCineplex = false, filterCinema = false, filterDate = false;
		
		movieIndex = chooseShowingMovie();
		
		filterCineplex = yes_or_no("Filter by Cineplex");
		if(filterCineplex){
			cineplexIndex = chooseCineplex();
			filterCinema = yes_or_no("Filter by Cineplex");
			if(filterCinema)
				cinemaIndex = chooseCinema(cineplexIndex);
		}
		filterDate = yes_or_no("Filter by Date");
		if(filterDate){
			year = getInputInteger("Year: ");
			month = getInputInteger("Month: ");
			date = getInputInteger("Date: ");
		}
		
		print("");
		print("List of show time: ");
		ShowTime[] st = (filterCineplex)? 
							(filterCinema? 
									(filterDate ?
											cinemaApp.getMovieShowTime(cineplexIndex, cinemaIndex, movieIndex, year, month, date):
											cinemaApp.getMovieShowTime(cineplexIndex, cinemaIndex, movieIndex)
											):
									(filterDate ?
											cinemaApp.getMovieShowTime(cineplexIndex, movieIndex, year, month, date) :
											cinemaApp.getMovieShowTime(cineplexIndex, movieIndex))
									):
							(filterDate ?
								cinemaApp.getMovieShowTime(movieIndex, year, month, date):
								cinemaApp.getMovieShowTime(movieIndex))
								;
		
		int a = 0;
		for (ShowTime showTime : st) {
			printNumeric( a++, showTime.toString());
		}
		return st;
	}
	private ShowTime[] showShowTimeByCineplex(){
		int cineplexIndex = chooseCineplex();
		int year = getInputInteger("Year: ");
		int month = getInputInteger("Month: ");
		int date = getInputInteger("Date: ");
		
		boolean cinema = yes_or_no("Filter Cinema");
		ShowTime[] st;
		if(cinema){
			int cinemaIndex = chooseCinema(cineplexIndex);
			st = cinemaApp.getShowTime(cineplexIndex, cinemaIndex, year, month, date);
		}else{
			st = cinemaApp.getShowTime(cineplexIndex, year, month, date);
		}
		int a = 0;
		for (ShowTime showTime : st) {
			printNumeric( a++, showTime.toString());
		}
		return st;
	}
	
	private void bookTicket(){
		ShowTime showtime = chooseShowTime();
		Seat[] seats = cinemaApp.getShowTimeSeatAllocation(showtime);
		int seatIndex = chooseSeat(seats);
		try {
			cinemaApp.purchaseSeat(showtime, seatIndex);
		} catch (IndexOutOfBoundsException | SeatOccupiedException
				| UserNotLoggedInException e) {
			e.printStackTrace();
		}
	}
	private void checkBooking(){
		try {
			print("Booking History (Ticket Bought): ");
			for (Ticket ticket : cinemaApp.getPurchaseHistory()) {
				print(ticket.toString());
			}
		} catch (UserNotLoggedInException e) {
			e.printStackTrace();
		}
	}
	
	private void addMovie(){
		String code = getInputString("Code: ");
		String title = getInputString("Title: ");
		String description = getInputString("Description: ");
		Genre genre = chooseGenre();
		Language language = chooseLanguage();
		Rating rating = chooseRating();
		cinemaApp.addMovie(code, title, description, genre, language, rating);
	}
	private void updateMovie(){
		int index = chooseAllMovie();
		cinemaApp.updateMovie(index, 
				yes_or_no("Change Code") ? getInputString("New Code: ") :null,
				yes_or_no("Change Title") ? getInputString("New Title: ") :null,
				yes_or_no("Change Description") ? getInputString("New Description: ") :null,
				yes_or_no("Change Genre") ? chooseGenre() :null,
				yes_or_no("Change Language") ? chooseLanguage() :null,
				yes_or_no("Change Rating") ? chooseRating() :null,
				yes_or_no("Change Status") ? chooseStatus() :null);
	}
	private void removeMovie(){
		cinemaApp.removeMovie(chooseAllMovie());
	}
	
	private void addShowTime(){
		print("Add ShowTime");
		int movieIndex = chooseAllMovie();
		int cineplexIndex = chooseCineplex();
		int cinemaIndex = chooseCinema(cineplexIndex);
		int year = getInputInteger("Year: ");
		int month = getInputInteger("Month: ", 1, 12);
		int date = getInputInteger("Day: ", 1, 31);
		int hour = getInputInteger("Hour: ", 0, 24);
		cinemaApp.showMovie(cineplexIndex, cinemaIndex, movieIndex, year, month, date, hour);
	}
	private void removeShowTime(){
		print("Remove ShowTime");
		int cineplexIndex = chooseCineplex();
		int cinemaIndex = chooseCinema(cineplexIndex);
		int year = getInputInteger("Year: ");
		int month = getInputInteger("Month: ", 1, 12);
		int date = getInputInteger("Day: ", 1, 31);
		int hour = getInputInteger("Hour: ", 0, 24);
		cinemaApp.removeMovie(cineplexIndex, cinemaIndex, year, month, date, hour);
	}
	/*
	 * Choosing
	 */
	private int chooseCineplex(){
		int choice;
		do{
			print("Choose a cineplex: ");
			showCineplex();
			choice = getInputInteger("Choice: ");
		}while(choice < 0 || choice >= cinemaApp.getNumberOfCineplex());
		return choice;
	}
	private int chooseCinema(int cineplexIndex){
		int choice;
		do{
			print("Choose a cinema: ");
			showCinema(cineplexIndex);
			choice = getInputInteger("Choice: ");
		}while(choice < 0 || choice >= cinemaApp.numCinemaInCineplex(cineplexIndex));
		return choice;
	}
	private int chooseShowingMovie(){
		int max = cinemaApp.getMovieListing(Status.NOW_SHOWING).size();
		print("Choose Movie: ");
		showShowingMovie();
		return getInputInteger("Choice: ", 0, max - 1);
	}
	private int chooseAllMovie(){
		print("Choose Movie: ");
		int i = 0;
		for (Movie movie : cinemaApp.getMovieListing()) {
			printNumeric(i++, movie.getTitle());
		}
		return getInputInteger("Choice: ", 0, cinemaApp.getNumberOfMovies() - 1);
	}
	private ShowTime chooseShowTime(){
		print("Choose Show Time: ");
		print("Search by: ");
		print("1. Cineplex");
		print("2. Movie");
		int choice;
		while(true){
			choice = getInputInteger("Choice: ");
			if(choice == 1){
				ShowTime[] st = showShowTimeByCineplex();
				return st[getInputInteger("Choice: ", 0, st.length - 1)];
			}else if(choice == 2){
				ShowTime[] st = showShowTimeByMovie();
				return st[getInputInteger("Choice: ", 0, st.length - 1)];
			}
		}
	}
	private int chooseSeat(Seat[] seats){
		print("Choose Seat: ");
		showSeat(seats);
		return getInputInteger("Choice: ", 0, seats.length - 1);
	}
	private Language chooseLanguage(){
		print("Choose Language:");
		Language[] langs = Language.values();
		for (int i = 0; i < langs.length; i++) {
			printNumeric(i, langs.toString());
		}
		return langs[getInputInteger("Choice: ", 0, langs.length - 1)];
	}
	private Genre chooseGenre(){
		print("Choose Genre:");
		Genre[] genres = Genre.values();
		for (int i = 0; i < genres.length; i++) {
			printNumeric(i, genres.toString());
		}
		return genres[getInputInteger("Choice: ", 0, genres.length - 1)];
	}
	private Rating chooseRating(){
		print("Choose Rating:");
		Rating[] ratings = Rating.values();
		for (int i = 0; i < ratings.length; i++) {
			printNumeric(i, ratings.toString());
		}
		return ratings[getInputInteger("Choice: ", 0, ratings.length - 1)];
	}
	private Status chooseStatus(){
		print("Choose Status:");
		Status[] status = Status.values();
		for (int i = 0; i < status.length; i++) {
			printNumeric(i, status.toString());
		}
		return status[getInputInteger("Choice: ", 0, status.length - 1)];
	}
	/*
	 * Showing 
	 */
	
	private void showCineplex(){
		int i = 0;
		for(Cineplex c : cinemaApp.getAllCineplex()){
			printNumeric(i++, c.toString());
		}
	}
	private void showCinema(int cineplexIndex){
		int i = 0;
		for(Cinema c : cinemaApp.getAllCinema(cineplexIndex)){
			print(String.format("%d, %s", i++, c.toString()));
		}		
	}
	private void showSeat(Seat[] seats){
		for (int i = 0; i < seats.length; i++) {
			printNumeric( i, seats[i].toString());
		}
	}
	
	private void printLoginMenu(){
		print("");
		print("---------------------------------------------------------------------");
		print("Welcome to MOvie Booking and LIsting Management Application (MOBLIMA)");
		print("---------------------------------------------------------------------");
		print("");
		print("Please select your option: ");
		print("1. Login");
		print("2. Register");
		print("");
	}
	private void registerUser(LoginApp loginApp){
		String name = getInputString("Name: ");
		final String password = getInputString("Password: ");
		final String mobileNumber = getInputString("Mobile: ");
		final int age = getInputInteger("Age: ");
		final String emailAddress = getInputString("Email: ");
		
		boolean admin = yes_or_no("Staff");
		
		while(true){
			try {
				if(admin){
					loginApp.createAdmin(name, password, mobileNumber, emailAddress);
				}else{
					loginApp.createUser(name, password, mobileNumber, age, emailAddress);	
				}
				break;
			} catch (UserNameExistsException e) {
				print("Exists user with the same name");
				name = getInputString("Name: ");
			} catch (LoginFailedException | UserNotLoggedInException e) {
				e.printStackTrace();
			}
		}
	}
	private boolean loginUser(LoginApp loginApp){
		for (int i = 3; i > 0; i--) {
			if(i < 3)
				print(String.format("%d tr%s left.", i, (i > 1)?"ies":"y"));
			String name = getInputString("Name: ");
			String password = getInputString("Password: ");
			try {
				loginApp.login(name, password);
				return true;
			} catch (LoginFailedException e) {
				print(e.getMessage());
			} catch (UserNotLoggedInException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	/*
	 * Utils
	 */
	private int getInputInteger(){
		return in.nextInt();
	}
	private int getInputInteger(String question, int llimit, int ulimit){
		int r;
		do{
			r = getInputInteger(question);
		}while(r < llimit || r > ulimit);
		return r;
	}
	private int getInputInteger(String question){
		print(question, false);
		return getInputInteger();
	}
	private String getInputString(){
		return in.nextLine();
	}
	private String getInputString(String question){
		print(question, false);
		return getInputString();
	}
	private void print(String message, boolean newline){
		if(newline)	System.out.println(message);
		else System.out.print(message); 
	}
	private void print(String message){
		System.out.println(message);
	}
	private void printNumeric(int d, String s){
		printNumeric( d, s);
	}
	private boolean match(String input, String compare){
		return input.equalsIgnoreCase(compare);
	}
	private boolean yes_or_no(String message){
		while(true){
			String i = getInputString(message + "(yes/no): ");
			if(match(i, "yes"))
				return true;
			else if(match(i, "no"))
				return false;
		}
	}
}
