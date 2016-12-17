package model;

public abstract class GamePiece {

	private Player player;
	private GameBoard board;
	
	// Each checkers piece knows which player and game board it belongs to
	public GamePiece(Player player, GameBoard board){
		this.player = player;
		this.board = board;
	}
	
	
	public boolean isValidMove(int x, int y){
		// TODO
		return false;
	}
}
