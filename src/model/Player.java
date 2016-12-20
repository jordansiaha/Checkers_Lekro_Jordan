package model;

import java.awt.Color;

public class Player {

	public static final byte POSITIVE_Y = 1;
	public static final byte NEGATIVE_Y = -1;
	public static final byte POSITIVE_X = 2;
	public static final byte NEGATIVE_X = -2;
	
	private GameBoard board;
	private boolean isTurn;
	private Color color;
	private int piecesCount = 8;
	private byte directionality;

	// Each player should know which board they belong to.
	public Player(GameBoard board, Color color, byte directionality) {
		this.board = board;
		this.color = color;
		this.directionality = directionality;
	}

	public int getPiecesCount() {
		return piecesCount;
	}

	public void decreasePiecesCount() {
		if (piecesCount > 0) {
			piecesCount -= 1;
		}
	}

	// Is it your turn?
	public boolean isTurn() {
		return isTurn;
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
	public void makeAMove(GameBoard board, int x1, int y1, int x2, int y2) {
		// First get the piece to be moved.
		GamePiece p = null;
		if (board.get(x1, y1) != null) {
			p = board.get(x1, y1);

			// Does this piece belong to me?
			if (p.getPlayer() == this) {
				// Move piece to desired location.
				board.put(p, x2, y2);
				board.remove(x1, y1);
			}
			else{
				System.out.println("This piece doesn't belong to you!");
			}
		} else {

			System.out.println("Please select a valid Checkers piece");
		}
	}
	
	public byte getDirectionality() {
		return directionality;
	}
	
	public void setDirectionality(byte newDirectionality) {
		directionality = newDirectionality;
	}
}
