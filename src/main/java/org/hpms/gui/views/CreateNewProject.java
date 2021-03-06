/*
 * Created by JFormDesigner on Tue Oct 22 23:19:39 CEST 2019
 */

package org.hpms.gui.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author Ray
 */
public class CreateNewProject extends JDialog {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - N
    private JPanel container;
    private JPanel subContainer;
    private JLabel newProjLabel;
    private JTextField newProjNameTxt;
    private JPanel subContainer2;
    private JButton okBtn;
    private JButton cancBtn;
    public CreateNewProject(Window owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - N
        container = new JPanel();
        subContainer = new JPanel();
        newProjLabel = new JLabel();
        newProjNameTxt = new JTextField();
        subContainer2 = new JPanel();
        okBtn = new JButton();
        cancBtn = new JButton();

        //======== this ========
        setTitle("Create New Project");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new CardLayout(10, 10));

        //======== container ========
        {
            container.setBorder(new EmptyBorder(0, 0, 0, 0));
            container.setLayout(new GridLayout(2, 0, 10, 0));

            //======== subContainer ========
            {
                subContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

                //---- newProjLabel ----
                newProjLabel.setText("Project Name:");
                subContainer.add(newProjLabel);

                //---- newProjNameTxt ----
                newProjNameTxt.setPreferredSize(new Dimension(400, 30));
                subContainer.add(newProjNameTxt);
            }
            container.add(subContainer);

            //======== subContainer2 ========
            {
                subContainer2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                ((FlowLayout) subContainer2.getLayout()).setAlignOnBaseline(true);

                //---- okBtn ----
                okBtn.setText("OK");
                subContainer2.add(okBtn);

                //---- cancBtn ----
                cancBtn.setText("Cancel");
                subContainer2.add(cancBtn);
            }
            container.add(subContainer2);
        }
        contentPane.add(container, "card5");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
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
