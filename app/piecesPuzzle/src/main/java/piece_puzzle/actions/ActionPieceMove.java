package piece_puzzle.actions;

import piece_puzzle.actions.validator.ActionPiecePlaceValidator;
import piece_puzzle.actions.validator.IValidator;
import piece_puzzle.model.AbstractPiece;
import piece_puzzle.model.Plateau;

public class ActionPieceMove implements IAction{

	private AbstractPiece m_piece;
	private Plateau m_plateau;
	private int m_xOffset;
	private int m_yOffset;

	public ActionPieceMove(Plateau plateau, AbstractPiece piece, int xOffset, int yOffset) {
		m_piece = piece;
		m_plateau = plateau;
		m_xOffset = xOffset;
		m_yOffset = yOffset;
	}
	
	@Override
	public boolean isValid() {
		m_plateau.removePiece(m_piece);
		m_piece.move(m_xOffset, m_yOffset);
		
		IValidator validator = new ActionPiecePlaceValidator(m_plateau, m_piece);
		boolean isValid = validator.isValid();
		
		m_piece.move(-m_xOffset, -m_yOffset);
		m_plateau.addPiece(m_piece);

		return isValid;
	}

	@Override
	public void apply() {
		m_piece.move(m_xOffset, m_yOffset);
	}
}
