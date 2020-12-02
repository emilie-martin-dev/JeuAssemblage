package assemblage.game.score;

import piece_puzzle.model.Plateau;

public interface IScoreCalculator {

    public int calculate(Plateau plateau);

    public int getScoreMax(Plateau plateau);

}
