package model.customer;

public class SeniorCustomer extends Customer {
	private final double priceRate = 0.8;
	
	public SeniorCustomer(String name, String mobileNumber, String emailAddress) {
		super(name, mobileNumber, emailAddress);
	}
	@Override
	public double getPriceRate() {
		return priceRate;
	}
}
