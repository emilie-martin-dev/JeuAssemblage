package assemblage.io.gson;

import assemblage.game.GameState;
import assemblage.io.IGameIO;
import assemblage.io.gson.serializer.CustomAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import piece_puzzle.model.AbstractPiece;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameIOGson implements IGameIO {

    private Gson m_gson;

    public GameIOGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(AbstractPiece.class, new CustomAdapter<>());
        builder.setPrettyPrinting();

        m_gson = builder.create();
    }

    @Override
    public void saveGameState(GameState state, String path) {
        try {
            FileWriter output = new FileWriter(path);
            m_gson.toJson(state, output);

            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GameState loadGameState(String path) {
        try {
            FileReader reader = new FileReader(path);
            return m_gson.fromJson(reader, GameState.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
