package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.Ticket;
import model.cinema.Cinema;
import model.cinema.Cineplex;
import model.cinema.Cinema.CinemaType;
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

public class ConsoleViewApp implements IViewApp {
	
	private final static int CHOICE_LOGIN_LOGIN = 1;
	private final static int CHOICE_LOGIN_REGISTER = 2;
	private final static int CHOICE_LOGIN_EXIT = 3;

	private final static int CHOICE_CUSTOMER_NOW_SHOWING = 1;
	private final static int CHOICE_CUSTOMER_COMING_SOON = 2;
	private final static int CHOICE_CUSTOMER_BY_CINEPLEX = 3;
	private final static int CHOICE_CUSTOMER_BY_MOVIE = 4;
	private final static int CHOICE_CUSTOMER_BOOK_TICKET = 5;
	private final static int CHOICE_CUSTOMER_CHECK_BOOKING = 6;
	private final static int CHOICE_CUSTOMER_EXIT = 7;

	private final static int CHOICE_STAFF_ADD_MOVIE = 1;
	private final static int CHOICE_STAFF_UPDATE_MOVIE = 2;
	private final static int CHOICE_STAFF_REMOVE_MOVIE = 3;
	private final static int CHOICE_STAFF_ADD_SHOWTIME = 4;
	private final static int CHOICE_STAFF_REMOVE_SHOWTIME = 5;
	
	private final static int CHOICE_STAFF_ADD_CINEPLEX = 6;
	private final static int CHOICE_STAFF_REMOVE_CINEPLEX = 7;
	private final static int CHOICE_STAFF_UPDATE_CINEPLEX = 8;
	private final static int CHOICE_STAFF_ADD_CINEMA = 9;
	private final static int CHOICE_STAFF_REMOVE_CINEMA = 10;
	private final static int CHOICE_STAFF_UPDATE_CINEMA = 11;
	
	private final static int CHOICE_STAFF_REVENUE_CINEPLEX = 12;
	private final static int CHOICE_STAFF_REVENUE_PERIOD = 13;
	private final static int CHOICE_STAFF_REVENUE_MOVIE = 14;
	private final static int CHOICE_STAFF_EXIT = 15;
	
	private Scanner in;
	private AbstractCinemaApp cinemaApp;
	
	public ConsoleViewApp() {
		in = new Scanner(System.in);
	}
	
	@Override
	public void start(AbstractCinemaApp cinemaApp, boolean admin) {
		int choice;
		this.cinemaApp = cinemaApp;
		while(true){
			printMenu(admin);
			try {
				do{
					choice = getInputInteger("Option: ");
				}while(!executes(choice, admin));
			} catch (ExitException e) {
				break;
			}
		}
	}

