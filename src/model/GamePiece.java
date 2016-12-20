package model;

public abstract class GamePiece {

	private Player player;
	private GameBoard board;
	
	// Each checkers piece knows which player and game board it belongs to
	public GamePiece(Player player, GameBoard board){
		this.player = player;
		this.board = board;
	}
	
	/**
	 * Let the GamePiece know which board it belongs to now.
	 * @param board - new board
	 */
	public void setBoard(GameBoard board) {
		this.board = board;
	}
	
	/**
	 * 
	 * @return the GameBoard to which this GamePiece belongs.
	 */
	public GameBoard getBoard() {
		return board;
	}

	/**
	 * 
	 * @return the Player to which this GamePiece belongs.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Change the GamePiece's ownership
	 * @param player - new player to which this piece belongs.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	public abstract char getCharacterRepresentation();
	public abstract boolean isCapturable(GamePiece piece);
	public abstract boolean isLegalMove(int x1, int y1, int x2, int y2);
}
