package piece_puzzle.actions;

import piece_puzzle.model.AbstractPiece;
import piece_puzzle.model.Plateau;

/**
 * Action pour déplacer une pièce
 */
public class ActionPieceMove implements IAction{

	/**
	 * La pièce à déplacer
	 */
	private AbstractPiece m_piece;
	/**
	 * Le plateau qui contient la pièce
	 */
	private Plateau m_plateau;
	/**
	 * Le déplacement x
	 */
	private int m_xOffset;
	/**
	 * Le déplacement y
	 */
	private int m_yOffset;

	public ActionPieceMove(Plateau plateau, AbstractPiece piece, int xOffset, int yOffset) {
		m_piece = piece;
		m_plateau = plateau;
		m_xOffset = xOffset;
		m_yOffset = yOffset;
	}
	
	@Override
	public boolean isValid() {
		int index = m_plateau.removePiece(m_piece);
		m_piece.move(m_xOffset, m_yOffset);

		ActionPiecePlace placer = new ActionPiecePlace(m_plateau, m_piece);
		boolean isValid = placer.isValid();
		
		m_piece.move(-m_xOffset, -m_yOffset);
		m_plateau.addPiece(m_piece, index);

		return isValid;
	}

	@Override
	public void apply() {
		m_piece.move(m_xOffset, m_yOffset);

		m_plateau.firePieceMoved(m_piece);
	}
}
