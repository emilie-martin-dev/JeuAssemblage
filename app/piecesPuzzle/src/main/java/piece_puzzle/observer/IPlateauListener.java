package piece_puzzle.observer;

import piece_puzzle.model.piece.AbstractPiece;

/**
 * Listener pour le plateau
 */
public interface IPlateauListener {

    /**
     * Une piece a bougé
     * @param piece La pièce qui a bougé
     */
    public void pieceMoved(AbstractPiece piece);

    /**
     * Une pièce a fait une rotation
     * @param piece La pièce qui a fait la rotation
     */
    public void pieceRotated(AbstractPiece piece);

    /**
     * Une pièce a été ajoutée
     * @param piece La piece ajoutée
     */
    public void pieceAdded(AbstractPiece piece);

    /**
     * Une pièce a été supprimée
     * @param piece La pièce supprimée
     */
    public void pieceRemoved(AbstractPiece piece);

}
