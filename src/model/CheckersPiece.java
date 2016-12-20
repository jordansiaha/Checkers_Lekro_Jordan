package model;

public class CheckersPiece extends GamePiece{

	private boolean isKing = false;
	public CheckersPiece(Player player, GameBoard board) {
		super(player, board);
	}

	@Override
	public char getCharacterRepresentation() {
		return 'O';
	}
	

	// Return whether this checkers piece can capture the other piece passed as a parameter.
	@Override
	public boolean isCapturable(GamePiece p) {
		return !this.getPlayer().equals(p.getPlayer());
	}

	@Override
	// Is this a legal move on this game board?
	public boolean isLegalMove(int x1, int y1, int x2, int y2) {
		
		if (!getBoard().isValidLocation(x2, y2)) return false;
		byte dir = getPlayer().getDirectionality();

		if (!isKing()) {
			if (x2 > x1 && dir == Player.NEGATIVE_Y || x1 > x2 && dir == Player.POSITIVE_Y)
			return false; // only kings can go back
		}
		
		// If we are attempting to move two spaces across a diagonal:
		if (Math.abs(y2 - y1) == 2 && Math.abs(x2-x1) == 2) {
			// Get the piece we are trying to capture:
			GamePiece target = getBoard().get((x1+x2)/2,(y1+y2)/2);
			// Get the tile we will finally land on
			GamePiece fin = getBoard().get(x2, y2);
			// Check if final tile is null (empty)
			if (fin != null) return false;
			// Check if we can actually capture piece in the middle.
			if (target != null && this.isCapturable(target)) return true;
			return false;
		}
		// Otherwise, we can only move once, but only if we cannot jump.
		if (canJump(x1, y1)) return false;
		if (Math.abs(y2 - y1) != 1 || Math.abs(x2-x1) != 1) return false; // move diagonally
		return true;
	}
	
	public boolean canJump(int x, int y) {
		byte dir = getPlayer().getDirectionality();
		if (isKing() || dir == Player.POSITIVE_Y) {
			if (isLegalMove(x, y, x+2, y+2)) return true;
			if (isLegalMove(x, y, x-2, y+2)) return true;
		}
		if (isKing() || dir == Player.NEGATIVE_Y) {
			if (isLegalMove(x, y, x+2, y-2)) return true;
			if (isLegalMove(x, y, x-2, y-2)) return true;
		}
		return false;
	}
	
	// Turn a checkers piece into a king.
	public void makeKing(){
		isKing = true;
	}
	
	public boolean isKing() {
		return isKing;
	}
	
}
