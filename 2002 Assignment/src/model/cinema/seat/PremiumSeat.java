package model.cinema.seat;

import model.Ticket;

public class PremiumSeat extends Seat{
	private final double priceRate = 1.5;
	
	public PremiumSeat(String seatId) {
		super("premium_" + seatId);
	}
	public PremiumSeat(String seatId, Ticket ticket){
		super(seatId, ticket);
	}
	@Override
	public double getPriceRate() {
		return priceRate;
	}
}
