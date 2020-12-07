package assemblage.game;

/**
 * Les règles qui définissent la partie
 */
public class GameRule {

    /**
     * Le nombre de coups maximums dans la partie
     */
    private int m_nbCoupsMax;

    public GameRule(int nbCoupsMax) {
        m_nbCoupsMax = nbCoupsMax;
    }

    public int getNbCoupsMax() {
        return m_nbCoupsMax;
    }

    public void setNbCoupsMax(int nbCoupsMax) {
        m_nbCoupsMax = nbCoupsMax;
    }

}
