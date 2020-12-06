package assemblage.window.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewGameDialog extends AbstractDialog {

    private JTextField m_widthTextField;
    private JTextField m_heightTextField;
    private JTextField m_nbCoupsMaxTextField;

    private Integer m_widthSelected;
    private Integer m_heightSelected;
    private Integer m_nbCoupsMaxSelected;

    public NewGameDialog(JFrame parent) {
        super(parent, "Nouvelle partie");
    }

    @Override
    protected JComponent getMainUI() {
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        //Création du label contenant l'écriture de "Largeur :"
        JLabel widthLabel = new JLabel("Largeur : ");
        c.gridx = 0;
        c.gridy = 0;
        mainContent.add(widthLabel, c);

        //Création du champ de saisie de la largeur
        m_widthTextField = new JTextField();
        m_widthTextField.setPreferredSize(new Dimension(200, 25));
        c.gridx = 1;
        c.gridy = 0;
        mainContent.add(m_widthTextField, c);

        //Création du label contenant l'écriture de "Hauteur :"
        JLabel heightLabel = new JLabel("Hauteur : ");
        c.gridx = 0;
        c.gridy = 1;
        mainContent.add(heightLabel, c);

        //Création du champ de saisie de la hauteur
        m_heightTextField = new JTextField();
        m_heightTextField.setPreferredSize(new Dimension(200, 25));
        c.gridx = 1;
        c.gridy = 1;
        mainContent.add(m_heightTextField, c);

        //Création du label contenant l'écriture de "Nombre de coups max :"
        JLabel nbCoupsMaxLabel = new JLabel("Nombre de coups max : ");
        c.gridx = 0;
        c.gridy = 2;
        mainContent.add(nbCoupsMaxLabel, c);

        //Création du champ de saisie de la hauteur
        m_nbCoupsMaxTextField = new JTextField();
        m_nbCoupsMaxTextField.setPreferredSize(new Dimension(200, 25));
        c.gridx = 1;
        c.gridy = 2;
        mainContent.add(m_nbCoupsMaxTextField, c);

        return mainContent;
    }

    @Override
    protected ActionListener getValidButtonEventHandler() {
        return (ActionEvent e) -> {
            //Vérification que la largeur mise par l'utilsateur correspond bien à un nombre
            try {
                this.m_widthSelected = Integer.parseInt(m_widthTextField.getText());
            } catch(NumberFormatException ex) {
                this.m_widthSelected = null;
                return;
            }

            //Vérification que la hauteur mise par l'utilsateur correspond bien à un nombre
            try {
                this.m_heightSelected = Integer.parseInt(m_heightTextField.getText());
            } catch(NumberFormatException ex) {
                this.m_heightSelected = null;
                return;
            }

            //Vérification que le nombrel de coups max mis par l'utilsateur correspond bien à un nombre
            try {
                this.m_nbCoupsMaxSelected = Integer.parseInt(m_nbCoupsMaxTextField.getText());
            } catch(NumberFormatException ex) {
                this.m_nbCoupsMaxSelected = null;
                return;
            }

            validateDialog();

            //On supprime la boite de dialogue après clic sur "Valider"
            NewGameDialog.this.dispose();
        };
    }

    /**
     * Récupère la largeur sélectionnée par l'utilisateur
     * @return la largeur séléctionée dans la boite de dialogue, null si la boite de dialogue n'a pas été validée
     */
    public Integer getSelectedWidth() {
        return this.m_widthSelected;
    }

    /**
     * Récupère la hauteur sélectionnée par l'utilisateur
     * @return la hauteur séléctionée dans la boite de dialogue, null si la boite de dialogue n'a pas été validée
     */
    public Integer getSelectedHeight() {
        return this.m_heightSelected;
    }

    /**
     * Récupère le nombre de coups max sélectionnée par l'utilisateur
     * @return le nombre de coups max séléctionée dans la boite de dialogue, null si la boite de dialogue n'a pas été validée
     */
    public Integer getSelectedNbCoupsMax() {
        return this.m_nbCoupsMaxSelected;
    }

}
