package model;

import java.text.SimpleDateFormat;

import model.cinema.seat.Seat;
import model.customer.Customer;
import model.showtime.ShowTime;

public class Ticket {
	private String id;
	private Customer customer;
	private Double price;
	private ShowTime showTime; 
	private final static double STANDARD_PRICE = 8.0;

	public static Ticket generateTicket(ShowTime showtime, Customer customer, Seat seat){
		SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyyMMddhhmm");
		return new Ticket(showtime.getMovie().getCode() + simpleFormatter.format(showtime.getStartTime()), 
				showtime, customer, 
				STANDARD_PRICE * showtime.getPriceRate() * showtime.getCinema().getPriceRate() * customer.getPriceRate() * seat.getPriceRate());
	}
	
	private Ticket(String id, ShowTime showTime, Customer customer, Double price) {
		this.id = id;
		this.price = price;
		this.customer = customer;
		this.showTime = showTime;
	}

	public String getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}
	public Double getPrice() {
		return price;
	}
	public ShowTime getShowTime() {
		return showTime;
	}
	public String toString(){
		return String.format("<Ticket: %s Name: %s, Mobile: %s, Email: %s, Price: %f>", getId(), customer.getName(), customer.getMobileNumber(), customer.getEmailAddress(), getPrice());
	}
}
