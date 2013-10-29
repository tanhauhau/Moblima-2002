package model;

import java.util.ArrayList;
import java.util.Date;
/**
 * A list of showtime
 * @author Tan Li Hau
 *
 */
public class ShowTimetable {
	private ArrayList<DayShowTime> days;
	private Cinema cinema;
	
	public ShowTimetable(Cinema cinema) {
		this.cinema = cinema;
		days = new ArrayList<DayShowTime>();
	}
	public DayShowTime createDayShowTime(Date day){
		return new DayShowTime(cinema, day);
	}
	public void addDayShowTime(DayShowTime day){
		days.add(day);
	}
	public void removeDayShowTime(DayShowTime day){
		days.remove(day);
	}
	public ArrayList<DayShowTime> getShowTimes(){
		return days;
	}
	public ShowTime getShowTime(int day, int time){
		return days.get(day).getShowTime(time);
	}
}
