package piece_puzzle.model;

import java.util.ArrayList;
import java.util.List;
import piece_puzzle.actions.ActionPiecePlace;

public class Plateau implements IEnsembleBlocs {
	
	private List<AbstractPiece> m_pieces;
	private int m_width;
	private int m_height;

	public Plateau() {
		this(10, 10);
	}
	
	public Plateau(int w, int h) {
		m_width = w;
		m_height = h;
		
		m_pieces = new ArrayList<>();
	}

	public void addPiece(AbstractPiece p) {
		ActionPiecePlace placement = new ActionPiecePlace(this, p);
		
		if(placement.isValid())
			placement.apply();
	}
	
	public void removePiece(AbstractPiece p) {
		m_pieces.remove(p);
	}

	public boolean isCaseFilledAt(int x, int y) {
		if(x < 0 || x >= m_width ||
			y < 0 || y >= m_height) {
			return true;
		}
		
		for(AbstractPiece p : m_pieces) {
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
	
	public AbstractPiece getPieceAt(int x, int y) {
		if(x < 0 || x >= m_width ||
			y < 0 || y >= m_height) {
			return null;
		}
		
		for(AbstractPiece p : m_pieces) {
			if(p.getPosition().getX() <= x 
					&& p.getPosition().getX() + p.getWidth() >= x
					&& p.getPosition().getY() <= y
					&& p.getPosition().getY() + p.getHeight() >= y) {
				if(p.isCaseFilledAt(x - p.getPosition().getX(),
						y - p.getPosition().getY())) {
					return p;
				}
			}
		}
		
		return null;
	}
	
	public List<AbstractPiece> getPieces() {
		return m_pieces;
	}

	public void setPieces(List<AbstractPiece> pieces) {
		this.m_pieces = pieces;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		
		for(int y = 0 ; y < m_height ; y++) {
			for(int x = 0 ; x < m_width ; x++) {
				char c = '.';
				for(AbstractPiece p : m_pieces) {
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

	@Override
	public int getWidth() {
		return m_width;
	}

	@Override
	public int getHeight() {
		return m_height;
	}
	
}
