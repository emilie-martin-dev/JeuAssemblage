package assemblage.game.score;

import piece_puzzle.model.Plateau;

/**
 * Interface pour le calcul du score du plateau
 */
public interface IScoreCalculator {

    /**
     * Calcule le score du plateau
     * @param plateau Le plateau
     * @return Le score du plateau
     */
    public int calculate(Plateau plateau);

    /**
     * Le score max qu'il est possible d'atteindre
     * @param plateau Le plateau
     * @return Le score max
     */
    public int getScoreMax(Plateau plateau);

}
