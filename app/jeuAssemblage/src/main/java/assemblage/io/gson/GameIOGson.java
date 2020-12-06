package assemblage.io.gson;

import assemblage.game.GameState;
import assemblage.io.IGameIO;
import assemblage.io.gson.serializer.GameStateAdapter;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import piece_puzzle.model.piece.AbstractPiece;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Import et export d'une partie par GSON
 */
public class GameIOGson implements IGameIO {

    /**
     * L'instance de GSON
     */
    private Gson m_gson;

    public GameIOGson() {
        ExclusionStrategy exclusionStrategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().equals("m_listeners");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(AbstractPiece.class, new GameStateAdapter<>());
        builder.setPrettyPrinting();
        builder.addSerializationExclusionStrategy(exclusionStrategy);

        builder.addDeserializationExclusionStrategy(exclusionStrategy);

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
