package storage;
 
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
 
public class Deserializer{
 
   public static void main (String args[]) {
 
	   Deserializer deserializer = new Deserializer();
	   UserList address = deserializer.deserializeUserList();
	   System.out.println(address);
   }
 
   public UserList deserializeUserList(){
 
	   UserList userList;
 
	   try{
 
		   FileInputStream fin = new FileInputStream("C:\\Users\\DS\\Desktop\\Java assignment\\Test.txt");
		   ObjectInputStream ois = new ObjectInputStream(fin);
		   userList = (UserList) ois.readObject();
		   ois.close();
 
		   return userList;
 
	   }catch(Exception ex){
		   ex.printStackTrace();
		   return null;
	   } 
   } 
}
