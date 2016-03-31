package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class StorageHandler {
	protected File file;
	
	final protected void readFile(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null){
				interpretLine(line);
			}
			reader.close();
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
	final protected void writeFile(){
		String data = getDataToWrite();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(data);
			writer.flush();
			writer.close();
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
	protected void setFilePath(String path){
		file = new File(path);
	}
	protected abstract void interpretLine(String line);
	protected abstract String getDataToWrite();
	public void loadData(){
		readFile();
	}
	public void saveData(){
		writeFile();
	}
}
