package model.cinema.showtime;

import java.util.Calendar;
import java.util.Date;
import model.cinema.Cinema;

public class MonthShowTime extends IShowTimeGroup{
	
	public MonthShowTime() {
		shows = new DayShowTime[31];
	}	
	@Override
	ShowTime[] get(Cinema cinema, Date date, LEVEL level) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		final int day = c.get(Calendar.DAY_OF_MONTH) - 1;
		
		if(shows[day] == null){
			DayShowTime d = new DayShowTime(cinema, date);
			shows[day] = d;
		}
		if(level == LEVEL.DAY){
			return shows[day].getAll();
		}
		return shows[day].get(cinema, date, level);
	}
}
