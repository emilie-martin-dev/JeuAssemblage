package jeu.window;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import piece_puzzle.model.AbstractPiece;
import piece_puzzle.model.PieceL;
import piece_puzzle.model.Plateau;

public class GameCanvas extends JPanel {
	
	private Plateau m_plateau;
	private static final Color[] COLORS = {Color.BLUE, Color.GREEN, Color.PINK, Color.orange};
	
	public GameCanvas(Plateau plateau) {
		m_plateau = plateau;
		
		m_plateau.addPiece(new PieceL(3, 5));
	}
	
	/**
	 * Récupère la taille d'une case
	 * @return la taille de la case
	 */
	protected float getCellSize() {
		return Math.min((float)getHeight() / (float)m_plateau.getHeight(), (float)getWidth() / (float)m_plateau.getWidth());
	}

	/**
	 * Dessine la grille
	 * @param g l'objet avec lequel on dessine
	 */
	protected void drawGrid(Graphics g) {
		g.setColor(Color.BLACK);

		float cellSize = getCellSize();

		for(int y = 0 ; y < m_plateau.getHeight() + 1; y++) {
			for(int x = 0 ; x < m_plateau.getWidth() + 1 ; x++) {
				g.drawLine((int)(x * cellSize), 0, (int)(x * cellSize), (int)(m_plateau.getHeight() * cellSize));
			}
			g.drawLine(0, (int)(y * cellSize), (int)(m_plateau.getWidth() * cellSize), (int)(y * cellSize));
		}
	}
	
	protected void drawPieces(Graphics g) {
		float cellSize = getCellSize();
		
		int i = 0;
		for(AbstractPiece piece : m_plateau.getPieces()) {
			g.setColor(COLORS[i % COLORS.length]);
			
			for(int x = 0 ; x < piece.getWidth() ; x++) {
				for(int y = 0 ; y < piece.getHeight() ; y++) {
					if(piece.isCaseFilledAt(x, y)) {
						g.fillRect((int) ((piece.getPosition().getX() + x) * cellSize), (int) ((piece.getPosition().getY() + y) * cellSize), (int) Math.round(cellSize), (int) Math.round(cellSize));
					}
				}
			}
			
			i++;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		drawPieces(g);
		
		drawGrid(g);
	}

	public Plateau getPlateau() {
		return m_plateau;
	}

	public void setPlateau(Plateau plateau) {
		m_plateau = plateau;
	}
	
}
