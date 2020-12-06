package piece_puzzle.actions;

import piece_puzzle.model.piece.AbstractPiece;
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
		// On supprime la pièce et on fait la rotation
		int index = m_plateau.removePiece(m_piece);
		m_piece.rotate();

		// On vérifie si on peut placer la pièce
		ActionPiecePlace placer = new ActionPiecePlace(m_plateau, m_piece);
		boolean isValid = placer.isValid();

		// On retourne dans l'autre sens la pièce et on la replace
		m_piece.unrotate();
		m_plateau.addPiece(m_piece, index);

		// On retourne le fait qu'on ait pu ou non placer la pièce
		return isValid;
	}

	@Override
	public void apply() {
		m_piece.rotate();

		m_plateau.firePieceRotated(m_piece);
	}
}
