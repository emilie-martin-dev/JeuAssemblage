package assemblage.game;

import piece_puzzle.model.Plateau;

public class GameState {

    private GameRule m_rules;
    private Plateau m_plateau;

    public GameState() {

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
}
