package model;

import java.awt.Color;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

// Fully implement the game of Checkers.
public class CheckersGame extends Observable {
	private int height ;
	private int width ;
	private Player p1;
	private Player p2;
	private GameBoard gameBoard;
	private int currentPlayerMove = 0; // Keep track of whose move it is. odd for player 1 even for player 2.
	private CheckersPiece multicapture;
	private String whoWon = "";
	private boolean gamesOver = false;
	
	// Checkers board size will be dynamic.
	public CheckersGame(int width, int height){
		this.height = height;
		this.width = width;
		p1 = new CheckersPlayer(gameBoard, Color.BLACK, CheckersPlayer.POSITIVE_Y);
		p2 = new CheckersPlayer(gameBoard, Color.RED, CheckersPlayer.NEGATIVE_Y);
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
		if (multicapture != null) {
			multicapture.setMulticapturing(false);
			multicapture = null;
		}
		CheckersPiece captured = (CheckersPiece) p.makeAMove(gameBoard, x1, y1, x2, y2);
		if (captured == null || !captured.canJump(x2, y2)) 
			currentPlayerMove += 1; // Other player's turn.
		else {
			multicapture = captured;
			multicapture.setMulticapturing(true);
		}
		isGameOver();
		setChanged();
		notifyObservers();
	}
	
	private void initializeGameBoard(){
		currentPlayerMove = 1;
		gameBoard = new GameBoard(width, height);
		
		// ODDS
		//for(int i = 1; i < width; i += 2){
			//gameBoard.put(new CheckersPiece(p1, gameBoard), i, 1);
			//gameBoard.put(new CheckersPiece(p2, gameBoard), i, 5);
			gameBoard.put(new CheckersPiece(p2, gameBoard), 1, 7);
		//}
		// EVENS
		//for(int i = 0; i < height; i += 2){
			//gameBoard.put(new CheckersPiece(p1, gameBoard), i, 0);
			gameBoard.put(new CheckersPiece(p1, gameBoard), 0, 2);
			//gameBoard.put(new CheckersPiece(p2, gameBoard), i, 6);
		//}
	}
	
	public boolean isGameOver(){
		System.out.println(((CheckersPlayer) p1).getPiecesCount());
		System.out.println(((CheckersPlayer) p2).getPiecesCount());
		if(p2.lost()){
			whoWon = "Player one WINS!";
			gamesOver = true;
		}
		else if(p1.lost()){
			whoWon = "Player two WINS!";
			gamesOver = true;
		}
		return gamesOver;
	}
	public String won(){
		return whoWon;
	}
	
	public void resetGame(){
		p1 = new CheckersPlayer(gameBoard, Color.BLACK, CheckersPlayer.POSITIVE_Y);
		p2 = new CheckersPlayer(gameBoard, Color.RED, CheckersPlayer.NEGATIVE_Y);
		gamesOver = false;
		whoWon = "";
		initializeGameBoard();
		setChanged();
		notifyObservers();
	}
	
	public Set<int[]> getValidPieces(Player p) {
		
		if (multicapture != null) {
			Set<int[]> mul = new HashSet<>();
			mul.add(getBoard().getCoordinates(multicapture));
			return mul;
		}
		
		Set<int[]> playerPieces = new HashSet<>();
		Set<int[]> skipPieces = new HashSet<>();
		
		for (int i = 0; i < gameBoard.getWidth(); i++) {
			for (int j = 0; j < gameBoard.getHeight(); j++) {
				CheckersPiece piece = (CheckersPiece) gameBoard.get(i, j);
				if (piece == null) continue;
				if (piece.getPlayer() == p) {
					if (!(piece.canJump(i, j) || piece.canWalk(i, j))) continue;
					playerPieces.add(new int[] {i, j});
					if (piece.canJump(i, j)) skipPieces.add(new int[] {i, j});
				}
			}
		}
		
		return playerPieces;
		/* To restrict players to only capture:
		if (skipPieces.size() == 0) return playerPieces;
		else return skipPieces;
		*/
	}
}
