package assemblage.window;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import assemblage.game.GameRule;
import assemblage.game.GameState;
import assemblage.game.score.IScoreCalculator;
import assemblage.game.score.ScoreCalculator;
import assemblage.io.IGameIO;
import assemblage.io.gson.GameIOGson;
import assemblage.observer.IGameStateListener;
import piece_puzzle.model.PieceL;
import piece_puzzle.model.PieceT;
import piece_puzzle.model.Plateau;

public class MainWindow extends JFrame implements IGameStateListener {

	private IGameIO m_gameIO;

	private GameCanvas m_canvas;
	private JLabel m_stateLabel;

	private GameState m_gameState;
	
	public MainWindow() {
		m_stateLabel = new JLabel();
		m_stateLabel.setBorder(new EmptyBorder(16, 16, 16, 16));
		m_canvas = new GameCanvas(null);

		newGame();
		
		this.setTitle("Assemblage");
		this.setLayout(new BorderLayout());
		this.getContentPane().add(m_canvas, BorderLayout.CENTER);
		this.getContentPane().add(m_stateLabel, BorderLayout.EAST);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createMenuBar();
		
		this.setSize(720, 480);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);

		m_gameIO = new GameIOGson();

		updateStateLabel();
	}
	
	/**
	 * Création de la barre de navigation
	 */
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menuPartie = new JMenu("Partie");
		JMenu menuJeu = new JMenu("Jeu");

		JMenuItem itemNouvellePartie = new JMenuItem("Nouvelle partie");
		itemNouvellePartie.addActionListener(actionEvent -> {
			newGame();
			m_canvas.setGameState(m_gameState);
		});
		JMenuItem itemOuvrir = new JMenuItem("Ouvrir");
		itemOuvrir.addActionListener(actionEvent -> {
			openGame();
		});
		JMenuItem itemSauvegarder = new JMenuItem("Sauvegarder");
		itemSauvegarder.addActionListener(actionEvent -> {
			saveGame();
		});
		menuPartie.add(itemNouvellePartie);
		menuPartie.add(itemOuvrir);
		menuPartie.add(itemSauvegarder);

		JMenuItem itemNouvelleConfig = new JMenuItem("Nouvelle configuration");
		JMenuItem itemCalculerScore = new JMenuItem("Calculer le score");
		itemCalculerScore.addActionListener(actionEvent -> {
			IScoreCalculator scoreCalculator = new ScoreCalculator();
			int score = scoreCalculator.calculate(m_gameState.getPlateau());
			int scoreMax = scoreCalculator.getScoreMax(m_gameState.getPlateau());

			JOptionPane.showMessageDialog(this, "Votre score est de " + score + " / " + scoreMax, "Votre score", JOptionPane.INFORMATION_MESSAGE);
		});

		menuJeu.add(itemNouvelleConfig);
		menuJeu.add(itemCalculerScore);

		menuBar.add(menuPartie);
		menuBar.add(menuJeu);

		this.setJMenuBar(menuBar);
	}

	public void newGame() {
		m_gameState = new GameState(new GameRule(10), new Plateau(20, 20));

		m_gameState.getPlateau().addPiece(new PieceL(3, 5));
		m_gameState.getPlateau().addPiece(new PieceT(5, 3, 5, 5));

		setGameState(m_gameState);
	}

	public void openGame() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showOpenDialog(this);

		File selectedFile = chooser.getSelectedFile();
		if(selectedFile == null)
			return;

		GameState save = m_gameIO.loadGameState(selectedFile.getPath());

		setGameState(save);
	}

	public void saveGame() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showSaveDialog(this);

		File selectedFile = chooser.getSelectedFile();
		if(selectedFile == null)
			return;

		m_gameIO.saveGameState(m_gameState, selectedFile.getPath());
	}

	public void updateStateLabel() {
		m_stateLabel.setText("Coups restants : " + m_gameState.getNbCoupsRestants() + "\n");
	}

	public void setGameState(GameState state) {
		if(m_gameState != null)
			m_gameState.removeListener(this);
		m_gameState = state;
		m_gameState.addListener(this);

		m_canvas.setGameState(state);

		updateStateLabel();
	}

	@Override
	public void nbCoupsRestantsChanged(int nbCoupsRestants) {
		updateStateLabel();
	}
}
