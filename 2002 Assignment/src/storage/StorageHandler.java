package storage;

import java.io.File;

public abstract class StorageHandler {
	protected File file;
		
	final public void openFile(){
		//TODO open the file
	}
	final public void readFile(){
		//TODO read the file, line by line
		//and call interpretLine(String line) for each line
	}
	final public void writeFile(){
		String data = getDataToWrite();
		//TODO write data into the file
	}
	final public void closeFile(){
		//TODO close the file
	}
	protected abstract void interpretLine(String line);
	protected abstract String getDataToWrite();
}
