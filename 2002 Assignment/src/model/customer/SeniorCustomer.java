package model.customer;

public class SeniorCustomer extends Customer {
	protected SeniorCustomer(String name, String mobileNumber, String emailAddress, int age) {
		super(name, mobileNumber, emailAddress, age);
		priceRate = 0.8;
	}
}
