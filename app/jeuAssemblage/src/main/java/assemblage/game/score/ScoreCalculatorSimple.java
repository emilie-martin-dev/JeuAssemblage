package assemblage.game.score;

import piece_puzzle.model.piece.AbstractPiece;
import piece_puzzle.model.Plateau;

public class ScoreCalculatorSimple implements IScoreCalculator {

    @Override
    public int calculate(Plateau plateau) {
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;

        for(AbstractPiece p : plateau.getPieces()) {

            if(p.getPosition().getX() < xMin) {
                xMin = p.getPosition().getX();
            }

            if(p.getPosition().getY() < yMin) {
                yMin = p.getPosition().getY();
            }

            if(p.getPosition().getX() + p.getWidth() > xMax) {
                xMax = p.getPosition().getX() + p.getWidth();
            }

            if(p.getPosition().getY() + p.getHeight() > yMax) {
                yMax = p.getPosition().getY() + p.getHeight();
            }
        }

        return plateau.getWidth() * plateau.getHeight() - (xMax-xMin) * (yMax-yMin);
    }

    @Override
    public int getScoreMax(Plateau plateau) {
        return plateau.getWidth() * plateau.getHeight();
    }

}
