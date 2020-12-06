package piece_puzzle.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import piece_puzzle.model.piece.PieceRectangle;

@RunWith(MockitoJUnitRunner.class)
public class TestPieceRectangle {
	
	@Test
	public void generate() {
		int h = 5;
		int w = 3;
		PieceRectangle pieceRectangle = new PieceRectangle(w, h);
		
		for(int y = 0 ; y < h ; y++) {
			for(int x = 0 ; x < w ; x++) {
				Assert.assertTrue(pieceRectangle.isCaseFilledAt(x, y));
			}	
		}
		
	}
	
}
