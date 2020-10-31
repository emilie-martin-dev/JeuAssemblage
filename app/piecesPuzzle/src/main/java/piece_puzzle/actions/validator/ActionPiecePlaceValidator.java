package piece_puzzle.actions.validator;

import piece_puzzle.model.AbstractPiece;
import piece_puzzle.model.Plateau;

public class ActionPiecePlaceValidator implements IValidator {
	
	private AbstractPiece m_piece;
	private Plateau m_plateau;

	public ActionPiecePlaceValidator(Plateau plateau, AbstractPiece piece) {
		m_piece = piece;
		m_plateau = plateau;
	}

	@Override
	public boolean isValid() {
		for(int x = 0 ; x < m_piece.getWidth() ; x++) {
			for(int y = 0 ; y < m_piece.getHeight(); y++) {
				if(!m_piece.isCaseFilledAt(x, y))
					continue;
				
				if(m_plateau.isCaseFilledAt(m_piece.getPosition().getX() + x, m_piece.getPosition().getY() + y)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
}
