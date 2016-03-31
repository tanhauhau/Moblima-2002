package storage;

import java.util.StringTokenizer;

import model.movie.Genre;
import model.movie.Language;
import model.movie.Movie;
import model.movie.Rating;
import controller.AbstractCinemaApp;

public class MovieStorageHandler extends StorageHandler{

	private static final String PATH_NAME = "Storage/movie.csv";
	private AbstractCinemaApp cinemaApp;
	private static final String COMMA = "@COMMA@";
	public MovieStorageHandler(AbstractCinemaApp cinemaApp) {
		this.cinemaApp = cinemaApp;
		setFilePath(PATH_NAME);
	}
	
	@Override
	protected void interpretLine(String line) {
		/* code, title, description, genre, language, rating */
		StringTokenizer tokenizer = new StringTokenizer(line, ",");  
		String code = tokenizer.nextToken().replace(COMMA, ",");
		String title = tokenizer.nextToken().replace(COMMA, ",");
		String description = tokenizer.nextToken().replace(COMMA, ",");
		Genre genre = Genre.valueOf(tokenizer.nextToken());
		Language language = Language.valueOf(tokenizer.nextToken());
		Rating rating = Rating.valueOf(tokenizer.nextToken());
		
		cinemaApp.addMovie(code, title, description, genre, language, rating);
	}

	@Override
	protected String getDataToWrite() {
		/* code, title, description, genre, language, rating */
		StringBuffer sb = new StringBuffer();
		for (Movie movie : cinemaApp.getMovieListing()) {
			sb.append(String.format("%s,%s,%s,%s,%s,%s",
					movie.getCode().replace(",", COMMA), movie.getTitle().replace(",", COMMA), movie.getDescription().replace(",", COMMA),
					movie.getGenre().name(), movie.getLanguage().name(), movie.getRating().name()));
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
}
