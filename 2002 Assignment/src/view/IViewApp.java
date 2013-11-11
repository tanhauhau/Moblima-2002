package view;

import controller.AbstractCinemaApp;
import controller.LoginApp;

public interface IViewApp {
	public void start(AbstractCinemaApp cinemaApp, boolean admin);
	public boolean auth(LoginApp loginApp);
}
