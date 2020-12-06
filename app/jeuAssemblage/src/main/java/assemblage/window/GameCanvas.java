package assemblage.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

import assemblage.game.GameState;
import assemblage.observer.IGameStateListener;
import piece_puzzle.actions.ActionPieceMove;
import piece_puzzle.actions.ActionPieceRotate;
import piece_puzzle.model.AbstractPiece;
import piece_puzzle.model.Plateau;
import piece_puzzle.observer.IPlateauListener;

public class GameCanvas extends JPanel implements MouseListener, MouseMotionListener, IPlateauListener, IGameStateListener {
	
	private static final Color[] COLORS = {Color.BLUE, Color.GREEN, Color.PINK, Color.ORANGE};
	
	private GameState m_state;
	private AbstractPiece m_selectedPiece;

	private int offsetX;
	private int offsetY;
	
	public GameCanvas(GameState state) {
		setGameState(state);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	/**
	 * Récupère la taille d'une case
	 * @return la taille de la case
	 */
	protected float getCellSize() {
		return Math.min((float)getHeight() / (float) m_state.getPlateau().getHeight(), (float)getWidth() / (float) m_state.getPlateau().getWidth());
	}

	/**
	 * Dessine la grille
	 * @param g l'objet avec lequel on dessine
	 */
	protected void drawGrid(Graphics g) {
		g.setColor(Color.BLACK);

		float cellSize = getCellSize();

		for(int y = 0; y < m_state.getPlateau().getHeight() + 1; y++) {
			for(int x = 0; x < m_state.getPlateau().getWidth() + 1 ; x++) {
				g.drawLine((int)(x * cellSize), 0, (int)(x * cellSize), (int)(m_state.getPlateau().getHeight() * cellSize));
			}
			g.drawLine(0, (int)(y * cellSize), (int)(m_state.getPlateau().getWidth() * cellSize), (int)(y * cellSize));
		}
	}
	
	protected void drawPieces(Graphics g) {
		float cellSize = getCellSize();

		int i = 0;
		for(AbstractPiece piece : m_state.getPlateau().getPieces()) {
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

		if(m_state != null)
			drawPieces(g);
		
		drawGrid(g);
	}

	public void redraw() {
		revalidate();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		if(m_state.isFinished())
			return;

		float cellSize = getCellSize();
		
		int caseX = (int) (me.getX() / cellSize);
		int caseY = (int) (me.getY() / cellSize);

		AbstractPiece piece = m_state.getPlateau().getPieceAt(caseX, caseY);
		if(piece != null) {
			ActionPieceRotate rotate = new ActionPieceRotate(m_state.getPlateau(), piece);
			if(rotate.isValid()) {
				rotate.apply();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {

	}	

	@Override
	public void mouseDragged(MouseEvent me) {
		if(m_state.isFinished())
			return;

		float cellSize = getCellSize();
		int caseX = (int) (me.getX() / cellSize);
		int caseY = (int) (me.getY() / cellSize);

		if(m_selectedPiece == null) {
			m_selectedPiece = m_state.getPlateau().getPieceAt(caseX, caseY);
			if(m_selectedPiece != null) {
				offsetX = caseX - m_selectedPiece.getPosition().getX();
				offsetY = caseY - m_selectedPiece.getPosition().getY();
			} else {
				return;
			}
		}

		int xOffset = caseX - m_selectedPiece.getPosition().getX() - offsetX;
		int yOffset = caseY - m_selectedPiece.getPosition().getY() - offsetY;

		ActionPieceMove action = new ActionPieceMove(m_state.getPlateau(), m_selectedPiece, xOffset, yOffset);
		if(action.isValid()) {
			action.apply();
		}
	}

	@Override
	public void mouseMoved(MouseEvent me) {
	}
	
	@Override
	public void mouseReleased(MouseEvent me) {
		if(m_selectedPiece != null && !m_state.isFinished()) {
			m_state.decrementNbCoupsRestants();
		}

		m_selectedPiece = null;
	}

	@Override
	public void mouseEntered(MouseEvent me) {
	}

	@Override
	public void mouseExited(MouseEvent me) {
	}

	@Override
	public void pieceMoved(AbstractPiece piece) {
		redraw();
	}

	@Override
	public void pieceRotated(AbstractPiece piece) {
		redraw();

		m_state.decrementNbCoupsRestants();
	}

	@Override
	public void pieceAdded(AbstractPiece piece) {
		redraw();
	}

	@Override
	public void pieceRemoved(AbstractPiece piece) {
		redraw();
	}

	public void setGameState(GameState state) {
		if(state == null)
			return;

		if(m_state != null) {
			m_state.getPlateau().removeListener(this);
			m_state.removeListener(this);
		}

		m_state = state;
		m_state.getPlateau().addListener(this);
		m_state.addListener(this);

		redraw();
	}

	@Override
	public void stateReset() {
		redraw();

		m_state.getPlateau().addListener(this);
	}

	@Override
	public void nbCoupsRestantsChanged(int nbCoupsRestants) {

	}

	public GameState getGameState() {
		return m_state;
	}

}
