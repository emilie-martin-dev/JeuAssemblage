package jeu;

import piece_puzzle.model.Plateau;
import piece_puzzle.model.AbstractPiece;
import piece_puzzle.model.PieceT;


public class Main {
	
	public static void main(String[] args) {
		Plateau map = new Plateau(30, 30);
		map.addPiece(new PieceT(3, 4, 0, 0));
		map.addPiece(new PieceT(3, 4, 0, 2));
		map.addPiece(new PieceT(3, 4, 0, 10));
		map.addPiece(new PieceT(3, 4, 0, 15));
		
		System.out.println(map);
		
		AbstractPiece piece = new PieceT(7, 5);
				
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
