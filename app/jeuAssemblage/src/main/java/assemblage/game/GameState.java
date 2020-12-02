package assemblage.game;

import assemblage.observer.IGameStateListener;
import piece_puzzle.model.Plateau;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private List<IGameStateListener> m_listeners;

    private GameRule m_rules;
    private Plateau m_plateau;

    private int m_nbCoupsRestants;

    public GameState(GameRule rules, Plateau plateau) {
        m_rules = rules;
        m_plateau = plateau;

        m_nbCoupsRestants = rules.getNbCoupsMax();

        m_listeners = new ArrayList<>();
    }

    public void decrementNbCoupsRestants() {
        m_nbCoupsRestants--;

        fireNbCoupsRestantsChanged();
    }

    public void addListener(IGameStateListener listener) {
        m_listeners.add(listener);
    }

    public void removeListener(IGameStateListener listener) {
        m_listeners.remove(listener);
    }

    public void fireNbCoupsRestantsChanged() {
        for(IGameStateListener l : m_listeners) {
            l.nbCoupsRestantsChanged(m_nbCoupsRestants);
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
}
