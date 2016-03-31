package model.cinema.showtime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import model.cinema.Cinema;
import model.cinema.showtime.IShowTimeGroup.LEVEL;
/**
 * A list of showtime
 * @author Tan Li Hau
 *
 */
public class ShowTimetable {
	private ArrayList<YearShowTime> years;
	private Cinema cinema;
	
	public ShowTimetable(Cinema cinema) {
		this.cinema = cinema;
		years = new ArrayList<YearShowTime>();
	}
//	
//	public DayShowTime createDayShowTime(Date day){
//		return new DayShowTime(cinema, day);
//	}
//	public DayShowTime addDayShowTime(Date day){
//		DayShowTime d;
//		if((d = getDayShowTime(day)) != null){
//			return d;
//		}
//		d = createDayShowTime(day);
//		addDayShowTime(d);
//		return d;
//	}
//	public void addDayShowTime(DayShowTime day){
//		days.add(day);
//	}
//	public void removeDayShowTime(DayShowTime day){
//		days.remove(day);
//	}
//	public DayShowTime getDayShowTime(Date day){
//		for (DayShowTime d : days) {
//			if(d.getDay().equals(day)){
//				return d;
//			}
//		}
//		return null;
//	}
//	public ArrayList<DayShowTime> getDayShowTimes(){
//		return days;
//	}
//	public ShowTime[] getShowTimes(int day){
//		return days.get(day).getShowTimes();
//	}
//	public ShowTime getShowTime(int day, int time){
//		return days.get(day).getShowTime(time);
//	}
//	
//	
	private YearShowTime getYearShowTime(int year){
		for (YearShowTime y : years) {
			if(y.getYear() == year){
				return y;
			}
		}
		YearShowTime y = new YearShowTime(year);
		years.add(y);
		return y;
	}
	
	public ShowTime[] getShowTime(int year, int month, int date, int hour){
		YearShowTime y = getYearShowTime(year);
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, date, hour, 0);
		return y.get(cinema, c.getTime(), LEVEL.HOUR);
	}
	public ShowTime[] getShowTime(int year, int month, int date){
		YearShowTime y = getYearShowTime(year);
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, date);
		return y.get(cinema, c.getTime(), LEVEL.DAY);
	}
	public ShowTime[] getShowTime(int year, int month){
		YearShowTime y = getYearShowTime(year);
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, 1);
		return y.get(cinema, c.getTime(), LEVEL.MONTH);
	}
	public ShowTime[] getShowTime(int year){
		YearShowTime y = getYearShowTime(year);
		Calendar c = Calendar.getInstance();
		c.set(year, - 1, 1);
		return y.get(cinema, c.getTime(), LEVEL.YEAR);
	}
	public ShowTime[] getShowTime(){
		ArrayList<ShowTime> shows = new ArrayList<ShowTime>();
		for (YearShowTime year : years) {
			shows.addAll(Arrays.asList(year.getAll()));
		}
		ShowTime[] s = new ShowTime[shows.size()];
		return shows.toArray(s);
	}
}
