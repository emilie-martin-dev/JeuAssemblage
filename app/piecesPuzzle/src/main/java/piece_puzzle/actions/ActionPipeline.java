package piece_puzzle.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionPipeline implements IAction {

	private List<IAction> m_actions;

	public ActionPipeline(IAction ...actions) {
		m_actions = new ArrayList<>();
		m_actions.addAll(Arrays.asList(actions));
	}
	
	@Override
	public boolean isValid() {
		for(IAction a : m_actions) {
			if(!a.isValid())
				return false;
		}
		
		return true;
	
	}

	@Override
	public void apply() {
		for(IAction a : m_actions) {
			a.apply();
		}
	}
	
	public void addAction(IAction action) {
		m_actions.add(action);
	}
	
}
