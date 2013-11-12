package storage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import model.Ticket;
import model.cinema.Cinema;
import model.cinema.Cineplex;
import model.cinema.seat.Seat;
import model.cinema.seat.Seat.SeatOccupiedException;
import model.cinema.showtime.ShowTime;
import model.customer.Customer;
import model.customer.User;
import model.customer.User.UserNotLoggedInException;
import model.movie.Movie;
import controller.AbstractCinemaApp;
import controller.LoginApp;

public class ShowTimeStorageHandler extends StorageHandler{

	private static final String PATH_NAME = "Storage/showtime.csv";
	private AbstractCinemaApp cinemaApp;
	private LoginApp loginApp;
	
	public ShowTimeStorageHandler(AbstractCinemaApp cinemaApp, LoginApp loginApp) {
		this.cinemaApp = cinemaApp;
		this.loginApp = loginApp;
		setFilePath(PATH_NAME);
	}
	
	private int lineCount;
	private ShowTime showtime;
	
	@Override
	public void loadData() {
		lineCount = 0;
		User user = null;
		try {
			user = loginApp.getCurrentUser();
		}catch (UserNotLoggedInException e) {
		}
		super.loadData();
		if(user != null)
			cinemaApp.setCustomer(user.getCustomer());
	}
	
	@Override
	protected void interpretLine(String line) {
		//time -> y,m,d,h,m
		/* cinplexindex, cinemaindex, movieindex, starttime, numberofseats
		 * <seat> -> seatid, customerindex, ticketid, ticketprice
		 */
		StringTokenizer st = new StringTokenizer(line, ",");
		if(lineCount == 0){
			int cineplexIndex = Integer.parseInt(st.nextToken());
			int cinemaIndex = Integer.parseInt(st.nextToken());
			int movieIndex = Integer.parseInt(st.nextToken());
			int syear = Integer.parseInt(st.nextToken());
			int smonth = Integer.parseInt(st.nextToken());
			int sday = Integer.parseInt(st.nextToken());
			int shour = Integer.parseInt(st.nextToken());
			lineCount = Integer.parseInt(st.nextToken());
			
			if(movieIndex != -1){	//has movie
				cinemaApp.showMovie(cineplexIndex, cinemaIndex, movieIndex, syear, smonth, sday, shour);
				showtime = cinemaApp.getShowTime(cineplexIndex, cinemaIndex, syear, smonth, sday, shour);
			}
		}else{
			 /* <seat> -> seatindex, customerindex, ticketid, ticketprice*/
			int seatIndex = Integer.parseInt(st.nextToken());
			boolean occupied = st.hasMoreTokens();
			if(occupied){
				try {
					int customerIndex = Integer.parseInt(st.nextToken());
					User user = loginApp.getUserList().get(customerIndex);
					cinemaApp.setCustomer(user.getCustomer());
					cinemaApp.purchaseSeat(showtime, seatIndex);
				} catch (UserNotLoggedInException | IndexOutOfBoundsException | SeatOccupiedException e) {
				}
			}
			lineCount --;
		}
	}

	@Override
	protected String getDataToWrite() {
		//time -> y,m,d,h
		/* cinplexindex, cinemaindex, movieindex, starttime, numberofseats
		 * <seat> -> seatindex, customerindex, ticketid, ticketprice
		 * */
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat format = new SimpleDateFormat("YYYY,MM,dd,HH");
		for (ShowTime showTime : cinemaApp.getShowTime()) {
			int cineplexIndex = cineplexIndex(showTime.getCinema().getCineplex());
			int cinemaIndex = cinemaIndex(showTime.getCinema(), cineplexIndex);
			int movieIndex = movieIndex(showTime.getMovie());
			String start = format.format(showTime.getStartTime());
			Seat[] seats = showTime.getSeatAllocations().getAllSeats();
			sb.append(String.format("%d,%d,%d,%s,%d", cineplexIndex, cinemaIndex, movieIndex, start, seats.length));
			sb.append(System.lineSeparator());
			for (int i = 0; i < seats.length; i++) {
				Seat seat = seats[i];
				int seatIndex = i;
				if(seat.isOccupied()){
					Ticket ticket = seat.getTicket();
					int customerIndex = customerIndex(ticket.getCustomer());
					String ticketId = ticket.getId();
					double ticketPrice = ticket.getPrice();
					sb.append(String.format("%d,%d,%s,%f", seatIndex, customerIndex, ticketId, ticketPrice));
				}else{
					sb.append(seatIndex);
				}
				sb.append(System.lineSeparator());
			}
		}
		return sb.toString();
	}
	private int cineplexIndex(Cineplex cineplex){
		return cinemaApp.getAllCineplex().indexOf(cineplex);
	}
	private int cinemaIndex(Cinema cinema, int cineplexIndex){
		return cinemaApp.getAllCinema(cineplexIndex).indexOf(cinema);
	}
	private int movieIndex(Movie movie){
		if(movie == null) return -1;
		return cinemaApp.getMovieListing().indexOf(movie);
	}
	private int customerIndex(Customer customer){
		ArrayList<User> userList = loginApp.getUserList();
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			if(user.getCustomer().equals(customer)){
				return i;
			}
		}
		return -1;
	}
}
