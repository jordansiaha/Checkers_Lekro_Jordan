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

public class CheckersView extends GraphicView {

	private CheckersGame game;

	private int xPos, yPos, tileSize, tileBorderRatio;
	private int[] selectedLocation;
	private Set<int[]> validPieces;

	public CheckersView(CheckersGame game) {
		super(game);
	}

	public void drawBoard(Graphics2D g, int x, int y, int tileSize, int rows, int cols, Color color1, Color color2) {
		// Draw the board completely red, then fill in black squares
		g.setColor(color1);
		g.fillRect(x, y, tileSize * rows, tileSize * cols);
		g.setColor(color2);
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if ((i + j) % 2 == 0)
					continue;
				g.fillRect(x + j * tileSize, y + i * tileSize, tileSize, tileSize);
			}
		}

	}

}
