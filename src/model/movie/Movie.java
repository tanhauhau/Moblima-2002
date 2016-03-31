package model.movie;

import java.util.ArrayList;

import model.cinema.showtime.ShowTime;

public class Movie {
	private String code;
	private String title;
	private String description;
	private Genre genre;
	private Language language;
	private Rating rating;
	private Status status;
	private double priceRate;
	private ArrayList<ShowTime> showTimes;
	
	public Movie(String code, String title, String description, Genre genre,
			Language language, Rating rating, Status status) {
		this.code = code;
		this.title = title;
		this.description = description;
		this.genre = genre;
		this.language = language;
		this.rating = rating;
		this.status = status;
		this.priceRate = 1.0;
		showTimes = new ArrayList<ShowTime>();
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		if(!status.equals(Status.NOW_SHOWING)){
			for (ShowTime showtime : showTimes) {
				showtime.setMovie(null);
			}
			showTimes.clear();
		}
		this.status = status;
	}

	public double getPriceRate() {
		return priceRate;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	
	public void addShowTime(ShowTime showTime){
		showTimes.add(showTime);
		setStatus(Status.NOW_SHOWING);
	}
	public void removeShowTime(ShowTime showTime){
		showTimes.remove(showTime);
		if(showTimes.size() == 0)	setStatus(Status.END_OF_SHOWING);
	}
	public ArrayList<ShowTime> getShowTimes(){
		return showTimes;
	}
	public String toString(){
		return String.format(	"<Movie>\n" +
								"Title: %s\n" +
								"Description: %s\n" +
								"Genre: %s\n" +
								"Language: %s\n" +
								"Status: %s\n",
								getTitle(), 
								getDescription(), 
								getGenre(), 
								getLanguage(), 
								getStatus());
	}
}
