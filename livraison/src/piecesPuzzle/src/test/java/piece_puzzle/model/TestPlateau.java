package piece_puzzle.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import piece_puzzle.model.piece.AbstractPiece;
import piece_puzzle.model.piece.PieceL;
import piece_puzzle.model.piece.PieceRectangle;

@RunWith(MockitoJUnitRunner.class)
public class TestPlateau {

    @Test
    public void isCaseFilledAt() {
        int w = 20;
        int h = 20;
        int wP = 5;
        int hP = 5;

        int xOffset = 5;
        int yOffset = 5;

        Plateau plateau = new Plateau(w, h);
        plateau.addPiece(new PieceL(wP, hP, xOffset, yOffset));

        // On verifie que toutes les cases sont à false et on skip les cases dans le périmètre de la pièce
        for(int y = 0 ; y < h ; y++) {
            for(int x = 0 ; x < w ; x++) {
                if(y >= yOffset && y <= yOffset + hP && x >= xOffset && x <= xOffset + wP) {
                    continue;
                }

                Assert.assertFalse(plateau.isCaseFilledAt(x, y));
            }
        }

        // A partir d'ici on vérifie la pièce

        // Vérifie la barre de gauche du L
        for(int y = 0 ; y < hP-1 ; y++) {
            for(int x = 0 ; x < wP ; x++) {
                if(x == 0)
                    Assert.assertTrue(plateau.isCaseFilledAt(x+xOffset, y+yOffset));
                else
                    Assert.assertFalse(plateau.isCaseFilledAt(x+xOffset, y+yOffset));
            }
        }

        // Vérifie la barre hozizontale en bas
        for(int x = 0 ; x < wP ; x++) {
            Assert.assertTrue(plateau.isCaseFilledAt(x+yOffset, yOffset+hP-1));
        }
    }

    @Test
    public void getPieceAt() {
        int w = 20;
        int h = 20;
        int wP = 5;
        int hP = 5;

        int xOffset = 5;
        int yOffset = 5;

        AbstractPiece piece = new PieceL(wP, hP, xOffset, yOffset);
        Plateau plateau = new Plateau(w, h);
        plateau.addPiece(piece);

        // On verifie que toutes les cases retournent null et on skip les cases dans le périmètre de la pièce
        for(int y = 0 ; y < h ; y++) {
            for(int x = 0 ; x < w ; x++) {
                if(y >= yOffset && y <= yOffset + hP && x >= xOffset && x <= xOffset + wP) {
                    continue;
                }

                Assert.assertNull(plateau.getPieceAt(x, y));
            }
        }

        // A partir d'ici on vérifie la pièce

        // Vérifie la barre de gauche du L
        for(int y = 0 ; y < hP-1 ; y++) {
            for(int x = 0 ; x < wP ; x++) {
                if(x == 0)
                    Assert.assertSame(plateau.getPieceAt(x + xOffset, y + yOffset), piece);
                else
                    Assert.assertNull(plateau.getPieceAt(x+xOffset, y+yOffset));
            }
        }

        // Vérifie la barre hozizontale en bas
        for(int x = 0 ; x < wP ; x++) {
            Assert.assertSame(plateau.getPieceAt(x+yOffset, yOffset+hP-1), piece);
        }
    }
}
