package controller;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.CheckersGame;
import view.GraphicView;
import view.TextView;

// Controller class, decides which view is shown to the user. To be implemented much later.
public class CheckersGUI extends JFrame {

	private static final int gameWidth = 1100;
	private static final int gameHeight = 1000;
	private TextView textView;
	private GraphicView graphicView;
	private CheckersGame theGame;
	private JPanel currentView;

	public static void main(String[] args) {
		CheckersGUI g = new CheckersGUI();
		g.setVisible(true);
	}

	public CheckersGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(gameWidth, gameHeight);
		this.setLocation(100, 10);
		this.setTitle("Checkers Game");

		initializeGameForTheFirstTime();
		textView = new TextView(theGame);
		graphicView = new GraphicView(theGame);
		this.setFocusable(true);
		addObservers();
		// Set default view
		setViewTo(textView); // Game starts out in text view.
	}

	// Add observers to the game.
	private void addObservers() {
		theGame.addObserver(textView);
		theGame.addObserver(graphicView);
	}

	// Initialize the game for the first time.
	private void initializeGameForTheFirstTime() {
		theGame = new CheckersGame(8, 8);
	}

	// Set the game view.
	private void setViewTo(JPanel newView) {
		if (currentView != null)
			remove(currentView);
		currentView = newView;
		add(currentView);
		currentView.repaint();
		validate();
	}
}
