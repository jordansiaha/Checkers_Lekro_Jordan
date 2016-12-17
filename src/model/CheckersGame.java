package model;

import java.util.Observable;

// Fully implement the game of Checkers.
public class CheckersGame extends Observable {
	private static int height ;
	private static int width ;
	private Player p1;
	private Player p2;
	private GameBoard gameBoard;
	
	// Checkers board size will be dynamic.
	public CheckersGame(int height, int width){
		this.height = height;
		this.width = width;
		gameBoard = new GameBoard(height, width);
	}
	
	// Return textual representation of the current game state.
	public String toString(){
		return gameBoard.toString();
	}
	
	
}
