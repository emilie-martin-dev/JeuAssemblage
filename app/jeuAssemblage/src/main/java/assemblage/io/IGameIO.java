package assemblage.io;

import assemblage.game.GameState;

public interface IGameIO {

    public void saveGameState(GameState state, String path);

    public GameState loadGameState(String path);

}
