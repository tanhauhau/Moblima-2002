package model.cinema.seat;

import java.util.ArrayList;

import model.Ticket;
import model.cinema.Cinema;
import model.cinema.seat.Seat.SeatOccupiedException;

public abstract class SeatAllocation {
	protected int totalNumberOfSeats;
	protected int totalNumberOfOccupiedSeats = 0;
	protected Seat[] seats;
	protected Cinema cinema;
	
	final public int getTotalNumberOfSeats(){
		return totalNumberOfSeats;
	}
	final public int getNumberOfAvailableSeats(){
		return totalNumberOfSeats - totalNumberOfOccupiedSeats;
	}
	final public int getNumberOfOccupiedSeats(){
		return totalNumberOfOccupiedSeats;
	}
	final public Seat[] getAllSeats(){
		return seats;
	}
	final public Seat[] getAvailableSeats(){
		ArrayList<Seat> allSeats = new ArrayList<Seat>();
		for (Seat seat : seats) {
			if(!seat.isOccupied()){
				allSeats.add(seat);
			}
		}
		Seat[] result = new Seat[allSeats.size()];
		return allSeats.toArray(result);
	}
	public void purchaseSeat(Seat seat, Ticket ticket) throws SeatOccupiedException{
		for (int i = 0; i < seats.length; i++) {
			if(seats[i] == seat){
				seat.occupy(ticket);
				totalNumberOfOccupiedSeats ++;
				break;
			}
		}
	}
	final public boolean isFull(){
		return totalNumberOfOccupiedSeats == totalNumberOfSeats;
	}
	final public boolean isEmpty() {
		return totalNumberOfOccupiedSeats == 0;
	}
	final public Cinema getCinema(){
		return cinema;
	}
}
