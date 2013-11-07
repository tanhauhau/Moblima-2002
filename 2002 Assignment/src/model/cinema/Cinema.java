package model.cinema;

import model.cinema.seat.PremiumSeat;
import model.cinema.seat.Seat;
import model.cinema.seat.SeatAllocation;
import model.showtime.ShowTimetable;

public class Cinema {
	
	public static enum CinemaType{NORMAL, PLATINUM_SUITE};
	
	private String id;
	private final double priceRate = 1.0;
	private Cineplex cineplex;
	private ShowTimetable showTimetable;
	
	public Cinema(Cineplex cineplex, String id) {
		this.cineplex = cineplex;
		this.id = id;
		showTimetable = new ShowTimetable(this);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getPriceRate() {
		return priceRate;
	}
	public Cineplex getCineplex() {
		return cineplex;
	}
	public SeatAllocation getSeatAllocation(){
		return new Seats(this);
	}
	public ShowTimetable getShowTimetable() {
		return showTimetable;
	}
	public void remove(){
		this.cineplex = null;
	}
	
	private class Seats extends SeatAllocation{
		private Seats(Cinema cinema){
			this.cinema = cinema;
			this.totalNumberOfSeats = 50;
			this.seats = new Seat[totalNumberOfSeats];
			for (int i = 0; i < 40; i++) {
				this.seats[i] = new Seat(String.valueOf(i));
			}
			for (int i = 40; i < 50; i++) {
				this.seats[i] = new PremiumSeat(String.valueOf(i));
			}
		}
	}
}
