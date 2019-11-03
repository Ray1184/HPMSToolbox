/*
 * Created by JFormDesigner on Tue Oct 29 16:18:03 CET 2019
 */

package org.hpms.gui.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author N
 */
public class CreateNewEventWizard extends JDialog {
    public CreateNewEventWizard(Window owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - N
        wizardManager = new JPanel();

        //======== this ========
        setTitle("Create New Event");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new CardLayout(10, 10));

        //======== wizardManager ========
        {
            wizardManager.setBorder(new EmptyBorder(0, 0, 0, 0));
            wizardManager.setLayout(new CardLayout());
        }
        contentPane.add(wizardManager, "card1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - N
    private JPanel wizardManager;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    /**
     * Getter for property 'wizardManager'.
     *
     * @return Value for property 'wizardManager'.
     */
    public JPanel getWizardManager() {
        return wizardManager;
    }
}
