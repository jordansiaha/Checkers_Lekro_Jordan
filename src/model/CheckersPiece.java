package model;

public class CheckersPiece extends GamePiece{

	public CheckersPiece(Player player, GameBoard board) {
		super(player, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public char getCharacterRepresentation() {
		// TODO use player emblem or something
		return 'O';
	}

	
}
