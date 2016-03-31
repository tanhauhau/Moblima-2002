package model.cinema.showtime;

import java.util.Calendar;
import java.util.Date;

import model.cinema.Cinema;

class DayShowTime extends IShowTimeGroup{
	private ShowTime[] shows;
	private Cinema cinema;
	private Date day;
	public DayShowTime(Cinema cinema, Date day) {
		this.cinema = cinema;
		this.day = day;
		shows = new ShowTime[12];

		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		for (int i = 0; i < 12; i++) {
			c.set(Calendar.HOUR_OF_DAY, i * 2);
			Date startTime = c.getTime();
			c.set(Calendar.HOUR_OF_DAY, (i + 1) * 2);
			Date endTime = c.getTime();
			
			shows[i] = new ShowTime(startTime, endTime, null, cinema);
		}
	}
	public Cinema getCinema() {
		return cinema;
	}
	public Date getDay() {
		return day;
	}
	@Override
	ShowTime[] getAll(){
		return shows;
	}
	@Override
	ShowTime[] get(Cinema cinema, Date date, LEVEL level) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		final int hour = c.get(Calendar.HOUR_OF_DAY);
		
		return new ShowTime[]{get(hour)};
	}
	private ShowTime get(int hour){
		return shows[hour/2];
	}
}
