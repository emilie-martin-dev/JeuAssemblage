package assemblage.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import piece_puzzle.actions.ActionPieceMove;
import piece_puzzle.actions.ActionPieceRotate;
import piece_puzzle.model.AbstractPiece;
import piece_puzzle.model.Plateau;

public class GameCanvas extends JPanel implements MouseListener, MouseMotionListener {
	
	private static final Color[] COLORS = {Color.BLUE, Color.GREEN, Color.PINK, Color.ORANGE};
	
	private Plateau m_plateau;
	private AbstractPiece m_selectedPiece;

	private int offsetX;
	private int offsetY;
	
	public GameCanvas(Plateau plateau) {
		setPlateau(plateau);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
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
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		if(m_plateau != null)
			drawPieces(g);
		
		drawGrid(g);
	}

	public void redraw() {
		revalidate();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		float cellSize = getCellSize();
		
		int caseX = (int) (me.getX() / cellSize);
		int caseY = (int) (me.getY() / cellSize);
		
		AbstractPiece piece = m_plateau.getPieceAt(caseX, caseY);
		if(piece != null) {
			ActionPieceRotate rotate = new ActionPieceRotate(m_plateau, piece);
			if(rotate.isValid())
				rotate.apply();

			redraw();
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {
		float cellSize = getCellSize();
		
		int caseX = (int) (me.getX() / cellSize);
		int caseY = (int) (me.getY() / cellSize);
		
		m_selectedPiece = m_plateau.getPieceAt(caseX, caseY);
		if(m_selectedPiece != null) {
			offsetX = caseX - m_selectedPiece.getPosition().getX();
			offsetY = caseY - m_selectedPiece.getPosition().getY();
		}
	}	

	@Override
	public void mouseDragged(MouseEvent me) {
		if(m_selectedPiece == null)
			return;

		float cellSize = getCellSize();

		int caseX = (int) (me.getX() / cellSize);
		int caseY = (int) (me.getY() / cellSize);

		int xOffset = caseX - m_selectedPiece.getPosition().getX() - offsetX;
		int yOffset = caseY - m_selectedPiece.getPosition().getY() - offsetY;

		ActionPieceMove action = new ActionPieceMove(m_plateau, m_selectedPiece, xOffset, yOffset);
		if(action.isValid()) {
			action.apply();
			redraw();
		}
	}

	@Override
	public void mouseMoved(MouseEvent me) {
	}
	
	@Override
	public void mouseReleased(MouseEvent me) {
		m_selectedPiece = null;
	}

	@Override
	public void mouseEntered(MouseEvent me) {
	}

	@Override
	public void mouseExited(MouseEvent me) {
	}
	
	public Plateau getPlateau() {
		return m_plateau;
	}

	public void setPlateau(Plateau plateau) {
		m_plateau = plateau;
		redraw();
	}
	
}
