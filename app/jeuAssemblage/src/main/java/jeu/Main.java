package jeu;

import piece_puzzle.PlateauPuzzle;
import piece_puzzle.pieces.PiecePuzzle;
import piece_puzzle.pieces.PieceT;


public class Main {
	
	public static void main(String[] args) {
		PlateauPuzzle map = new PlateauPuzzle(30, 30);
		map.addPiece(new PieceT(3, 4, 0, 0));
		map.addPiece(new PieceT(3, 4, 0, 2));
		map.addPiece(new PieceT(3, 4, 0, 10));
		map.addPiece(new PieceT(3, 4, 0, 15));
		
		System.out.println(map);
		
		PiecePuzzle piece = new PieceT(7, 5);
				
		System.out.println(piece);
		System.out.println("");
		piece.rotate();
		System.out.println(piece);
		System.out.println("");
		piece.rotate();
		System.out.println(piece);
		System.out.println("");
		piece.rotate();
		System.out.println(piece);
		System.out.println("");
		piece.rotate();
		System.out.println(piece);
		System.out.println("");
		piece.rotate();
	}
}
