package piece_puzzle.utils;

public class Position {
	
	private int m_x;
	private int m_y;

	public Position(int x, int y) {
		m_x = x;
		m_y = y;
	}	

	public void move(int xOffset, int yOffset) {
		m_x += xOffset;
		m_y += yOffset;
	}
	
	public int getX() {
		return m_x;
	}

	public void setX(int x) {
		this.m_x = x;
	}

	public int getY() {
		return m_y;
	}

	public void setY(int y) {
		this.m_y = y;
	}
	
}
