package piece_puzzle.model.piece;

import java.util.Random;

/**
 * Permet de générere les pièces
 */
public class PieceFactory {

    private static final int TAILLE_MIN = 2;
    private static final int TAILLE_MAX = 7;

    /**
     * Génère une pièce aléatoirement
     * @return La pièce générée
     */
    public static AbstractPiece generatePiece() {
        Random rand = new Random();
        // On prend une pièce, ses dimensions et sa rotation au hasard
        int choixPiece = rand.nextInt(3);
        int w = rand.nextInt(TAILLE_MAX-TAILLE_MIN);
        int h = rand.nextInt(TAILLE_MAX-TAILLE_MIN);
        int rotation = rand.nextInt(4);

        // On génère la pièce selon le nombre tiré
        AbstractPiece piece = null;
        switch (choixPiece) {
            case 0:
                piece = new PieceL(w+TAILLE_MIN, h+TAILLE_MIN);
                break;

            case 1:
                piece = new PieceRectangle(w+TAILLE_MIN, h+TAILLE_MIN);
                break;

            case 2:
                piece = new PieceT(w+TAILLE_MIN, h+TAILLE_MIN);
                break;
        }

        // Applique les rotations
        for(int r = 0 ; r < rotation ; r++) {
            piece.rotate();
        }

        return piece;
    }

}
