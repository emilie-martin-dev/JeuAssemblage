package assemblage.game;

import assemblage.io.gson.serializer.GameStateAdapter;
import assemblage.observer.IGameStateListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import piece_puzzle.model.piece.AbstractPiece;
import piece_puzzle.model.Plateau;

import java.util.ArrayList;
import java.util.List;

/**
 * L'état de la partie
 */
public class GameState {

    /**
     * Les listeners
     */
    private List<IGameStateListener> m_listeners;

    /**
     * Les règles du jeu
     */
    private GameRule m_rules;
    /**
     * Le plateau en cours
     */
    private Plateau m_plateau;
    /**
     * Le plateau de départ
     */
    private Plateau m_plateauDepart;

    /**
     * Le nom du meilleur joueur
     */
    private String m_bestPlayerName;
    /**
     * Le meilleur score
     */
    private int m_bestScore;

    /**
     * Le nombre de coups restants
     */
    private int m_nbCoupsRestants;

    public GameState(GameRule rules, Plateau plateau) {
        m_rules = rules;
        m_plateauDepart = plateau;
        m_plateau = copyPlateauDepart();

        m_bestScore = 0;

        m_nbCoupsRestants = rules.getNbCoupsMax();

        m_listeners = new ArrayList<>();
    }

    /**
     * Permet d'éffectuer une copie du plateau de départ
     * @return La copie du plateau de départ
     */
    public Plateau copyPlateauDepart() {
        // TODO : Pas efficace DUTOUT! J'utilise cette méthode afin de gagner du temps
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(AbstractPiece.class, new GameStateAdapter<>());
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String json = gson.toJson(m_plateauDepart);
        return gson.fromJson(json, Plateau.class);
    }

    /**
     * reset la partie
     */
    public void reset() {
        m_nbCoupsRestants = m_rules.getNbCoupsMax();

        m_plateau = copyPlateauDepart();

        fireGameReset();
    }

    /**
     * Décrémente le nombre de coups restants
     */
    public void decrementNbCoupsRestants() {
        m_nbCoupsRestants--;

        fireNbCoupsRestantsChanged();
    }

    /**
     * Retourne true si la partie est finie, false sinon
     * @return true si la partie est finie, false sinon
     */
    public boolean isFinished() {
        return m_nbCoupsRestants <= 0;
    }

    /**
     * Ajoute un listener
     * @param listener Le listener à ajouter
     */
    public void addListener(IGameStateListener listener) {
        if(m_listeners == null)
            m_listeners = new ArrayList<>();

        m_listeners.add(listener);
    }

    /**
     * Supprime un listener
     * @param listener Le listener à supprimer
     */
    public void removeListener(IGameStateListener listener) {
        m_listeners.remove(listener);
    }

    /**
     * Notifie que le nombre de coups restants à changé
     */
    public void fireNbCoupsRestantsChanged() {
        for(IGameStateListener l : m_listeners) {
            l.nbCoupsRestantsChanged(m_nbCoupsRestants);
        }
    }

    /**
     * Notifie que la partie a été reset
     */
    public void fireGameReset() {
        for(IGameStateListener l : m_listeners) {
            l.stateReset();
        }
    }

    public GameRule getRules() {
        return m_rules;
    }

    public void setRules(GameRule rules) {
        m_rules = rules;
    }

    public Plateau getPlateau() {
        return m_plateau;
    }

    public void setPlateau(Plateau plateau) {
        m_plateau = plateau;
    }

    public int getNbCoupsRestants() {
        return m_nbCoupsRestants;
    }

    public String getBestPlayerName() {
        return m_bestPlayerName;
    }

    public void setBestPlayerName(String bestPlayerName) {
        m_bestPlayerName = bestPlayerName;
    }

    public int getBestScore() {
        return m_bestScore;
    }

    public void setBestScore(int bestScore) {
        m_bestScore = bestScore;
    }
}
