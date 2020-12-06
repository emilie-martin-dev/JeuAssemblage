package piece_puzzle.actions;

/**
 * Définit une action
 */
public interface IAction {

	/**
	 * Applique l'action
	 */
	public void apply();

	/**
	 * Vérifie si l'action est valide
	 * @return Retourne si l'action est valide
	 */
	public boolean isValid();
	
}
