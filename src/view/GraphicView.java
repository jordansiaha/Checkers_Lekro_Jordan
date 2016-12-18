package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.CheckersGame;
import model.GameBoard;
import model.GamePiece;

// To be implemented MUCH MUCH later, this will be our fancy visual layout of the game.
public class GraphicView extends JPanel implements Observer{

	private CheckersGame game;
	@Override
	public void update(Observable o, Object arg) {
		revalidate();
		repaint();
	}
	
	public GraphicView(CheckersGame game){
		this.game = game;
	}
	
	@Override
	public void paintComponent(Graphics ga) {
		
		super.paintComponent(ga); // Bind ga parameter to paintComponent's superclasse's graphics object instead
									// This prevents the repainting of objects elsewhere within the graphics panel.
		
		Graphics2D g = (Graphics2D) ga;
		
		
		int borderSize = 32;
		int tileSize = 72;
		int tileBorderSize = tileSize / 8;
		
		
		// following are gotten from game now
		int rows = game.getBoard().getWidth();
		int cols = game.getBoard().getHeight(); 
		
		int height = borderSize * 2 + tileSize * rows;
		int width = borderSize * 2 + tileSize * cols;
		
		Color darkTile = new Color(0xff, 0xda, 0xb2);
		Color lightTile = new Color(0xa0, 0x6c, 0x30);
		
		// Draw the board completely red, then fill in black squares
		g.setColor(lightTile);
		g.fillRect(borderSize, borderSize, tileSize * rows, tileSize * cols);
		g.setColor(darkTile);
		for (int i = 0; i < cols; i++)  {
			for (int j = 0; j < rows; j++) {
				if ((i+j) % 2 == 0) continue;
				g.fillRect(borderSize + j * tileSize, borderSize + i * tileSize, tileSize, tileSize);
			}
		}
		
		GameBoard board = game.getBoard();
		
		// Now we draw pieces...
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				GamePiece piece = board.get(i, j);
				if (piece == null) continue;
				g.setColor(piece.getPlayer().getColor());
				g.fillOval(borderSize + i * tileSize + tileBorderSize, 
						borderSize + (rows - 1 - j) * tileSize + tileBorderSize, 
						tileSize - 2 * tileBorderSize, tileSize - 2 * tileBorderSize);
			}
		}
		
		
	}

}
