package piece_puzzle.model;

public class PieceT extends AbstractPiece {
	
	public PieceT(int w, int h) {
		super(w, h);
	}

	public PieceT(int w, int h, int x, int y) {
		super(w, h, x, y);
	}	

	@Override
	protected boolean[] generatePiece(int w, int h) {
		boolean[] blocs = new boolean[w*h];
		
		for(int x = 0 ; x < w ; x++) {
			blocs[x] = true;
		}
		
		for(int y = 1 ; y < h ; y++) {
			blocs[w / 2 + y * w] = true;
			
			if(w % 2 == 0) {
				blocs[w / 2 - 1 + y * w] = true;
			}
		}			
		
		return blocs;
	}
	
}