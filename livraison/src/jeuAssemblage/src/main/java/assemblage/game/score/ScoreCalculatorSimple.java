package assemblage.game.score;

import piece_puzzle.model.piece.AbstractPiece;
import piece_puzzle.model.Plateau;

/**
 * Méthode de calcule simple qui retire l'aire prise par toutes les pièces à l'aire maximum de la grille - l'aire minimum que prennent les pièces. Le défaut de cette classe est qu'il n'est pas forcément possible d'obtenir le score maximum car les pièces peuvent ne pas pouvoir former un rectangle complet
 */
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
        // Calcule l'aire minimum que peuvent prendre les pièces du jeu
        int sommePiece = 0;

        for(AbstractPiece piece : plateau.getPieces()) {
            for(boolean v : piece.getBlocsPosition()) {
                if(v) {
                    sommePiece++;
                }
            }
        }

        return plateau.getWidth() * plateau.getHeight() - sommePiece;
    }

}
