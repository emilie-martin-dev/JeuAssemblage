package assemblage.window;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import assemblage.game.GameRule;
import assemblage.game.GameState;
import assemblage.game.ai.Ai;
import assemblage.game.generator.IPlateauGenerator;
import assemblage.game.generator.PlateauGeneratorRandom;
import assemblage.game.score.IScoreCalculator;
import assemblage.game.score.ScoreCalculatorSimple;
import assemblage.io.IGameIO;
import assemblage.io.gson.GameIOGson;
import assemblage.observer.IGameStateListener;
import assemblage.window.dialog.NewGameDialog;
import piece_puzzle.model.Plateau;

/**
 * Fenêtre principal de l'application
 */
public class MainWindow extends JFrame implements IGameStateListener {

	/**
	 * Classe pour charger / sauvegarder une partie
	 */
	private IGameIO m_gameIO;

	/**
	 * Le composant sur lequel le jeu s'affiche
	 */
	private GameCanvas m_canvas;
	/**
	 * Le label qui se charge d'afficher l'etat du jeu (ex : coups restants)
	 */
	private JLabel m_stateLabel;

	/**
	 * L'état du jeu
	 */
	private GameState m_gameState;
	
	public MainWindow() {
		m_stateLabel = new JLabel();
		m_stateLabel.setBorder(new EmptyBorder(16, 16, 16, 16));
		m_canvas = new GameCanvas(null);
		m_gameIO = new GameIOGson();

		newGame(20, 20, 50);
		
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

		JMenuItem itemNouvelleConfig = new JMenuItem("Reset de la grille");
		itemNouvelleConfig.addActionListener(actionEvent -> {
			m_gameState.reset();
		});

		JMenuItem itemCalculerScore = new JMenuItem("Calculer le score");
		itemCalculerScore.addActionListener(actionEvent -> {
			IScoreCalculator scoreCalculator = new ScoreCalculatorSimple();
			int score = scoreCalculator.calculate(m_gameState.getPlateau());
			int scoreMax = scoreCalculator.getScoreMax(m_gameState.getPlateau());

			JOptionPane.showMessageDialog(this, "Votre score est de " + score + " / " + scoreMax, "Votre score", JOptionPane.INFORMATION_MESSAGE);
		});

		JMenuItem itemBestScore = new JMenuItem("Meilleur score");
		itemBestScore.addActionListener(actionEvent -> {
			IScoreCalculator scoreCalculator = new ScoreCalculatorSimple();
			int scoreMax = scoreCalculator.getScoreMax(m_gameState.getPlateau());

			if(m_gameState.getBestScore() == 0)
				JOptionPane.showMessageDialog(this, "Aucun meilleur score n'a encore été enregistré", "Meilleur  score", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(this, m_gameState.getBestPlayerName() + " : " + m_gameState.getBestScore() + " / " + scoreMax, "Meilleur score", JOptionPane.INFORMATION_MESSAGE);
		});

		JMenuItem itemAi = new JMenuItem("Faire jouer cette grille à l'IA");
		itemAi.addActionListener(actionEvent -> {
			Ai ai = new Ai();
			//setGameState(ai.compute(m_gameState));
		});

		menuJeu.add(itemNouvelleConfig);
		menuJeu.add(itemCalculerScore);
		menuJeu.add(itemBestScore);
		menuJeu.add(itemAi);

		menuBar.add(menuPartie);
		menuBar.add(menuJeu);

		this.setJMenuBar(menuBar);
	}

	/**
	 * Démarre une partie en affichant une boite de dialogue
	 */
	public void newGame() {
		NewGameDialog newGameDialog = new NewGameDialog(this);
		if(!newGameDialog.isValidated())
			return;

		newGame(newGameDialog.getSelectedWidth(), newGameDialog.getSelectedHeight(), newGameDialog.getSelectedNbCoupsMax());
	}

	/**
	 * Démarre une partie selon les paramètre fournis
	 * @param width Largeur du plateau
	 * @param height Hauteur du plateau
	 * @param nbCoupsMax Nombre de coups maximum pour la partie
	 */
	public void newGame(int width, int height, int nbCoupsMax) {
		IPlateauGenerator plateauGenerator = new PlateauGeneratorRandom();
		Plateau p = plateauGenerator.generate(width, height);

		m_gameState = new GameState(new GameRule(nbCoupsMax), p);

		setGameState(m_gameState);
	}

	/**
	 * Chargement d'une partie
	 */
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

	/**
	 * Sauvegarde de la partie en cours
	 */
	public void saveGame() {
		// On vérifie s'il s'agit d'un nouveau meilleur score
		IScoreCalculator scoreCalculator = new ScoreCalculatorSimple();
		int score = scoreCalculator.calculate(m_gameState.getPlateau());
		if(m_gameState.getBestScore() < score) {
			String playerName = JOptionPane.showInputDialog(this, "Entrez votre pseudo", "Nouveau meilleur score", JOptionPane.QUESTION_MESSAGE);
			if(playerName == null)
				return;

			m_gameState.setBestPlayerName(playerName);
			m_gameState.setBestScore(score);
		}

		// On choisit l'emplacement de la sauvegarde
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showSaveDialog(this);

		File selectedFile = chooser.getSelectedFile();
		if(selectedFile == null)
			return;

		m_gameIO.saveGameState(m_gameState, selectedFile.getPath());
	}

	/**
	 * Met à jour les informations sur la partie en cours
	 */
	public void updateStateLabel() {
		m_stateLabel.setText("Coups restants : " + m_gameState.getNbCoupsRestants() + "\n");
	}

	/**
	 * Change l'etat du jeu en cours
	 * @param state L'etat du jeu en cours
	 */
	public void setGameState(GameState state) {
		if(m_gameState != null)
			m_gameState.removeListener(this);
		m_gameState = state;
		m_gameState.addListener(this);

		m_canvas.setGameState(state);

		updateStateLabel();
	}

	@Override
	public void stateReset() {
		updateStateLabel();
	}

	@Override
	public void nbCoupsRestantsChanged(int nbCoupsRestants) {
		updateStateLabel();

		if(m_gameState.isFinished()) {
			IScoreCalculator scoreCalculator = new ScoreCalculatorSimple();
			int score = scoreCalculator.calculate(m_gameState.getPlateau());
			int scoreMax = scoreCalculator.getScoreMax(m_gameState.getPlateau());

			int choix = JOptionPane.showConfirmDialog(this, "Votre score est de " + score + " / " + scoreMax + "\nVoulez-vous sauvegarder votre partie?", "Fin de partie", JOptionPane.YES_NO_OPTION);
			if(choix == JOptionPane.NO_OPTION)
				return;

			saveGame();
		}
	}
}
