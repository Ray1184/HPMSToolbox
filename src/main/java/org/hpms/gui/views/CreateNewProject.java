/*
 * Created by JFormDesigner on Tue Oct 22 23:19:39 CEST 2019
 */

package org.hpms.gui.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

/**
 * @author Ray
 */
public class CreateNewProject extends JDialog {
    public CreateNewProject(Window owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ray
        newProjNameTxt = new JTextField();
        newProjLabel = new JLabel();
        cancBtn = new JButton();
        okBtn = new JButton();

        //======== this ========
        setTitle("Create New Project");
        setResizable(false);
        Container contentPane = getContentPane();

        //---- newProjLabel ----
        newProjLabel.setText("Project Name:");

        //---- cancBtn ----
        cancBtn.setText("Cancel");

        //---- okBtn ----
        okBtn.setText("OK");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(newProjLabel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(newProjNameTxt, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(96, Short.MAX_VALUE)
                    .addComponent(okBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(cancBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addGap(90, 90, 90))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(newProjNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(newProjLabel))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(okBtn)
                        .addComponent(cancBtn))
                    .addGap(16, 16, 16))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ray
    private JTextField newProjNameTxt;
    private JLabel newProjLabel;
    private JButton cancBtn;
    private JButton okBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JTextField getNewProjNameTxt() {
        return newProjNameTxt;
    }

    public JButton getCancBtn() {
        return cancBtn;
    }

    public JButton getOkBtn() {
        return okBtn;
    }
}
