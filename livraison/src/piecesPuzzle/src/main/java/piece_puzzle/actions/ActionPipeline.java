package piece_puzzle.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Suite d'action à effectuer
 */
public class ActionPipeline implements IAction {

	/**
	 * La liste d'action
	 */
	private List<IAction> m_actions;

	public ActionPipeline(IAction ...actions) {
		m_actions = new ArrayList<>();
		m_actions.addAll(Arrays.asList(actions));
	}
	
	@Override
	public boolean isValid() {
		// On vérifie les actions suite a suite
		for(IAction a : m_actions) {
			if(!a.isValid())
				return false;
		}
		
		return true;
	}

	@Override
	public void apply() {
		// On applique les actions suite a suite
		for(IAction a : m_actions) {
			a.apply();
		}
	}

	/**
	 * Ajoute une action
	 * @param action L'action a ajouter
	 */
	public void addAction(IAction action) {
		m_actions.add(action);
	}
	
}
