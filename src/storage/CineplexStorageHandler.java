package storage;

import java.util.ArrayList;
import java.util.StringTokenizer;

import model.cinema.Cinema;
import model.cinema.Cinema.CinemaType;
import model.cinema.Cineplex;

import controller.AbstractCinemaApp;

public class CineplexStorageHandler extends StorageHandler{
	private AbstractCinemaApp cinemaApp;
	private boolean cineplexLine;
	private int currentCineplexIndex;
	private int lineCount;
	private StringTokenizer tokenizer;
	private static final String PATH_NAME = "Storage/cineplex.csv";
	
	public CineplexStorageHandler(AbstractCinemaApp cinemaApp) {
		this.cinemaApp = cinemaApp;
		cineplexLine = true;
		setFilePath(PATH_NAME);
	}

	@Override
	protected void interpretLine(String line) {
		/*
		 * name, linecount
		 * id, type
		 * ...
		 */
		tokenizer = new StringTokenizer(line, ",");
		if(cineplexLine){
			String name = tokenizer.nextToken();
			lineCount = Integer.parseInt(tokenizer.nextToken());
			currentCineplexIndex = cinemaApp.addCineplex(name);
			if(lineCount > 0)
				cineplexLine = false;
		}else{
			String id = tokenizer.nextToken();
			String type = tokenizer.nextToken();
			CinemaType cinemaType = CinemaType.valueOf(type);			
			cinemaApp.addCinema(currentCineplexIndex, id, cinemaType);
			lineCount --;
			if(lineCount <= 0){
				cineplexLine = true;
			}
		}
	}
	
	@Override
	protected String getDataToWrite() {
		/*
		 * name, linecount
		 * id, type
		 * ...
		 */
		StringBuffer sb = new StringBuffer();
		ArrayList<Cineplex> cineplexes = cinemaApp.getAllCineplex(); 
		for (int i = 0; i < cineplexes.size(); i++) {
			Cineplex cineplex = cineplexes.get(i);
			ArrayList<Cinema> cinemas = cineplex.getCinemas();
			sb.append(String.format("%s,%d", cineplex.getName(), cinemas.size()));
			sb.append(System.lineSeparator());
			for (Cinema cinema : cinemas) {
				sb.append(String.format("%s,%s", cinema.getId(), cinema.getType().name()));
				sb.append(System.lineSeparator());
			}
		}
		return sb.toString();
	}
}
