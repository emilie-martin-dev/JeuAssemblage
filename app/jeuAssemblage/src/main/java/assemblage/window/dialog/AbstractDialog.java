package assemblage.window.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class AbstractDialog extends JDialog {

    private boolean m_validateDialog = false;

    public AbstractDialog(JFrame parent, String title) {
        super(parent, title, true);

        initDialogUI();
    }

    /**
     * Création d'une boite de dialogue
     */
    private void initDialogUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(getMainUI(), BorderLayout.CENTER);
        panel.add(getControlButtons(), BorderLayout.PAGE_END);

        this.setContentPane(panel);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Création du contenu centrale de la boite de dialogue
     * @return JComponent
     */
    protected abstract JComponent getMainUI();

    /**
     * Action émise lors d'un clic sur le bouton "Valider"
     * @return l'action émise
     */
    protected abstract ActionListener getValidButtonEventHandler();

    /**
     * Création du layout contenant les boutons "Annuler" et "Valider"
     * @return le layout créé
     */
    private JComponent getControlButtons() {
        //Création du bouton "Annuler"
        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.addActionListener(new ActionListener() {
            //Suppression de la boite de dialogue lorsque l'on clique sur "Annuler"
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractDialog.this.dispose();
            }
        });

        //Création du bouton "Valider"
        JButton boutonValider = new JButton("Valider");
        boutonValider.addActionListener(getValidButtonEventHandler());

        //Création du layout des boutons  "Annuler" et "Valider"
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));

        controlPanel.add(Box.createHorizontalGlue());
        controlPanel.add(boutonAnnuler);
        controlPanel.add(boutonValider);

        return controlPanel;
    }

    protected void validateDialog() {
        m_validateDialog = true;
    }

    public boolean isValidated() {
        return m_validateDialog;
    }

}
