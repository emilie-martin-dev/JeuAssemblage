package piece_puzzle.model.piece;

/**
 * Pièce du tétris en forme de T
 */
public class PieceT extends AbstractPiece {

	/**
	 * Pièce en T
	 * @param w Largeur
	 * @param h Hauteur
	 */
	public PieceT(int w, int h) {
		super(w, h);
	}

	/**
	 * Piece en T
	 * @param w Largueur
	 * @param h Hauteur
	 * @param x Position x
	 * @param y Position y
	 */
	public PieceT(int w, int h, int x, int y) {
		super(w, h, x, y);
	}	

	@Override
	protected boolean[] generatePiece(int w, int h) {
		boolean[] blocs = new boolean[w*h];

		// Barre horizontale
		for(int x = 0 ; x < w ; x++) {
			blocs[x] = true;
		}

		// Barre verticale
		for(int y = 1 ; y < h ; y++) {
			blocs[w / 2 + y * w] = true;
			
			if(w % 2 == 0) {
				blocs[w / 2 - 1 + y * w] = true;
			}
		}			
		
		return blocs;
	}
	
}