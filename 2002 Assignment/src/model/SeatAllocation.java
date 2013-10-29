package model;

public interface SeatAllocation {
	public int getTotalNumberOfSeats();
	public int getNumberOfAvailableSeats();
	public int getNumberOfOccupiedSeats();
	public Seat[] getAllSeats();
	public Seat[] getAvailableSeats();
}
