package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;

import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.CheckersGame;

// Text representation of the game. Will use this earlier than Graphic view.
public class TextView extends JPanel implements Observer{

	private CheckersGame game;
	private JTextArea gameArea;
	
	private static int height = 570;
	private static int width = 450;

	@Override
	public void update(Observable o, Object arg) {
		updateFields();
	}
	

	public TextView(CheckersGame game) {
		this.setBackground(Color.BLACK);
		this.game = game;
		
		initializeTextFields();
		updateFields();
	}
	private void initializeTextFields() {
		gameArea = new JTextArea(game.toString());
		gameArea.setBackground(Color.BLACK);
		gameArea.setForeground(Color.WHITE);

		gameArea.setSize(width, height);
		gameArea.setLocation(100, 15);
		
		gameArea.setFont(new Font("Courier", Font.BOLD, 24));
		this.setLayout(null);
		this.add(gameArea); // Add the game area to the JPanel.
		gameArea.setEditable(false); // For weird people who try to edit the textArea Lol.
		
		
		
	}
	// Update the textual representation of the game.
	// Just calls on the toString method in the CheckersGame which reveals the
	// current state of the game.
	public void updateFields() {
		gameArea.setText(game.toString());
	}
}
