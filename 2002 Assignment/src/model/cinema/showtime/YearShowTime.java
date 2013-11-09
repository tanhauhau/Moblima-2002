package model.cinema.showtime;

import java.util.Calendar;
import java.util.Date;

import model.cinema.Cinema;

public class YearShowTime extends IShowTimeGroup{
	private int year;
	
	public YearShowTime(int year) {
		shows = new MonthShowTime[12];
		this.year = year;
	}
	public int getYear() {
		return year;
	}
	@Override
	ShowTime[] get(Cinema cinema, Date date, LEVEL level) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		final int month = c.get(Calendar.MONTH);
		
		if(shows[month] == null){
			MonthShowTime s = new MonthShowTime();
			shows[month] = s;
		}
		if(level == LEVEL.YEAR){
			return getAll();
		}else if(level == LEVEL.MONTH){
			return shows[month].getAll();
		}
		return shows[month].get(cinema, date, level);
	}
}
