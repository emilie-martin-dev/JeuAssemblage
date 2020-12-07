package piece_puzzle.actions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import piece_puzzle.model.Plateau;
import piece_puzzle.model.piece.AbstractPiece;
import piece_puzzle.model.piece.PieceL;
import piece_puzzle.model.piece.PieceRectangle;
import piece_puzzle.utils.Position;

@RunWith(MockitoJUnitRunner.class)
public class TestActionPieceMove {

	@Spy
	private PieceRectangle m_piece = new PieceRectangle(5, 5, 5, 5);

	@Test
	public void isValid() {
		int w = 20;
		int h = 20;

		Plateau plateau = new Plateau(w, h);
		plateau.addPiece(m_piece);

		// On test si le déplacement est ok et si le isValid ne change pas la position
		ActionPieceMove actionPieceMove = new ActionPieceMove(plateau, m_piece, -2, -2);
		Assert.assertTrue(actionPieceMove.isValid());
		Assert.assertEquals(5, m_piece.getPosition().getX());
		Assert.assertEquals(5, m_piece.getPosition().getY());

		actionPieceMove = new ActionPieceMove(plateau, m_piece, 2, 2);
		Assert.assertTrue(actionPieceMove.isValid());

		// On test si le déplacement est false cas elle est sur un bloc
		PieceRectangle pieceRectangle = new PieceRectangle(w, 4);
		plateau.addPiece(pieceRectangle);
		Assert.assertEquals( 2, plateau.getPieces().size());

		actionPieceMove = new ActionPieceMove(plateau, m_piece, 0, -2);
		Assert.assertFalse(actionPieceMove.isValid());

		// On test si le déplacement est false car elle sort de la map
		plateau = new Plateau(5, 5);
		m_piece.setPosition(new Position(0, 0));
		plateau.addPiece(m_piece);

		Assert.assertEquals( 1, plateau.getPieces().size());

		actionPieceMove = new ActionPieceMove(plateau, m_piece, 0, -9000);
		Assert.assertFalse(actionPieceMove.isValid());
	}

	@Test
	public void apply() {
		Plateau plateau = new Plateau(20, 20);
		plateau.addPiece(m_piece);

		Assert.assertEquals( 1, plateau.getPieces().size());

		ActionPieceMove actionPieceMove = new ActionPieceMove(plateau, m_piece, 1, 1);
		Assert.assertTrue( actionPieceMove.isValid());
		actionPieceMove.apply();

		Assert.assertEquals(6, m_piece.getPosition().getX());
		Assert.assertEquals(6, m_piece.getPosition().getY());
		Assert.assertEquals( 1, plateau.getPieces().size());
	}
}
