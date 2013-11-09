package model.cinema.showtime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import model.cinema.Cinema;

abstract class IShowTimeGroup {
	
	static enum LEVEL {YEAR, MONTH, DAY, HOUR}
	
	IShowTimeGroup[] shows;
	
	abstract ShowTime[] get(Cinema cinema, Date date, LEVEL level);

	ShowTime[] getAll() {
		List<ShowTime> showTimes = new ArrayList<ShowTime>(); 
		for (IShowTimeGroup show : shows) {
			if(show != null){
				showTimes.addAll(Arrays.asList(show.getAll()));
			}
		}
		ShowTime[] result = new ShowTime[showTimes.size()];
		return showTimes.toArray(result);
	}
}
