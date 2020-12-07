package piece_puzzle.actions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import piece_puzzle.model.Plateau;
import piece_puzzle.model.piece.PieceRectangle;
import piece_puzzle.utils.Position;

@RunWith(MockitoJUnitRunner.class)
public class TestActionPiecePlace {

	@Spy
	private PieceRectangle m_piece = new PieceRectangle(5, 5, 5, 5);

	@Test
	public void isValid() {
		int w = 20;
		int h = 20;

		Plateau plateau = new Plateau(w, h);

		// On test si le placement est ok
		ActionPiecePlace actionPiecePlace = new ActionPiecePlace(plateau, m_piece);
		Assert.assertTrue(actionPiecePlace.isValid());
		Assert.assertEquals( 0, plateau.getPieces().size());

		// On test si le placement est false cas elle est sur un bloc
		PieceRectangle pieceRectangle = new PieceRectangle(w, 4);
		plateau.addPiece(pieceRectangle);
		Assert.assertEquals( 1, plateau.getPieces().size());

		m_piece.setPosition(new Position(0, 0));
		actionPiecePlace = new ActionPiecePlace(plateau, m_piece);
		Assert.assertFalse(actionPiecePlace.isValid());

		// On test si le placement est false car elle sort de la map
		plateau = new Plateau(5, 5);
		m_piece.setPosition(new Position(0, -4));
		actionPiecePlace = new ActionPiecePlace(plateau, m_piece);
		Assert.assertFalse(actionPiecePlace.isValid());
	}

	@Test
	public void apply() {
		Plateau plateau = new Plateau(20, 20);

		ActionPiecePlace actionPiecePlace = new ActionPiecePlace(plateau, m_piece);
		actionPiecePlace.apply();

		Assert.assertEquals( 1, plateau.getPieces().size());
	}
	
}
