package model.customer;

public class StudentCustomer extends Customer{
	protected StudentCustomer(String name, String mobileNumber, String emailAddress, int age) {
		super(name, mobileNumber, emailAddress, age);
		priceRate = 0.7;
	}
}
