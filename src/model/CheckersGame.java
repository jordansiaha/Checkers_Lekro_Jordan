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
	public CheckersGame(int width, int height){
		this.height = height;
		this.width = width;
		initializeGameBoard();
		setChanged();
		notifyObservers();
	}
	
	// Return textual representation of the current game state.
	public String toString(){
		return gameBoard.toString();
	}
	
	private void initializeGameBoard(){
		gameBoard = new GameBoard(width, height);
		
		// ODDS
		for(int i = 1; i < width; i += 2){
			gameBoard.put(new CheckersPiece(p1, gameBoard), i, 1);
			gameBoard.put(new CheckersPiece(p2, gameBoard), i, 5);
			gameBoard.put(new CheckersPiece(p2, gameBoard), i, 7);
		}
		// EVENS
		for(int i = 0; i < height; i += 2){
			gameBoard.put(new CheckersPiece(p1, gameBoard), i, 0);
			gameBoard.put(new CheckersPiece(p1, gameBoard), i, 2);
			gameBoard.put(new CheckersPiece(p2, gameBoard), i, 6);
		}
	}
	
}
