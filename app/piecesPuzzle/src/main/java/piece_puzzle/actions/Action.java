package piece_puzzle.actions;

public interface Action {
	
	public boolean isValid();
	
	public void apply();
	
}
