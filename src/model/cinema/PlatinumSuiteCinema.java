package model.cinema;

import model.cinema.seat.PremiumSeat;
import model.cinema.seat.Seat;
import model.cinema.seat.SeatAllocation;

public class PlatinumSuiteCinema extends Cinema{
	private final double priceRate = 1.5;
	public PlatinumSuiteCinema(Cineplex cineplex, String id) {
		super(cineplex, id);
	}
	
	public SeatAllocation getSeatAllocation(){
		return new Seats(this);
	}
	
	@Override
	public double getPriceRate() {
		return priceRate;
	}
	
	public CinemaType getType(){
		return CinemaType.PLATINUM_SUITE; 
	}
	
	private class Seats extends SeatAllocation{
		private Seats(Cinema cinema){
			this.cinema = cinema;
			this.totalNumberOfSeats = 30;
			this.seats = new Seat[totalNumberOfSeats];
			for (int i = 0; i < 10; i++) {
				this.seats[i] = new Seat(String.valueOf(i));
			}
			for (int i = 10; i < 30; i++) {
				this.seats[i] = new PremiumSeat(String.valueOf(i));
			}
		}
	}
}
