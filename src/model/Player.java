package model;

import java.awt.Color;

public abstract class Player {

	private GameBoard board;
	private Color color;

	// Each player should know which board they belong to.
	public Player(GameBoard board, Color color) {
		this.board = board;
		this.color = color;
	}

	public abstract boolean lost();

	// Is it your turn?
	public abstract boolean isTurn();
	
	public GameBoard getBoard(){
		return board;
	}

	/**
	 * Color in which pieces which belong to this player will be rendered
	 * 
	 * @return
	 */
	public Color getColor() {
		return color;
	}

	// For now, moves will be made by entering exact coordinates.
	// Right now, there are no rules to moving pieces, so players can just move
	// their pieces anywhere within the board.
	// Returns: the piece which made a capture, or null
	public abstract GamePiece makeAMove(GameBoard board, int x1, int y1, int x2, int y2);
		
}
