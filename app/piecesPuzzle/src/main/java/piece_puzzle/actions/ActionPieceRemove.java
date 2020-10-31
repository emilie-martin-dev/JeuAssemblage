package piece_puzzle.actions;

import piece_puzzle.model.AbstractPiece;
import piece_puzzle.model.Plateau;

public class ActionPieceRemove implements IAction {

	private AbstractPiece m_piece;
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
	}
	
}
