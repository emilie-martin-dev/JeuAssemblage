package piece_puzzle.model;

public class PieceRectangle extends AbstractPiece {
	
	public PieceRectangle(int w, int h) {
		super(w, h);
	}	
	
	public PieceRectangle(int w, int h, int x, int y) {
		super(w, h, x, y);
	}	

	@Override
	protected boolean[] generatePiece(int w, int h) {
		boolean[] blocs = new boolean[w*h];
		
		for(int x = 0 ; x < w ; x++) {
			for(int y = 0 ; y < h ; y++) {
				blocs[x + y * w] = true;
			}
		}			
		
		return blocs;
	}
	
}