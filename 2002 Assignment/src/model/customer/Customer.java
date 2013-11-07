package model.customer;

public class Customer {
	private String name;
	private String mobileNumber;
	private String emailAddress;
	private final double priceRate = 1; 

	public Customer(String name, String mobileNumber, String emailAddress) {
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
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
	public double getPriceRate(){
		return priceRate;
	}
}
