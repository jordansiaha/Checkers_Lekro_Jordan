package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.CheckersGame;
import model.CheckersPiece;
import model.GameBoard;
import model.GamePiece;

// To be implemented MUCH MUCH later, this will be our fancy visual layout of the game.
public class GraphicView extends JPanel implements Observer, MouseListener {

	private CheckersGame game;
	
	private int xPos, yPos, tileSize, tileBorderRatio;
	private int[] selectedLocation;
	private Set<int[]> validPieces;

	@Override
	public void update(Observable o, Object arg) {
		selectedLocation = null;
		validPieces = game.getValidPieces(game.getCurrentPlayer());
		revalidate();
		repaint();
	}
	
	public GraphicView(CheckersGame game){
		this.setBackground(Color.BLACK);
		this.game = game;
		this.addMouseListener(this);
		setxPos(32);
		setyPos(32);
		setTileSize(72);
		setTileBorderRatio(8);
		selectedLocation = null;
		validPieces = game.getValidPieces(game.getCurrentPlayer());
		revalidate();
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics ga) {
		
		super.paintComponent(ga); // Bind ga parameter to paintComponent's superclasse's graphics object instead
									// This prevents the repainting of objects elsewhere within the graphics panel.
		
		Graphics2D g = (Graphics2D) ga;
		
		
		int xPos = getxPos();
		int yPos = getyPos();
		int tileSize = getTileSize();
		int tileBorderSize = tileSize / getTileBorderRatio();
		
		
		// following are gotten from game now
		int rows = game.getBoard().getWidth();
		int cols = game.getBoard().getHeight(); 
		
		Color darkTile = new Color(0xff, 0xda, 0xb2);
		Color lightTile = new Color(0xa0, 0x6c, 0x30);
		drawChessBoard(g, xPos, yPos, tileSize, rows, cols, lightTile, darkTile);
		
		GameBoard board = game.getBoard();
		
		// Now we draw pieces...
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				GamePiece piece = board.get(i, j);
				if (piece == null) continue;
				g.setColor(piece.getPlayer().getColor());
				g.fillOval(xPos + i * tileSize + tileBorderSize, 
						yPos + (rows - 1 - j) * tileSize + tileBorderSize, 
						tileSize - 2 * tileBorderSize, tileSize - 2 * tileBorderSize);
				if (((CheckersPiece) piece).isKing()) {
					int innerBorderSize = tileSize / 4;
					g.setColor(Color.WHITE);
					g.fillOval(xPos + i * tileSize + innerBorderSize, 
							yPos + (rows - 1 - j) * tileSize + innerBorderSize, 
							tileSize - 2 * innerBorderSize, tileSize - 2 * innerBorderSize);
				}
			}
		}
		
		if (validPieces == null) return; // no highlighting.
		
		g.setColor(Color.BLUE);
		
		g.setComposite(AlphaComposite.SrcAtop.derive(0.3f));
		// Now draw highlighting of valid pieces, if none is selected:
		if (selectedLocation == null) {
			for (int[] vec : validPieces) {
				g.fillRect(xPos + vec[0] * tileSize, yPos + (rows - 1 - vec[1]) * tileSize, tileSize, tileSize);
			}
		} 
		// And valid moves, if one is selected:
		else {
			for (int i = 0; i < cols; i++)  {
				for (int j = 0; j < rows; j++) {
					int srcX = selectedLocation[0];
					int srcY = selectedLocation[1];
					if (!game.getBoard().get(srcX, srcY).isLegalMove(srcX, srcY, i, j)) continue;
					g.fillRect(xPos + i * tileSize, yPos + (rows - 1 - j) * tileSize, tileSize, tileSize);
				}
			}
		}
		
	}
	
	private void drawChessBoard(Graphics2D g, int x, int y, int tileSize, int rows, int cols, Color color1, Color color2) {
		// Draw the board completely red, then fill in black squares
		g.setColor(color1);
		g.fillRect(x, y, tileSize * rows, tileSize * cols);
		g.setColor(color2);
		for (int i = 0; i < cols; i++)  {
			for (int j = 0; j < rows; j++) {
				if ((i+j) % 2 == 0) continue;
				g.fillRect(x + j * tileSize, y + i * tileSize, tileSize, tileSize);
			}
		}
	}
	
	private void drawShogiBoard(Graphics2D g, int x, int y, int tileSize, int rows, int cols, Color color1, Color color2) {
		// Draw the board completely red, then fill in black squares
		g.setColor(color2);
		g.fillRect(x, y, tileSize * rows, tileSize * cols);
		g.setColor(color1);
		for (int i = 0; i < cols; i++)  {
			for (int j = 0; j < rows; j++) {
				g.drawRect(x + j * tileSize, y + i * tileSize, tileSize, tileSize);
			}
		}
	}
	
	private void drawGoBoard(Graphics2D g, int x, int y, int tileSize, int rows, int cols, Color color1, Color color2) {
		// Draw the board completely red, then fill in black squares
		g.setColor(color2);
		g.fillRect(x, y, tileSize * rows, tileSize * cols);
		g.setColor(color1);
		for (int i = 0; i < cols; i++)  {
			g.drawLine(x + i * tileSize + tileSize / 2, y, x + i * tileSize + tileSize / 2, y + tileSize * rows);
		}
		for (int i = 0; i < rows; i++) {
			g.drawLine(x, y + i * tileSize + tileSize / 2, x + tileSize * cols, y + i * tileSize + tileSize / 2);
		}
	}
	
	private int[] getTileVector(int x, int y) {
		return new int[] { (x - getxPos()) / getTileSize(), game.getBoard().getHeight() - 1 - (y - getyPos()) / getTileSize()};
	}
	

	@Override
	public void mouseClicked(MouseEvent me) {
		int[] vec = getTileVector(me.getX(), me.getY());
		if (!game.getBoard().isValidLocation(vec)) return; // not valid, don't do anything
		
		if (validPieces == null) return; // don't do anything more
		System.out.println(Arrays.toString(vec));
		
		if (selectedLocation == null) {
			// need to loop through each item of the set to check equality of vec
			for (int[] val : validPieces) {
				if (val[0] == vec[0] && val[1] == vec[1]) {
					System.out.println("VAL"+Arrays.toString(val));
					selectedLocation = vec;
					revalidate();
					repaint();
					break;
				}
			}
		}
		else if (selectedLocation != null) {
			if (!game.getBoard().get(selectedLocation).isLegalMove(selectedLocation[0], selectedLocation[1], vec[0], vec[1])) {

				selectedLocation = null; // if illegal don't do it
				revalidate();
				repaint();
			}
			else
				game.executeMove(game.getCurrentPlayer(), selectedLocation[0], selectedLocation[1], vec[0], vec[1]);
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
	

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public int getTileBorderRatio() {
		return tileBorderRatio;
	}

	public void setTileBorderRatio(int tileBorderRatio) {
		this.tileBorderRatio = tileBorderRatio;
	}

}
