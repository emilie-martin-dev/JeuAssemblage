package piece_puzzle.actions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import piece_puzzle.model.Plateau;
import piece_puzzle.model.piece.AbstractPiece;
import piece_puzzle.model.piece.PieceRectangle;

@RunWith(MockitoJUnitRunner.class)
public class TestActionPieceRemove{
	
	@Test
	public void isValid() {
		Plateau plateau = new Plateau(20, 20);
		PieceRectangle pieceRectangle = new PieceRectangle(5, 5);
		plateau.addPiece(pieceRectangle);

		Assert.assertEquals(1, plateau.getPieces().size());

		ActionPieceRemove actionPieceRemove = new ActionPieceRemove(plateau, pieceRectangle);
		Assert.assertTrue(actionPieceRemove.isValid());
	}

	@Test
	public void apply() {
		Plateau plateau = new Plateau(20, 20);
		PieceRectangle pieceRectangle = new PieceRectangle(5, 5);
		plateau.addPiece(pieceRectangle);

		Assert.assertEquals(1, plateau.getPieces().size());

		ActionPieceRemove actionPieceRemove = new ActionPieceRemove(plateau, pieceRectangle);
		Assert.assertTrue( actionPieceRemove.isValid());
		actionPieceRemove.apply();

		Assert.assertEquals(0, plateau.getPieces().size());
	}
	
}
