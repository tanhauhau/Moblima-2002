package model.customer;

public class StudentCustomer extends Customer{
	
	private final double priceRate = 0.7;
	
	public StudentCustomer(String name, String mobileNumber, String emailAddress) {
		super(name, mobileNumber, emailAddress);
	}
	public double getPriceRate() {
		return priceRate;
	}
}
