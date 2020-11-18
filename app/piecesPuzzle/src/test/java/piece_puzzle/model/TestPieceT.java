package piece_puzzle.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestPieceT {
	
	@Test
	public void generate_oddW() {
		int h = 5;
		int w = 3;
		int middle = 1;
		
		PieceT pieceT = new PieceT(w, h);
		
		// Vérifie la barre hozizontale en haut 
		for(int x = 0 ; x < w ; x++) {
			Assert.assertTrue(pieceT.isCaseFilledAt(x, 0));
		}
		
		// Vérifie la barre du milieu du T (pas besoin de vérifier la premiere ligne)
		for(int y = 1 ; y < h ; y++) {
			for(int x = 0 ; x < w ; x++) {
				if(x == middle)
					Assert.assertTrue(pieceT.isCaseFilledAt(x, y));
				else
					Assert.assertFalse(pieceT.isCaseFilledAt(x, y));
			}	
		}
	}
	
	@Test
	public void generate_pairW() {
		int h = 5;
		int w = 4;
		int middle1 = 1;
		int middle2 = 2;
		
		PieceT pieceT = new PieceT(w, h);
		
		// Vérifie la barre hozizontale en haut 
		for(int x = 0 ; x < w ; x++) {
			Assert.assertTrue(pieceT.isCaseFilledAt(x, 0));
		}
		
		// Vérifie la barre du milieu du T (pas besoin de vérifier la premiere ligne)
		for(int y = 1 ; y < h ; y++) {
			for(int x = 0 ; x < w ; x++) {
				if(x == middle1 || x == middle2)
					Assert.assertTrue(pieceT.isCaseFilledAt(x, y));
				else
					Assert.assertFalse(pieceT.isCaseFilledAt(x, y));
			}	
		}		
	}
	
}
