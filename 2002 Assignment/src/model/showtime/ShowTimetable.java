package model.showtime;

import java.util.ArrayList;
import java.util.Date;

import model.cinema.Cinema;
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
	public DayShowTime addDayShowTime(Date day){
		DayShowTime d;
		if((d = getDayShowTime(day)) != null){
			return d;
		}
		d = createDayShowTime(day);
		addDayShowTime(day);
		return d;
	}
	public void addDayShowTime(DayShowTime day){
		days.add(day);
	}
	public void removeDayShowTime(DayShowTime day){
		days.remove(day);
	}
	public DayShowTime getDayShowTime(Date day){
		for (DayShowTime d : days) {
			if(d.getDay().equals(day)){
				return d;
			}
		}
		return null;
	}
	public ArrayList<DayShowTime> getDayShowTimes(){
		return days;
	}
	public ShowTime[] getShowTimes(int day){
		return days.get(day).getShowTimes();
	}
	public ShowTime getShowTime(int day, int time){
		return days.get(day).getShowTime(time);
	}
}
