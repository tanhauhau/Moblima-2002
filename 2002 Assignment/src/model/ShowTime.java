package model;

import java.util.Date;

public class ShowTime implements Comparable<ShowTime>{
	private Date startTime, endTime;
	private Movie movie;
	private Cinema cinema;
	
	public ShowTime(Date startTime, Date endTime, Movie movie, Cinema cinema) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.movie = movie;
		this.cinema = cinema;
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

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Cinema getCinema() {
		return cinema;
	}

	@Override
	public int compareTo(ShowTime showTime) {
		if(this.startTime.compareTo(showTime.startTime) == 0){
			return this.endTime.compareTo(showTime.endTime);
		}else{
			return this.startTime.compareTo(showTime.startTime);
		}
	}
}
