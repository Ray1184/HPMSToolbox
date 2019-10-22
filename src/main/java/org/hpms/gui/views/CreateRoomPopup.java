/*
 * Created by JFormDesigner on Fri Sep 13 14:56:19 CEST 2019
 */

package org.hpms.gui.views;

import java.awt.*;
import javax.swing.*;

/**
 * @author N
 */
public class CreateRoomPopup extends JDialog {
    public CreateRoomPopup(Window owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ray
        roomNameLabel = new JLabel();
        roomNameTxt = new JTextField();
        sMapTxt = new JTextField();
        sMapLabel = new JLabel();
        sMapLoadBtn = new JButton();
        genSecGrChk = new JCheckBox();
        okBtn = new JButton();
        cancBtn = new JButton();
        sMapLabel2 = new JLabel();
        comboBox1 = new JComboBox();

        //======== this ========
        setTitle("Create New Room");
        setResizable(false);
        Container contentPane = getContentPane();

        //---- roomNameLabel ----
        roomNameLabel.setText("Room Name:");

        //---- sMapLabel ----
        sMapLabel.setText("Sectors Map:");

        //---- sMapLoadBtn ----
        sMapLoadBtn.setText("...");

        //---- genSecGrChk ----
        genSecGrChk.setText("Generate sector groups? (available only for .dae format)");

        //---- okBtn ----
        okBtn.setText("OK");

        //---- cancBtn ----
        cancBtn.setText("Cancel");

        //---- sMapLabel2 ----
        sMapLabel2.setText("Pipeline Type:");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(160, Short.MAX_VALUE)
                    .addComponent(okBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(cancBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addGap(146, 146, 146))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                            .addComponent(roomNameLabel)
                                            .addGap(18, 18, 18))
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                            .addComponent(sMapLabel)
                                            .addGap(18, 18, 18)))
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(roomNameTxt, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(sMapTxt, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(sMapLoadBtn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(genSecGrChk))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(sMapLabel2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(23, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(roomNameLabel)
                        .addComponent(roomNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(sMapLabel)
                        .addComponent(sMapTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(sMapLoadBtn))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(sMapLabel2)
                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                    .addComponent(genSecGrChk)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(okBtn)
                        .addComponent(cancBtn))
                    .addGap(15, 15, 15))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ray
    private JLabel roomNameLabel;
    private JTextField roomNameTxt;
    private JTextField sMapTxt;
    private JLabel sMapLabel;
    private JButton sMapLoadBtn;
    private JCheckBox genSecGrChk;
    private JButton okBtn;
    private JButton cancBtn;
    private JLabel sMapLabel2;
    private JComboBox comboBox1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
