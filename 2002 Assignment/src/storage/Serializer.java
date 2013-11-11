package storage;
 
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
 
public class Serializer {
 
   public static void main (String args[]) {
 
	   Serializer serializer = new Serializer();
	   serializer.serializeUserList("Desheng", "12345678", "123@hotmail.com", "24");
   }
 
   public void serializeUserList(String name, String mobileNumber, String emailAddress, String age){
 
	   UserList userList = new UserList();
	   userList.setName(name);
	   userList.setMobileNumber(mobileNumber);
	   userList.setEmailAddress(emailAddress);
	   userList.setAge(age);
	   
 
	   try{
 
		FileOutputStream fout = new FileOutputStream("C:\\Users\\DS\\Desktop\\Java assignment\\Test.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fout);   
		oos.writeObject(userList);
		oos.close();
		System.out.println("Done");
 
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
   }
}
