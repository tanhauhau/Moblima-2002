package model.cinema.seat;

import model.Ticket;

public class Seat {
	private String seatId;
	private boolean occupied;
	private Ticket ticket;
	private final double priceRate = 1.0;
	
	public Seat(String seatId) {
		this.seatId = seatId;
		occupied = false;
		ticket = null;
	}
	public Seat(String seatId, Ticket ticket){
		this.seatId = seatId;
		try{
			occupy(ticket);
		}catch(SeatOccupiedException e){}
	}
	
	public void occupy(Ticket ticket) throws SeatOccupiedException{
		if(occupied){
			throw new SeatOccupiedException();
		}
		this.occupied = true;
		this.ticket = ticket;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public double getPriceRate() {
		return priceRate;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public String getSeatId() {
		return seatId;
	}
	@Override
	public String toString() {
		return String.format("%s - %s", seatId, occupied?"Occupied":"Empty");
	}
	public static class SeatOccupiedException extends Exception{
		private static final long serialVersionUID = 1L;
		public SeatOccupiedException() {
		}
		@Override
		public String getMessage() {
			return "The seat is occupied!";
		}
	}
}
