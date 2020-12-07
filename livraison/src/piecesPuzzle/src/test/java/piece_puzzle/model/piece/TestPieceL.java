package piece_puzzle.model.piece;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import piece_puzzle.model.piece.PieceL;

@RunWith(MockitoJUnitRunner.class)
public class TestPieceL {
	
	@Test
	public void generate() {
		int h = 5;
		int w = 3;
		PieceL pieceL = new PieceL(w, h);
		
		// Vérifie la barre de gauche du L
		for(int y = 0 ; y < h-1 ; y++) {
			for(int x = 0 ; x < w ; x++) {
				if(x == 0)
					Assert.assertTrue(pieceL.isCaseFilledAt(x, y));
				else
					Assert.assertFalse(pieceL.isCaseFilledAt(x, y));
			}	
		}
		
		// Vérifie la barre hozizontale en bas 
		for(int x = 0 ; x < w ; x++) {
			Assert.assertTrue(pieceL.isCaseFilledAt(x, h-1));
		}
		
	}
	
}
