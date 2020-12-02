package assemblage.window;

import java.awt.BorderLayout;
import javax.swing.*;

import assemblage.game.score.IScoreCalculator;
import assemblage.game.score.ScoreCalculator;
import piece_puzzle.model.PieceL;
import piece_puzzle.model.PieceT;
import piece_puzzle.model.Plateau;

public class MainWindow extends JFrame {
	
	private GameCanvas m_canvas;

	private Plateau m_plateau;
	
	public MainWindow() {
		newGame();
		m_canvas = new GameCanvas(m_plateau);
		
		this.setTitle("Assemblage");
		this.setLayout(new BorderLayout());
		this.getContentPane().add(m_canvas, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createMenuBar();
		
		this.setSize(720, 480);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
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
			m_canvas.setPlateau(m_plateau);
		});
		JMenuItem itemOuvrir = new JMenuItem("Ouvrir");
		itemOuvrir.addActionListener(actionEvent -> {

		});
		JMenuItem itemSauvegarder = new JMenuItem("Sauvegarder");
		itemSauvegarder.addActionListener(actionEvent -> {
			newGame();
			m_canvas.setPlateau(m_plateau);
		});
		menuPartie.add(itemNouvellePartie);
		menuPartie.add(itemOuvrir);
		menuPartie.add(itemSauvegarder);

		JMenuItem itemNouvelleConfig = new JMenuItem("Nouvelle configuration");
		JMenuItem itemCalculerScore = new JMenuItem("Calculer le score");
		itemCalculerScore.addActionListener(actionEvent -> {
			IScoreCalculator scoreCalculator = new ScoreCalculator();
			int score = scoreCalculator.calculate(m_plateau);
			int scoreMax = scoreCalculator.getScoreMax(m_plateau);

			JOptionPane.showMessageDialog(this, "Votre score est de " + score + " / " + scoreMax, "Votre score", JOptionPane.INFORMATION_MESSAGE);
		});

		menuJeu.add(itemNouvelleConfig);
		menuJeu.add(itemCalculerScore);

		menuBar.add(menuPartie);
		menuBar.add(menuJeu);

		this.setJMenuBar(menuBar);
	}

	public void newGame() {
		m_plateau = new Plateau(20, 20);

		m_plateau.addPiece(new PieceL(3, 5));
		m_plateau.addPiece(new PieceT(5, 3, 5, 5));
	}
	
}
