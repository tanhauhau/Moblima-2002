package model.cinema;

import model.cinema.seat.Seat;
import model.cinema.seat.SeatAllocation;

public class Cinema {
	private String id;
	private final double priceRate = 1.0;
	private Cineplex cineplex;
	
	public Cinema(Cineplex cineplex, String id) {
		this.cineplex = cineplex;
		this.id = id;
	}
	
	
	public String getId() {
		return id;
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
