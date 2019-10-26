/*
 * Created by JFormDesigner on Thu Oct 24 13:52:55 CEST 2019
 */

package org.hpms.gui.views;

import java.awt.*;
import javax.swing.*;

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
        container = new JPanel();
        subContainerLabel = new JPanel();
        label1 = new JLabel();
        subContainerWizard = new JPanel();

        //======== this ========
        setTitle("Create New Event");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new CardLayout(10, 10));

        //======== container ========
        {
            container.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
            swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border
            . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new Font ("Dia\u006cog"
            , Font .BOLD ,12 ), Color. red) ,container. getBorder
            ( )) ); container. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
            .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException
            ( ); }} );
            container.setLayout(new GridLayout(2, 1, 10, 0));

            //======== subContainerLabel ========
            {
                subContainerLabel.setLayout(new CardLayout());

                //---- label1 ----
                label1.setText("STEP 1");
                label1.setFont(new Font("Segoe UI", Font.BOLD, 20));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                subContainerLabel.add(label1, "card1");
            }
            container.add(subContainerLabel);

            //======== subContainerWizard ========
            {
                subContainerWizard.setLayout(new CardLayout());
            }
            container.add(subContainerWizard);
        }
        contentPane.add(container, "card5");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - N
    private JPanel container;
    private JPanel subContainerLabel;
    private JLabel label1;
    private JPanel subContainerWizard;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JPanel getSubContainerWizard() {
        return subContainerWizard;
    }
}
