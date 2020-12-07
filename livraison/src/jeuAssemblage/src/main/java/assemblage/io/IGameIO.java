package assemblage.io;

import assemblage.game.GameState;

/**
 * Interface pour l'export et l'import d'une partie
 */
public interface IGameIO {

    /**
     * Sauvegarde une partie
     * @param state La partie à sauvegarder
     * @param path l'emplacement où enregistrer la partie
     */
    public void saveGameState(GameState state, String path);

    /**
     * Charge une partie
     * @param path L'emplacement où charger la partie
     * @return La partie chargée
     */
    public GameState loadGameState(String path);

}
