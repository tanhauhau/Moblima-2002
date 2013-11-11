package model.movie;

public class Movie3D extends Movie{

	private final double priceRate = 2.0;
	public Movie3D(String code, String title, String description, Genre genre,
			Language language, Rating rating, Status status) {
		super(code, title, description, genre, language, rating, status);
	}
	public double getPriceRate() {
		return priceRate ;
	}
}
