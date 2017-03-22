package model;

import java.awt.Color;

public class CheckersPlayer extends Player {

	public static final byte POSITIVE_Y = 1;
	public static final byte NEGATIVE_Y = -1;
	public static final byte POSITIVE_X = 2;
	public static final byte NEGATIVE_X = -2;

	private boolean isTurn;
	private Color color;
	private int piecesCount = 12;
	private byte directionality;
	private boolean hasLost = false;

	// Each player should know which board they belong to.
	public CheckersPlayer(GameBoard board, Color color, byte directionality) {
		super(board, color);
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
	public boolean lost(){
		return hasLost;
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
	// Returns: the piece which made a capture, or null
	public GamePiece makeAMove(GameBoard board, int x1, int y1, int x2, int y2) {
		// First get the piece to be moved.
		GamePiece p = null;
		GamePiece ret = null;

		if (board.get(x1, y1) != null) {
			p = board.get(x1, y1);

			// Does this piece belong to me?
			if (p.getPlayer() == this) {
				// Move piece to desired location.
				if (p.isLegalMove(x1, y1, x2, y2)) {
					if(((CheckersPiece) p).moveWouldMakeAKing(x1, y1, x2, y2)){
						((CheckersPiece) p).makeKing();
					}
					if (Math.abs(y2 - y1) == 2 && Math.abs(x2-x1) == 2) {
						// Get the piece we are trying to capture:
						GamePiece target = board.get((x1+x2)/2,(y1+y2)/2);
						// Check if we can actually capture piece in the middle.
						if (target != null && p.canCapture(target)){
							((CheckersPlayer) target.getPlayer()).decreasePiecesCount(); // THIS LINE NEEDS TO BE CHECKED
							if(((CheckersPlayer) target.getPlayer()).getPiecesCount() == 0){
								hasLost = true;
							}
							board.remove((x1+x2)/2,(y1+y2)/2);
							ret = p;
						}
					}
				}
				board.remove(x1, y1);
				board.put(p, x2, y2);
			} else {
				System.out.println("This piece doesn't belong to you!");
			}
		} else {

			System.out.println("Please select a valid Checkers piece");
		}
		return ret;
	}

	public byte getDirectionality() {
		return directionality;
	}

	public void setDirectionality(byte newDirectionality) {
		directionality = newDirectionality;
	}
}
