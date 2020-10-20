package piece_puzzle.pieces;

public class PieceL extends PiecePuzzle {
	
	public PieceL(int w, int h) {
		super(w, h);
	}	
	
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