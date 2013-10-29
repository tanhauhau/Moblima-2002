package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
	private String id;
	private String name;
	private String mobileNumber;
	private String emailAddress;
	private Double price;

	public static Ticket generateTicket(Cinema cinema, Date time, String name, String mobileNumber,
			String emailAddress, Double price){
		SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyyMMddhhmm");
		return new Ticket(simpleFormatter.format(time) + cinema.getCode(), name, mobileNumber, emailAddress, price);
	}
	
	private Ticket(String id, String name, String mobileNumber,
			String emailAddress, Double price) {
		this.id = id;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public Double getPrice() {
		return price;
	}
	
	public String toString(){
		return String.format("<Ticket: %s Name: %s, Mobile: %s, Email: %s, Price: %f>", getId(), getName(), getMobileNumber(), getEmailAddress(), getPrice());
	}
}
