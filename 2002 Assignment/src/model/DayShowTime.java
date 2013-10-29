package model;

import java.util.Calendar;
import java.util.Date;

public class DayShowTime {
	private ShowTime[] shows;
	private Cinema cinema;
	public DayShowTime(Cinema cinema, Date day) {
		this.cinema = cinema;
		shows = new ShowTime[12];
		
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		for (int i = 0; i < 12; i++) {
			c.set(Calendar.HOUR_OF_DAY, i * 2);
			Date startTime = c.getTime();
			c.set(Calendar.HOUR_OF_DAY, (i + 1) * 2);
			Date endTime = c.getTime();
			
			shows[i] = new ShowTime(startTime, endTime, null, cinema);
		}
	}
	public ShowTime getShowTime(int hour){
		return shows[hour/2];
	}
	public ShowTime[] getShowTimes(){
		return shows;
	}
}
