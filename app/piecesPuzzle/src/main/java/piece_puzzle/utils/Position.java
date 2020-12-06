package piece_puzzle.utils;

public class Position {

	/**
	 * Coordonnée x
	 */
	private int m_x;
	/**
	 * Coordonnée y
	 */
	private int m_y;

	/**
	 * @param x Coordonnée x
	 * @param y Coordonnée y
	 */
	public Position(int x, int y) {
		m_x = x;
		m_y = y;
	}

	/**
	 * Déplace la position d'un certains montant en x et y
	 * @param xOffset Montant en x
	 * @param yOffset Montant en y
	 */
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
