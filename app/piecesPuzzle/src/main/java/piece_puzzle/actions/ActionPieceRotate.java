package piece_puzzle.actions;

import piece_puzzle.model.AbstractPiece;
import piece_puzzle.model.Plateau;

/**
 * Effectue une rotation sur une pièce
 */
public class ActionPieceRotate implements IAction{

	/**
	 * La pièce a retourner
	 */
	private AbstractPiece m_piece;
	/**
	 * Le plateau sur lequel est la pièce
	 */
	private Plateau m_plateau;

	public ActionPieceRotate(Plateau plateau, AbstractPiece piece) {
		m_piece = piece;
		m_plateau = plateau;
	}
	
	@Override
	public boolean isValid() {
		int index = m_plateau.removePiece(m_piece);
		m_piece.rotate();
		
		ActionPiecePlace placer = new ActionPiecePlace(m_plateau, m_piece);
		boolean isValid = placer.isValid();
		
		m_piece.unrotate();
		m_plateau.addPiece(m_piece, index);

		return isValid;
	}

	@Override
	public void apply() {
		m_piece.rotate();

		m_plateau.firePieceRotated(m_piece);
	}
}
