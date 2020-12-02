package piece_puzzle.observer;

import piece_puzzle.model.AbstractPiece;

public interface IPlateauListener {

    public void pieceMoved(AbstractPiece piece);

    public void pieceRotated(AbstractPiece piece);

    public void pieceAdded(AbstractPiece piece);

    public void pieceRemoved(AbstractPiece piece);

}
