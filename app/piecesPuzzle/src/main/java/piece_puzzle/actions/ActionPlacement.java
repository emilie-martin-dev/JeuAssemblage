package piece_puzzle.actions;

import piece_puzzle.PlateauPuzzle;
import piece_puzzle.pieces.PiecePuzzle;

public class ActionPlacement implements Action {

	private PiecePuzzle m_piece;
	private PlateauPuzzle m_plateau;

	public ActionPlacement(PlateauPuzzle plateau, PiecePuzzle piece) {
		this.m_piece = piece;
		this.m_plateau = plateau;
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

	@Override
	public void apply() {
		m_plateau.getPieces().add(m_piece);
	}
	
}
