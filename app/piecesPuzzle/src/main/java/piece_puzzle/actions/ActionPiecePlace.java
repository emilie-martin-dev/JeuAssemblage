package piece_puzzle.actions;

import piece_puzzle.model.Plateau;
import piece_puzzle.model.piece.AbstractPiece;

/**
 * Place une pièce sur le plateau
 */
public class ActionPiecePlace implements IAction {

	/**
	 * La pièce a placer
	 */
	private AbstractPiece m_piece;
	/**
	 * Le plateau sur lequel ajouter la pièce
	 */
	private Plateau m_plateau;
	/**
	 * L'indice du plateau sur lequel ajouter la pièce
	 */
	private int m_index;

	public ActionPiecePlace(Plateau plateau, AbstractPiece piece) {
		this(plateau, piece, plateau.getPieces().size());
	}

	public ActionPiecePlace(Plateau plateau, AbstractPiece piece, int index) {
		m_piece = piece;
		m_plateau = plateau;
		m_index = index;
	}
	
	@Override
	public boolean isValid() {
		int pieceX = m_piece.getPosition().getX();
		int pieceY = m_piece.getPosition().getY();

		if(pieceX < 0 || pieceX + m_piece.getWidth() > m_plateau.getWidth()
			|| pieceY < 0 || pieceY + m_piece.getHeight() > m_plateau.getHeight())
			return false;

		for(int x = 0 ; x < m_piece.getWidth() ; x++) {
			for(int y = 0 ; y < m_piece.getHeight(); y++) {
				if(!m_piece.isCaseFilledAt(x, y))
					continue;

				if(m_plateau.isCaseFilledAt(pieceX + x, pieceY + y)) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public void apply() {
		m_plateau.getPieces().add(m_index, m_piece);

		m_plateau.firePieceAdded(m_piece);
	}
	
}
