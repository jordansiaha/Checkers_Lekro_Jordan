package model;

// Leaving this class to you.
public class GameBoard {
	
	private GamePiece [][] gameBoard;
	private int width, height;
	
	/**
	 * Instantiates a GameBoard of size width * height.
	 * @param width
	 * @param height
	 */
	public GameBoard(int width, int height){
		// We will have x coordinate first, y coordinate later.
		// The bottom left corner is (0, 0), like in chess A1.
		this.width = width;
		this.height = height;
		gameBoard = new GamePiece[width][height];
	}
	

	/**
	 * Adds a game piece to the board at location specified by (x, y).
	 * The newly added GamePiece has its board set to this one.
	 * The removed GamePiece has its board set to null.
	 * @param p - Piece to add
	 * @param x - X coordinate at which to add
	 * @param y - Y coordinate at which to add
	 * @return the GamePiece previously at the location at which the new one was added, or null
	 * @throws IndexOutOfBoundsException - if the coordinates (x, y) are not on the board
	 */
	public GamePiece put(GamePiece p, int x, int y){
		checkCoordinates(x, y);
		
		GamePiece old = gameBoard[x][y];
		old.setBoard(null);
		p.setBoard(this);
		gameBoard[x][y] = p;
		return old;
		
	}
	
	/**
	 * Adds a game piece to the board at location specified by given 2d vector.
	 * The newly added GamePiece has its board set to this one.
	 * The removed GamePiece has its board set to null.
	 * @param p - Piece to add
	 * @param vec - 2d vector specifying location at which to add piece
	 * @return the GamePiece previously at the location at which the new one was added, or null
	 * @throws IndexOutOfBoundsException - if the coordinates (x, y) are not on the board
	 */
	public GamePiece put(GamePiece p, int[] vec) {
		check2dVector(vec);
		return put(p, vec[0], vec[1]);
	}
	
	/**
	 * Retrieve the GamePiece at (x, y). The GameBoard is not modified.
	 * @param x
	 * @param y
	 * @return the GamePiece at (x, y).
	 * @throws IndexOutOfBoundsException - if the coordinates (x, y) are not on the board
	 */
	public GamePiece get(int x, int y) {
		checkCoordinates(x, y);
		return gameBoard[x][y];
	}
	
	/**
	 * Retrieve the GamePiece at the given 2d vector. The GameBoard is not modified.
	 * @param x
	 * @param y
	 * @return the GamePiece at the given 2d vector.
	 * @throws IndexOutOfBoundsException - if the coordinates (x, y) are not on the board
	 */
	public GamePiece get(int[] vec) {
		check2dVector(vec);
		return get(vec[0], vec[1]);
	}
	
	/**
	 * Remove a GamePiece at (x, y). Its spot in the gameBoard becomes null.
	 * The removed GamePiece has its board set to null.
	 * @param x
	 * @param y
	 * @return the GamePiece which was removed
	 * @throws IndexOutOfBoundsException - if the coordinates (x, y) are not on the board
	 */
	public GamePiece remove(int x, int y) {
		checkCoordinates(x, y);
		GamePiece p = gameBoard[x][y];
		gameBoard[x][y] = null;
		p.setBoard(null);
		return p;
	}
	
	/**
	 * Remove a GamePiece at the given 2d vector. Its spot in the gameBoard becomes null.
	 * The removed GamePiece has its board set to null.
	 * @param x
	 * @param y
	 * @return the GamePiece which was removed
	 * @throws IndexOutOfBoundsException - if the coordinates (x, y) are not on the board
	 */
	public GamePiece remove(int[] vec) {
		check2dVector(vec);
		return remove(vec[0], vec[1]);
	}
	
	/**
	 * Gets the coordinates of the given GamePiece.
	 * @param p - GamePiece to find
	 * @return the coordinates as array of length 2 (2d vector) in format {x, y}, or null if not found
	 */
	public int[] getCoordinates(GamePiece p) {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				if (p == gameBoard[i][j]) return new int[] {i, j};
			}
		}
		return null;
	}
	
	/**
	 * Throws an IndexOutOfBoundsException if (x, y) are not within this game board.
	 * @param x
	 * @param y
	 */
	private void checkCoordinates(int x, int y) {

		if (x < 0 || x >= width || y < 0 || y >= height) {
			String s = "Coordinates ("+x+", "+y+") are not in this board of size "+width+" * "+height;
			throw new IndexOutOfBoundsException(s);
		}
		
	}
	
	/**
	 * Throws an IllegalArgumentException if the input vector is not two-dimensional
	 * (array of length 2)
	 * @param vec
	 */
	private void check2dVector(int[] vec) {
		if (vec.length != 2) throw new IllegalArgumentException("Input must be length 2");
	}
}
