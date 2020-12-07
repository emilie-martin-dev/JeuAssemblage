package piece_puzzle.actions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestActionPipeline {

	@Mock
	private IAction m_action1;

	@Mock
	private IAction m_action2;

	@Test
	public void isValid() {
		ActionPipeline pipeline = new ActionPipeline();
		pipeline.addAction(m_action1);
		pipeline.addAction(m_action2);

		// Mock le résultat de isValid
		Mockito.when(m_action1.isValid()).thenReturn(true);
		Mockito.when(m_action2.isValid()).thenReturn(true);

		Assert.assertTrue(pipeline.isValid());

		// On vérifie que isValid a été call sur les deux IActions
		Mockito.verify(m_action1, Mockito.times(1)).isValid();
		Mockito.verify(m_action2, Mockito.times(1)).isValid();

		// On change le premier Mock
		Mockito.when(m_action1.isValid()).thenReturn(false);

		Assert.assertFalse(pipeline.isValid());

		// On vérifie que le isValid du premier mock a été call une fois de plus et pas l'autre
		Mockito.verify(m_action1, Mockito.times(2)).isValid();
		Mockito.verify(m_action2, Mockito.times(1)).isValid();
	}

	@Test
	public void apply() {
		ActionPipeline pipeline = new ActionPipeline();
		pipeline.addAction(m_action1);
		pipeline.addAction(m_action2);

		// On vérifie que l'action est considérée comme valide
		Mockito.when(m_action1.isValid()).thenReturn(true);
		Mockito.when(m_action2.isValid()).thenReturn(true);

		Assert.assertTrue(pipeline.isValid());

		// On regarde si tous les apply sont call
		pipeline.apply();

		Mockito.verify(m_action1, Mockito.times(1)).apply();
		Mockito.verify(m_action2, Mockito.times(1)).apply();
	}
	
}
