package assemblage.game;

import assemblage.io.gson.serializer.CustomAdapter;
import assemblage.observer.IGameStateListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import piece_puzzle.model.piece.AbstractPiece;
import piece_puzzle.model.Plateau;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private List<IGameStateListener> m_listeners;

    private GameRule m_rules;
    private Plateau m_plateau;
    private Plateau m_plateauDepart;

    private String m_bestPlayerName;
    private int m_bestScore;

    private int m_nbCoupsRestants;

    public GameState(GameRule rules, Plateau plateau) {
        m_rules = rules;
        m_plateauDepart = plateau;
        m_plateau = copyPlateauDepart();

        m_bestScore = 0;

        m_nbCoupsRestants = rules.getNbCoupsMax();

        m_listeners = new ArrayList<>();
    }


    public Plateau copyPlateauDepart() {
        // Pas efficace DUTOUT! J'utilise cette méthode afin de gagner de temps
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(AbstractPiece.class, new CustomAdapter<>());
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String json = gson.toJson(m_plateauDepart);
        return gson.fromJson(json, Plateau.class);
    }

    public void reset() {
        m_nbCoupsRestants = m_rules.getNbCoupsMax();

        m_plateau = copyPlateauDepart();

        fireGameReset();
    }

    public void decrementNbCoupsRestants() {
        m_nbCoupsRestants--;

        fireNbCoupsRestantsChanged();
    }

    public boolean isFinished() {
        return m_nbCoupsRestants <= 0;
    }

    public void addListener(IGameStateListener listener) {
        if(m_listeners == null)
            m_listeners = new ArrayList<>();

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
