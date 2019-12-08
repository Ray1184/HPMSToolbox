/*
 * Created by JFormDesigner on Wed Oct 23 18:01:21 CEST 2019
 */

package org.hpms.gui.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author N
 */
public class WorkspaceChooser extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - N
    private JPanel container;
    private JLabel chooseWorkspaceLbl;
    private JPanel subContainer;
    private JTextField workspaceTxt;
    private JButton workspaceLoadBtn;
    private JPanel subContainer2;
    private JButton confirmWorkspaceBtn;
    public WorkspaceChooser() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - N
        container = new JPanel();
        chooseWorkspaceLbl = new JLabel();
        subContainer = new JPanel();
        workspaceTxt = new JTextField();
        workspaceLoadBtn = new JButton();
        subContainer2 = new JPanel();
        confirmWorkspaceBtn = new JButton();

        //======== this ========
        setTitle("Select Workspace Location");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new CardLayout(10, 10));

        //======== container ========
        {
            container.setBorder(new EmptyBorder(0, 0, 0, 0));
            container.setLayout(new GridLayout(3, 0));

            //---- chooseWorkspaceLbl ----
            chooseWorkspaceLbl.setText("<html>Welcome to HPMS ToolBox. Before starting to design your game you<br/>need to create a workspace for your projects.<br/>Please select a folder where create your workspace.</html>");
            chooseWorkspaceLbl.setBorder(new EmptyBorder(10, 10, 20, 20));
            chooseWorkspaceLbl.setHorizontalAlignment(SwingConstants.CENTER);
            container.add(chooseWorkspaceLbl);

            //======== subContainer ========
            {
                subContainer.setLayout(new FlowLayout());

                //---- workspaceTxt ----
                workspaceTxt.setMinimumSize(new Dimension(300, 46));
                workspaceTxt.setPreferredSize(new Dimension(400, 30));
                subContainer.add(workspaceTxt);

                //---- workspaceLoadBtn ----
                workspaceLoadBtn.setText("...");
                subContainer.add(workspaceLoadBtn);
            }
            container.add(subContainer);

            //======== subContainer2 ========
            {
                subContainer2.setLayout(new FlowLayout());

                //---- confirmWorkspaceBtn ----
                confirmWorkspaceBtn.setText("Confirm Location");
                confirmWorkspaceBtn.setPreferredSize(new Dimension(200, 46));
                subContainer2.add(confirmWorkspaceBtn);
            }
            container.add(subContainer2);
        }
        contentPane.add(container, "card1");
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables

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

    /**
     * Getter for property 'confirmWorkspaceBtn'.
     *
     * @return Value for property 'confirmWorkspaceBtn'.
     */
    public JButton getConfirmWorkspaceBtn() {
        return confirmWorkspaceBtn;
    }


}
