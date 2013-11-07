package model.customer;

public class Customer {
	private String name;
	private String mobileNumber;
	private String emailAddress;
	private int age;
	protected double priceRate = 1; 

	private static final int STUDENT_AGE = 18;
	private static final int SENIOR_AGE = 55;
	
	public static Customer createCustomer(String name, String mobileNumber, String emailAddress, int age){
		if(age <= STUDENT_AGE){
			return new StudentCustomer(name, mobileNumber, emailAddress, age);
		}else if(age >= SENIOR_AGE){
			return new SeniorCustomer(name, mobileNumber, emailAddress, age);
		}else{
			return new Customer(name, mobileNumber, emailAddress, age);
		}
	}
	
	protected Customer(String name, String mobileNumber, String emailAddress, int age) {
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
		this.age = age;
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
	public int getAge(){
		return age;
	}
	 
}
