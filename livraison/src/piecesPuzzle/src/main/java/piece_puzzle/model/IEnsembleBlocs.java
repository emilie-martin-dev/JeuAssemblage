package piece_puzzle.model;

/**
 * Interface pour définir un ensemble de pièce
 */
public interface IEnsembleBlocs {

	public int getWidth();	
	public int getHeight();

	/**
	 * Permet de savoir si la case à la position x, y est remplie ou non
	 * @param x Position x
	 * @param y Position y
	 * @return true si la case est remplie, false sinon
	 */
	public boolean isCaseFilledAt(int x, int y);

}
