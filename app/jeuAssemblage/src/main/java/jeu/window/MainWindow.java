package jeu.window;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import piece_puzzle.model.Plateau;

public class MainWindow extends JFrame {
	
	private GameCanvas m_canvas;
	
	public MainWindow() {
		m_canvas = new GameCanvas(new Plateau(20, 20));
		
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
		//Création de la barre de navigation
		JMenuBar menuBar = new JMenuBar();

		//Création des catégories de la barre de navigation
		JMenu menuFichier = new JMenu("Jeu");
		JMenu menuAffichage = new JMenu("Mode");

		//Création de la sous-catégorie "nouvelle partie" de "jeu"
		JMenuItem itemNewGame = new JMenuItem("Nouvelle partie");
		menuFichier.add(itemNewGame);

		//Création de la sous-catégorie "Changer l'image" de "Mode"
		JMenuItem itemChangerImage = new JMenuItem("Changer l'image");
		menuAffichage.add(itemChangerImage);
		//Evenement de l'item "mode chiffres"

		//Ajout des catégories à la barre de navigation
		menuBar.add(menuFichier);
		menuBar.add(menuAffichage);

		this.setJMenuBar(menuBar);
	}
	
}
