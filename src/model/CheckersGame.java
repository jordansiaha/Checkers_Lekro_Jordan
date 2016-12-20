package model;

import java.awt.Color;
import java.util.Observable;

// Fully implement the game of Checkers.
public class CheckersGame extends Observable {
	private int height ;
	private int width ;
	private Player p1;
	private Player p2;
	private GameBoard gameBoard;
	private int currentPlayerMove = 1; // Keep track of whose move it is. odd for player 1 even for player 2.
	
	// Checkers board size will be dynamic.
	public CheckersGame(int width, int height){
		this.height = height;
		this.width = width;
		p1 = new Player(gameBoard, Color.BLACK, Player.POSITIVE_Y);
		p2 = new Player(gameBoard, Color.RED, Player.NEGATIVE_Y);
		initializeGameBoard();
		setChanged();
		notifyObservers();
	}
	
	// Return textual representation of the current game state.
	public String toString(){
		return gameBoard.toString();
	}
	// Return the player who has the current turn.
	public Player getCurrentPlayer(){
		if(currentPlayerMove % 2 == 0){
			return p2;
		}
		else{
			return p1;
		}
	}
	
	public GameBoard getBoard() {
		return gameBoard;
	}
	public void executeMove(Player p, int x1, int y1, int x2, int y2){
		p.makeAMove(gameBoard, x1, y1, x2, y2);
		currentPlayerMove += 1; // Other player's turn.
		setChanged();
		notifyObservers();
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
	
	public boolean isGameOver(){
		return p1.getPiecesCount() == 0 || p2.getPiecesCount() == 0;
	}
	
	public void resetGame(){
		p1 = new Player(gameBoard, Color.BLACK, Player.POSITIVE_Y);
		p2 = new Player(gameBoard, Color.RED, Player.NEGATIVE_Y);
		initializeGameBoard();
		setChanged();
		notifyObservers();
	}
}
