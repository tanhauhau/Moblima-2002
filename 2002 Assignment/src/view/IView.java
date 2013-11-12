package view;

import storage.Saveable;
import controller.AbstractCinemaApp;
import controller.LoginApp;

public interface IView {
	public void start(AbstractCinemaApp cinemaApp, boolean admin);
	public boolean auth(LoginApp loginApp);
	public void setSavable(Saveable saveable);
}
