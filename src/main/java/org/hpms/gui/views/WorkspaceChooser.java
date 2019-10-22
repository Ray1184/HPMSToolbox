/*
 * Created by JFormDesigner on Tue Oct 22 12:07:09 CEST 2019
 */

package org.hpms.gui.views;

import javax.swing.*;
import java.awt.*;

/**
 * @author N
 */
public class WorkspaceChooser extends JFrame {
    public WorkspaceChooser() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ray
        chooseWorkspaceLbl = new JLabel();
        workspaceTxt = new JTextField();
        workspaceLoadBtn = new JButton();
        confirmWorkspaceBtn = new JButton();
        invalidLocationLbl = new JLabel();

        //======== this ========
        setTitle("Select Workspace Location");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- chooseWorkspaceLbl ----
        chooseWorkspaceLbl.setText("<html>Welcome to HPMS ToolBox. Before starting to design your game you need to create a workspace for your projects. Please select a folder where create your workspace.</html>");

        //---- workspaceLoadBtn ----
        workspaceLoadBtn.setText("...");

        //---- confirmWorkspaceBtn ----
        confirmWorkspaceBtn.setText("Confirm Location");

        //---- invalidLocationLbl ----
        invalidLocationLbl.setText("Invalid location!");
        invalidLocationLbl.setForeground(Color.red);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(invalidLocationLbl, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(confirmWorkspaceBtn, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
                        .addComponent(chooseWorkspaceLbl, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(workspaceTxt, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(workspaceLoadBtn, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(26, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(chooseWorkspaceLbl, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(workspaceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(workspaceLoadBtn))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(invalidLocationLbl)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                    .addComponent(confirmWorkspaceBtn)
                    .addGap(19, 19, 19))
        );
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ray
    private JLabel chooseWorkspaceLbl;
    private JTextField workspaceTxt;
    private JButton workspaceLoadBtn;
    private JButton confirmWorkspaceBtn;
    private JLabel invalidLocationLbl;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    /**
     * Getter for property 'confirmWorkspaceBtn'.
     *
     * @return Value for property 'confirmWorkspaceBtn'.
     */
    public JButton getConfirmWorkspaceBtn() {
        return confirmWorkspaceBtn;
    }


    /**
     * Getter for property 'workspaceTxt'.
     *
     * @return Value for property 'workspaceTxt'.
     */
    public JTextField getWorkspaceTxt() {
        return workspaceTxt;
    }

    /**
     * Getter for property 'workspaceLoadBtn'.
     *
     * @return Value for property 'workspaceLoadBtn'.
     */
    public JButton getWorkspaceLoadBtn() {
        return workspaceLoadBtn;
    }

    public JLabel getInvalidLocationLbl() {
        return invalidLocationLbl;
    }
}
