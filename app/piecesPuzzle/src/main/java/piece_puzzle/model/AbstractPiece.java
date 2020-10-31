package piece_puzzle.model;

import piece_puzzle.utils.Position;

public abstract class AbstractPiece implements IEnsembleBlocs {
	
	private Position m_position;
	private int m_rotationCount;
	private int m_width;
	private int m_height;
	
	private boolean[] m_cells;

	public AbstractPiece(int w, int h) {
		this(w, h, 0, 0);
	}
	
	public AbstractPiece(int w, int h, int x, int y) {
		m_rotationCount = 0;
		
		m_cells = generatePiece(w, h);		
		
		m_width = w;
		m_height = h;
		
		m_position = new Position(x, y);
	}	
	
	protected abstract boolean[] generatePiece(int w, int h);
	
	public boolean isCaseFilledAt(int x, int y) {
		if(x < 0 || x >= getWidth()) {
			return false;
		} else if(y < 0 || y >= getHeight()) {
			return false;
		}
				
		int pieceX = x;
		int pieceY = y;
		
		// cf : https://calcworkshop.com/wp-content/uploads/common-rotations-origin.png
		for(int i = m_rotationCount ; i % 4 != 0 ; i--) {
			int tmp = pieceX;
			pieceX = pieceY;
			pieceY = -tmp;
			
			pieceY += ((i % 2 == 0) ? m_width : m_height);	
			pieceY -= 1;
		}

		if(m_cells[pieceX + pieceY * m_width]) {
			return true;
		}
		
		return false;
	}
	
	public void rotate() {
		m_rotationCount++;
		
		if(m_rotationCount >= 4)
			m_rotationCount %= 4;
	}
	
	public void unrotate() {
		m_rotationCount--;
		
		if(m_rotationCount < 0)
			m_rotationCount = 3;
	}
	
	public void move(int xOffset, int yOffset) {
		m_position.move(xOffset, yOffset);
	}
	
	public int getWidth() {
		if(m_rotationCount % 2 == 0)
			return m_width;
		
		return m_height;
	}
	
	public int getHeight() {
		if(m_rotationCount % 2 == 0)
			return m_height;
		
		return m_width;		
	}
	
	public Position getPosition() {
		return m_position;
	}

	public void setPosition(Position position) {
		this.m_position = position;
	}
	
	public boolean[] getBlocsPosition() {
		return m_cells;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		
		for(int y = 0 ; y < getHeight() ; y++) {
			for(int x = 0 ; x < getWidth() ; x++) {
				char c = '.';
				if(isCaseFilledAt(x, y)) {
					c = 'X';
				}
				
				buffer.append(c);
			}
			
			buffer.append("\n");
		}
		
		return buffer.toString();
	}

}
