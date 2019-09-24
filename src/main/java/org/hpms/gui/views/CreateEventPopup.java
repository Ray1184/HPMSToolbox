/*
 * Created by JFormDesigner on Fri Sep 13 21:23:55 CEST 2019
 */

package org.hpms.gui.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Ray1184
 */
public class CreateEventPopup extends JDialog {
    public CreateEventPopup(Window owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ray1184
        evtNameTxt = new JTextField();
        evtLabel = new JLabel();
        triggeredLabel = new JLabel();
        comboBox1 = new JComboBox();
        priorityLabel = new JLabel();
        priorityCombo = new JComboBox();
        evtLabel2 = new JLabel();
        comboBox2 = new JComboBox();
        scrollPane1 = new JScrollPane();
        scrollPane2 = new JScrollPane();
        comboBox3 = new JComboBox();
        evtLabel3 = new JLabel();
        okBtn = new JButton();
        cancBtn = new JButton();

        //======== this ========
        setTitle("Create New Event");
        setResizable(false);
        Container contentPane = getContentPane();

        //---- evtLabel ----
        evtLabel.setText("Event Name:");

        //---- triggeredLabel ----
        triggeredLabel.setText("Triggered on:");

        //---- priorityLabel ----
        priorityLabel.setText("Priority:");

        //---- evtLabel2 ----
        evtLabel2.setText("Conditions:");

        //---- evtLabel3 ----
        evtLabel3.setText("Actions:");

        //---- okBtn ----
        okBtn.setText("OK");

        //---- cancBtn ----
        cancBtn.setText("Cancel");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(evtLabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(evtNameTxt, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(triggeredLabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(priorityLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(priorityCombo, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(33, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(evtLabel2, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(evtLabel3, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(comboBox3, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
                            .addGap(32, 32, 32))))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(258, 258, 258)
                    .addComponent(okBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(cancBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 258, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(evtNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(priorityCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(evtLabel)
                        .addComponent(triggeredLabel)
                        .addComponent(priorityLabel))
                    .addGap(33, 33, 33)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(evtLabel2))
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(evtLabel3))
                            .addComponent(comboBox3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(okBtn)
                        .addComponent(cancBtn))
                    .addGap(26, 26, 26))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ray1184
    private JTextField evtNameTxt;
    private JLabel evtLabel;
    private JLabel triggeredLabel;
    private JComboBox comboBox1;
    private JLabel priorityLabel;
    private JComboBox priorityCombo;
    private JLabel evtLabel2;
    private JComboBox comboBox2;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JComboBox comboBox3;
    private JLabel evtLabel3;
    private JButton okBtn;
    private JButton cancBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
