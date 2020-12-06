package assemblage.game.generator;

import piece_puzzle.actions.ActionPiecePlace;
import piece_puzzle.model.Plateau;
import piece_puzzle.model.piece.AbstractPiece;
import piece_puzzle.model.piece.PieceL;
import piece_puzzle.model.piece.PieceRectangle;
import piece_puzzle.model.piece.PieceT;
import piece_puzzle.utils.Position;

import java.util.Random;

public class PlateauGenerator implements IPlateauGenerator {

    @Override
    public Plateau generate(int width, int height) {
        Random rand = new Random();

        Plateau plateau = new Plateau(width, height);

        for(int i = 0 ; i < (width * height) / 30 ; i++) {
            int choixPiece = rand.nextInt(3);

            int w = rand.nextInt(7-2);
            int h = rand.nextInt(7-2);
            int rotation = rand.nextInt(4);

            AbstractPiece piece = null;
            switch (choixPiece) {
                case 0:
                    piece = new PieceL(w+2, h+2);
                    break;

                case 1:
                    piece = new PieceRectangle(w+2, h+2);
                    break;

                case 2:
                    piece = new PieceT(w+2, h+2);
                    break;
            }

            for(int r = 0 ; r < rotation ; r++) {
                piece.rotate();
            }

            boolean isPlaced = false;
            int j = 0;
            while(!isPlaced && j < 10) {
                int x = rand.nextInt(width);
                int y = rand.nextInt(height);

                piece.setPosition(new Position(x, y));

                ActionPiecePlace actionPiecePlace = new ActionPiecePlace(plateau, piece);
                if(actionPiecePlace.isValid()) {
                    actionPiecePlace.apply();
                    isPlaced = true;
                }

                j++;
            }
        }

        return plateau;
    }
}
