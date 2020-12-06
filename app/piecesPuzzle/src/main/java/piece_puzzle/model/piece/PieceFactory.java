package piece_puzzle.model.piece;

import java.util.Random;

/**
 * Permet de générere les pièces
 */
public class PieceFactory {

    /**
     * Génère une pièce aléatoirement
     * @return La pièce générée
     */
    public static AbstractPiece generatePiece() {
        Random rand = new Random();
        // On prend une pièce, ses dimensions et sa rotation au hasard
        int choixPiece = rand.nextInt(3);
        int w = rand.nextInt(7-2);
        int h = rand.nextInt(7-2);
        int rotation = rand.nextInt(4);

        // On génère la pièce selon le nombre tiré
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

        // Applique les rotations
        for(int r = 0 ; r < rotation ; r++) {
            piece.rotate();
        }

        return piece;
    }

}
