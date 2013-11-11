package storage;
 
import java.io.Serializable;
 
public class UserList implements Serializable{
 
	   String name;
	   String mobileNumber;
	   String emailAddress;
	   String age;
	   
 
	   public void setName(String name){
		   this.name = name;
	   }
 
	   public void setMobileNumber(String mobileNumber){
		   this.mobileNumber = mobileNumber;
	   }
	   
	   public void setEmailAddress(String emailAddress){
		   this.emailAddress = emailAddress;
	   }
	   
	   public void setAge(String age){
		   this.age = age;
	   }
 
	   public String getName(){
		   return this.name;
	   }
 
	   public String getMobileNumber(){
		   return this.mobileNumber;
	   }

	   public String getEmailAddress(){
		   return this.emailAddress;
	   }
	   
	   public String getAge(){
		   return this.age;
	   }
	   
	   @Override
	   public String toString() {
    	   return new StringBuffer(" Name : ")
    	   .append(this.name)
    	   .append(" Mobile Number : ")
    	   .append(this.mobileNumber)
    	   .append(" Email Address : ")
    	   .append(this.emailAddress)
    	   .append(" Age : ")
    	   .append(this.age).toString();
    	   
    	   
	   }
 
}
