package model;

public class Player {

	private GameBoard board;
	private boolean isTurn;
	// Each player should know which board they belong to.
	public Player(GameBoard board){
		this.board = board;
	}
	
	// Is it your turn?
	public boolean isTurn(){
		return isTurn;
	}
	
	
	public void changeTurn(){
		if(isTurn == false){
			isTurn = true;
		}
		else{
			isTurn = false;
		}
	}
}
