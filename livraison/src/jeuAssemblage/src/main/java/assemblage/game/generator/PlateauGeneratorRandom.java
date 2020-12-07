package assemblage.game.generator;

import piece_puzzle.actions.ActionPiecePlace;
import piece_puzzle.model.Plateau;
import piece_puzzle.model.piece.*;
import piece_puzzle.utils.Position;

import java.util.Random;

public class PlateauGeneratorRandom implements IPlateauGenerator {

    /**
     * Le nombre d'essais max pour placer une pièce
     */
    private static final int NB_ESSAIS_MAX = 10;
    /**
     * Le facteur par lequel on divise la taille de la grille. Plus il est haut, moins il y a de pièce
     */
    private static final int FACTOR_PIECE = 30;

    @Override
    public Plateau generate(int width, int height) {
        Plateau plateau = new Plateau(width, height);

        Random rand = new Random();

        // On génère (width * height) / FACTOR_PIECE pieces
        for(int i = 0 ; i < (width * height) / FACTOR_PIECE ; i++) {
            AbstractPiece piece = PieceFactory.generatePiece();

            boolean isPlaced = false;
            int essais = 0;
            // Tant que la pièce n'est pas placée et qu'on a fait moins de NB_ESSAIS_MAX essais, on tire une position au hasard et on tente de la placer
            while(!isPlaced && essais < NB_ESSAIS_MAX) {
                int x = rand.nextInt(width);
                int y = rand.nextInt(height);

                piece.setPosition(new Position(x, y));

                ActionPiecePlace actionPiecePlace = new ActionPiecePlace(plateau, piece);
                if(actionPiecePlace.isValid()) {
                    actionPiecePlace.apply();
                    isPlaced = true;
                }

                essais++;
            }
        }

        return plateau;
    }
}
