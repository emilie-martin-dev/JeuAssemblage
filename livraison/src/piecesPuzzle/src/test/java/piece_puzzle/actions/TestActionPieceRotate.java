package piece_puzzle.actions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import piece_puzzle.model.Plateau;
import piece_puzzle.model.piece.PieceL;
import piece_puzzle.model.piece.PieceRectangle;
import piece_puzzle.utils.Position;

@RunWith(MockitoJUnitRunner.class)
public class TestActionPieceRotate {

	@Spy
	private PieceL m_piece = new PieceL(3, 5, 5, 5);

	@Test
	public void isValid() {
		int w = 20;
		int h = 20;

		Plateau plateau = new Plateau(w, h);
		plateau.addPiece(m_piece);

		// On test si la rotation est ok
		ActionPieceRotate actionPieceRotate = new ActionPieceRotate(plateau, m_piece);
		Assert.assertTrue(actionPieceRotate.isValid());
		Assert.assertEquals(0, m_piece.getRotationCount());

		// On test si la rotation est false cas elle est sur un bloc
		PieceRectangle pieceRectangle = new PieceRectangle(5, h);
		pieceRectangle.setPosition(new Position(5+3, 0));
		plateau.addPiece(pieceRectangle);

		Assert.assertEquals( 2, plateau.getPieces().size());
		Assert.assertFalse(actionPieceRotate.isValid());

		// On test si la rotation est false car elle sort de la map
		plateau = new Plateau(3, 5);
		m_piece.setPosition(new Position(0, 0));
		plateau.addPiece(m_piece);

		Assert.assertEquals( 1, plateau.getPieces().size());
		actionPieceRotate = new ActionPieceRotate(plateau, m_piece);
		Assert.assertFalse(actionPieceRotate.isValid());
	}

	@Test
	public void apply() {
		Plateau plateau = new Plateau(20, 20);
		plateau.addPiece(m_piece);

		Assert.assertEquals( 1, plateau.getPieces().size());

		ActionPieceRotate actionPieceRotate = new ActionPieceRotate(plateau, m_piece);
		Assert.assertTrue(actionPieceRotate.isValid());
		actionPieceRotate.apply();

		// 2 car isValid fait une rotation
		Mockito.verify(m_piece, Mockito.times(2)).rotate();
		Assert.assertEquals( 1, plateau.getPieces().size());
	}
}
