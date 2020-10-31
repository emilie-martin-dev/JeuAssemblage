package piece_puzzle.actions;

import piece_puzzle.actions.validator.IValidator;

public interface IAction extends IValidator {
	
	public void apply();
	
}
