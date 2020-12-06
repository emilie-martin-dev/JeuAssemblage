package assemblage.observer;

/**
 * Listener pour les changements sur l'état du jeu
 */
public interface IGameStateListener {

    /**
     * Le jeu est remis à 0
     */
    void stateReset();

    /**
     * Le compteur d'actions restantes a changé
     * @param nbCoupsRestants Le nouveau nombre d'actions restantes
     */
    void nbCoupsRestantsChanged(int nbCoupsRestants);
}
