package model;

import java.util.ArrayList;

public class Movie {
	private String title;
	private String description;
	private Genre genre;
	private Language language;
	private int rating;
	private Status status;
	private double priceRate;
	private ArrayList<ShowTime> showTimes;
	
	public Movie(String title, String description, Genre genre,
			Language language, int rating, Status status) {
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getPriceRate() {
		return priceRate;
	}
	public void addShowTime(ShowTime showTime){
		showTimes.add(showTime);
	}
	public void removeShowTime(ShowTime showTime){
		showTimes.remove(showTime);
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
