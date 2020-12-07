package assemblage.game.ai;

import assemblage.game.GameState;
import assemblage.game.score.IScoreCalculator;
import assemblage.game.score.ScoreCalculatorSimple;
import assemblage.io.IGameIO;
import assemblage.io.gson.GameIOGson;
import assemblage.io.gson.serializer.GameStateAdapter;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import piece_puzzle.actions.ActionPieceMove;
import piece_puzzle.actions.ActionPieceRotate;
import piece_puzzle.actions.IAction;
import piece_puzzle.model.Plateau;
import piece_puzzle.model.piece.AbstractPiece;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Ai {

    public GameState compute(GameState state) {
        /*
         * L'idée de l'ia etait de faire 100 parties différentes en prenant des actions totalement aléatoires.
         * Une fois que chaque partie est terminée on la comparait à la meilleure config que l'on a trouvé et on garde
         * la plus interessante
         *
         * Le code est commenté car l'IA ne finissait pas de partie.
         *
         * Une solution plus intelligente aurait été de commencer par déterminer un assemblage possible en comptant le
         * nombre d'action nécessaires (1 par déplacement et 1 par rotation)
         * de chaque pièce afin de former un rectangle le plus parfais possible une fois cette assemblage déterminé on
         * pourrait tenter de former ce rectangle.
         *
         * Une autre solution aurait pu être de faire un algorithme basé sur une sorte de min max où l'on chercherait
         * toujours a maximiser le score. Une sorte de max-max en soit. Le problème est qu'il aurait fallut réévaluer
         * la grille différement car le calcul de score actuel ne fait pas la différence entre les deux configs suivante
         * (elles valent le meme score):
         *
         * X X X X . . . . . . . . . . .
         * X . . . . . . . . . . . . . .
         * X . . X X X X X X X X . . . .
         * . . . X X X X X X . X . . . .
         * . . . X . . X X X . X . . . .
         * . . . X . . X . . . X . . . .
         * . . . X X X X X X X X . . . .
         * . . . . . . . . . . . . . . .
         * . . . . . . . . . . . . . X X
         * . . . . . . . . . . . . . X X
         *
         *
         *
         * X X X X . . . . . X X X . . .
         * X . . . . . . . . . . X . X .
         * X . . . . . . X . . . . . X .
         * . . . . . . . X . . . . . X .
         * . . . X . . . X . . . . . X .
         * . . . X . . . . . . . . . . .
         * . . . X X . X . X X X . . . .
         * X X . . . . X . . . . . . . .
         * X X . . . . X . . . . . . X X
         * X X . . . X X X . . . . . X X
         */

        /*String fileName = ".ia";

        IGameIO gameIO = new GameIOGson();
        gameIO.saveGameState(state, fileName);

        int maxScore = 0;
        GameState maxState = null;

        IScoreCalculator scoreCalculator = new ScoreCalculatorSimple();
        Random rand = new Random();

        for(int i = 0 ; i < 100 ; i++) {
            GameState currentState = gameIO.loadGameState(fileName);
            Plateau plateau = currentState.getPlateau();

            while(!state.isFinished()) {
                int choix = rand.nextInt(101);

                IAction action = null;
                if(choix < 75) {
                    action = new ActionPieceMove(plateau, plateau.getPieces().get(rand.nextInt(plateau.getPieces().size())), rand.nextInt(11)-5, rand.nextInt(11)-5);
                } else {
                    action = new ActionPieceRotate(plateau, plateau.getPieces().get(rand.nextInt(plateau.getPieces().size())));
                }

                if(action.isValid())
                    action.apply();
            }

            int currentScore = scoreCalculator.calculate(currentState.getPlateau());
            if(currentScore > maxScore) {
                maxScore = currentScore;
                maxState = currentState;
            }
        }

        try {
            Files.delete(Paths.get(fileName));
        } catch (IOException e) {
        }

        return maxState;*/

        return null;
    }

}