	@Override
	public boolean auth(LoginApp loginApp) {
		
		while(true){
			printLoginMenu();
			try{
				int choice = getInputInteger();
				switch(choice){
					case CHOICE_LOGIN_LOGIN:
						try{
							if(loginUser(loginApp)){
								print("You have successfully logged in!");
								return true;
							}
						}catch (ExitException e) {}
						break;
					case CHOICE_LOGIN_REGISTER:
						try{
							registerUser(loginApp);
							print("Congratulations, you have successfully registered!");
						}catch (ExitException e) {}
						break;
					case CHOICE_LOGIN_EXIT:
						return false;
				}
			}catch(ExitException e){
				return false;
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
		print("7.Exit");
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
		print("CINEPLEX & CINEMA SETTINGS");
		print("6.Add Cineplex");
		print("7.Remove Cineplex");
		print("8.Update Cineplex");
		print("9.Add Cinema");
		print("10.Remove Cinema");
		print("11.Update Cinema");
		print("");
		print("REVENUE REPORT");
		print("12.by cineplex");
		print("13.by period");
		print("14.by movie");
		print("");
		print("15. exit");
		
	}
	
	private boolean executes(int choice, boolean admin) throws ExitException{
		if(admin)
			return staffExecutes(choice);
		else
			return customerExecutes(choice);
	}
	private boolean customerExecutes(int choice) throws ExitException{
		try{
			switch(choice){
			case CHOICE_CUSTOMER_NOW_SHOWING:
				showShowingMovie();
				holdOn();
				break;
			case CHOICE_CUSTOMER_COMING_SOON:
				showComingSoon();
				holdOn();
				break;
			case CHOICE_CUSTOMER_BY_MOVIE:
				showShowTimeByMovie();
				holdOn();
				break;
			case CHOICE_CUSTOMER_BY_CINEPLEX:
				showShowTimeByCineplex();
				holdOn();
				break;
			case CHOICE_CUSTOMER_BOOK_TICKET:
				bookTicket();
				break;
			case CHOICE_CUSTOMER_CHECK_BOOKING:
				checkBooking();
				holdOn();
				break;
			case CHOICE_CUSTOMER_EXIT:
				throw new ExitException();
			default:
				return false;
			}
		}catch(ExitException e){
			if(choice == CHOICE_CUSTOMER_EXIT)
				throw e;
		}
		return true;
	}
	private boolean staffExecutes(int choice) throws ExitException{
		try{
			switch(choice){
			case CHOICE_STAFF_ADD_MOVIE:
				addMovie();
				break;
			case CHOICE_STAFF_UPDATE_MOVIE:
				updateMovie();
				break;
			case CHOICE_STAFF_REMOVE_MOVIE:
				removeMovie();
				break;
			case CHOICE_STAFF_ADD_SHOWTIME:
				addShowTime();
				break;
			case CHOICE_STAFF_REMOVE_SHOWTIME:
				removeShowTime();
				break;
			case CHOICE_STAFF_ADD_CINEPLEX:
				addCineplex();
				break;
			case CHOICE_STAFF_REMOVE_CINEPLEX:
				removeCineplex();
				break;
			case CHOICE_STAFF_UPDATE_CINEPLEX:
				updateCineplex();
				break;
			case CHOICE_STAFF_ADD_CINEMA:
				addCinema();
				break;
			case CHOICE_STAFF_REMOVE_CINEMA:
				removeCinema();
				break;
			case CHOICE_STAFF_UPDATE_CINEMA:
				updateCinema();
				break;
			case CHOICE_STAFF_REVENUE_CINEPLEX:
			case CHOICE_STAFF_REVENUE_PERIOD:
			case CHOICE_STAFF_REVENUE_MOVIE:
				break;
			case CHOICE_STAFF_EXIT:
				throw new ExitException();
			default:
				return false;
			}
		}catch(ExitException e){
			if(choice == CHOICE_STAFF_EXIT)
				throw e;
		}
		return true;
	}
	private void showShowingMovie(){
		print("Now Showing Movies: ");
		int i = 1;
		for(Movie movie : cinemaApp.getMovieListing(Status.NOW_SHOWING)){
			printNumeric( i++, movie.toString());
			print("");
		}
		if(i == 1)
			print("No movie available.");
	}
	private void showComingSoon(){
		print("Coming Soon Movies: ");
		
		int i = 1;
		for(Movie movie : cinemaApp.getMovieListing(Status.COMING_SOON)){
			printNumeric( i++, movie.toString());
			print("");
		}
		if(i == 1)
			print("No movie available.");
	}
	
	private ShowTime[] showShowTimeByMovie() throws ExitException{
		int movieIndex;
		int cineplexIndex = 0, cinemaIndex = 0;
		int year = 0, month = 0, date = 0;
		
		boolean filterCineplex = false, filterCinema = false, filterDate = false;
		
		movieIndex = chooseShowingMovie();
		
		filterCineplex = yes_or_no("Filter by Cineplex");
		if(filterCineplex){
			cineplexIndex = chooseCineplex();
			filterCinema = yes_or_no("Filter by Cinema");
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
		if(st.length == 0)
			print("No ShowTime available.");
		else{
			int a = 0;
			for (ShowTime showTime : st) {
				printNumeric( a++, showTime.toString());
			}
		}
		return st;
	}
	private ShowTime[] showShowTimeByCineplex() throws ExitException{
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
		if(st.length == 0)
			print("No Show Time available.");
		else{
			int a = 0;
			for (ShowTime showTime : st) {
				printNumeric( a++, showTime.toString());
			}
		}
		return st;
	}
	
	private void bookTicket() throws ExitException{
		while(true){
			ShowTime showtime = chooseShowTime();
			if(cinemaApp.isShowTimeFull(showtime)){
				if(yes_or_no("Exit")){
					return;
				}
			}else{
				Seat[] seats = cinemaApp.getShowTimeSeatAllocation(showtime);
				while(true){
					int seatIndex = chooseSeat(seats);
					try {
						cinemaApp.purchaseSeat(showtime, seatIndex);
						return;
					}catch(SeatOccupiedException e){
						if(!yes_or_no("Choose again")){
							break;
						}
					} catch (IndexOutOfBoundsException | UserNotLoggedInException e) {
						e.printStackTrace();
					}
				}
				if(yes_or_no("Exit")) return;
			}
		}
	}
	private void checkBooking(){
		try {
			print("Booking History (Ticket Bought): ");
			int i = 0;
			for (Ticket ticket : cinemaApp.getPurchaseHistory()) {
				printNumeric(i++, ticket.toString());
			}
			if(i == 0) print("No history available.");
		} catch (UserNotLoggedInException e) {
			e.printStackTrace();
		}
	}
	
	private void addMovie() throws ExitException{
		String code = getInputString("Code: ");
		String title = getInputString("Title: ");
		String description = getInputString("Description: ");
		Genre genre = chooseGenre();
		Language language = chooseLanguage();
		Rating rating = chooseRating();
		cinemaApp.addMovie(code, title, description, genre, language, rating);
		print("Movie successfully added!");
	}
	private void updateMovie() throws ExitException{
		int index = chooseAllMovie();
		cinemaApp.updateMovie(index, 
				yes_or_no("Change Code") ? getInputString("New Code: ") :null,
				yes_or_no("Change Title") ? getInputString("New Title: ") :null,
				yes_or_no("Change Description") ? getInputString("New Description: ") :null,
				yes_or_no("Change Genre") ? chooseGenre() :null,
				yes_or_no("Change Language") ? chooseLanguage() :null,
				yes_or_no("Change Rating") ? chooseRating() :null,
				yes_or_no("Change Status") ? chooseStatus() :null);
		print("Movie successfully updated!");
	}
	private void removeMovie() throws ExitException{
		cinemaApp.removeMovie(chooseAllMovie());
		print("Movie successfully removed!");
	}
	
	private void addShowTime() throws ExitException{
		print("Add ShowTime");
		int movieIndex = chooseAllMovie();
		int cineplexIndex = chooseCineplex();
		int cinemaIndex = chooseCinema(cineplexIndex);
		int year = getInputInteger("Year: ");
		int month = getInputInteger("Month: ", 1, 12);
		int date = getInputInteger("Day: ", 1, 31);
		int hour = getInputInteger("Hour: ", 0, 24);
		cinemaApp.showMovie(cineplexIndex, cinemaIndex, movieIndex, year, month, date, hour);
		print("ShowTime added!");
	}
	private void removeShowTime() throws ExitException{
		print("Remove ShowTime");
		int cineplexIndex = chooseCineplex();
		int cinemaIndex = chooseCinema(cineplexIndex);
		int year = getInputInteger("Year: ");
		int month = getInputInteger("Month: ", 1, 12);
		int date = getInputInteger("Day: ", 1, 31);
		int hour = getInputInteger("Hour: ", 0, 24);
		cinemaApp.removeMovie(cineplexIndex, cinemaIndex, year, month, date, hour);
		print("Show Time removed!");
	}
	
	private void addCineplex() throws ExitException{
		print("Add Cineplex");
		String name = getInputString("Name: ");
		cinemaApp.addCineplex(name);
		print("Cineplex successfully added");
	}
	private void removeCineplex() throws ExitException{
		print("Remove Cineplex");
		cinemaApp.removeCineplex(chooseCineplex());
		print("Cineplex successfully removed");
	}
	private void updateCineplex() throws ExitException{
		print("Update Cineplex");
		cinemaApp.updateCineplex(chooseCineplex(), getInputString("New name: "));
		print("Cineplex successfully updated");
	}
	private void addCinema() throws ExitException{
		print("Add Cinema");
		int index = chooseCineplex(); 
		String id = getInputString("ID: ");
		cinemaApp.addCinema(index, id, chooseCinemaType());
		print("Cinema successfully added");
	}
	private void removeCinema() throws ExitException{
		print("Remove Cinema");
		int cineplexIndex = chooseCineplex();
		cinemaApp.removeCinema(cineplexIndex, chooseCinema(cineplexIndex));
		print("Cinema successfully removed");
	}
	private void updateCinema() throws ExitException{
		print("Update Cineplex");
		int cineplexIndex = chooseCineplex();
		cinemaApp.updateCinema(cineplexIndex, chooseCinema(cineplexIndex), getInputString("New id: "));
		print("Cinema successfully updated");		
	}
	
	/*
	 * Choosing
	 */
	private int chooseCineplex() throws ExitException{
		int choice;
		do{
			print("Choose a cineplex: ");
			showCineplex();
			choice = getInputInteger("Choice: ");
		}while(choice < 0 || choice >= cinemaApp.getNumberOfCineplex());
		return choice;
	}
	private int chooseCinema(int cineplexIndex) throws ExitException{
		int choice;
		do{
			print("Choose a cinema: ");
			showCinema(cineplexIndex);
			choice = getInputInteger("Choice: ");
		}while(choice < 0 || choice >= cinemaApp.numCinemaInCineplex(cineplexIndex));
		return choice;
	}
	private int chooseShowingMovie() throws ExitException{
		int max = cinemaApp.getMovieListing(Status.NOW_SHOWING).size();
		print("Choose Movie: ");
		showShowingMovie();
		return getInputInteger("Choice: ", 1, max) - 1;
	}
	private int chooseAllMovie() throws ExitException{
		print("Choose Movie: ");
		int i = 1;
		for (Movie movie : cinemaApp.getMovieListing()) {
			printNumeric(i++, movie.getTitle());
		}
		return getInputInteger("Choice: ", 1, cinemaApp.getNumberOfMovies()) - 1;
	}
	private ShowTime chooseShowTime() throws ExitException{
		while(true){
			print("Choose Show Time: ");
			print("Search by: ");
			print("1. Cineplex");
			print("2. Movie");
			int choice;
			while(true){
				choice = getInputInteger("Choice: ");
				if(choice == 1){
					ShowTime[] st = showShowTimeByCineplex();
					if(st.length == 0)	break;
					return st[getInputInteger("Choice: ", 0, st.length - 1)];
				}else if(choice == 2){
					ShowTime[] st = showShowTimeByMovie();
					if(st.length == 0)	break;
					return st[getInputInteger("Choice: ", 0, st.length - 1)];
				}
			}
		}
	}
	private int chooseSeat(Seat[] seats) throws ExitException{
		print("Choose Seat: ");
		showSeat(seats);
		return getInputInteger("Choice: ", 0, seats.length - 1);
	}
	private Language chooseLanguage() throws ExitException{
		print("Choose Language:");
		Language[] langs = Language.values();
		for (int i = 0; i < langs.length; i++) {
			printNumeric(i+1, langs[i].name());
		}
		return langs[getInputInteger("Choice: ", 1, langs.length) - 1];
	}
	private Genre chooseGenre() throws ExitException{
		print("Choose Genre:");
		Genre[] genres = Genre.values();
		for (int i = 0; i < genres.length; i++) {
			printNumeric(i+1, genres[i].name());
		}
		return genres[getInputInteger("Choice: ", 1, genres.length) - 1];
	}
	private Rating chooseRating() throws ExitException{
		print("Choose Rating:");
		Rating[] ratings = Rating.values();
		for (int i = 0; i < ratings.length; i++) {
			printNumeric(i+1, ratings[i].name());
		}
		return ratings[getInputInteger("Choice: ", 1, ratings.length) - 1];
	}
	private Status chooseStatus() throws ExitException{
		print("Choose Status:");
		Status[] status = Status.values();
		for (int i = 0; i < status.length; i++) {
			printNumeric(i+1, status[i].name());
		}
		return status[getInputInteger("Choice: ", 1, status.length) - 1];
	}
	private CinemaType chooseCinemaType() throws ExitException{
		print("Choose cinema type:");
		CinemaType[] cinemaType = CinemaType.values();
		for (int i = 0; i < cinemaType.length; i++) {
			printNumeric(i+1, cinemaType[i].name());
		}
		return cinemaType[getInputInteger("Choice: ", 1, cinemaType.length) - 1];
	}
	/*
	 * Showing 
	 */
	
	private void showCineplex(){
		print("Cineplex: ");
		int i = 0;
		for(Cineplex c : cinemaApp.getAllCineplex()){
			printNumeric(i++, c.toString());
		}
		if(i == 0)
			print("No cinplex available");
	}
	private void showCinema(int cineplexIndex){
		print("Cinema: ");
		int i = 0;
		for(Cinema c : cinemaApp.getAllCinema(cineplexIndex)){
			print(String.format("%d, %s", i++, c.toString()));
		}
		if(i == 0)
			print("No cinema available");
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
		print("3. Exit");
		print("");
	}
	private void registerUser(LoginApp loginApp) throws ExitException{
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
			}
		}
	}
	private boolean loginUser(LoginApp loginApp) throws ExitException{
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
	private int getInputInteger() throws ExitException{
		while(true){
			try{
				int i = in.nextInt();
				in.nextLine();
				return i;
			}catch(InputMismatchException e){
				String s = in.nextLine();
				if(s.toLowerCase().equals("exit")){
					throw new ExitException();
				}
				print("Try again!");
			}
		}
	}
	private int getInputInteger(String question, int llimit, int ulimit) throws ExitException{
		int r;
		do{
			r = getInputInteger(question);
		}while(r < llimit || r > ulimit);
		return r;
	}
	private int getInputInteger(String question) throws ExitException{
		print(question, false);
		return getInputInteger();
	}
	private String getInputString() throws ExitException{
		String input = in.nextLine();
		if(input.toLowerCase().equals("exit"))
			throw new ExitException();
		return input;
	}
	private String getInputString(String question) throws ExitException{
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
		print(String.format("%d. %s", d, s));
	}
	private boolean match(String input, String compare){
		return input.equalsIgnoreCase(compare);
	}
	private boolean yes_or_no(String message) throws ExitException{
		while(true){
			String i = getInputString(message + "(yes/no): ");
			if(match(i, "yes"))
				return true;
			else if(match(i, "no"))
				return false;
		}
	}
	private void holdOn(){
		print("<Press ENTER key to continue>");
		in.nextLine();
	}
	class ExitException extends Exception{
		private static final long serialVersionUID = 1L;
	}
}
