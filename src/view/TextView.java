package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.CheckersGame;

// Text representation of the game. Will use this earlier than Graphic view.
public class TextView extends JPanel implements Observer {

	private CheckersGame game;
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public TextView(CheckersGame game){
		this.game = game;
	}

}
