package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.CheckersGame;
import view.CheckersView;
import view.GraphicView;
import view.TextView;

// Controller class, decides which view is shown to the user. To be implemented much later.
public class CheckersGUI extends JFrame implements ActionListener, Observer {

	private static final String GAME_NAME = "Checkers";
	private static final String GAME_VERSION = "0.0.1";

	private static final int gameWidth = 1280;
	private static final int gameHeight = 720;
	private TextView textView;
	private GraphicView checkersView;
	private CheckersGame theGame;
	private JPanel currentView;
	private JPanel movePanel;
	private JTextField moveFromX;
	private JTextField moveFromY;
	private JTextField moveToX;
	private JTextField moveToY;
	private JLabel x1Label;
	private JLabel y1Label;
	private JLabel x2Label;
	private JLabel y2Label;
	private JButton makeMove;
	private JButton textViewButton;
	private JButton checkersViewButton;
	private JPanel viewChangePanel;
	private JTextArea gameIsOver;
	private JButton resetButton;

	public static void main(String[] args) {
		CheckersGUI g = new CheckersGUI();
		g.setVisible(true);
	}

	public CheckersGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(gameWidth, gameHeight + 50);
		this.setLocation(200, 150);
		this.setTitle(GAME_NAME + " " + GAME_VERSION);

		initializeGameForTheFirstTime();
		textView = new TextView(theGame);
		checkersView = new CheckersView(theGame);
		this.setFocusable(true);
		addObservers();

		// Set default view
		setViewTo(checkersView);
	}

	// Add observers to the game.
	private void addObservers() {
		theGame.addObserver(textView);
		theGame.addObserver(checkersView);
		theGame.addObserver(this);
	}

	// Initialize the game for the first time.
	private void initializeGameForTheFirstTime() {
		theGame = new CheckersGame(8, 8);
		movePanel = new JPanel();
		movePanel.setSize(300, 150);
		movePanel.setLocation(900, 300);
		movePanel.setBackground(Color.BLACK);

		makeMove = new JButton("Make Move");
		makeMove.setBackground(Color.BLACK);
		makeMove.setForeground(Color.RED);
		makeMove.addActionListener(this);
		x1Label = new JLabel("X coord to move from");
		y1Label = new JLabel("Y coord to move from");
		x2Label = new JLabel("X coord to move to");
		y2Label = new JLabel("Y coord to move to");

		moveFromX = new JTextField();
		//moveFromX.setBackground(Color.GRAY);
		moveFromX.setPreferredSize(new Dimension(150, 20));
		moveFromY = new JTextField();
		moveFromY.setPreferredSize(new Dimension(150, 20));
		moveToX = new JTextField();
		moveToX.setPreferredSize(new Dimension(150, 20));
		moveToY = new JTextField();
		moveToY.setPreferredSize(new Dimension(150, 20));

		movePanel.add(x1Label);
		movePanel.add(moveFromX);
		movePanel.add(y1Label);
		movePanel.add(moveFromY);
		movePanel.add(x2Label);
		movePanel.add(moveToX);
		movePanel.add(y2Label);
		movePanel.add(moveToY);
		movePanel.add(makeMove);

		viewChangePanel = new JPanel();
		viewChangePanel.setBackground(Color.GRAY); 
		textViewButton = new JButton("Text View");
		textViewButton.setForeground(Color.RED);
		textViewButton.setBackground(Color.BLACK);
		textViewButton.addActionListener(this);
		checkersViewButton = new JButton("Graphic View");
		checkersViewButton.setForeground(Color.RED);
		checkersViewButton.setBackground(Color.BLACK);
		checkersViewButton.addActionListener(this);
		resetButton = new JButton("RESET");
		resetButton.setForeground(Color.RED);
		resetButton.setBackground(Color.BLACK);
		resetButton.addActionListener(this);
		
		//viewChangePanel.add(textViewButton);
		//viewChangePanel.add(checkersViewButton);
		viewChangePanel.add(resetButton);
		gameIsOver = new JTextArea();
		gameIsOver.setBackground(Color.GRAY);
		gameIsOver.setFont(new Font("gothic", Font.ITALIC, 76));
		gameIsOver.setSize(300, 100);
		gameIsOver.setLocation(650, 200);
		gameIsOver.setForeground(Color.GREEN);
		gameIsOver.setText(theGame.won());
		gameIsOver.setFocusable(false);
		gameIsOver.setEditable(false);
		//this.add(movePanel, BorderLayout.SOUTH);
		this.add(viewChangePanel, BorderLayout.NORTH);
		this.add(gameIsOver, BorderLayout.EAST);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == textViewButton) {
			setViewTo(textView);
		} else if (e.getSource() == checkersViewButton) {
			setViewTo(checkersView);
		} else if (e.getSource() == resetButton) {
			theGame.resetGame();
		} else {
			if (!moveFromX.getText().isEmpty() && !moveFromY.getText().isEmpty() && !moveToX.getText().isEmpty()
					&& !moveToY.getText().isEmpty()) {
				int x1 = Integer.parseInt(moveFromX.getText());
				int y1 = Integer.parseInt(moveFromY.getText());
				int x2 = Integer.parseInt(moveToX.getText());
				int y2 = Integer.parseInt(moveToY.getText());
				theGame.executeMove(theGame.getCurrentPlayer(), x1, y1, x2, y2);
				moveFromX.setText("");
				moveFromY.setText("");
				moveToX.setText("");
				moveToY.setText("");
				textView.updateFields();
			}

		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (theGame.isGameOver()) {
			JOptionPane.showMessageDialog(this, theGame.won());
		}
		
	}
}
