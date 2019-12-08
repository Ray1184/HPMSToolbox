/*
 * Created by JFormDesigner on Tue Oct 29 16:18:03 CET 2019
 */

package forms;

import javax.swing.*;
import java.awt.*;

/**
 * @author N
 */
public class CreateNewEventWizard extends JDialog {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - N
    private JPanel wizardManager;

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
            wizardManager.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
                    .swing.border.EmptyBorder(0, 0, 0, 0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax.swing
                    .border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.
                    Font("Dia\u006cog", java.awt.Font.BOLD, 12), java.awt.Color.red
            ), wizardManager.getBorder()));
            wizardManager.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("\u0062ord\u0065r".equals(e.getPropertyName(
                    ))) throw new RuntimeException();
                }
            });
            wizardManager.setLayout(new CardLayout());
        }
        contentPane.add(wizardManager, "card1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
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
