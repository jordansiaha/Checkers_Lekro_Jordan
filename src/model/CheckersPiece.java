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
		if(this.getPlayer().equals(p.getPlayer())){
			return false;
		}
		return true;
	}

	@Override
	// Is this a legal move on this game board?
	public boolean isLegalMove(GameBoard board, int x1, int y1, int x2, int y2) {
		// TODO
		return false;
	}
	// Turn a checkers piece into a king.
	public void makeKing(){
		isKing = true;
	}
	
}
