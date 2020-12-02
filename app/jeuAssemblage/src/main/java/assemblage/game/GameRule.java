package assemblage.game;

public class GameRule {

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
