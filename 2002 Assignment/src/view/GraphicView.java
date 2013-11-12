package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.cinema.Cineplex;
import model.cinema.showtime.ShowTime;
import model.customer.User.LoginFailedException;
import model.customer.User.UserNameExistsException;
import model.customer.User.UserNotLoggedInException;
import model.movie.Movie;
import model.movie.Status;
import storage.Saveable;
import controller.AbstractCinemaApp;
import controller.LoginApp;

public class GraphicView extends JFrame implements IView, ActionListener, WindowListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel leftPanel, rightPanel, mainPanel;
	private JTextArea textArea;
	private JComboBox<String> movieList, cineplexList, dateList;
	private JButton findButton; 
	private final SimpleDateFormat format = new SimpleDateFormat("EEE dd-MMM-yyyy");
	//dimensions
	private final int FRAME_WIDTH = 600;
	private final int FRAME_HEIGHT = 450;
	private final Dimension COMBO_BOX_DIMENSION = new Dimension(FRAME_WIDTH/3, 25);
	
	public GraphicView() {
		System.out.println("graphic view");
	}
	
	private AbstractCinemaApp cinemaApp;
	private Saveable saveable;
	private JScrollPane scrollPane;
	
	private void layout(AbstractCinemaApp cinemaApp) {
		this.cinemaApp = cinemaApp;
		
		leftPanel = new JPanel();
		leftPanel.setMinimumSize(new Dimension(FRAME_WIDTH/2, FRAME_HEIGHT));
		
		//Select Movie
		DefaultComboBoxModel<String> mList = new DefaultComboBoxModel<String>();
		ArrayList<Movie> movies = cinemaApp.getMovieListing(Status.NOW_SHOWING);
		for (Movie movie : movies) {
			mList.addElement(movie.getTitle());
		}
		movieList = new JComboBox<String>(mList);
		movieList.setMaximumSize(COMBO_BOX_DIMENSION);
		movieList.setMinimumSize(COMBO_BOX_DIMENSION);
		
		//Select Cineplex
		DefaultComboBoxModel<String> cList = new DefaultComboBoxModel<String>();
		ArrayList<Cineplex> cineplexes = cinemaApp.getAllCineplex();
		for (Cineplex cineplex : cineplexes) {
			cList.addElement(cineplex.getName());
		}
		cineplexList = new JComboBox<String>(cList);
		cineplexList.setMaximumSize(COMBO_BOX_DIMENSION);
		cineplexList.setMinimumSize(COMBO_BOX_DIMENSION);
		
		//Select Date
		DefaultComboBoxModel<String> dList = new DefaultComboBoxModel<String>();
		Calendar date = Calendar.getInstance();
		date.set(2013,10,2);
		for (int i = 0; i < 5; i++) {
			dList.addElement(format.format(date.getTime()));
			date.add(Calendar.DATE, 1);
		}
		dateList = new JComboBox<String>(dList);
		dateList.setMaximumSize(COMBO_BOX_DIMENSION);
		dateList.setMinimumSize(COMBO_BOX_DIMENSION);
		
		//find button
		findButton = new JButton("Search");
		findButton.addActionListener(this);
		
		//Left Panel
		leftPanel.add(new JLabel("Date"));
		leftPanel.add(dateList);
		leftPanel.add(new JLabel("Location"));
		leftPanel.add(cineplexList);
		leftPanel.add(new JLabel("Movie"));
		leftPanel.add(movieList);
		leftPanel.add(findButton);
		leftPanel.setBorder(BorderFactory.createLineBorder(new Color(0)));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);

		//RIGHT PANEL
		rightPanel = new JPanel();
		rightPanel.add(scrollPane);
		rightPanel.setMinimumSize(new Dimension(FRAME_WIDTH/2, FRAME_HEIGHT));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		//MAIN PANEL
		mainPanel = new JPanel();
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		this.add(mainPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == findButton){
			Calendar date = Calendar.getInstance();
			
			StringBuffer sb = new StringBuffer();
			try {
				date.setTime(format.parse((String) dateList.getSelectedItem()));
				ShowTime[] showtime = cinemaApp.getMovieShowTime(cineplexList.getSelectedIndex(), movieList.getSelectedIndex(), date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.get(Calendar.DATE));
				for (ShowTime st : showtime) {
					sb.append(st.toString());
					sb.append(System.lineSeparator()).append(System.lineSeparator());
				}
			} catch (ParseException e1) {
			}
			textArea.setText(sb.toString());
			textArea.setCaretPosition(0);
		}
	}

	@Override
	public void start(AbstractCinemaApp cinemaApp, boolean admin) {
		System.out.println("start!");
		layout(cinemaApp);
		setTitle("Search/List movie for showtimes");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocation(new Point(400, 150));
		setAlwaysOnTop(true);
		setResizable(false);
	}

	@Override
	public boolean auth(LoginApp loginApp) {
		final String username = "graphicUI";
		final String password = "password";
		try {
			loginApp.createUser(username, password, "12345678", 10, "email@address.com");
			return true;
		} catch (UserNameExistsException e){}
		try{
			loginApp.login(username, password);
		}catch(LoginFailedException | UserNotLoggedInException e) {}
		//patch
		return true;
	}

	
	@Override
	public void setSavable(Saveable saveable) {
		this.saveable = saveable;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if(saveable != null)
			saveable.save();
	}
	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
}
