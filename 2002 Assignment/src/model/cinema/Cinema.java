package model.cinema;

import model.cinema.seat.PremiumSeat;
import model.cinema.seat.Seat;
import model.cinema.seat.SeatAllocation;
import model.cinema.showtime.ShowTime;
import model.cinema.showtime.ShowTimetable;

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
	public ShowTime[] getShowTime(int year, int month, int date, int hour){
		return showTimetable.getShowTime(year, month, date, hour);
	}
	public ShowTime[] getShowTime(int year, int month, int date){
		return showTimetable.getShowTime(year, month, date);
	}
	public ShowTime[] getShowTime(int year, int month){
		return showTimetable.getShowTime(year, month);
	}
	public ShowTime[] getShowTime(int year){
		return showTimetable.getShowTime(year);
	}
	public ShowTime[] getShowTime(){
		return showTimetable.getShowTime();
	}	
	public CinemaType getType(){
		return CinemaType.NORMAL; 
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
				this.seats[i] = new PremiumSeat("Premium - " + String.valueOf(i));
			}
		}
	}
	@Override
	public String toString() {
		return String.format("Cinema <%s>", id);
	}
}
