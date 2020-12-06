package piece_puzzle.actions;

import piece_puzzle.model.AbstractPiece;
import piece_puzzle.model.Plateau;

/**
 * Supprime une pièce du plateau
 */
public class ActionPieceRemove implements IAction {

	/**
	 * La pièce a placer
	 */
	private AbstractPiece m_piece;
	/**
	 * Le plateau sur lequel ajouter la pièce
	 */
	private Plateau m_plateau;

	public ActionPieceRemove(Plateau plateau, AbstractPiece piece) {
		m_piece = piece;
		m_plateau = plateau;
	}
	
	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void apply() {
		m_plateau.removePiece(m_piece);

		m_plateau.firePieceRemoved(m_piece);
	}
	
}
