package piece_puzzle.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestAbstractPiece {
	
	/*
	 * Génère une pièce de test sous la forme suivante :
	 * 
	 * X.X.X
	 * .X.X.
	 * X.X.X
	 */
	public AbstractPiece _generateAbstractPiece(int w, int h) {
		return new AbstractPiece(w, h) {
			@Override
			protected boolean[] generatePiece(int w, int h) {
				boolean[] blocs = new boolean[w*h];
		
				for(int i = 0 ; i < w * h ; i++) {
					blocs[i] =  i % 2 == 0;
				}

				return blocs;
			}
		};
	}
	
	@Test	
	public void getWidth() {
		int w = 5;
		int h = 3;
		
		AbstractPiece piece = _generateAbstractPiece(w, h);
		
		Assert.assertEquals(w, piece.getWidth());
		piece.rotate();
		Assert.assertEquals(h, piece.getWidth());
		piece.rotate();
		Assert.assertEquals(w, piece.getWidth());
		piece.rotate();
		Assert.assertEquals(h, piece.getWidth());
		piece.rotate();
		Assert.assertEquals(w, piece.getWidth());
	}
	
	@Test	
	public void getHeight() {
		int w = 5;
		int h = 3;
		
		AbstractPiece piece = _generateAbstractPiece(w, h);
		
		Assert.assertEquals(h, piece.getHeight());
		piece.rotate();
		Assert.assertEquals(w, piece.getHeight());
		piece.rotate();
		Assert.assertEquals(h, piece.getHeight());
		piece.rotate();
		Assert.assertEquals(w, piece.getHeight());
		piece.rotate();
		Assert.assertEquals(h, piece.getHeight());
	}
	
	@Test	
	public void isCaseFilledAt() {
		int w = 5;
		int h = 5;
		
		AbstractPiece piece = _generateAbstractPiece(w, h);
		
		for(int i = 0 ; i < w * h ; i++) {
			if(i % 2 == 0)
				Assert.assertTrue(piece.isCaseFilledAt(i % w, i / h));
			else				
				Assert.assertFalse(piece.isCaseFilledAt(i % w, i / h));
		}
	}
	
	@Test	
	public void isCaseFilledAt_rotation() {
		int w = 2;
		int h = 3;
		
		/*
		 * X.
		 * X.
		 * X.
		 */
		AbstractPiece piece = _generateAbstractPiece(w, h);
		
		// Test initial
		_test_r0(piece, w, h);
		
		// Tests rotate
		piece.rotate();
		_test_r1(piece, w, h);
		
		piece.rotate();
		_test_r2(piece, w, h);
		
		piece.rotate();
		_test_r3(piece, w, h);
		
		piece.rotate();
		_test_r0(piece, w, h);
		
		// Tests unrotate
		piece.unrotate();
		_test_r3(piece, w, h);
		
		piece.unrotate();
		_test_r2(piece, w, h);
		
		piece.unrotate();
		_test_r1(piece, w, h);
		
		piece.unrotate();
		_test_r0(piece, w, h);
	}
	
	public void _test_r0(AbstractPiece piece, int w,  int h) {
		for(int y = 0 ; y < h ; y++)  {
			for(int x = 0 ; x < w ; x++) {
				if(x == 0)
					Assert.assertTrue(piece.isCaseFilledAt(x, y));
				else 
					Assert.assertFalse(piece.isCaseFilledAt(x, y));
			}
		}
	}
	
	public void _test_r1(AbstractPiece piece, int w,  int h) {
		for(int y = 0 ; y < h ; y++)  {
			for(int x = 0 ; x < w ; x++) {
				if(y == 0)
					Assert.assertTrue(piece.isCaseFilledAt(x, y));
				else 
					Assert.assertFalse(piece.isCaseFilledAt(x, y));
			}
		}
	}
	
	public void _test_r2(AbstractPiece piece, int w,  int h) {
		for(int y = 0 ; y < h ; y++)  {
			for(int x = 0 ; x < w ; x++) {
				if(x == 1)
					Assert.assertTrue(piece.isCaseFilledAt(x, y));
				else 
					Assert.assertFalse(piece.isCaseFilledAt(x, y));
			}
		}
	}
	
	public void _test_r3(AbstractPiece piece, int w,  int h) {
		for(int y = 0 ; y < h ; y++)  {
			for(int x = 0 ; x < w ; x++) {
				if(y == 1)
					Assert.assertTrue(piece.isCaseFilledAt(x, y));
				else 
					Assert.assertFalse(piece.isCaseFilledAt(x, y));
			}
		}
	}
	
	
}
