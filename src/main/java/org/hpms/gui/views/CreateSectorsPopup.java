/*
 * Created by JFormDesigner on Fri Sep 13 21:07:12 CEST 2019
 */

package org.hpms.gui.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Ray1184
 */
public class CreateSectorsPopup extends JDialog {
    public CreateSectorsPopup(Window owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ray1184
        sgNameTxt = new JTextField();
        sgLabel = new JLabel();
        cancBtn = new JButton();
        okBtn = new JButton();

        //======== this ========
        setTitle("Add Sector Group");
        setResizable(false);
        Container contentPane = getContentPane();

        //---- sgLabel ----
        sgLabel.setText("Sector Group Name:");

        //---- cancBtn ----
        cancBtn.setText("Cancel");

        //---- okBtn ----
        okBtn.setText("OK");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(27, Short.MAX_VALUE)
                    .addComponent(sgLabel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(sgNameTxt, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                    .addGap(21, 21, 21))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(111, 111, 111)
                    .addComponent(okBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(cancBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(115, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(sgLabel)
                        .addComponent(sgNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(okBtn)
                        .addComponent(cancBtn))
                    .addGap(17, 17, 17))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ray1184
    private JTextField sgNameTxt;
    private JLabel sgLabel;
    private JButton cancBtn;
    private JButton okBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
