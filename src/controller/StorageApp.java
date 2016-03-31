package controller;

import storage.CineplexStorageHandler;
import storage.MovieStorageHandler;
import storage.ShowTimeStorageHandler;
import storage.StorageHandler;
import storage.UserStorageHandler;

public class StorageApp {
	public StorageApp() {
	}
	
	public void loadCineplexes(AbstractCinemaApp cinemaApp){
		StorageHandler cineplexStorage = new CineplexStorageHandler(cinemaApp);
		cineplexStorage.loadData();
	}
	public void loadMovies(AbstractCinemaApp cinemaApp){
		StorageHandler movieStorage = new MovieStorageHandler(cinemaApp);
		movieStorage.loadData();
	}
	public void loadShowTime(AbstractCinemaApp cinemaApp, LoginApp loginApp){
		StorageHandler stStorage = new ShowTimeStorageHandler(cinemaApp, loginApp);
		stStorage.loadData();
	}
	public void loadUsers(LoginApp loginApp){
		StorageHandler userStorage = new UserStorageHandler(loginApp);
		userStorage.loadData();
	}
	
	public void saveCineplexes(AbstractCinemaApp cinemaApp){
		StorageHandler cineplexStorage = new CineplexStorageHandler(cinemaApp);
		cineplexStorage.saveData();
	}
	public void saveMovies(AbstractCinemaApp cinemaApp){
		StorageHandler movieStorage = new MovieStorageHandler(cinemaApp);
		movieStorage.saveData();
	}
	public void saveShowTime(AbstractCinemaApp cinemaApp, LoginApp loginApp){
		StorageHandler stStorage = new ShowTimeStorageHandler(cinemaApp, loginApp);
		stStorage.saveData();
	}
	public void saveUsers(LoginApp loginApp){
		StorageHandler userStorage = new UserStorageHandler(loginApp);
		userStorage.saveData();
	}
	
	public boolean hasStorage(){
		return true;
	}
}
