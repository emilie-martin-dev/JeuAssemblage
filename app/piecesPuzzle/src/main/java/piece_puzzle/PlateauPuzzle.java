package piece_puzzle;

import java.util.ArrayList;
import java.util.List;
import piece_puzzle.actions.ActionPlacement;
import piece_puzzle.pieces.PiecePuzzle;

public class PlateauPuzzle {
	
	private List<PiecePuzzle> m_pieces;
	private int m_w;
	private int m_h;

	public PlateauPuzzle() {
		this(10, 10);
	}
	
	public PlateauPuzzle(int w, int h) {
		m_w = w;
		m_h = h;
		
		m_pieces = new ArrayList<>();
	}

	public void addPiece(PiecePuzzle p) {
		ActionPlacement placement = new ActionPlacement(this, p);
		
		if(placement.isValid())
			placement.apply();
	}

	public boolean isCaseFilledAt(int x, int y) {
		for(PiecePuzzle p : m_pieces) {
			if(p.getPosition().getX() <= x 
					&& p.getPosition().getX() + p.getWidth() >= x
					&& p.getPosition().getY() <= y
					&& p.getPosition().getY() + p.getHeight() >= y) {
				if(p.isCaseFilledAt(x - p.getPosition().getX(),
						y - p.getPosition().getY())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public List<PiecePuzzle> getPieces() {
		return m_pieces;
	}

	public void setPieces(List<PiecePuzzle> pieces) {
		this.m_pieces = pieces;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		
		for(int y = 0 ; y < m_h ; y++) {
			for(int x = 0 ; x < m_w ; x++) {
				char c = '.';
				for(PiecePuzzle p : m_pieces) {
					if(p.isCaseFilledAt(x - p.getPosition().getX(), y - p.getPosition().getY())) {
						c = 'X';
						break;
					}
					
				}
				buffer.append(c);
			}
			
			buffer.append("\n");
		}
		
		return buffer.toString();
	}	
	
}
