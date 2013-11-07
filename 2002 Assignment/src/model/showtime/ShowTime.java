package model.showtime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.cinema.Cinema;
import model.cinema.seat.SeatAllocation;
import model.movie.Movie;

public class ShowTime{
	private Date startTime, endTime;
	private Movie movie;
	private Cinema cinema;
	private SeatAllocation seatAllocations;
	private final double NORMAL_RATE = 1.0;
	private final double MIDNITE_RATE = 0.8;
	private final double WEEKEND_RATE = 1.4;
	private double priceRate;
	
	public ShowTime(Date startTime, Date endTime, Cinema cinema) {
		this(startTime, endTime, null, cinema);
	}
	public ShowTime(Date startTime, Date endTime, Movie movie, Cinema cinema) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.movie = movie;
		this.cinema = cinema;
		this.seatAllocations = cinema.getSeatAllocation();
		setPriceRate();
	}
	private void setPriceRate(){
		Calendar c = Calendar.getInstance();
		c.setTime(startTime);
		
		int day = c.get(Calendar.DAY_OF_WEEK);
		int time = c.get(Calendar.HOUR_OF_DAY);
		
		priceRate = NORMAL_RATE;
		
		if(day == Calendar.SATURDAY || day == Calendar.SUNDAY){
			priceRate *= WEEKEND_RATE;
		}
		//12am - 6am
		if(time < 6){
			priceRate *= MIDNITE_RATE;
		}
	}
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public boolean hasMovie(){
		return movie != null;
	}
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public Cinema getCinema() {
		return cinema;
	}
	
	public double getPriceRate() {
		return priceRate;
	}
	
	public SeatAllocation getSeatAllocations() {
		return seatAllocations;
	}
}
