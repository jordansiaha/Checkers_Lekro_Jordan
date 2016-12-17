package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.CheckersGame;

// To be implemented MUCH MUCH later, this will be our fancy visual layout of the game.
public class GraphicView extends JPanel implements Observer{

	private CheckersGame game;
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public GraphicView(CheckersGame game){
		this.game = game;
	}

}
