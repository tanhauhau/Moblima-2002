package model.cinema;

import java.util.ArrayList;

public class Cineplex {
	private ArrayList<Cinema> cinemas;
	private String name;
	
	public Cineplex(String name) {
		this.name = name;
		cinemas = new ArrayList<Cinema>();
	}
	
	public String getName() {
		return name;
	}
	public void changeName(String name){
		this.name = name;
	}
	
	public boolean addCinema(Cinema cinema){
		return cinemas.add(cinema);
	}
	public void addCinema(Cinema...cinemas){
		for (Cinema cinema : cinemas) {
			this.cinemas.add(cinema);
		}
	}
	public void removeCinema(Cinema cinema){
		cinemas.remove(cinema);
	}
	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}
	public int getNumberOfCinema(){
		return cinemas.size();
	}
	@Override
	public String toString() {
		return String.format("Cineplex %s", name);
	}
}
