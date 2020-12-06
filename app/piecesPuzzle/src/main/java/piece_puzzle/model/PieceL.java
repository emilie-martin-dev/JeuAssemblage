package piece_puzzle.model;

/**
 * Pièce en L
 */
public class PieceL extends AbstractPiece {

	/**
	 * Pièce en L
	 * @param w Largeur
	 * @param h Hauteur
	 */
	public PieceL(int w, int h) {
		super(w, h);
	}

	/**
	 * Pièce en L
	 * @param w Largeur
	 * @param h Hauteur
	 * @param x Position x
	 * @param y Position y
	 */
	public PieceL(int w, int h, int x, int y) {
		super(w, h, x, y);
	}	

	@Override
	protected boolean[] generatePiece(int w, int h) {
		boolean[] blocs = new boolean[w*h];
		
		for(int x = 0 ; x < w ; x++) {
			blocs[x + w * (h-1)] = true;
		}
		
		for(int y = 0 ; y < h ; y++) {
			blocs[w * y] = true;
		}
		
		return blocs;
	}
	
}