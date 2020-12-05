package assemblage.observer;

public interface IGameStateListener {

    void stateReset();

    void nbCoupsRestantsChanged(int nbCoupsRestants);
}
