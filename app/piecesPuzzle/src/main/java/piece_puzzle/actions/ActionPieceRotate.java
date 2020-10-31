package piece_puzzle.actions;

import piece_puzzle.actions.validator.ActionPiecePlaceValidator;
import piece_puzzle.actions.validator.IValidator;
import piece_puzzle.model.AbstractPiece;
import piece_puzzle.model.Plateau;

public class ActionPieceRotate implements IAction{

	private AbstractPiece m_piece;
	private Plateau m_plateau;

	public ActionPieceRotate(Plateau plateau, AbstractPiece piece) {
		m_piece = piece;
		m_plateau = plateau;
	}
	
	@Override
	public boolean isValid() {
		m_plateau.removePiece(m_piece);
		m_piece.rotate();
		
		IValidator validator = new ActionPiecePlaceValidator(m_plateau, m_piece);
		boolean isValid = validator.isValid();
		
		m_piece.unrotate();
		m_plateau.addPiece(m_piece);

		return isValid;
	}

	@Override
	public void apply() {
		m_piece.rotate();
	}
}
