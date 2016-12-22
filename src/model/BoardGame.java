package model;

import java.util.Observable;
import java.util.Set;

public abstract class BoardGame extends Observable{
	private Player p1;
	private Player p2;
	private GameBoard gameBoard;
	private int currentPlayerMove = 0; // Keep track of whose move it is. odd for player 1 even for player 2.
	private CheckersPiece multicapture;
	private String whoWon = "";
	private boolean gamesOver = false;
	public BoardGame(int width, int height, Player p1, Player p2){
		this.p1 = p1;
		this.p2 = p2;
		gameBoard = new GameBoard(width, height);
	}
	public GameBoard getBoard() {
		return gameBoard;
	}
	public Player getPlayed1(){
		return p1;
	}
	public Player getPlayer2(){
		return p2;
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
	public abstract void initializeGameBoard();
	public abstract void executeMove(Player p, int x1, int y1, int x2, int y2);
	public abstract void resetGame();
	public abstract boolean isGameOver();
	public abstract String won();
	public abstract Set <int[]> getValidPieces(Player p);
}
