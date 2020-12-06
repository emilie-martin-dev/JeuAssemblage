package assemblage.game.generator;

import piece_puzzle.model.Plateau;

/**
 * Interface pour générer des plateau
 */
public interface IPlateauGenerator {

    /**
     * Génère un plateau avec les dimensions demandés
     * @param width Largeur du plateau
     * @param height Hauteur du plateau
     * @return Le plateau généré
     */
    public Plateau generate(int width, int height);
	
}
