package piece_puzzle;

public class Position {
	
	private int m_x;
	private int m_y;

	public Position(int x, int y) {
		this.m_x = x;
		this.m_y = y;
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
